package it.wefox.quarkus.petservice;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import it.wefox.quarkus.petservice.service.MigrationService;
import org.jboss.logging.Logger;

import javax.inject.Inject;

/**
 * @author Christian Chiama
 * --
 */
@QuarkusMain(name = "PetServiceApplication")
public class Application {


    public static void main(String... args) {
        Quarkus.run(PetServiceApplication.class, args);
    }

    public static class PetServiceApplication implements QuarkusApplication {

        private static final Logger LOGGER = Logger.getLogger("ListenerBean");

        @Override
        public int run(String... args) throws Exception {
            LOGGER.info("Do startup logic here");
            Quarkus.waitForExit();
            return 0;
        }
    }
}
