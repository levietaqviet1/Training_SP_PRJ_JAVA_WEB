package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {

    public Connection getConnection() throws Exception {
        // Tạo chuỗi URL kết nối đến cơ sở dữ liệu dựa trên thông tin cấu hình
        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;

        // Tải driver JDBC của SQL Server vào classpath của ứng dụng
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        // Yêu cầu một kết nối với cơ sở dữ liệu dựa trên URL, tên đăng nhập và mật khẩu
        return DriverManager.getConnection(url, userID, password);
    }

    /*Insert your other code right after this comment*/
    /*Change/update information of your database connection, DO NOT change name of instance variables in this class*/
    private final String serverName = "DESKTOP-FOIPU4I";
    private final String dbName = "DemoStudent";
    private final String portNumber = "1433";
    private final String userID = "sa";
    private final String password = "123";
}
