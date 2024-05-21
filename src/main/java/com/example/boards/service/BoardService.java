package com.example.boards.service;

import com.example.boards.dto.BoardRequest;
import com.example.boards.dto.BoardResponse;

import java.util.List;

public interface BoardService {
    void addBoard(BoardRequest req);
    List<BoardResponse> getBoard();
    BoardResponse getBoardById(String boardId);
    void deleteBoard(String boardId);
}
