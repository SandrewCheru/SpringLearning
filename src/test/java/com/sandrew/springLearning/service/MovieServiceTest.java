package com.sandrew.springLearning.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.sandrew.springLearning.SpringLearningApplication;
import com.sandrew.springLearning.domain.Movie;
import com.sandrew.springLearning.domain.Site;
import com.sandrew.springLearning.domain.Studio;
import com.sandrew.springLearning.domain.StudioCollection;
import com.sandrew.springLearning.domain.Tag;
import com.sandrew.springLearning.domain.TagCollection;
import com.sandrew.springLearning.domain.Movie.MovieBuilder;
import com.sandrew.springLearning.service.MovieService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringLearningApplication.class)
public class MovieServiceTest {

    @Autowired
    MovieService underTest;

    @Test
    public void addedMoviesWithAttributesArePersisted() {
        final MovieBuilder mb = new MovieBuilder();
        mb.title("Title");

        // Add a site to the movie
        final Site site = new Site("Site TEst");
        mb.site(site);

        // Add 2 studios to the movie
        final Studio studio1 = new Studio("Studio 1");
        final Studio studio2 = new Studio("Studio 2");
        final StudioCollection studioCollection = new StudioCollection();
        studioCollection.add(Sets.newSet(studio1, studio2));
        mb.studioCollections(studioCollection);

        // Add 2 tags to the movie
        final Tag tag1 = new Tag("Tag 1");
        final Tag tag2 = new Tag("Tag 2");
        final TagCollection tagCollection = new TagCollection();
        tagCollection.add(Sets.newSet(tag1, tag2));
        mb.tagCollection(tagCollection);

        final Movie movie = mb.build();
        this.underTest.persist(movie);

        final Set<Movie> foundMovies = this.underTest.findByTitle("Title");
        assertFalse(foundMovies.isEmpty());
        assertEquals(1, foundMovies.size());
        assertTrue(foundMovies.contains(movie));
        final Movie foundMovie = foundMovies.iterator().next();

        this.underTest.getAll();

        // This fails
        assertFalse(foundMovie.getTagCollection().isEmpty());

        // Querying for all entities gives this results
        // TagCollection: id=[1] Values=[[]]
        // TagCollection: id=[2] Values=[[Tag [id=1, tagName=Tag 1], Tag [id=2, tagName=Tag 2]]]
        // TagCollection: id=[3] Values=[[]]
        // TagCollection: id=[4] Values=[[]]
        // Site: id=[1] + Name=Site TEst] + tag=[TagCollection: id=[1] Values=[[]]]
        // Studio id=[1] + Name=Studio 2] + tag=[TagCollection: id=[1] Values=[[]]]
        // Studio id=[2] + Name=Studio 1] + tag=[TagCollection: id=[2] Values=[[Tag [id=1, tagName=Tag 1], Tag [id=2,
        // tagName=Tag 2]]]]
        // Movie: id=[1] + Title=Title] + tag=[TagCollection: id=[1] Values=[[]]]
    }

}
