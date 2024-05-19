package com.example.NoticeBoardHongpark.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor   // 기본 생성자 추가 어노테이션
@ToString
@Entity
public class Article {
    @Id   // 엔티티 대표값(기본키)
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // 자동 생성 기능 (자동으로 1씩 증가)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    // @Getter로 생략 가능
    public Long getId() {
        return id;
    }

    public void patch(Article article) {
        if(article.title != null) this.title = article.title;
        if(article.content != null) this.content = article.content;
    }

    /*
    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
     */


}
