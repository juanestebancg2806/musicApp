package app.springframework.musicApp.domain;

import javax.persistence.*;
import java.util.*;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Integer duration;

    @ManyToOne
    private Genre genre;

    @ManyToMany
    @JoinTable(name="user_song",joinColumns = @JoinColumn(name="song_id"),inverseJoinColumns = @JoinColumn(name="user_id"))
    private Set<User> users = new HashSet<>();


    @ManyToOne
    private Author author;

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Song(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public Song(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        return id != null ? id.equals(song.id) : song.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


}
