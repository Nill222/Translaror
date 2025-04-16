package my.kukish.translator.dto;

import lombok.Value;

import java.time.ZonedDateTime;

@Value
public class SubtitleTranslatorCreateEditDto {
    String selectedText;
    String translatedText;
    String sourceLanguage;
    String targetLanguage;
    ZonedDateTime translatedAt;
    Long userId;
    int subtitleId;
}
