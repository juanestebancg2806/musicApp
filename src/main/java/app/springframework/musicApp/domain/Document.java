package app.springframework.musicApp.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.*;

import javax.persistence.*;

@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String name;

    @OneToMany
    @JoinColumn(name="document_id")
    private Set<User> users = new HashSet<>();

    public Document(String name) {
        this.name = name;
    }
    public Document(){

    }

    @JsonManagedReference(value = "user-document")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> usersId) {
        this.users = usersId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document document = (Document) o;

        return id != null ? id.equals(document.id) : document.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
