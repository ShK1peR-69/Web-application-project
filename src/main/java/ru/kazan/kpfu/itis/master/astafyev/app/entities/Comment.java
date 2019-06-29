package ru.kazan.kpfu.itis.master.astafyev.app.entities;

import javax.persistence.*;

/*****
 * @author Igor Astafyev
 * May, 2019
 * DB "Comments" table
 *****/

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "comment_seq", sequenceName = "comments_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "comment_seq")
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "date")
    private String date;

    @ManyToOne
            (cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "author")
    private User author;

    @ManyToOne
            (cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "article")
    private Article article;

    public Comment() {
    }

    public Comment(String text, String date, User author, Article article) {
        this.text = text;
        this.date = date;
        this.author = author;
        this.article = article;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", author=" + author +
                ", article=" + article +
                '}';
    }
}