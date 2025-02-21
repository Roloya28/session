package com.example.demo.todo.controller;

import com.example.demo.todo.dto.*;
import com.example.demo.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todos")
    public ResponseEntity<TodoSaveResponseDto> save(@RequestBody TodoSaveRequestDto dto) {
        return ResponseEntity.ok(todoService.save(dto));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TodoResponseDto>> getAll() {
        return ResponseEntity.ok(todoService.findAll());
    }

    @GetMapping("/todos")
    public ResponseEntity<TodoResponseDto> getOne(@PathVariable Long todoId) {
        return ResponseEntity.ok(todoService.findById(todoId));
    }

    @PutMapping("/todos/{todoId}")
    public ResponseEntity<TodoUpdateResponseDto> update(@PathVariable Long todoId, @RequestBody TodoUpdateRequestDto dto) {
        return ResponseEntity.ok(todoService.update(todoId, dto));
    }

    @DeleteMapping("/todos/{todoId}")
    public void delete(@PathVariable Long todoId) {
        todoService.deleteById(todoId);
    }
}
