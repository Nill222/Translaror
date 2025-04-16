package my.kukish.translator.dto;

import lombok.Value;
import my.kukish.translator.database.entity.Subtitle;
import my.kukish.translator.database.entity.User;

import java.time.ZonedDateTime;

@Value
public class SubtitleTranslatorReadDto {
    Integer id;
    String selectedText;
    String translatedText;
    String sourceLanguage;
    String targetLanguage;
    ZonedDateTime translatedAt;
    UserReadDto user;
    SubtitleReadDto subtitle;

}
