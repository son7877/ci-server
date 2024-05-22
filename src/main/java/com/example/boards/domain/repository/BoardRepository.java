package com.example.boards.domain.repository;

import com.example.boards.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface BoardRepository
        extends JpaRepository<Board, UUID> {
}
