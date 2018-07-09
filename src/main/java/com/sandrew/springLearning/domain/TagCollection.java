package com.sandrew.springLearning.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.assertj.core.util.Preconditions;

@Entity
@Table(name = "TAG_COLLECTION")
public class TagCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinTable(name = "tag_link", joinColumns = @JoinColumn(name = "tagCollection_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private final Set<Tag> tags = new HashSet<>();

    public TagCollection() {

    }

    public Long getId() {
        return this.id;
    }

    public Set<Tag> getTags() {
        return this.tags;
    }

    public void add(final Tag tag) {
        Preconditions.checkNotNull(tag);
        this.tags.add(tag);
    }

    public void add(final Set<Tag> tags) {
        Preconditions.checkNotNull(tags);
        Preconditions.checkArgument(!tags.isEmpty(), "Cannot add empty tags", tags);
        this.tags.addAll(tags);
    }

    public boolean isEmpty() {
        return this.tags.isEmpty();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.tags == null) ? 0 : this.tags.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "TagCollection: id=[" + this.id + "] Values=[" + Arrays.toString(this.tags.toArray()) + "]";
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final TagCollection other = (TagCollection) obj;
        if (this.tags == null) {
            if (other.tags != null) {
                return false;
            }
        } else if (!this.tags.equals(other.tags)) {
            return false;
        }
        return true;
    }
}
