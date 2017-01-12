package spring.hessian.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;

public class SparkServerJDBC {

    private static Log log = LogFactory.getLog(SparkServerJDBC.class);
    private static final String DRIVER = "org.apache.hive.jdbc.HiveDriver";
    private static final String CONN = "jdbc:hive2://10.70.11.13:10001/default?hive.server2.transport.mode=http;hive.server2.thrift.http.path=cliservice";
    private static final String USER = "sinova";

    public static boolean connection(String sql, String settingId) {
        Connection conn = null;
        boolean result = false;
        String tableName = "zhfw_" + settingId;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(CONN, USER, "");
            log.info("Connection to SparkThriftServer...");

            Statement stat = conn.createStatement();
            String createTable = "create table if not exists " + tableName +
                    " (mobile string)" +
                    " row format delimited " +
                    " fields terminated by '\t'";
            log.info("use default: " + stat.execute("use default"));
            log.info(createTable + ": " + stat.execute(createTable));
            log.info("Execute truncate table " + tableName + ": " + stat.execute("truncate table " + tableName));

            result = stat.execute("insert into table  " + tableName + " as " + sql);
            log.info("ExecuteQuery...insert into table " + tableName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
