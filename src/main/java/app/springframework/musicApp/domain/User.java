package app.springframework.musicApp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.*;

@Entity
public class User {
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String names,lastnames,email,password;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date birthdate;

    @ManyToOne
    private Document document;


    @ManyToMany(mappedBy = "users")
    private Set<Song> songs = new HashSet<>();

    public User(String names, String lastnames, String email, String password, Date birthdate) {
        this.names = names;
        this.lastnames = lastnames;
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
    }

    public User() {
    }

    //@JsonBackReference(value = "song-user")
    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastnames() {
        return lastnames;
    }

    public void setLastnames(String lastnames) {
        this.lastnames = lastnames;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @JsonBackReference(value = "user-document")
    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id != null ? id.equals(user.id) : user.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
