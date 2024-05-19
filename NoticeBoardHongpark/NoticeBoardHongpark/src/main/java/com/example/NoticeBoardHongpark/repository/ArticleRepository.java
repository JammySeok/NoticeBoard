package com.example.NoticeBoardHongpark.repository;

import com.example.NoticeBoardHongpark.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

// CrudRepository : JPA에서 제공하는 인터페이스. 엔티티를 관리(생성, 조회, 수정, 삭제)할 수 있다.
public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    ArrayList<Article> findAll();  // Iterable -> ArrayList 수정
}


