package my.kukish.translator.Mapper;

import lombok.RequiredArgsConstructor;
import my.kukish.translator.database.entity.Subtitle;
import my.kukish.translator.database.entity.SubtitleTranslation;
import my.kukish.translator.database.entity.User;
import my.kukish.translator.database.repository.SubtitleRepository;
import my.kukish.translator.database.repository.UserRepository;
import my.kukish.translator.dto.SubtitleTranslatorCreateEditDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SubtitleTranslatorCreateEditMapper implements Mapper<SubtitleTranslatorCreateEditDto, SubtitleTranslation> {
    private final UserRepository userRepository;
    private final SubtitleRepository subtitleRepository;


    @Override
    public SubtitleTranslation map(SubtitleTranslatorCreateEditDto object) {
        SubtitleTranslation subtitleTranslation = new SubtitleTranslation();
        copy(object, subtitleTranslation);
        return subtitleTranslation;
    }

    private void copy(SubtitleTranslatorCreateEditDto object, SubtitleTranslation subtitleTranslation){
        subtitleTranslation.setSelectedText(object.getSelectedText());
        subtitleTranslation.setSelectedText(object.getTranslatedText());
        subtitleTranslation.setSourceLanguage(object.getSourceLanguage());
        subtitleTranslation.setTargetLanguage(object.getTargetLanguage());
        subtitleTranslation.setTranslatedAt(object.getTranslatedAt());
        subtitleTranslation.setUser(getUser(object.getUserId()));
        subtitleTranslation.setSubtitle(getSubtitle(object.getSubtitleId()));
    }

    @Override
    public SubtitleTranslation map(SubtitleTranslatorCreateEditDto fromObject, SubtitleTranslation toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private Subtitle getSubtitle(Integer subtitleId) {
        return Optional.ofNullable(subtitleId)
                .flatMap(subtitleRepository::findById)
                .orElse(null);
    }

    private User getUser(Long userId) {
        return Optional.ofNullable(userId)
                .flatMap(userRepository::findById)
                .orElse(null);
    }

}
