package my.kukish.translator.http.rest;

import lombok.RequiredArgsConstructor;
import my.kukish.translator.database.service.SubtitleService;
import my.kukish.translator.dto.SubtitleCreateEditDto;
import my.kukish.translator.dto.SubtitleReadDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subtitle")
@RequiredArgsConstructor
public class SubtitleRestController {

    private final SubtitleService subtitleService;

    @GetMapping
    public List<SubtitleReadDto> findAll() {
        return subtitleService.findAll();
    }

    @GetMapping("/id")
    public SubtitleReadDto findById(@PathVariable("id") Integer id) {
        return subtitleService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public SubtitleReadDto crate(@Validated @RequestBody SubtitleCreateEditDto subtitle) {

        return subtitleService.create(subtitle);
    }

    @PutMapping("/id")
    public SubtitleReadDto update(@PathVariable("id") Integer id, @Validated @RequestBody SubtitleCreateEditDto subtitle) {
        return subtitleService.update(id, subtitle)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @DeleteMapping("/id")
    public void delete(@PathVariable("id") Integer id) {
        if(!subtitleService.delete(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
