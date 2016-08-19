/*
 * Copyright 2016 Sebastian Szlachetka.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.szlachet.beetle.posts.boundary;

import com.szlachet.beetle.posts.entity.Author;
import static com.szlachet.beetle.posts.entity.Author.AUTHOR_FIND_ALL;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.bson.types.ObjectId;

/**
 *
 * @author Sebastian Szlachetka
 */
@Stateless
public class AuthorsBoundary {

    @PersistenceContext
    private EntityManager entityManager;

    public Author getAuthor(ObjectId id) {
        return entityManager.find(Author.class, id);
    }

    public List<Author> getAuthors() {
        return entityManager.createNamedQuery(AUTHOR_FIND_ALL, Author.class).getResultList();
    }

    public String createAuthor(Author aAuthor) {
        return entityManager.merge(aAuthor).getId().toString();
    }

}
