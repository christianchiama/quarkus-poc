package it.wefox.quarkus.customerservice;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.jboss.logging.Logger;

/**
 * @author Christian Chiama
 * --
 */
@QuarkusMain
public class Application {
    public static void main(String... args) {
        Quarkus.run(CustomerServiceApplication.class, args);
    }

    public static class CustomerServiceApplication implements QuarkusApplication {

        private static final Logger LOGGER = Logger.getLogger(CustomerServiceApplication.class);

        @Override
        public int run(String... args) throws Exception {
            System.out.println("Do startup logic here");
            Quarkus.waitForExit();
            return 0;
        }
    }
}
