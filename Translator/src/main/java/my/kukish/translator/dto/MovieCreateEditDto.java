package my.kukish.translator.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.time.ZonedDateTime;

@Value
public class MovieCreateEditDto {
    @NotNull
    String title;
    @NotBlank
            @Size(min = 1, max = 200)
    String description;
    MultipartFile videoUrl;
    String thumbnailUrl;
    ZonedDateTime uploadAt;
}
