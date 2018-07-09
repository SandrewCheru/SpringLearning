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
@Table(name = "STUDIO_COLLECTION")
public class StudioCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinTable(name = "studio_link", joinColumns = @JoinColumn(name = "studioCollection_id"), inverseJoinColumns = @JoinColumn(name = "studio_id"))
    private final Set<Studio> studios = new HashSet<>();

    public StudioCollection() {

    }

    public Long getId() {
        return this.id;
    }

    public Set<Studio> getStudios() {
        return this.studios;
    }

    public void add(final Studio studio) {
        Preconditions.checkNotNull(studio);
        this.studios.add(studio);
    }

    public void add(final Set<Studio> studios) {
        Preconditions.checkNotNull(studios);
        Preconditions.checkArgument(!studios.isEmpty(), "Cannot add empty studios", studios);
        this.studios.addAll(studios);
    }

    public void remove(final Studio studio) {
        Preconditions.checkNotNull(studio);
        this.studios.remove(studio);
    }

    public void clear() {
        this.studios.clear();
    }

    public boolean contains(final Studio studio) {
        return this.studios.contains(studio);
    }

    public boolean isEmpty() {
        return this.studios.isEmpty();
    }

    public int size() {
        return this.studios.size();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.studios == null) ? 0 : this.studios.hashCode());
        return result;
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
        final StudioCollection other = (StudioCollection) obj;
        if (this.studios == null) {
            if (other.studios != null) {
                return false;
            }
        } else if (!this.studios.equals(other.studios)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StudioCollection [id=" + this.id + ", studios=" + Arrays.toString(this.studios.toArray()) + "]";
    }
}
