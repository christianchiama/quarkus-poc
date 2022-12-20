package it.wefox.quarkus.petcommons.mongodb;

import io.quarkus.mongodb.reactive.ReactiveMongoClient;
import io.quarkus.mongodb.reactive.ReactiveMongoCollection;
import io.quarkus.mongodb.reactive.ReactiveMongoDatabase;
import lombok.Data;
import org.bson.Document;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.inject.Singleton;

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

    @ConfigProperty(name = "it.wefox.quarkus.petcommons.mongodb.dbName")
    String dbName;

    @ConfigProperty(name = "it.wefox.quarkus.petcommons.mongodb.collectionPet")
    String collectionPet;

    @ConfigProperty(name = "it.wefox.quarkus.petcommons.mongodb.collectionSpecies")
    String collectionSpecies;

    public ReactiveMongoCollection<Document> getPetCollection() {
        ReactiveMongoDatabase db = reactiveMongoClient.getDatabase(dbName);
        return db.getCollection(collectionPet);
    }

    public ReactiveMongoCollection<Document> getSpeciesCollection() {
        ReactiveMongoDatabase db = reactiveMongoClient.getDatabase(dbName);
        return db.getCollection(collectionSpecies);
    }

}
