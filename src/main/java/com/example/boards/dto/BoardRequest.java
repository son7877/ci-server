package com.example.boards.dto;

import com.example.boards.domain.entity.Board;

public record BoardRequest (
        String boardName,
        String text
){
    public Board toEntity(){
        return Board.builder()
                .boardName(boardName)
                .text(text)
                .build();
    }
}
