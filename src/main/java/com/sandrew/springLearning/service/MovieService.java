package com.sandrew.springLearning.service;

import java.util.Set;

import javax.persistence.EntityManager;

import org.assertj.core.util.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sandrew.springLearning.dao.MovieRepository;
import com.sandrew.springLearning.dao.SiteRepository;
import com.sandrew.springLearning.dao.StudioRepository;
import com.sandrew.springLearning.dao.TagCollectionRepository;
import com.sandrew.springLearning.domain.Movie;
import com.sandrew.springLearning.domain.Site;
import com.sandrew.springLearning.domain.Studio;
import com.sandrew.springLearning.domain.TagCollection;

@Service
@Transactional
public class MovieService {

    @Autowired
    private final EntityManager entityManager;

    @Autowired
    private final MovieRepository movieRepository;

    @Autowired
    private TagCollectionRepository tagCollectionRepository;

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private StudioRepository studioRepository;

    public MovieService(final EntityManager entityManager, final MovieRepository movieRepository) {
        Preconditions.checkNotNull(entityManager);
        Preconditions.checkNotNull(movieRepository);
        this.entityManager = entityManager;
        this.movieRepository = movieRepository;
    }

    public void persist(final Movie movie) {
        Preconditions.checkNotNull(movie);
        Preconditions.checkArgument(!movie.getId().isPresent(), "The movie to persist has an id. Should be merged",
                        movie);
        this.entityManager.persist(movie);
    }

    public Movie merge(final Movie movie) {
        Preconditions.checkNotNull(movie);
        Preconditions.checkNotNull(movie.getId());
        return this.entityManager.merge(movie);
    }

    public Set<Movie> findByTitle(final String title) {
        return this.movieRepository.findByTitle(title);
    }

    public void getAll() {
        final Iterable<TagCollection> tagCollIt = this.tagCollectionRepository.findAll();
        for (final TagCollection tc : tagCollIt) {
            System.out.println(tc.toString());
        }

        final Iterable<Site> siteIt = this.siteRepository.findAll();
        for (final Site tc : siteIt) {
            System.out.println(tc.toString());
        }

        final Iterable<Studio> studioIt = this.studioRepository.findAll();
        for (final Studio tc : studioIt) {
            System.out.println(tc.toString());
        }

        final Iterable<Movie> mIt = this.movieRepository.findAll();
        for (final Movie tc : mIt) {
            System.out.println(tc.toString());
        }

    }
}
