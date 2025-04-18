package my.kukish.translator.dto;

import lombok.Value;

@Value
public class SubtitleCreateEditDto {
    double startTime;
    double endTime;
    String text;
    Long movieId;
}
