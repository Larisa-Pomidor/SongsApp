package com.spring.SpringApp.text;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/texts")
@AllArgsConstructor
public class TextController {
    private TextService textService;

    @GetMapping
    public List<Text> getTexts() {
        return textService.getTexts();
    }

    @GetMapping(path="{path}")
    public List<String> getTextByPath(@PathVariable("path") String path) {
        return textService.getTextByPath(path);
    }

    @PostMapping(path="{id}")
    public void addText(@PathVariable("id") Long id, @RequestBody Text text) {
        textService.addText(id, text);
    }

    @DeleteMapping(path="{id}")
    public void deleteText(@PathVariable("id") Long id) {
        textService.deleteText(id);
    }
}

