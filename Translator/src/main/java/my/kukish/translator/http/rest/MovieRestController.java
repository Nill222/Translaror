package my.kukish.translator.http.rest;

import lombok.RequiredArgsConstructor;
import my.kukish.translator.database.entity.Movie;
import my.kukish.translator.database.service.MovieService;
import my.kukish.translator.database.service.UserService;
import my.kukish.translator.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movie")
@RequiredArgsConstructor
public class MovieRestController {

    private final MovieService movieService;

    @GetMapping
    public List<MovieReadDto> findAll() {
        return movieService.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MovieReadDto>> findByTitle(@PathVariable("title") String title, Pageable pageable) {
        List<MovieReadDto> result = movieService.findByTitle(title, pageable);

        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of());
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/id")
    public MovieReadDto findById(@PathVariable("id") Long id) {
        return movieService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public MovieReadDto crate(@Validated @RequestBody MovieCreateEditDto movie) {
        return movieService.create(movie);
    }

    @PutMapping("/id")
    public MovieReadDto update(@PathVariable("id") Long id, @Validated @RequestBody MovieCreateEditDto movie) {
        return movieService.update(id, movie)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    }

    @DeleteMapping("/id")
    public void delete(@PathVariable("id") Long id) {
        if(!movieService.delete(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}

