package my.kukish.translator.Mapper;

import lombok.RequiredArgsConstructor;
import my.kukish.translator.database.entity.Movie;
import my.kukish.translator.database.entity.Subtitle;
import my.kukish.translator.database.repository.MovieRepository;

import my.kukish.translator.dto.SubtitleCreateEditDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SubtitleCreateEditMapper implements Mapper<SubtitleCreateEditDto, Subtitle> {
    private final MovieRepository movieRepository;

    @Override
    public Subtitle map(SubtitleCreateEditDto object) {
        Subtitle subtitle = new Subtitle();
        copy(object, subtitle);
        return subtitle;
    }

    private void copy(SubtitleCreateEditDto object, Subtitle subtitle){
        subtitle.setStartTime(object.getStartTime());
        subtitle.setEndTime(object.getEndTime());
        subtitle.setText(object.getText());
        subtitle.setMovie(getMovie(object.getMovieId()));
    }

    @Override
    public Subtitle map(SubtitleCreateEditDto fromObject, Subtitle toObject) {
        copy(fromObject, toObject);
        return  toObject;
    }

    private Movie getMovie(Long movieId) {
        return Optional.ofNullable(movieId)
                .flatMap(movieRepository::findById)
                .orElse(null);
    }
}
