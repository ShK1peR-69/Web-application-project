package ru.kazan.kpfu.itis.master.astafyev.app.controllers.entities;

import javax.persistence.*;
import java.util.List;

/*****
 * @author Igor Astafyev
 * May, 2019
 * DB "Articles" table
 *****/

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "article_seq", sequenceName = "articles_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "article_seq")
    private Long id;

    @Column(name = "sport")
    private String sport;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "image")
    private String image;

    @Column(name = "date")
    private String date;

    @Column(name = "source")
    private String source;

    @ManyToOne
            (cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "author")
    private User author;

    @OneToMany(cascade = CascadeType.REFRESH,
            mappedBy = "article")
    private List<Comment> comment;

    public Article() {
    }

    public Article(String sport, String title, String text, String image, String date, String source, User author) {
        this.sport = sport;
        this.title = title;
        this.text = text;
        this.image = image;
        this.date = date;
        this.source = source;
        this.author = author;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", image='" + image + '\'' +
                ", date=" + date +
                ", source=" + source + '\'' +
                ", author=" + author +
                '}';
    }
}