package com.example.board.Service;

import com.example.board.domain.Board;
import com.example.board.domain.BoardRepository;
import com.example.board.service.BoardServiceImpl;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardServiceImplTest {

    @Autowired
    private BoardServiceImpl boardServiceImpl;

    @Autowired
    private BoardRepository boardRepository;

    @Before
    public void before(){
        boardRepository.deleteAll();
        assertThat(boardRepository).isNotNull();
        assertThat(boardRepository.count()).isZero();
    }


    @Test
    public void list_success_when_empty(){
        boardRepository.deleteAll();
        List<Board> actualList = boardServiceImpl.list();
        assertThat(actualList).isEqualTo(Lists.emptyList());
    }

    @Test
    public void list_success(){
        //setup
        final Board board1 = new Board("title1", "content1", "writer1", 1111);
        final Board board2 = new Board("title2", "content2", "writer2", 2222);

        boardRepository.save(board1);
        boardRepository.save(board2);

        assertNotNull(boardRepository.findAll());
        assertThat(boardRepository.findAll().size()).isEqualTo(2);

        //test
        List<Board> actualList = boardServiceImpl.list();

        //assert
        assertThat(actualList).hasSize(2).contains(board1).contains(board2);
    }

    @Test//TODO edit는 @MockBean을 써서 만들기 불가능해보이는데.. 가능하려나..?
    public void edit_success(){

        //setup
        final Board board1 = new Board("title1", "content1", "writer1", 1111);
        final Board board2 = new Board("title2", "content2", "writer2", 2222);

        boardRepository.save(board1);
        boardRepository.save(board2);

        assertNotNull(boardRepository.findAll());
        assertThat(boardRepository.findAll().size()).isEqualTo(2);

        //test
        Board expectedBoard = new Board("modifiedT", "modifiedC", "modifiedW", 3333);
        expectedBoard.setSeq(board1.getSeq());
        Board actualBoard = boardServiceImpl.edit(expectedBoard);

        //assert
        assertThat(actualBoard)
                .isNotNull()
                .isEqualTo(expectedBoard);

        List<Board> actualBoardList = boardServiceImpl.list();
        assertThat(actualBoardList)
                .isNotNull()
                .isNotEmpty()
                .hasSize(2)
                .doesNotContain(board1)
                .contains(board2)
                .contains(expectedBoard);
    }
}
