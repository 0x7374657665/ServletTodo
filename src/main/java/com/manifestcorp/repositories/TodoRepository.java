package com.manifestcorp.repositories;

import com.manifestcorp.models.Todo;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TodoRepository {
    private QueryRunner runner;

    private static final Logger LOGGER = Logger.getLogger(TodoRepository.class.getName());

    public TodoRepository(DataSource ds) {
        runner = new QueryRunner(ds);
    }

    public List<Todo> getTodos() {
        List<Todo> todos = new ArrayList<>();
        BeanListHandler<Todo> todosTemplate = new BeanListHandler<>(Todo.class);

        try {
            todos = runner.query("select * from todo", todosTemplate);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Encountered SQL exception while retrieving todos: ", e);
        }

        return todos;
    }
}
