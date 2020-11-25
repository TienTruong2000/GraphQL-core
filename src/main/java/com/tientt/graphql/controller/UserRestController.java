package com.tientt.graphql.controller;

import com.tientt.graphql.service.GraphQLService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/users/api"})
public class UserRestController {
    @Autowired
    GraphQLService graphQLService;

    public UserRestController() {
    }

    @PostMapping
    public ResponseEntity getAllBooks(@RequestBody String query) {
        ExecutionResult executionResult = this.graphQLService.graphQL().execute(query);
        return ResponseEntity.ok(executionResult);
    }
}
