package com.spring.SpringApp.text;

import com.spring.SpringApp.song.Song;
import com.spring.SpringApp.song.SongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class TextService {
    private final TextRepository textRepository;
    private final SongRepository songRepository;

    public List<Text> getTexts() {
        return textRepository.findAll();
    }

    public void addText(Long id, Text text) {
        Song song = songRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("Song with id " + id + " does not exist."));
        text.setSong(song);
        textRepository.save(text);
    }

    public List<String> getTextByPath(String path) {
        try (Stream<String> stream = Files.lines(Paths.get("src/main/resources/static/" + path))) {
            return stream.collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteText(Long id) {
        if (textRepository.existsById(id))
            textRepository.deleteById(id);
        else {
            throw new IllegalStateException("Text with id " + id + " does not exist.");
        }
    }
}
