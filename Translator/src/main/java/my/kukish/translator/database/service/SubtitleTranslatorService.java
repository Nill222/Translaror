package my.kukish.translator.database.service;

import lombok.RequiredArgsConstructor;
import my.kukish.translator.Mapper.SubtitleTranslatorCreateEditMapper;
import my.kukish.translator.Mapper.SubtitleTranslatorReadMapper;
import my.kukish.translator.database.repository.SubtitleTranslationRepository;
import my.kukish.translator.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubtitleTranslatorService {
    private final SubtitleTranslationRepository subtitleTranslationRepository;
    private final SubtitleTranslatorCreateEditMapper subtitleTranslatorCreateEditMapper;
    private final SubtitleTranslatorReadMapper subtitleTranslatorReadMapper;

    public List<SubtitleTranslatorReadDto> findAll() {
        return subtitleTranslationRepository.findAll().stream()
                .map(subtitleTranslatorReadMapper::map)
                .toList();
    }

    public Optional<SubtitleTranslatorReadDto> findById(Integer id) {
        return subtitleTranslationRepository.findById(id)
                .map(subtitleTranslatorReadMapper::map);
    }

    @Transactional
    public SubtitleTranslatorReadDto create(SubtitleTranslatorCreateEditDto subtitleTranslatorCreateEditDto) {
        return Optional.of(subtitleTranslatorCreateEditDto)
                .map(subtitleTranslatorCreateEditMapper::map)
                .map(subtitleTranslationRepository::save)
                .map(subtitleTranslatorReadMapper::map)
                .orElse(null);
    }

    @Transactional
    public Optional<SubtitleTranslatorReadDto> update(Integer id, SubtitleTranslatorCreateEditDto subtitleTranslatorCreateEditDto) {
        return subtitleTranslationRepository.findById(id)
                .map(entity -> subtitleTranslatorCreateEditMapper.map(subtitleTranslatorCreateEditDto, entity))
                .map(subtitleTranslationRepository::saveAndFlush)
                .map(subtitleTranslatorReadMapper::map);
    }

    @Transactional
    public boolean delete(Integer id) {
        return subtitleTranslationRepository.findById(id)
                .map(entity -> {
                    subtitleTranslationRepository.delete(entity);
                    subtitleTranslationRepository.flush();
                    return true;
                }).orElse(false);
    }
}
