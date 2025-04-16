package my.kukish.translator.Mapper;

import lombok.RequiredArgsConstructor;
import my.kukish.translator.database.entity.SubtitleTranslation;
import my.kukish.translator.dto.SubtitleReadDto;
import my.kukish.translator.dto.SubtitleTranslatorReadDto;
import my.kukish.translator.dto.UserReadDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SubtitleTranslatorReadMapper implements Mapper<SubtitleTranslation, SubtitleTranslatorReadDto> {
    private final SubtitleReadMapper subtitleReadMapper;
    private final UserReadMapper userReadMapper;


    @Override
    public SubtitleTranslatorReadDto map(SubtitleTranslation object) {
        SubtitleReadDto subtitle = Optional.ofNullable(object.getSubtitle())
                .map(subtitleReadMapper::map)
                .orElse(null);
        UserReadDto user = Optional.ofNullable(object.getUser())
                .map(userReadMapper::map)
                .orElse(null);
        return new SubtitleTranslatorReadDto(
                object.getId(),
                object.getSelectedText(),
                object.getTranslatedText(),
                object.getSourceLanguage(),
                object.getTargetLanguage(),
                object.getTranslatedAt(),
                user,
                subtitle
        );
    }
}

