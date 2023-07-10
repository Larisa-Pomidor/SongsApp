package com.spring.SpringApp.song;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    @Query("SELECT NEW com.spring.SpringApp.song.Song(s.id, s.name, s.author) FROM Song s")
    public List<Song> findSongsShort();

    public Optional<Song> findById (Long id);

    @Query("SELECT s FROM Song s WHERE s.name LIKE %:search% OR s.author LIKE %:search%")
    List<Song> searchSong(@Param("search") String search);
}
