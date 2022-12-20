package it.wefox.quarkus.treatmentcommons.mongodb;

import io.quarkus.mongodb.reactive.ReactiveMongoClient;
import io.quarkus.mongodb.reactive.ReactiveMongoCollection;
import io.quarkus.mongodb.reactive.ReactiveMongoDatabase;
import org.bson.Document;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.function.Supplier;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 09/12/22
 * @Time: 21:36
 */
@Singleton
public class ReactiveMongoClientExecutor {

    @Inject
    ReactiveMongoClient reactiveMongoClient;

    @ConfigProperty(name = "it.wefox.quarkus.treatmentcommons.mongodb.dbName")
    String dbName;

    @ConfigProperty(name = "it.wefox.quarkus.treatmentcommons.mongodb.collectionTreatment")
    String collectionTreatment;


    public ReactiveMongoCollection<Document> getTreatmentCollection() {
        ReactiveMongoDatabase db = reactiveMongoClient.getDatabase(dbName);
        return db.getCollection(collectionTreatment);
    }

    private <R> R ifExists(Supplier<R> code, R fallback) {
        if(reactiveMongoClient.getDatabase(dbName)!=null) return code.get();
        return fallback;
    }
}
