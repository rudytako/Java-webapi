package com.maciek.movielibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;
    @GetMapping("")
    public List<Movie> getAll() {
        return movieRepository.getAll();
    }

    @GetMapping("/{id}")
    public Movie getById(@PathVariable("id") int id) {
        return movieRepository.getById(id);
    }

    @PostMapping("")
    public int add(@RequestBody List<Movie> movies) {
        return movieRepository.save(movies);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Movie updatedMovie) {
        Movie movie = movieRepository.getById(id);

        if (movie == null) return 0;

        movie.setName(updatedMovie.getName());
        movie.setRating(updatedMovie.getRating());

        return movieRepository.update((movie));
    }

    @PatchMapping("/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody Movie updatedMovie) {
        Movie movie = movieRepository.getById(id);
        if (movie == null) return 0;
        if (updatedMovie.getName() != null) movie.setName(updatedMovie.getName());
        if (updatedMovie.getRating() > 0) movie.setRating(updatedMovie.getRating());

        return movieRepository.update(movie);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id) {
        return movieRepository.delete(id);
    }

}
