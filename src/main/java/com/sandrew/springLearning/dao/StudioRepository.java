/**
 *
 */
package com.sandrew.springLearning.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sandrew.springLearning.domain.Studio;

/**
 *
 */
@Repository
public interface StudioRepository extends CrudRepository<Studio, Long> {

    Optional<Studio> findByName(String Name);
}
