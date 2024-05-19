package com.example.NoticeBoardHongpark.controller;

import com.example.NoticeBoardHongpark.dto.ArticleForm;
import com.example.NoticeBoardHongpark.dto.CommentDto;
import com.example.NoticeBoardHongpark.entity.Article;
import com.example.NoticeBoardHongpark.repository.ArticleRepository;
import com.example.NoticeBoardHongpark.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Slf4j   // 로깅 기능
@Controller   // 컨트룰러 선언
public class ArticleController {
    @Autowired   // 스프링 부트가 미리 생성해 놓은 리파지터리 객체 주입(DI)
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;   // 서비스 객체 주입
    
    @GetMapping("/articles/new")   // URL 요청 접수
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        log.info(form.toString());

        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity();   // new Article(id, title, content)
        log.info(article.toString());
        // 2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {   // 매개변수로 id 받아오기
        log.info("id = " + id);

        // 1. id를 조회해 데이터 가져오기
        //  Optional<Article> articleEntity = articleRepository.findById(id);
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentsDtos = commentService.comments(id);
        
        // 2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);  // article은 show.mustache에서 {{#article}}
        model.addAttribute("commentDtos", commentsDtos);   // 댓글 목록 모델에 등록

        // 3. 뷰 페이지 반환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        // 1. 모든 데이터 가져오기
        // Iterable<Article> articleEntityList = articleRepository.findAll();
        ArrayList<Article> articleEntityList = articleRepository.findAll();   // ArticleRepository에서 findAll 메소드 추가

        // 2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);   // articleList는 index.mustache에서 {{#articleList}}

        // 3. 뷰 페이지 설정하기
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        // 수정할 페이지 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        // 뷰 페이지 설정하기
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        log.info(form.toString());
        // 1. DTO를 엔티티로 변환
        Article articleEntity = form.toEntity();   // DTO를 엔티티로 변환
        log.info(articleEntity.toString());
        // 2. 엔티티를 BD에 저장하기
        // 2-1. DB에서 기존 데이터 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        // 2-2. 기존 데이터 값을 갱신하기`
        if (target != null) {
            articleRepository.save(articleEntity);
        }

        // 3. 수정 결과 페이지로 리다이렉트하기
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        log.info("삭제 요청이 들어왔습니다.");
        // 1. 삭제할 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        // 2. 엔티티 대상 삭제하기
        if(target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제되었습니다.");
        }
        // 3. 결과 페이지로 리다이렉트
        return "redirect:/articles";
    }
}
