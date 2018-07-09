package com.sandrew.springLearning.domain;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;

@Entity
@Table(name = "STUDIO")
public class Studio implements MovieEntity, Representable, Taggable {

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

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "studio_aliases", joinColumns = @JoinColumn(name = "studio_id"), inverseJoinColumns = @JoinColumn(name = "alias_id"))
    private final Set<Studio> aliases = new HashSet<>();

    @SuppressWarnings("unused")
    private Studio() {

    }

    @Override
    public String toString() {
        return "Studio id=[" + this.id + "] + Name=" + this.name + "] + tag=[" + this.tags + "]";
    }

    public Studio(final String name) {
        this.name = name;
    }

    @Override
    public MovieEntityType getMovieEntityType() {
        return MovieEntityType.STUDIO;
    }

    public static boolean isValid(final Studio studio) {
        return StringUtils.isNotBlank(studio.getName());
    }

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
        final Studio other = (Studio) obj;
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equalsIgnoreCase(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public Optional<Long> getId() {
        return Optional.ofNullable(this.id);
    }

    @Override
    public TagCollection getTagCollection() {
        return this.tags;
    }
}
