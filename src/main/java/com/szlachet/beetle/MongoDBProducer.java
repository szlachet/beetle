package com.szlachet.beetle;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 *
 * @author Sebastian Szlachetka
 */
@ApplicationScoped
public class MongoDBProducer {
   
   private MongoDatabase mongoDB;
    
   @PostConstruct
   public void init() {
       MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017/beetledb");
       MongoClient mongoClient = new MongoClient(uri);
       mongoDB = mongoClient.getDatabase(uri.getDatabase());
   }
   
   @Produces
   public MongoDatabase createMongoDatabase() {
       return mongoDB;
   }
}
