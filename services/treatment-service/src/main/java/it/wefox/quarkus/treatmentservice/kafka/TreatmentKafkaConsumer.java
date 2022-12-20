package it.wefox.quarkus.treatmentservice.kafka;

import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 12/12/22
 * @Time: 17:02
 */
@ApplicationScoped
public class TreatmentKafkaConsumer {

    private final Logger logger = Logger.getLogger(TreatmentKafkaConsumer.class);

    @Incoming("pet-deleted")
    public void receive(Record<String, String> record) {
        logger.info("Pet deleted with id:" + record.key());
    }
}
