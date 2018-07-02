package com.manifestcorp.listeners;

import com.manifestcorp.db.DbConnector;
import org.apache.commons.dbutils.QueryRunner;

import javax.annotation.Resource;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@WebListener
public class ExampleDataLoader implements ServletContextListener {

    private static Logger LOGGER = Logger.getLogger(ExampleDataLoader.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        Optional<String> loadExampleDbParam = Optional.ofNullable(sce.getServletContext().getInitParameter("loadExampleDB"));
        boolean loadExampleData = loadExampleDbParam.isPresent() && loadExampleDbParam.get().equalsIgnoreCase("true");


        if(loadExampleData) {
            QueryRunner runner = new QueryRunner(DbConnector.getDataSource());

            try {
                String createTableQuery = "CREATE TABLE TODO(id int primary key auto_increment, text varchar(255), done boolean)";
                runner.update(createTableQuery);

                String insertDataQuery = "INSERT INTO TODO(text, done) values (?,?)";
                runner.batch(insertDataQuery,new Object[][] {
                        {"Walk dog", false},
                        {"Get groceries", true},
                        {"Mow lawn", false}
                });

            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Could not populate example database on app startup", e);
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // do nothing
    }

}
