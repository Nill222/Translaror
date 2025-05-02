package my.kukish.translator.dto;

import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.time.ZonedDateTime;

@Value
public class MovieReadDto {
    Long id;
    String title;
    String description;
    String videoUrl;
    String thumbnailUrl;
    ZonedDateTime uploadAt;
}
