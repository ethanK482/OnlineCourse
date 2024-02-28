package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
   public Connection getConnection() {
       String url = "jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true;databaseName=test";
        String user = "sa";
        String password = "123";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection c = DriverManager.getConnection(url, user, password);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    
}
    public static void main(String[] args) {
        ConnectDB db = new ConnectDB();
        System.out.println(db.getConnection());
    }
}
