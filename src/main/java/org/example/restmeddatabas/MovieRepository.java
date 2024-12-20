package org.example.restmeddatabas;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import org.example.restmeddatabas.model.Movie;

import java.util.List;
// Denna klass hanterar interaktionen med databasen för Movie klassen
public class MovieRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Lägger till en ny film i databasen
    @Transactional
    public void addMovie(Movie movie) {
        entityManager.persist(movie);
    }

    // Hämtar alla filmer från databasen
    public List<Movie> getAllMovies() { // det den gör är att först är det en metod som returnerar en lista av Movie-objekt.
        // Skapar och utför en JPQL-fråga som hämtar alla Movie-entiteter
        return entityManager.createQuery("SELECT m FROM Movie m", Movie.class).getResultList();
    }

    // Hämtar en specifik film baserat på ID
    public Movie getMovieById(Long id) {
        return entityManager.find(Movie.class, id);
    }

    // Uppdaterar en specifik film baserat på fimlens ID
    @Transactional
    public void updateMovie(Long id, Movie updatedMovie) {
        Movie existingMovie = entityManager.find(Movie.class, id);
        if (existingMovie != null) {
            existingMovie.setTitle(updatedMovie.getTitle());
            existingMovie.setGenre(updatedMovie.getGenre());
            existingMovie.setReleaseYear(updatedMovie.getReleaseYear());
            existingMovie.setDescription(updatedMovie.getDescription());
            existingMovie.setDirector(updatedMovie.getDirector());
            entityManager.merge(existingMovie); // merge() gör att det uppdaterar filmerna i databsen
        }
    }

    // delete funkar Tar bort en specifik film baserat på ID
    @Transactional
    public void deleteMovie(Long id) {
        Movie movie = entityManager.find(Movie.class, id); // hämtar filmerna som ska bort.
        if (movie != null) {
            entityManager.remove(movie);
        }
    }



}
