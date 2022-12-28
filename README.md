# Training_SP_PRJ_JAVA_WEB

Tải phần mềm code ở trên 

## Tạo 1 class DBContext 
```java
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
```

## Ví dụ về 1 hàm class DAO có đẩy đủ CRUD

```java

public class StudentDAO {

    Connection connection;
    DBContext db;

    public StudentDAO() {
        try {
            //lay connection ra 
            db = new DBContext();
            connection = db.getConnection();
            System.out.println("ok");
        } catch (Exception e) {
            System.out.println("Loi : " + e);
        }
    }

    public List<Student> getAll() {
        List<Student> list = new ArrayList<Student>();
        String sql = "select * from Student ,[Class] \n"
                + "where Student.ClassId = [Class].Id";
        try {
            // tạo khay chứa câu lệnh 
            PreparedStatement pre = connection.prepareStatement(sql);
            //tạo bảng chứa giá trị của câu lệnh
            ResultSet rs = pre.executeQuery();
            // lặp theo từng dòng
            while (rs.next()) {
                //lấy giá trị theo từng cột
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Date date = rs.getDate(3);
                boolean gender = rs.getBoolean(4);
                int classId = rs.getInt(6);
                String nameClass = rs.getString(7);
                //add giá trị đã lấy vào object
                ClassRoom classRoom = new ClassRoom(classId, nameClass);
                Student student = new Student(id, name, date, gender, classRoom);
                //add giá trị vào list 
                list.add(student);
            }

        } catch (Exception e) {
            System.out.println("" + e);
        }

        return list;
    }

    public Student getStudentById(int id) {
        Student student = new Student();
        String sql = "select* from Student ,Class "
                + "where Student.id =? "
                + "and Student.ClassId =Class.Id";
        try {
            // tạo khay chứa câu lệnh 
            PreparedStatement pre = connection.prepareStatement(sql);
            //set value cho dấu hỏi chấm
            pre.setInt(1, id);
            //tạo bảng chứa giá trị của câu lệnh
            ResultSet rs = pre.executeQuery();
            // lặp theo từng dòng
            while (rs.next()) {
                //lấy giá trị theo từng cột
                String name = rs.getString(2);
                Date date = rs.getDate(3);
                boolean gender = rs.getBoolean(4);
                int classId = rs.getInt(6);
                String nameClass = rs.getString(7);
                //add giá trị đã lấy vào object
                ClassRoom classRoom = new ClassRoom(classId, nameClass);
                student = new Student(id, name, date, gender, classRoom);
                //add giá trị vào list 
            }

        } catch (Exception e) {
            System.out.println("" + e);
        }

        return student;
    }

    public void insertStudent(Student student) {
        String sql = "INSERT INTO [dbo].[Student]\n"
                + "           ([Name]\n"
                + "           ,[Date]\n"
                + "           ,[Gender]\n"
                + "           ,[ClassId])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            // tạo khay chứa câu lệnh 
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, student.getName());
            // covert java.util.date -->java.sql.date
            java.sql.Date DateSql = new java.sql.Date(student.getDate().getTime());
            pre.setDate(2, DateSql);
            pre.setBoolean(3, student.isGender());
            pre.setInt(4, student.getClassRoom().getId());
            pre.executeUpdate();

        } catch (Exception e) {
            System.out.println("" + e);
        }

    }

    public void updateStudent(Student student) {
        String sql = "UPDATE [dbo].[Student]\n"
                + "   SET [Name] = ?\n"
                + "      ,[Date] = ? \n"
                + "      ,[Gender] = ? \n"
                + "      ,[ClassId] = ? \n"
                + " WHERE id = ?";
        try {
            // tạo khay chứa câu lệnh 
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, student.getName());
            // covert java.util.date -->java.sql.date
            java.sql.Date DateSql = new java.sql.Date(student.getDate().getTime());
            pre.setDate(2, DateSql);
            pre.setBoolean(3, student.isGender());
            pre.setInt(4, student.getClassRoom().getId());
            pre.setInt(5, student.getId());
            pre.executeUpdate();

        } catch (Exception e) {
            System.out.println("" + e);
        }

    }

    public void deleteStudent(int id) {
        String sql = "delete Student where id = ? ";
        try {
            // tạo khay chứa câu lệnh 
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            pre.executeUpdate();

        } catch (Exception e) {
            System.out.println("" + e);
        }

    }

    public static void main(String[] args) {
        StudentDAO s = new StudentDAO();

    }

}
```
