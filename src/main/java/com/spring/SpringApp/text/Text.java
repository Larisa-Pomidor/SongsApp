package com.spring.SpringApp.text;

import com.spring.SpringApp.song.Song;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="texts")
public class Text {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @Column(name = "`keys`")
    private String keys;
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "song_id"
    )
    private Song song;

    public Text (String content, Song song, String keys) {
        this.content = content;
        this.song = song;
        this.keys = keys;
    }

    public Text (String content, String keys) {
        this.content = content;
        this.keys = keys;
    }
}
