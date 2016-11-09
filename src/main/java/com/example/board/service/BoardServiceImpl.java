package com.example.board.service;

import com.example.board.domain.Board;
import com.example.board.domain.BoardRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
//괄호 속 문자열은 식별자를 의미한다.
//괄호를 생략할 경우 클래스명 그대로 사용한다.
//따라서 ,같은 클래스명이 존재 할 시 같은 식별자가 생성되기때문에 에러가 발생한다.
public class BoardServiceImpl implements BoardService{

    @Resource
    private BoardRepository boardRepository;

    @Override
    public List<Board> list() {
        return boardRepository.findAll();
    }

    @Override
    public boolean delete(Board board) {

        Board finedPost = boardRepository.findOne(board.getSeq());
        if(finedPost.getPassword() == board.getPassword()){
            boardRepository.delete(board);
            return true;
        }
        return false;
    }

    @Override
    public Board edit(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public Board write(Board board) {
        board.setRegDate(new Date());
        return boardRepository.save(board);
    }

    @Override
    public Board read(int seq) {
        Board board = boardRepository.findOne(seq);
        board.setCnt(board.getCnt() + 1);
        boardRepository.save(board);
        return board;
    }
}
