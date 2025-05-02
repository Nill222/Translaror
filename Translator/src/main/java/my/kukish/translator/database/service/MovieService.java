package my.kukish.translator.database.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import my.kukish.translator.Mapper.MovieCreateEditMapper;
import my.kukish.translator.Mapper.MovieReadMapper;
import my.kukish.translator.Mapper.UserCreateEditMapper;
import my.kukish.translator.database.entity.Movie;
import my.kukish.translator.database.repository.MovieRepository;
import my.kukish.translator.dto.MovieCreateEditDto;
import my.kukish.translator.dto.MovieReadDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieReadMapper movieReadMapper;
    private final VideoService videoService;
    private final MovieCreateEditMapper movieCreateEditMapper;
    private final UserCreateEditMapper userCreateEditMapper;

    public List<MovieReadDto> findAll() {
        return movieRepository.findAll()
                .stream()
                .map(movieReadMapper::map)
                .toList();
    }

    public List<MovieReadDto> findByTitle(String title, Pageable pageable) {
        return movieRepository.findByTitleContainingIgnoreCase(title, pageable)
                .stream()
                .map(movieReadMapper::map)
                .toList();
    }

    public Optional<MovieReadDto>  findById(Long id) {
        return movieRepository.findById(id)
                .map(movieReadMapper::map);
    }

    @Transactional
    public MovieReadDto create(MovieCreateEditDto movieCreateEditDto) {
        return Optional.of(movieCreateEditDto)
                .map(dto -> {
                    uploadVideo(dto.getVideoUrl());
                    return movieCreateEditMapper.map(dto);
                })
                .map(movieRepository::save)
                .map(movieReadMapper::map)
                .orElse(null);
    }

    @Transactional
    public Optional<MovieReadDto>  update(Long id, MovieCreateEditDto movieCreateEditDto) {
        return movieRepository.findById(id)
                .map(entity -> {
                    uploadVideo(movieCreateEditDto.getVideoUrl());
                    return movieCreateEditMapper.map(movieCreateEditDto, entity);
                })
                .map(movieRepository::saveAndFlush)
                .map(movieReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return movieRepository.findById(id)
                .map(entity -> {
                    movieRepository.delete(entity);
                    movieRepository.flush();
                    return true;
                }).orElse(false);
    }
    @SneakyThrows
    private void uploadVideo(MultipartFile videoUrl) {
        if(!videoUrl.isEmpty()) {
            videoService.upload(videoUrl.getOriginalFilename(),
                    videoUrl.getInputStream());
        }
    }

    public Optional<byte[]> findVideo(Long id) {
        return movieRepository.findById(id)
                .map(Movie::getVideoUrl)
                .filter(StringUtils::hasText)
                .flatMap(videoService::get);
    }
}
