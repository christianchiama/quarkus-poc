package it.wefox.quarkus.treatmentservice;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

/**
 * @author Christian Chiama
 * --
 */
@QuarkusMain(name = "TreatmentServiceApplication")
public class Application {
    public static void main(String... args) {
        Quarkus.run(TreatmentServiceApplication.class, args);
    }

    public static class TreatmentServiceApplication implements QuarkusApplication {

        private static final Logger LOGGER = Logger.getLogger(TreatmentServiceApplication.class);

        @Override
        public int run(String... args) throws Exception {
            System.out.println("Do startup logic here");
            Quarkus.waitForExit();
            return 0;
        }
    }
}
