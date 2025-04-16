package my.kukish.translator.Mapper;

import my.kukish.translator.database.entity.Movie;
import my.kukish.translator.dto.MovieCreateEditDto;
import org.springframework.stereotype.Component;

@Component
public class MovieCreateEditMapper implements Mapper<MovieCreateEditDto, Movie> {
    @Override
    public Movie map(MovieCreateEditDto object) {
        Movie movie = new Movie();
        copy(object, movie);
        return movie;
    }

    private void copy(MovieCreateEditDto object, Movie movie){
        movie.setTitle(object.getTitle());
        movie.setDescription(object.getDescription());
        movie.setVideoUrl(object.getVideoUrl());
        movie.setThumbnailUrl(object.getThumbnailUrl());
        movie.setUploadedAt(object.getUploadAt());

    }

    @Override
    public Movie map(MovieCreateEditDto fromObject, Movie toObject) {
        copy(fromObject, toObject);
        return toObject;
    }
}
