package com.szlachet.beetle.business.posts.boundary;

import com.mongodb.client.MongoDatabase;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Sebastian Szlachetka
 */
@Stateless
public class PostsBoundary {

    @Inject
    private MongoDatabase mongoDB;        
    
    public String getPost(String id) {
        return mongoDB.getCollection("posts").find(new Document("_id", new ObjectId(id))).first().toJson();
    }

    List<JsonObject> getAllPosts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String addPost(JsonObject post) {
        Document postDoc = Document.parse(post.toString());
        mongoDB.getCollection("posts").insertOne(postDoc);
        return postDoc.get("_id", ObjectId.class).toString();
    }

    void removePost(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
