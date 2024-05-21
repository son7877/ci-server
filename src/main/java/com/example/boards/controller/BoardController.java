package com.example.boards.controller;

import com.example.boards.dto.BoardRequest;
import com.example.boards.dto.BoardResponse;
import com.example.boards.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/boards")
@CrossOrigin(
        allowedHeaders = "*",
        origins = "*",
        methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE, RequestMethod.OPTIONS }
)
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public void addBoard(@RequestBody BoardRequest req){
        boardService.addBoard(req);
    }

    @GetMapping
    public List<BoardResponse> getBoard(){
        return boardService.getBoard();
    }

    @GetMapping("/{boardId}")
    public BoardResponse getBoardById(@PathVariable String boardId){
        return boardService.getBoardById(boardId);
    }

    @DeleteMapping("/{boardId}")
    public void deleteBoard(@PathVariable String boardId){
        boardService.deleteBoard(boardId);
    }
}
