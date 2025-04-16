package my.kukish.translator.Mapper;

import my.kukish.translator.database.entity.Movie;
import my.kukish.translator.dto.MovieReadDto;
import org.springframework.stereotype.Component;

@Component
public class MovieReadMapper implements Mapper<Movie, MovieReadDto> {

    @Override
    public MovieReadDto map(Movie object) {
        return new MovieReadDto(
                object.getId(),
                object.getTitle(),
                object.getDescription(),
                object.getVideoUrl(),
                object.getThumbnailUrl(),
                object.getUploadedAt()
        );
    }
}
