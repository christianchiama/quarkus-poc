package it.wefox.quarkus.petservice.kafka;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 12/12/22
 * @Time: 16:20
 */
import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PetKafkaProducer {

    private static final Logger LOGGER = Logger.getLogger(PetKafkaProducer.class);

    @Inject
    @Channel("on-pet-deleted")
    Emitter<Record<String, String>> emitter;

    public void onPetDeleted(String id) {
        LOGGER.info("-- Pet deleted --");
        emitter.send(Record.of(id,"pet"));
    }
}
