package com.sandrew.springLearning.domain;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.text.WordUtils;
import org.assertj.core.util.Preconditions;

@Entity
@Table(name = "TAG")
public final class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotEmpty
    @Size(min = 1)
    @Column(unique = true, nullable = false)
    private String tagName;

    @SuppressWarnings("unused")
    private Tag() {

    }

    public Tag(final String tagName) {
        Preconditions.checkNotNull(tagName);
        Preconditions.checkNotNullOrEmpty(tagName.trim());
        // If the name is all upper case, do not modify it, otherwise, fully capitalise it.
        String tmpTagName = tagName.trim();
        if (tmpTagName.equals(tmpTagName.toUpperCase()) == false) {
            tmpTagName = WordUtils.capitalizeFully(tmpTagName);
        }
        this.tagName = tmpTagName;
    }

    public String getTagName() {
        return this.tagName;
    }

    public Optional<Long> getId() {
        return Optional.ofNullable(this.id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + this.getTagName().toLowerCase().hashCode();
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
        final Tag other = (Tag) obj;
        if (this.tagName == null) {
            if (other.tagName != null) {
                return false;
            }
        } else if (!this.tagName.equalsIgnoreCase(other.tagName)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "Tag [id=" + this.id + ", tagName=" + this.getTagName() + "]";
    }
}
