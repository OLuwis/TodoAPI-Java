package com.luwis.application.controllers;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import com.luwis.application.authorities.Delete;
import com.luwis.application.authorities.Write;
import com.luwis.application.graphql.inputs.CreateTodoInput;
import com.luwis.application.graphql.inputs.DeleteTodoInput;
import com.luwis.application.graphql.responses.CreateTodoRes;
import com.luwis.application.graphql.responses.DeleteTodoRes;
import com.luwis.application.services.TodoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TodoController {
    
    private final TodoService todoService;
    
    @Write
    @MutationMapping
    public CreateTodoRes CreateTodo(@Argument CreateTodoInput todo) {
        return todoService.create(todo);
    }

    @Delete
    @MutationMapping
    public DeleteTodoRes DeleteTodo(@Argument DeleteTodoInput todo) {
        return todoService.delete(todo);
    }
}