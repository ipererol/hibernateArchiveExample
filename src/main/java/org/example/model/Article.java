package org.example.model;

import jakarta.persistence.*;
import org.example.converter.ArticleStatusConverter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "articles", uniqueConstraints = {@UniqueConstraint(columnNames = {"author_id", "title"})})
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String content;
    @Column(nullable = false)
    private Calendar publishDate;

    @Column(nullable = false)
    @Convert(converter = ArticleStatusConverter.class)
    private ArticleStatus status;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    @ManyToMany( fetch = FetchType.EAGER)
    @JoinTable(name = "tags_articles", joinColumns = @JoinColumn(name = "article_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags = new ArrayList<>();

    public Article(){}

    public Article(String articleTitle, String articleContent){
        this.publishDate = Calendar.getInstance();
        this.title = articleTitle;
        this.content = articleContent;
        this.status = ArticleStatus.DRAFT;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Author getAuthor(){
        return author;
    }

    public List<Tag> getTags(){
        return this.tags;
    }

    public void setTags(List<Tag> tags){
        this.tags = tags;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Calendar publishDate) {
        this.publishDate = publishDate;
    }

    public ArticleStatus getStatus() {
        return status;
    }

    public void setStatus(ArticleStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", publishDate=" + publishDate.getTime() +
                ", author=" + author.getName() +
                ", tags=");
        if (!tags.isEmpty()) {
            for (int i = 0; i < tags.size() - 1; i++) {
                result.append("#").append(tags.get(i).getName()).append(",");
            }
            result.append("#").append(tags.getLast().getName());
        } else {
            result.append("No tags");
        }
        result.append(", ").append(status).append("}");
        return result.toString();
    }
}
