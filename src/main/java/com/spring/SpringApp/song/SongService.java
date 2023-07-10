package com.spring.SpringApp.song;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SongService {
    private final SongRepository songRepository;

    public List<Song> getSongs() {
        return songRepository.findAll();
    }

    public List<Song> getSongsShort() {
        return songRepository.findSongsShort();
    }

    public Song getSongById(Long id) {
        return songRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Song is not found!")
        );
    }

    public void addSong(Song song) {
        songRepository.save(song);
    }

    public List<Song> searchSong(String search) {
        return songRepository.searchSong(search);
    }

    public void deleteSong(Long id) {
        if (songRepository.existsById(id))
            songRepository.deleteById(id);
        else throw new IllegalStateException("Song with id " + id + " does not exist.");
    }
}
