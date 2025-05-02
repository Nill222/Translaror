package my.kukish.translator.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.time.ZonedDateTime;

@Value
public class SubtitleTranslatorCreateEditDto {
    @NotNull
    String selectedText;
    String translatedText;
    String sourceLanguage;
    String targetLanguage;
    ZonedDateTime translatedAt;
    Long userId;
    int subtitleId;
}
