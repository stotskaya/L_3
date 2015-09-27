package utils;

import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class JdbcUtils {

    private static final Logger LOGGER = Logger.getLogger(JdbcUtils.class.getName());
    private static final String SOMETHING_WRONG_WHEN_ROLL_BACK_DB = "Something wrong when rollBack DB";
    private static final String SOMETHING_WRONG_WHEN_RESULT_SET_TO_CLOSE = "Something wrong when ResultSet to close()";
    private static final String SOMETHING_WRONG_WHEN_STATEMENT_TO_CLOSE = "Something wrong when Statement to close()";
    private static final String SOMETHING_WRONG_WHEN_CONNECTION_TO_CLOSE = "Something wrong when Connection to close()";

    private JdbcUtils() {
        throw new AssertionError("Instantiating utility class...");
    }

    /**
     * Standard roll back idiom
     * @param conn the connection with DB
     */
    public static void rollbackQuietly(Connection conn) {
        if (conn != null) {
            try {
                conn.rollback();
            }catch (SQLException e) {
                LOGGER.error(SOMETHING_WRONG_WHEN_ROLL_BACK_DB, e);
            }
        }
    }

    /**
     * Force ResultSet to close
     * standard idiom
     * @param rs resulset
     */
    public static void closeQuietly(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            }catch (SQLException e) {
                LOGGER.error(SOMETHING_WRONG_WHEN_RESULT_SET_TO_CLOSE, e);
            }
        }
    }

    /**
     * force Statement to close
     * standard idiom
     * @param ps preparedStatement
     */
    public static void closeQuietly(Statement ps) {
        if (ps != null) {
            try {
                ps.close();
            }catch (SQLException e) {
                LOGGER.error(SOMETHING_WRONG_WHEN_STATEMENT_TO_CLOSE, e);
            }
        }
    }

    /**
     * force Connection to close
     * standard idiom
     * @param conn the connection with DB
     */
    public static void closeQuietly(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            }catch (SQLException e) {
                LOGGER.error(SOMETHING_WRONG_WHEN_CONNECTION_TO_CLOSE);
            }
        }
    }

    public static void initDriver(String str) {
        try {
            Class.forName(str);
        } catch (ClassNotFoundException ignore) {
        }
    }


}
