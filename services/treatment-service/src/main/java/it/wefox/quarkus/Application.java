package it.wefox.quarkus;

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
        Quarkus.run(TreatmentServiceApplication.class, args);
    }

    public static class TreatmentServiceApplication implements QuarkusApplication {

        private static final Logger LOGGER = Logger.getLogger(TreatmentServiceApplication.class);

        @Override
        public int run(String... args) throws Exception {
            LOGGER.info("Do startup logic here");
            Quarkus.waitForExit();
            return 0;
        }
    }
}
