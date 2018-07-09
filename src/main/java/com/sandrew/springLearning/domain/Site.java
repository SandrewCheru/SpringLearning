package com.sandrew.springLearning.domain;

import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.assertj.core.util.Preconditions;

@Entity
@Table(name = "SITE")
public class Site implements MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotEmpty
    @Size(min = 1)
    @Column(unique = true, nullable = false)
    private String name;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "id")
    private final TagCollection tags = new TagCollection();

    @SuppressWarnings("unused")
    private Site() {

    }

    public Site(final String name) {
        Preconditions.checkNotNullOrEmpty(name);
        this.name = name;
    }

    @Override
    public MovieEntityType getMovieEntityType() {
        return MovieEntityType.SITE;
    }

    @Override
    public String toString() {
        return "Site: id=[" + this.id + "] + Name=" + this.name + "] + tag=[" + this.tags + "]";
    }

    /*
     * public static boolean isValid(final Site site) { return StringUtils.isNotBlank(site.getName()); }
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.name == null) ? 0 : this.name.toLowerCase().hashCode());
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
        final Site other = (Site) obj;
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equalsIgnoreCase(other.name)) {
            return false;
        }
        return true;
    }

    // @Override
    public String getName() {
        return this.name;
    }

    public Optional<Long> getId() {
        return Optional.ofNullable(this.id);
    }
}
