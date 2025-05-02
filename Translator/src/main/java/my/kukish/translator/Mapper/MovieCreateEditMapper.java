package my.kukish.translator.Mapper;

import my.kukish.translator.database.entity.Movie;
import my.kukish.translator.dto.MovieCreateEditDto;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.function.Predicate;

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
        Optional.ofNullable(object.getVideoUrl())
                .filter(file -> !file.isEmpty())
                .ifPresent(file -> movie.setVideoUrl(file.getOriginalFilename()));
        movie.setThumbnailUrl(object.getThumbnailUrl());
        movie.setUploadedAt(object.getUploadAt());

    }

    @Override
    public Movie map(MovieCreateEditDto fromObject, Movie toObject) {
        copy(fromObject, toObject);
        return toObject;
    }
}
