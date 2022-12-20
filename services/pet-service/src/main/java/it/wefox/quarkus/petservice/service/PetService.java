package it.wefox.quarkus.petservice.service;

import com.mongodb.client.result.InsertOneResult;
import io.quarkus.panache.common.Page;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import it.wefox.quarkus.petapi.request.PetRequest;
import it.wefox.quarkus.petapi.request.PetWithTreatmentsRequest;
import it.wefox.quarkus.petcommons.mongodb.ReactiveMongoClientExecutor;
import it.wefox.quarkus.petservice.domain.Pet;
import it.wefox.quarkus.petservice.kafka.PetKafkaProducer;
import it.wefox.quarkus.petservice.mapper.PetMapper;
import it.wefox.quarkus.petservice.mapper.SpeciesMapper;
import it.wefox.quarkus.petservice.repository.PetReactiveMongoRepository;
import it.wefox.quarkus.pagination.PageRequest;
import it.wefox.quarkus.redis.service.RedisService;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 08/12/22
 * @Time: 02:46
 */
@ApplicationScoped
public class PetService {

    @Inject
    PetReactiveMongoRepository petReactiveMongoRepository;

    @Inject
    PetMapper petMapper;

    @Inject
    SpeciesMapper speciesMapper;

    @Inject
    ReactiveMongoClientExecutor reactiveMongoClientExecutor;

    @Inject
    PetKafkaProducer petKafkaProducer;

    @Inject
    RedisService<String,String> redisService;

    /**
     * @return
     */
    public Multi<Pet> streamAllPet() {
        return Pet.streamAll();
    }

    /**
     * @param petRequest
     * @return
     */
    public Uni<Pet> create(PetRequest petRequest) {
        Uni<PetRequest> petUni = Uni.createFrom().item(petRequest);

        return petUni.onItem().transform(p -> {
            p.setBirthDate(petRequest.getBirthDate());
            p.setOwnerId(petRequest.getOwnerId());
            p.setSpecies(petRequest.getSpecies());
            p.setName(petRequest.getName());
            p.setTreatments(petRequest.getTreatments());
            return p;
        }).map(p -> petMapper.toEntity().apply(p)).call(p -> p.persist());
    }

    /**
     * @param petRequest
     * @return
     */
    public Uni<InsertOneResult> createPetWithTreatment(PetWithTreatmentsRequest petRequest) {
        Document document = new Document()
                .append("name", petRequest.getName())
                .append("birthDate", petRequest.getBirthDate())
                .append("species", petRequest.getSpecies())
                .append("ownerId", petRequest.getOwnerId())
                .append("treatments", petRequest.getTreatments());
        return reactiveMongoClientExecutor.getPetCollection().insertOne(document);
    }
    /**
     *
     * @param id
     * @param petRequest
     * @return
     */
    public Uni<Pet> update(String id, PetRequest petRequest) {
        ObjectId petId = new ObjectId(id);

        return petReactiveMongoRepository.findById(petId)
                .onItem().transform(p -> {
                    p.setBirthDate(petRequest.getBirthDate());
                    p.setOwnerId(petRequest.getOwnerId());
                    p.setSpecies(speciesMapper.toEntity().apply(petRequest.getSpecies()));
                    p.setName(petRequest.getName());
                    return p;
                }).call(p -> p.update());
    }


    /**
     *
     * @param id
     * @return
     */
    public Uni<Void> delete(String id) {
        ObjectId petId = new ObjectId(id);
        petKafkaProducer.onPetDeleted(id);
        redisService.set("id", id);
        return petReactiveMongoRepository.deleteById(petId).replaceWithVoid();
    }

    /**
     *
     * @param id
     * @return
     */
    public Uni<Pet> findByOwnerId(String id) {
        ObjectId ownerId = new ObjectId(id);
        return petReactiveMongoRepository
                .findById(ownerId);
    }

    /**
     *
     * @param id
     * @return
     */
    public Uni<Pet> findById(String id) {
        ObjectId petId = new ObjectId(id);
        return petReactiveMongoRepository
                .findById(petId);
    }

    /**
     *
     * @param name
     * @return
     */
    public Uni<Pet> findByName(String name) {
        return petReactiveMongoRepository.findByName(name);
    }

    public Uni<List<Pet>> listPaged(PageRequest pageRequest) {
        return petReactiveMongoRepository.findAll()
                .page(Page.of(pageRequest.getPageNum(), pageRequest.getPageSize()))
                .list();
    }

    public Uni<Void> deleteAll() {
        return petReactiveMongoRepository.deleteAllPet();
    }
}
