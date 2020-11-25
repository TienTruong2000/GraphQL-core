package com.tientt.graphql.service;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.tientt.graphql.service.datafetcher.UserDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

@Component
public class GraphQLService {
    private GraphQL graphQL;
    @Autowired
    UserDataFetcher userDataFetcher;

    public GraphQLService() {
    }

    @Bean
    public GraphQL graphQL() {
        return this.graphQL;
    }

    @PostConstruct
    public void init() throws IOException {
        URL url = Resources.getResource("users.graphql");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = this.buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry registry = (new SchemaParser()).parse(sdl);
        RuntimeWiring runtimeWiring = this.buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(registry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring().
                type(TypeRuntimeWiring.newTypeWiring("Query").
                        dataFetcher("userByUsername", this.userDataFetcher.getUserByUsernameDataFetcher()).
                        dataFetcher("allUsers", this.userDataFetcher.getAllUsersDataFetcher())).
                build();
    }
}
