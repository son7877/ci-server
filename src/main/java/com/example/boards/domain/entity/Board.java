package com.example.boards.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "BOARD")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {
    @Id
    @GeneratedValue
    @Column(name = "BOARD_ID")
    private UUID id;

    @Column(name="BOARD_NAME")
    private String boardName;

    @Column(name="BOARD_TEXT")
    private String text;
}
