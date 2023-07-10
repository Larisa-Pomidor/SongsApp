package com.spring.SpringApp.song;

import com.fasterxml.jackson.annotation.*;
import com.spring.SpringApp.chord.Chord;
import com.spring.SpringApp.text.Text;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name="songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "`name`")
    private String name;
    @Column(name = "`author`")
    private String author;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name= "song_chord",
    joinColumns = @JoinColumn(name="song_id"),
    inverseJoinColumns = @JoinColumn(name = "chord_id"))
    private Set<Chord> chords;

    @OneToMany(mappedBy = "song")
    private List<Text> texts;

    public Song(String name,
                String author,
                Set<Chord> chords) {
        this.name = name;
        this.author = author;
        this.chords = chords;
    }

    public Song(String name,
                String author,
                Set<Chord> chords,
                List<Text> texts) {
        this.name = name;
        this.author = author;
        this.chords = chords;
        this.texts = texts;
    }

    public Song(Long id, String name,
                String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public Song(String name,
                String author) {
        this.name = name;
        this.author = author;
    }
}
