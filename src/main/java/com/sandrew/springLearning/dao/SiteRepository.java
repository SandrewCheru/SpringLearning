/**
 *
 */
package com.sandrew.springLearning.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sandrew.springLearning.domain.Site;

/**
 *
 */
@Repository
public interface SiteRepository extends CrudRepository<Site, Long> {

    Optional<Site> findByName(String Name);
}
