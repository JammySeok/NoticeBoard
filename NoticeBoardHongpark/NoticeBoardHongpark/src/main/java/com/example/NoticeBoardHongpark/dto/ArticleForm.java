package com.example.NoticeBoardHongpark.dto;

import com.example.NoticeBoardHongpark.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {
    private Long id;
    private String title;
    private String content;

    /*
    // 롬복(@AllArgsConstructor) 생략 가능
    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }
     */

    /*
    // 롬복(@ToString) 생략 가능
    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
     */

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
