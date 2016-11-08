package com.example.board.Service;

import com.example.board.domain.Board;
import com.example.board.domain.BoardRepository;
import com.example.board.service.BoardServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardServiceImplTest_byMockBean {

    @MockBean//테스트 대상과 연관된 클래스를 Mock으로 만든다.
    private BoardRepository mockBoardRepository;

    @Autowired
    private BoardServiceImpl boardServiceImpl;

    @Test
    public void list_success(){ //BoardServiceImplTest 클래스에 있는 동명 메소드와 동일한 기능이다. 다만 MockBean을 사용해서 만들어 보았다.
        final Board board1 = new Board("title1", "content1", "writer1", 1111);
        final Board board2 = new Board("title2", "content2", "writer2", 2222);
        given(mockBoardRepository.findAll()).willReturn(Arrays.asList(board1, board2));

        List<Board> actualBoardList = boardServiceImpl.list();

        assertThat(actualBoardList).isEqualTo(Arrays.asList(board1, board2));
    }
}
