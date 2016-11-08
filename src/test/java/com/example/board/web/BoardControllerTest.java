package com.example.board.web;

import com.example.board.service.BoardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(BoardController.class)
public class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoardService boardService;//TODO MockBean을 이용하여 리퀘스트의 받은 내용이 정상 동작하는지

    @Test
    public void board_list_success() throws Exception {
        MvcResult result = mockMvc.perform(get("/board/list").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("/board/list"))
                .andDo(print())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        assertThat(content).isNotEmpty();
        System.out.println("content=" + content);//TODO 어떻게 검증할 수 있을까 보자.

    }

    @Test
    public void board_list_post_request_fail() throws Exception {
        MvcResult result = mockMvc.perform(post("/board/list").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isMethodNotAllowed())
                .andDo(print())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertThat(content).isEmpty();
    }
}
