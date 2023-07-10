package com.spring.SpringApp.chord;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/chords")
@AllArgsConstructor
public class ChordController {
    private ChordService chordService;

    @GetMapping
    public List<Chord> getChords() {
        return chordService.getChords();
    }

    @PostMapping
    public void addChord(@RequestBody Chord chord) {
        chordService.addChord(chord);
    }

    @DeleteMapping(path="{id}")
    public void deleteChord(@PathVariable("id") Long id) {
        chordService.deleteChord(id);
    }
}
