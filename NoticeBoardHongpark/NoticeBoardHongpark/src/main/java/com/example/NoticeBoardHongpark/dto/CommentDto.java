package com.example.NoticeBoardHongpark.dto;

import com.example.NoticeBoardHongpark.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor   // 모든 필드를 매개변수로 갖는 생성자 자동 생성
@NoArgsConstructor   // 매개변수가 아예 없는 기본 생성자 자동 생성
@Getter   // 각 필드 값을 조회할 수 있는 getter 메서드 자동 생성
@ToString   // 모든 필드를 출력할 수 있는 toString 메서드 자동 생성
public class CommentDto {
    private Long id;   // 댓글의 id
//    @JsonProperty("article_id")
    private Long articleId;   // 댓글의 부모 id
    private String nickname;   // 댓글 작성자
    private String body;   // 댓글 본문

    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getArticle().getId(),
                comment.getNickname(),
                comment.getBody()
        );
    }
}
