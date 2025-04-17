package my.kukish.translator.dto;

import lombok.Value;

@Value
public class SubtitleReadDto {
    Integer id;
    double startTime;
    double endTime;
    String text;
    MovieReadDto movie;
}
