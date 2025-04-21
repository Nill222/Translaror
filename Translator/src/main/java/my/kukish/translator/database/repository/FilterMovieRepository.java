package my.kukish.translator.database.repository;

import my.kukish.translator.database.entity.Movie;
import my.kukish.translator.dto.MovieFilter;

import java.util.List;

public interface FilterMovieRepository {
    List<Movie> findAllByFilter(MovieFilter filer);
}
