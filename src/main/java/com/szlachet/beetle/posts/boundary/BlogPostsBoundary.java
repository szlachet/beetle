package com.szlachet.beetle.posts.boundary;

import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
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
public class BlogPostsBoundary {

    private static final String POSTS_COLLECTION = "posts";
    
    @Inject
    private MongoDatabase mongoDB;        
    
    public String getPost(String id) {
        return mongoDB.getCollection(POSTS_COLLECTION).find(new Document("_id", new ObjectId(id))).first().toJson();
    }

    public List<String> getAllPosts() {
        List<String> jsonPosts = new ArrayList<>();
        for(Document d : mongoDB.getCollection(POSTS_COLLECTION).find()) {
            jsonPosts.add(d.toJson());
        }
        return jsonPosts;
    }

    public String addPost(JsonObject post) {
        Document postDoc = Document.parse(post.toString());
        mongoDB.getCollection(POSTS_COLLECTION).insertOne(postDoc);
        return postDoc.get("_id", ObjectId.class).toString();
    }

    public void removePost(String id) {
        mongoDB.getCollection(POSTS_COLLECTION).deleteOne(eq("_id",  new ObjectId(id)));
    }
    

    
}
