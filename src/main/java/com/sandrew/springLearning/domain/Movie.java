package com.sandrew.springLearning.domain;

import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Preconditions;

@Entity
@Table(name = "MOVIE")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = false, nullable = false)
    private String title;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "id")
    private final TagCollection tagCollection = new TagCollection();

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "id")
    private final StudioCollection studioCollection = new StudioCollection();

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "site_id")
    private Site site;

    @SuppressWarnings("unused")
    private Movie() {

    }

    @Override
    public String toString() {
        return "Movie: id=[" + this.id + "] + Title=" + this.title + "] + tag=[" + this.tagCollection + "]";
    }

    public Movie(final String title, final String sortTitle) {
        Preconditions.checkNotNullOrEmpty(title);
        Preconditions.checkNotNullOrEmpty(sortTitle);
        this.title = title;
    }

    private Movie(final MovieBuilder builder) {
        Preconditions.checkNotNullOrEmpty(builder.title);
        this.title = builder.title;

        this.site = builder.site;

        if (!builder.tagCollection.getTags().isEmpty()) {
            this.tagCollection.add(builder.tagCollection.getTags());
        }
        if (!builder.studioCollection.getStudios().isEmpty()) {
            this.studioCollection.add(builder.studioCollection.getStudios());
        }
    }

    public static boolean isValid(final Movie movie) {
        return StringUtils.isNotBlank(movie.getTitle());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = (prime * result) + ((this.title == null) ? 0 : this.title.toLowerCase().hashCode());
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
        final Movie other = (Movie) obj;

        if (this.title == null) {
            if (other.title != null) {
                return false;
            }
        } else if (!this.title.equalsIgnoreCase(other.title)) {
            return false;
        }
        return true;
    }

    public Optional<Long> getId() {
        return Optional.ofNullable(this.id);
    }

    public String getTitle() {
        return this.title;
    }

    public TagCollection getTagCollection() {
        return this.tagCollection;
    }

    public StudioCollection getStudioCollection() {
        return this.studioCollection;
    }

    public static class MovieBuilder {

        private String title;

        private String sortTitle;

        private Site site;

        private final TagCollection tagCollection = new TagCollection();

        private final StudioCollection studioCollection = new StudioCollection();

        public MovieBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public MovieBuilder sortTitle(final String sortTitle) {
            this.sortTitle = sortTitle;
            return this;
        }

        public MovieBuilder tagCollection(final TagCollection tagCollection) {
            this.tagCollection.add(tagCollection.getTags());
            return this;
        }

        public MovieBuilder studioCollections(final StudioCollection studioCollection) {
            this.studioCollection.clear();
            this.studioCollection.add(studioCollection.getStudios());
            return this;
        }

        public MovieBuilder site(final Site site) {
            this.site = site;
            return this;
        }

        public Movie build() {
            final Movie m = new Movie(this);
            return m;
        }
    }
}
