package com.example.boards.controller;

import com.example.boards.dto.BoardRequest;
import com.example.boards.dto.BoardResponse;
import com.example.boards.service.BoardService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.UUID;

@WebMvcTest(BoardController.class)
class BoardControllerTest {

    @MockBean // 가짜 객체를 만들어서 주입시킴
    private BoardService boardService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void addBoard() {
        // given
        BoardRequest boardRequest = new BoardRequest("hongbeom", "content");


        // when

        // then

    }

    @Test
    void getBoard() throws Exception {
        // given
        // boardService.getBoard() -> List<BoardResponse>
            // BDD: business driven development (비즈니스 로직에 맞게 테스트 코드 개발)
            // EDA: event driven architecture (데이터 분석에 많이 사용)
            // TDD: test driven development (테스트 코드를 먼저 작성)
            // monolithic: 하나로 모든 것을 처리
            // MSA: 여러 개로 나눠서 처리
        BDDMockito.given(boardService.getBoard())
                .willReturn(List.of(
                        new BoardResponse(UUID.randomUUID().toString(),"hongbeom", "content"),
                        new BoardResponse(UUID.randomUUID().toString(),"hongbeom2", "content2"),
                        new BoardResponse(UUID.randomUUID().toString(),"hongbeom3", "content3")
                ));
        // when
        // then
        // static import 필요
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/boards"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].boardName", Matchers.is("hongbeom")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].text", Matchers.is("content")));
    }

    @Test
    void getBoardById() throws Exception {
        // given
        String boardId = UUID.randomUUID().toString();
        BDDMockito.given(boardService.getBoardById(boardId))
                .willReturn(new BoardResponse(boardId, "hongbeom", "content"));
        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/boards/" + boardId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.boardName", Matchers.is("hongbeom")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.text", Matchers.is("content")));
    }

}