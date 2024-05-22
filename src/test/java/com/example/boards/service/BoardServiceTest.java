package com.example.boards.service;

import com.example.boards.domain.entity.Board;
import com.example.boards.domain.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @InjectMocks
    private BoardServiceImpl boardService;
    @Mock
    private BoardRepository boardRepository;

    @Test
    void deleteBoard() {
        // given
        UUID boardId = UUID.randomUUID();
        BDDMockito.doNothing().when(boardRepository).delete(BDDMockito.any(Board.class));
        BDDMockito.given(boardRepository.findById(boardId))
                .willReturn(Optional.of(
                        new Board(boardId, "hongbeom", "content")));

        // when
        boardService.deleteBoard(boardId.toString());

        // then
        BDDMockito.then(boardRepository).should().delete(BDDMockito.any(Board.class));
    }


    @Test
    void deleteBoardFail(){
        // given
        UUID boardId = UUID.randomUUID();
        BDDMockito.given(boardRepository.findById(boardId))
                .willReturn(Optional.empty());
        // when
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, ()
                -> boardService.deleteBoard(boardId.toString()));
        // then
        assertEquals(null, e.getMessage());
    }
}