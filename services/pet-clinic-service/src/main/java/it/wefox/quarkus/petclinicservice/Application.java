package it.wefox.quarkus.petclinicservice;

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
        Quarkus.run(PetClinicServiceApplication.class, args);
    }

    public static class PetClinicServiceApplication implements QuarkusApplication {

        private static final Logger LOGGER = Logger.getLogger(PetClinicServiceApplication.class);

        @Override
        public int run(String... args) throws Exception {
            LOGGER.info("Do startup logic here");
            Quarkus.waitForExit();
            return 0;
        }
    }
}
