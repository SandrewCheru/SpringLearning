/**
 *
 */
package com.sandrew.springLearning.dao;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sandrew.springLearning.domain.Movie;

/**
 *
 */
@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {

    Set<Movie> findByTitle(String Name);

    Optional<Movie> findById(Long id);
}
