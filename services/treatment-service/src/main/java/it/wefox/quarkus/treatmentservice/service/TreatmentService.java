package it.wefox.quarkus.treatmentservice.service;

import io.quarkus.panache.common.Page;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import it.wefox.quarkus.redis.service.RedisService;
import it.wefox.quarkus.treatmentapi.request.TreatmentRequest;
import it.wefox.quarkus.treatmentservice.domain.Treatment;
import it.wefox.quarkus.treatmentservice.mapper.TreatmentMapper;
import it.wefox.quarkus.treatmentservice.repository.TreatmentReactiveMongoRepository;
import it.wefox.quarkus.pagination.PageRequest;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * @author Christian Chiama
 * --
 */
@ApplicationScoped
public class TreatmentService {

    @Inject
    TreatmentReactiveMongoRepository treatmentReactiveMongoRepository;

    @Inject
    TreatmentMapper treatmentMapper;

    @Inject
    RedisService<String,String> redisService;

    public Multi<Treatment> streamAllTreatment() {
        return Treatment.streamAll();
    }

    /**
     * @param treatmentRequest
     * @return
     */
    public Uni<Treatment> create(TreatmentRequest treatmentRequest) {
        Uni<Treatment> treatmentUni = Uni.createFrom().item(treatmentMapper.mapTreatmentRequestToEntity().apply(treatmentRequest));
        return treatmentUni
                .onItem()
                .transform(t -> {
            t.setName(treatmentRequest.getName());
            t.setCreatedAt(treatmentRequest.getCreatedAt());
            t.setPrice(treatmentRequest.getPrice());
            t.setPetId(treatmentRequest.getPetId());
            t.setResult(treatmentRequest.getResult());
                    return t;
        }).call(t -> t.persist());
    }

    /**
     * @param id
     * @param treatment
     * @return
     */
    public Uni<Treatment> update(String id, TreatmentRequest treatment) {
        ObjectId petId = new ObjectId(id);
        Uni<Treatment> treatmentUni = treatmentReactiveMongoRepository.findById(petId);
        return treatmentUni
                .onItem().transform(t -> {
                    t.setName(treatment.getName());
                    t.setPetId(treatment.getPetId());
                    t.setPrice(treatment.getPrice());
                    t.setCreatedAt(treatment.getCreatedAt());
                    t.setResult(treatment.getResult());
                    return t;
                }).call(t -> Treatment.update(t));
    }


    /**
     * @param id
     * @return
     */
    public Uni<Void> delete(String id) {
        ObjectId treatmentId = new ObjectId(id);
        redisService.set("id",id);
        return treatmentReactiveMongoRepository.deleteById(treatmentId).replaceWithVoid();
    }

    public Uni<Void> deleteAll() {
        return treatmentReactiveMongoRepository.deleteAll().replaceWithVoid();
    }

    /**
     * @param petId - the owner id
     * @return - a treament with given {@param ownerId}
     */
    public Uni<Treatment> findByPetId(String petId) {
        ObjectId id = new ObjectId(petId);
        return treatmentReactiveMongoRepository.findByPetId(id.toString());
    }

    /**
     * @param name
     * @return
     */
    public Uni<Treatment> findByName(String name) {
        return treatmentReactiveMongoRepository.findByName(name);
    }

    /**
     *
     * @param id
     * @return
     */
    public Uni<Treatment> findById(String id) {
        return treatmentReactiveMongoRepository.findById(new ObjectId(id));
    }

    public Uni<List<Treatment>> listPaged(PageRequest pageRequest) {
        return treatmentReactiveMongoRepository.findAll()
                .page(Page.of(pageRequest.getPageNum(), pageRequest.getPageSize()))
                .list();
    }


}