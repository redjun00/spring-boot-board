package com.example.board.service;

import com.example.board.domain.Board;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface BoardService {

    public abstract List<Board> list();

    public abstract boolean delete(Board board);

    public abstract Board edit(Board board);

    public abstract Board write(Board board);

    public abstract Board read(int seq);
}
