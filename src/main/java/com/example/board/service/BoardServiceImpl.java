package com.example.board.service;

import com.example.board.domain.Board;
import com.example.board.domain.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

//TODO transactional 확인해보자.
@Service
public class BoardServiceImpl implements BoardService{

    @Resource
    private BoardRepository boardRepository;

    public BoardRepository getBoardRepository(){
        return boardRepository;
    }

    public void setBoardRepository(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

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
