package it.wefox.quarkus.customerservice.mapper;

import it.wefox.quarkus.customerservice.domain.Customer;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 11/12/22
 * @Time: 17:39
 */
public class CustomerCodecProvider implements CodecProvider {
    @Override
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        if (clazz.equals(Customer.class)) {
            return (Codec<T>) new CustomerCodec();
        }
        return null;
    }

}