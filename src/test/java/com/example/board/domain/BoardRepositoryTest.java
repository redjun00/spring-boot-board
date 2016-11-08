package com.example.board.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;//구현한 코딩이 별로 없기 때문에 테스트 할 필요가 없다.. 예시로 만들어준 것 뿐..

    @Before
    public void before(){
        boardRepository.deleteAll();

        Board board1 = new Board("title1", "content1", "writer1", 1111);
        boardRepository.save(board1);

        assertNotNull(boardRepository.findAll());
        assertThat(boardRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    public void testExample(){
    }
}
