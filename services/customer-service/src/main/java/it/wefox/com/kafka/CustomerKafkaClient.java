package it.wefox.com.kafka;

/**
 * @author Christian Chiama
 * --
 */
import org.apache.kafka.clients.producer.RecordMetadata;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.Future;


public interface CustomerKafkaClient {

    Logger logger = LoggerFactory.getLogger(CustomerKafkaClient.class);

    @Channel("customer.deleted")
    Future<RecordMetadata> customerDeleted(String key, String customerId);

    default Future<RecordMetadata> customerDeleted(String customerId) {
        logger.debug("propagate delete of single customer: {}", customerId);
        return customerDeleted("customer", customerId);
    }

}

