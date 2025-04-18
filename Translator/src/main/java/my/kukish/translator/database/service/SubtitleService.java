package my.kukish.translator.database.service;

import lombok.RequiredArgsConstructor;
import my.kukish.translator.Mapper.SubtitleCreateEditMapper;
import my.kukish.translator.Mapper.SubtitleReadMapper;
import my.kukish.translator.database.repository.SubtitleRepository;
import my.kukish.translator.dto.SubtitleCreateEditDto;
import my.kukish.translator.dto.SubtitleReadDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubtitleService {
    private final SubtitleRepository subtitleRepository;
    private final SubtitleCreateEditMapper subtitleCreateEditMapper;
    private final SubtitleReadMapper subtitleReadMapper;

    public List<SubtitleReadDto> findAll() {
        return subtitleRepository.findAll()
                .stream()
                .map(subtitleReadMapper::map)
                .toList();
    }

    public Optional<SubtitleReadDto> findById(Integer id) {
        return subtitleRepository.findById(id)
                .map(subtitleReadMapper::map);
    }

    @Transactional
    public SubtitleReadDto create(SubtitleCreateEditDto subtitleCreateEditDto) {
        return Optional.of(subtitleCreateEditDto)
                .map(subtitleCreateEditMapper::map)
                .map(subtitleRepository::save)
                .map(subtitleReadMapper::map)
                .orElse(null);
    }

    @Transactional
    public Optional<SubtitleReadDto>  update(Integer id, SubtitleCreateEditDto subtitleCreateEditDto) {
        return subtitleRepository.findById(id)
                .map(entity -> subtitleCreateEditMapper.map(subtitleCreateEditDto, entity))
                .map(subtitleRepository::saveAndFlush)
                .map(subtitleReadMapper::map);
    }

    @Transactional
    public boolean delete(Integer id) {
        return subtitleRepository.findById(id)
                .map(entity -> {
                    subtitleRepository.delete(entity);
                    subtitleRepository.flush();
                    return true;
                }).orElse(false);
    }
}
