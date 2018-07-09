/**
 *
 */
package com.sandrew.springLearning.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sandrew.springLearning.domain.Tag;

/**
 *
 */
@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {

    Optional<Tag> findByTagName(String tagName);

    Optional<Tag> findById(Long id);
}
