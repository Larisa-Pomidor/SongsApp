package com.spring.SpringApp.song;

import com.spring.SpringApp.text.Text;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path="api/v1/songs")
@AllArgsConstructor
public class SongController {
    private SongService songService;
    private HttpServletRequest request;

    @GetMapping
    public List<Song> getSongs() {
        return songService.getSongs();
    }

    @GetMapping("/search")
    public List<Song> searchSongs(@RequestParam("like") String search) {
        return songService.searchSong(search);
    }

    @GetMapping("/mix")
    public List<Song> mixSongs() {
        List<Song> songs = songService.getSongs();
        Collections.shuffle(songs);
        return songs;
    }

    @GetMapping(path = "/short")
    public List<Song> getSongsShort() {
        return songService.getSongsShort();
    }

    @GetMapping(path = "{id}")
    public Song getSongById(@PathVariable("id") Long id) {
        return songService.getSongById(id);
    }

    @PostMapping
    public void addSong(@RequestBody Song song) {
        songService.addSong(song);
    }

    @PostMapping(path="/add")
    public void addSongText(@ModelAttribute MultipartFile file,
                            @RequestParam("name") String name,
                            @RequestParam("author") String author,
                            @RequestParam("keys") String keys) throws IOException {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
       // String fileName = date + file.getOriginalFilename();

        String folderPath = "src/main/resources/static";
        String filePath = folderPath + File.separator + date;

        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        Song song = new Song(name, author);
        song.setTexts(Collections.singletonList(new Text(date, keys)));
        songService.addSong(song);
    }

    @DeleteMapping(path="{id}")
    public void deleteSong(@PathVariable("id") Long id) {
        songService.deleteSong(id);
    }

}
