package it.wefox.quarkus.service;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import it.wefox.quarkus.domain.Treatment;
import it.wefox.quarkus.repository.TreatmentReactiveMongoRepository;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author Christian Chiama
 * --
 */
@ApplicationScoped
public class TreatmentService {

    @Inject
    TreatmentReactiveMongoRepository treatmentReactiveMongoRepository;

    /**
     * @return
     */
    public Multi<Treatment> streamAllTreatment() {
        return Treatment.streamAll();
    }

    /**
     * @param treatment
     * @return
     */
    public Uni<Treatment> create(Treatment treatment) {
        Uni<Treatment> treatmentUni = Uni.createFrom().item(treatment);

        return treatmentUni.onItem().transform(t -> {
            t.setName(treatment.getName());
            return t;
        }).call(t -> treatment.persist());
    }

    /**
     * @param id
     * @param treatment
     * @return
     */
    public Uni<Treatment> update(String id, Treatment treatment) {
        ObjectId petId = new ObjectId(id);
        Uni<Treatment> treatmentUni = treatmentReactiveMongoRepository.findById(petId);
        return treatmentUni
                .onItem().transform(t -> {
                    t.setName(treatment.getName());
                    return t;
                }).call(t -> Treatment.update(t));
    }


    /**
     * @param id
     * @return
     */
    public Uni<Treatment> delete(String id) {
        ObjectId treatmentId = new ObjectId(id);
        Uni<Treatment> petUni = treatmentReactiveMongoRepository.findById(treatmentId);
        return petUni
                .onItem()
                .call(c -> treatmentReactiveMongoRepository.deleteById(treatmentId));
    }

    /**
     * @param id
     * @return
     */
    public Uni<Treatment> findByOwnerId(String id) {
        ObjectId ownerId = new ObjectId(id);
        return treatmentReactiveMongoRepository.findById(ownerId);
    }

    /**
     * @param name
     * @return
     */
    public Uni<Treatment> findByName(String name) {
        return treatmentReactiveMongoRepository.findByName(name);
    }
}