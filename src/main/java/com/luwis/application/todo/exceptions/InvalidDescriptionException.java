package com.luwis.application.todo.exceptions;

import org.springframework.graphql.execution.ErrorType;

import graphql.GraphQLError;

public class InvalidDescriptionException extends RuntimeException {
    private String message = "Invalid Description: Please Insert A Description";

    private String type = ErrorType.BAD_REQUEST.toString();

    public GraphQLError error() {
        return GraphQLError
        .newError()
        .errorType(ErrorType.BAD_REQUEST)
        .message(message)
        .build();
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }
}