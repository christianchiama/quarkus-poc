package it.wefox.quarkus.customercommons.mongodb;

import com.mongodb.reactivestreams.client.MongoCollection;
import io.quarkus.mongodb.reactive.ReactiveMongoClient;
import io.quarkus.mongodb.reactive.ReactiveMongoCollection;
import io.quarkus.mongodb.reactive.ReactiveMongoDatabase;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithName;
import io.smallrye.mutiny.Uni;
import lombok.Data;
import org.bson.Document;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 09/12/22
 * @Time: 21:36
 */
@Data
@Singleton
public class ReactiveMongoClientExecutor {

    @Inject
    ReactiveMongoClient reactiveMongoClient;

    @ConfigProperty(name = "it.wefox.quarkus.customercommons.mongodb.dbName")
    String dbName;

    @ConfigProperty(name = "it.wefox.quarkus.customercommons.mongodb.collectionName")
    String collectionName;



}
