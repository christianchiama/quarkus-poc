package it.wefox.quarkus.customerservice;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import it.wefox.quarkus.customerservice.mapper.CustomerCodec;
import it.wefox.quarkus.customerservice.mapper.CustomerCodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.jboss.logging.Logger;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author Christian Chiama
 * --
 */
@QuarkusMain(name = "CustomerServiceApplication")
public class Application {


    public static void main(String... args) {
        CodecRegistries.fromRegistries(CodecRegistries.fromCodecs(new CustomerCodec()),
                CodecRegistries.fromProviders(new CustomerCodecProvider()));
        Quarkus.run(CustomerServiceApplication.class, args);
    }

    public static class CustomerServiceApplication implements QuarkusApplication {

        private static final Logger LOGGER = Logger.getLogger(CustomerServiceApplication.class);

        @Override
        public int run(String... args) throws Exception {
            LOGGER.info("Do startup logic here");
            Quarkus.waitForExit();
            return 0;
        }
    }
}
