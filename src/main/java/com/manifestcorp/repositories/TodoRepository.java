package com.manifestcorp.repositories;

import com.manifestcorp.models.Todo;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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

    public void mergeTodos(List<Todo> todos) {

        String mergeQuery = "merge into todo key (id) values (?,?,?)";
        Object[][] mergeValues = new Object[todos.size()][3];
        for (int i = 0; i < todos.size(); i++) {
            Todo todo = todos.get(i);
            mergeValues[i] = new Object[]{todo.getId(), todo.getText(), todo.isDone()};
        }

        try {
            runner.batch(mergeQuery, mergeValues);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Encountered SQL exception while merging todos: ", e);
        }
    }

    public void mergeTodo(Todo todo) {
        mergeTodos(Arrays.asList(new Todo[]{todo}));
    }

    public int addTodo(String newTodo) {
        String insertQuery = "insert into todo (text,done) values (?,?)";
        int key = -1;
        try {
            key = runner.insert(insertQuery, new ScalarHandler<Integer>(), newTodo, false);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Encountered SQL exception inserting todo '" + newTodo + "'", e);
        }

        return key;
    }

    public void clearDone() {
        String clearQuery = "delete from todo where done = true";
        try {
            runner.update(clearQuery);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Encountered SQL exception clearing done todos", e);
        }
    }

    public List<Todo> getDoneTodos() {
        List<Todo> todos = new ArrayList<>();
        BeanListHandler<Todo> todosTemplate = new BeanListHandler<>(Todo.class);

        try {
            todos = runner.query("select * from todo where done = true", todosTemplate);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Encountered SQL exception while retrieving todos: ", e);
        }

        return todos;
    }
}
