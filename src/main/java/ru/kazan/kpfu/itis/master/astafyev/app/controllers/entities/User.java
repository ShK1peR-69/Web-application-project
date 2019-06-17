package ru.kazan.kpfu.itis.master.astafyev.app.controllers.entities;

import javax.persistence.*;
import java.util.List;

/*****
 * @author Igor Astafyev
 * May, 2019
 * DB "Users" table
 *****/

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "user_seq", sequenceName = "users_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "secondName")
    private String secondName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @OneToMany(cascade = CascadeType.REFRESH,
            mappedBy = "author")
    private List<Comment> comment;

    @OneToMany(cascade = CascadeType.REFRESH,
            mappedBy = "author")
    private List<Article> article;

    public User() {
    }

    public User(String name, String secondName, String email, String password, String role) {
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
        this.role = role;
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

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public List<Article> getArticle() {
        return article;
    }

    public void setArticle(List<Article> article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
