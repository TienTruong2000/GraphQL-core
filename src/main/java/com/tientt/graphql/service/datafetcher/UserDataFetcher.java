package com.tientt.graphql.service.datafetcher;

import com.tientt.graphql.entity.UserEntity;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDataFetcher {
    public UserDataFetcher() {
    }

    public DataFetcher getUserByUsernameDataFetcher() {
        return (dataFetchingEnvironment) -> {
            String username = (String)dataFetchingEnvironment.getArgument("username");
            return new UserEntity(username, "123", "123", true);
        };
    }

    public DataFetcher getAllUsersDataFetcher() {
        return (dataFetchingEnvironment) -> {
            List<UserEntity> list = new ArrayList();
            list.add(new UserEntity("123", "123", "123", true));
            list.add(new UserEntity("abc", "abc", "abc", true));
            return list;
        };
    }
}
