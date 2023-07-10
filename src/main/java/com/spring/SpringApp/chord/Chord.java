package com.spring.SpringApp.chord;

import com.fasterxml.jackson.annotation.*;
import com.spring.SpringApp.song.Song;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="`chords`")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Chord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    @JsonIgnore
    @ManyToMany(mappedBy = "chords")
    private Set<Song> songs;

    public Chord(String name, String image, Set<Song> songs) {
        this.name = name;
        this.image = image;
        this.songs = songs;
    }
}
