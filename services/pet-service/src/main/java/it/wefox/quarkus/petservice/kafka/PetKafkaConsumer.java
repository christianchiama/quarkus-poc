package it.wefox.quarkus.petservice.kafka;

import io.smallrye.common.annotation.Blocking;
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
public class PetKafkaConsumer {

    private final Logger logger = Logger.getLogger(PetKafkaConsumer.class);

    @Incoming("customer")
    public void receive(Record<String, String> record) {
        logger.info("Customer deleted with id:" + record.key());
    }
}
