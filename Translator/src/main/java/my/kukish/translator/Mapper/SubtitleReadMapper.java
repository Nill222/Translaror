package my.kukish.translator.Mapper;

import lombok.RequiredArgsConstructor;
import my.kukish.translator.database.entity.Subtitle;
import my.kukish.translator.dto.MovieReadDto;
import my.kukish.translator.dto.SubtitleReadDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SubtitleReadMapper implements Mapper<Subtitle, SubtitleReadDto> {
    private final MovieReadMapper movieReadMapper;

    @Override
    public SubtitleReadDto map(Subtitle object) {
        MovieReadDto movie = Optional.ofNullable(object.getMovie())
                .map(movieReadMapper::map)
                .orElse(null);
        return new SubtitleReadDto(
                object.getId(),
                object.getStartTime(),
                object.getEndTime(),
                object.getText(),
                movie
                );
    }
}
