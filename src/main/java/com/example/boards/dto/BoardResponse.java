package com.example.boards.dto;

import com.example.boards.domain.entity.Board;

public record BoardResponse (
        String id,
        String boardName,
        String text
){
    public static BoardResponse from(Board board){
        return new BoardResponse(
                board.getId().toString(),
                board.getBoardName(),
                board.getText()
        );
    }
}
