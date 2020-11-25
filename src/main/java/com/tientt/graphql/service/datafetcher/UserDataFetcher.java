package com.tientt.graphql.service.datafetcher;

import com.tientt.graphql.entity.UserEntity;
import com.tientt.graphql.repository.UserRepository;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDataFetcher {
    public UserDataFetcher() {
    }

    @Autowired
    UserRepository repository;

    public DataFetcher getUserByUsernameDataFetcher() {
        return (dataFetchingEnvironment) -> {
            String username = dataFetchingEnvironment.getArgument("username");
            UserEntity user = repository.findByUsername(username);
            return user;
        };
    }

    public DataFetcher getAllUsersDataFetcher() {
        return (dataFetchingEnvironment) -> {
            return repository.findAll();
        };
    }
}
