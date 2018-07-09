/**
 *
 */
package com.sandrew.springLearning.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sandrew.springLearning.domain.TagCollection;

/**
 *
 */
@Repository
public interface TagCollectionRepository extends CrudRepository<TagCollection, Long> {

}
