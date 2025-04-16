package my.kukish.translator.dto;

import lombok.Value;
import my.kukish.translator.database.entity.Movie;

@Value
public class SubtitleReadDto {
    Integer id;
    double startTime;
    double endTime;
    String text;
    MovieReadDto movie;
}
