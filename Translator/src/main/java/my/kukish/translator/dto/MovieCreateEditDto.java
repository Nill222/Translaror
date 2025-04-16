package my.kukish.translator.dto;

import lombok.Value;

import java.time.ZonedDateTime;

@Value
public class MovieCreateEditDto {
    String title;
    String description;
    String videoUrl;
    String thumbnailUrl;
    ZonedDateTime uploadAt;
}
