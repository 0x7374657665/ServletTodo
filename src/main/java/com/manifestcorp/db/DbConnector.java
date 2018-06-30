package com.manifestcorp.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.logging.Logger;

public class DbConnector {

    private static final Logger LOGGER = Logger.getLogger(DbConnector.class.getName());

    private static DataSource instance = null;

    private DbConnector() {}

    public static DataSource getDataSource() {
        if (instance == null) {
            Context ctx = null;
            try {
                ctx = new InitialContext();
                instance = (DataSource) ctx.lookup("java:/comp/env/jdbc/todoDS");
            } catch (NamingException e) {
                LOGGER.severe("Could not find data source jdbc/todoDS");
            }
        }

        return instance;
    }
}
