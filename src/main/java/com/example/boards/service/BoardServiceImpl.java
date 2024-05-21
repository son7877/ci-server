package com.example.boards.service;

import com.example.boards.domain.entity.Board;
import com.example.boards.domain.repository.BoardRepository;
import com.example.boards.dto.BoardRequest;
import com.example.boards.dto.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public void addBoard(BoardRequest req) {
        Board board = req.toEntity();
        boardRepository.save(board);
    }

    @Override
    public List<BoardResponse> getBoard() {
        return boardRepository.findAll().stream()
                .map(BoardResponse::from)
                .toList();
    }

    @Override
    public BoardResponse getBoardById(String boardId) {
        return boardRepository.findById(UUID.fromString(boardId))
                .map(BoardResponse::from)
                .orElseThrow();
    }
}
