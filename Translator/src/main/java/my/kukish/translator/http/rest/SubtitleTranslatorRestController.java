package my.kukish.translator.http.rest;

import lombok.RequiredArgsConstructor;
import my.kukish.translator.database.service.SubtitleTranslatorService;
import my.kukish.translator.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subtitleT")
@RequiredArgsConstructor
public class SubtitleTranslatorRestController {

    private final SubtitleTranslatorService subtitleTranslatorService;

    @GetMapping
    public List<SubtitleTranslatorReadDto> findAll() {
        return subtitleTranslatorService.findAll();
    }

    @GetMapping("/id")
    public SubtitleTranslatorReadDto findById(@PathVariable("id") Integer id) {
        return subtitleTranslatorService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public SubtitleTranslatorReadDto crate(@Validated @RequestBody SubtitleTranslatorCreateEditDto subtitleTranslator) {
        return subtitleTranslatorService.create(subtitleTranslator);
    }

    @PutMapping("/id")
    public SubtitleTranslatorReadDto update(@PathVariable("id") Integer id, @Validated @RequestBody SubtitleTranslatorCreateEditDto subtitleTranslator) {
        return subtitleTranslatorService.update(id, subtitleTranslator)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @DeleteMapping("/id")
    public void delete(@PathVariable("id") Integer id) {
        if(!subtitleTranslatorService.delete(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}

