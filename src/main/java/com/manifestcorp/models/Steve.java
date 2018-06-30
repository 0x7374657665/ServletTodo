package com.manifestcorp.models;

import com.manifestcorp.db.DbConnector;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Steve {
	
	private static final Logger LOGGER = Logger.getLogger(Steve.class.getName());

    private static final QueryRunner RUNNER = new QueryRunner(DbConnector.getDataSource());
	
	public static void printTodos() {

		 try {

             MapListHandler mapListHandler = new MapListHandler();
             List<Map<String, Object>> results = RUNNER.query("select * from todo", mapListHandler);
             results.stream().forEach( row -> {
                 System.out.println("--------------------------------------------");
                 System.out.println("id: " + row.get("ID"));
                 System.out.println("text: " + row.get("TEXT"));
                 System.out.println("done: " + row.get("DONE"));
             });


         } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
