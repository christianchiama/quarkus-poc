package it.wefox.quarkus.customerservice.kafka;

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
public class CustomerKafkaProducer {

    private static final Logger LOGGER = Logger.getLogger(CustomerKafkaProducer.class);

    @Inject @Channel("on-customer-deleted")
    Emitter<Record<String, String>> emitter;

    public void onCustomerDeleted(String id) {
        LOGGER.info("-- Customer deleted --");
        emitter.send(Record.of(id,"customer"));
    }
}
