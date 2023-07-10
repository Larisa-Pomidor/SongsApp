package com.spring.SpringApp.chord;

import com.spring.SpringApp.song.Song;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChordService {
    private final ChordRepository chordRepository;

    public List<Chord> getChords() { return chordRepository.findAll(); }

    public void addChord(Chord chord) {
        chordRepository.save(chord);
    }

    public void deleteChord(Long id) {
        if (chordRepository.existsById(id))
            chordRepository.deleteById(id);
        else {
            throw new IllegalStateException("Chord with id " + id + " does not exist.");
        }
    }
}
