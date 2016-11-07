package com.example.board.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Board {

    @Id
    @GeneratedValue
    private int seq;

    @Length(min=2, message="제목은 2자 이상 입력하세요.")
    private String title;
    @NotEmpty(message = "내용을 입력하세요.")
    private String content;
    @NotEmpty(message = "작성자를 입력하세요.")
    private String writer;
    private int password;
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;
    private int cnt;

    public Board() { }

    public Board(String title, String content, String writer, int password) {
        super();
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.password = password;
        this.cnt = 0;
    }

}
