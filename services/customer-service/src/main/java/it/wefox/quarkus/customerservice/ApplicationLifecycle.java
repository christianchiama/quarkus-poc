package it.wefox.quarkus.customerservice;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import it.wefox.quarkus.customercommons.mongodb.ReactiveMongoClientExecutor;
import org.jboss.logging.Logger;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 * @author Christian Chiama
 * --
 */
@ApplicationScoped
public class ApplicationLifecycle {

    private static final Logger LOGGER = Logger.getLogger("ListenerBean");

    public void onStart(@Observes StartupEvent ev) {

        LOGGER.info("The application is starting...");
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://localhost:6379")
                .setConnectionPoolSize(10)
                .setConnectionMinimumIdleSize(5).setConnectTimeout(30000);

        RedissonClient redissonClient = Redisson.create(config);

        LOGGER.info(redissonClient.getMap("redisMap").keySet().toString());
        LOGGER.info(redissonClient.getMap("redisMap").values().toString());
    }

    public void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("The application is stopping...");
    }

}
