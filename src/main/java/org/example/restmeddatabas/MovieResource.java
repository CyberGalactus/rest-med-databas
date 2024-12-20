package org.example.restmeddatabas;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.restmeddatabas.model.Movie;

import java.util.List;

//Denna klass hanterar REST API för movie klassen

@Path("/movies") // @Path
@Produces(MediaType.APPLICATION_JSON) // @Produces och @Consumes anger att metoderna i denna klass returnerar JSON.
@Consumes(MediaType.APPLICATION_JSON)
public class MovieResource {

//    @PersistenceContext
//    private EntityManager entityManager;

    @Inject // @Inject hanterar MovieRepository för att hantera databasanrop.
    private MovieRepository movieRepository;

    @POST //skapar
    @Transactional
    public Response addMovie(Movie movie) {
        movieRepository.addMovie(movie);
        return Response.status(Response.Status.CREATED).entity("Film skapad").build();
    }

    @GET
    public List<Movie> getMovies() {
        return movieRepository.getAllMovies();
    }

    @GET //läser
    @Path("/{id}")// id för specifika filmen
    public Response getMovie(@PathParam("id") Long id) {
        Movie movie = movieRepository.getMovieById(id);
        if (movie == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Film inte hittad").build();
            // kort fattat. Om filmen inte finns, returnerar den
            // en 404 NOT FOUND fel med meddelandet “Film inte hittad”.

        }
        return Response.ok(movie).build();
    }

    @PUT // uppdaterar
    @Path("/{id}") // id för specifika filmen
    public Response updateMovie(@PathParam("id") Long id, Movie movie) {
        Movie existingMovie = movieRepository.getMovieById(id);
        if (existingMovie == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Film inte hittad").build();
        }
        movieRepository.updateMovie(id, movie);
        return Response.ok(movie).entity("Film uppdaterad").build();
    }

    @DELETE // tar bort
    @Path("/{id}") // id för specifika filmen
    public Response deleteMovie(@PathParam("id") Long id) {
        Movie movie = movieRepository.getMovieById(id);
        if (movie == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Film inte hittad").build();
        }
        movieRepository.deleteMovie(id);
        return Response.noContent().build();
    }

}
