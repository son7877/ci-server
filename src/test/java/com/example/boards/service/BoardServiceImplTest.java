package com.example.boards.service;

import com.example.boards.domain.entity.Board;
import com.example.boards.domain.repository.BoardRepository;
import com.example.boards.dto.BoardRequest;
import com.example.boards.dto.BoardResponse;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceImplTest {

    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardRepository boardRepository;


    // test git
    @Nested
    class 생성{
        @Test
        void addBoard(){
            // given
            BoardRequest req = new BoardRequest(
                    "hongbeom",
                    "content"
            );
            // when
            boardService.addBoard(req);
            // then
            assertEquals(1, boardRepository.findAll().size());
            assertEquals("hongbeom", boardRepository.findAll().get(0).getBoardName());
            assertEquals("content", boardRepository.findAll().get(0).getText());
        }
        @Test
        void BuilderTest() {
            Board board = Board.builder()
                    .boardName("hongbeom")
                    .text("content")
                    .build();
            boardRepository.save(board);
            assertEquals(1, boardRepository.findAll().size());
            assertEquals("hongbeom", boardRepository.findAll().get(0).getBoardName());
            assertEquals("content", boardRepository.findAll().get(0).getText());
        }
    }
    @Nested
    class 조회{
        @Test
        void getBoard(){
            // given
            BoardRequest req1 = new BoardRequest(
                    "hongbeom",
                    "content"
            );
            BoardRequest req2 = new BoardRequest(
                    "hongbeom2",
                    "content2"
            );
            boardService.addBoard(req1);
            boardService.addBoard(req2);
            List<BoardResponse> list = boardService.getBoard();
            // then
            assertEquals(2, list.size());
        }

        @Test
        void getBoardById(){
            // given
            BoardRequest req = new BoardRequest(
                    "hongbeom",
                    "content"
            );
            boardService.addBoard(req);
            // when
            List<BoardResponse> list = boardService.getBoard();
            String boardId = list.get(0).id();
            BoardResponse boardResponse = boardService.getBoardById(boardId);
            // then
            assertEquals(boardId, boardResponse.id());
            assertEquals("hongbeom", boardResponse.boardName());
            assertEquals("content", boardResponse.text());
        }
    }

    @Nested
    class 삭제{
        @Test
        void 아이디가있을때(){
            // given
            Board save = boardRepository.save(Board.builder()
                    .boardName("hongbeom")
                    .text("content")
                    .build());
            String boardId = save.getId().toString();
            // when
            boardService.deleteBoard(boardId);
            // then
            assertEquals(0, boardRepository.findAll().size());
        }

        @Test
        void 아이디가없을때(){
            // given
            BoardRequest req = new BoardRequest(
                    "hongbeom",
                    "content"
            );
            boardService.addBoard(req);
            String wrongId = UUID.randomUUID().toString();
            // when && then
            assertThrows(IllegalArgumentException.class, ()
                    -> boardService.deleteBoard(wrongId));
        }
    }
}