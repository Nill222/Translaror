package my.kukish.translator.database.service;

import lombok.RequiredArgsConstructor;
import my.kukish.translator.Mapper.MovieCreateEditMapper;
import my.kukish.translator.Mapper.MovieReadMapper;
import my.kukish.translator.database.repository.MovieRepository;
import my.kukish.translator.dto.MovieCreateEditDto;
import my.kukish.translator.dto.MovieReadDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieReadMapper movieReadMapper;
    private final MovieCreateEditMapper movieCreateEditMapper;

    public List<MovieReadDto> findAll() {
        return movieRepository.findAll()
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
                .map(movieCreateEditMapper::map)
                .map(movieRepository::save)
                .map(movieReadMapper::map)
                .orElse(null);
    }

    @Transactional
    public Optional<MovieReadDto>  update(Long id, MovieCreateEditDto movieCreateEditDto) {
        return movieRepository.findById(id)
                .map(entity -> movieCreateEditMapper.map(movieCreateEditDto, entity))
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
}
