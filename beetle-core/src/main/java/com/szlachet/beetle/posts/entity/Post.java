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
package com.szlachet.beetle.posts.entity;

import com.szlachet.beetle.comments.entity.Comment;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Sebastian Szlachetka
 */
@Entity
@Table(name = "post")
public class Post extends com.szlachet.beetle.BaseEntity {

    @Column(name = "title")
    private String title;
    
    @OneToOne
    @JoinColumn(name = "content_id")
    private Content content;

    @OneToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Enumerated
    @Column(name = "status")
    private Status status;
    
    @ManyToMany
    @JoinTable(name = "post_tag", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Status getPostStatus() {
        return status;
    }

    public void setPostStatus(Status postStatus) {
        this.status = postStatus;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    
    public enum Status {
        DRAFT, PUBLISHED, OUTDATED;
    }
}
