package com.szlachet.beetle.posts.boundary;

import com.szlachet.beetle.posts.entity.Post;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.bson.types.ObjectId;

/**
 *
 * @author Sebastian Szlachetka
 */
@Stateless
public class PostsBoundary {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public Post getPost(ObjectId id) {
        return entityManager.find(Post.class, id);
    }

    public String createPost(Post aPost) {
        return entityManager.merge(aPost).getId().toString();
    }
    

    
}
