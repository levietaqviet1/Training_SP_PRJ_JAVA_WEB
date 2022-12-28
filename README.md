# Training_SP_PRJ_JAVA_WEB

Tải phần mềm code ở trên bằng cách clone xuống hoặc tải file zip của github
<!-- Table of Contents -->
# :notebook_with_decorative_cover: Table of Contents
- [Lý Thuyết](#Lý-Thuyết)
- [Tạo 1 class DBContext](#Tạo-1-class-DBContext)
- [Ví dụ về 1 hàm class DAO có đẩy đủ CRUD](#Ví-dụ-về-1-hàm-class-DAO-có-đẩy-đủ-CRUD)
   * [Ví dụ riêng về Create ( tạo )](#Ví-dụ-riêng-về-Create) 
   * [Ví dụ riêng về Read ( đọc dữ liệu )](#Ví-dụ-riêng-về-Read)  
   * [Ví dụ riêng về Update ( sửa dữ liệu )](#Ví-dụ-riêng-về-Update)  
   * [Ví dụ riêng về Delete ( tạo )](#Ví-dụ-riêng-về-Delete)

## Lý Thuyết
- [Đến Menu](#notebook_with_decorative_cover-Table-of-Contents)
<br/>+ java.sql.Statement là một interface trong thư viện JDBC cung cấp các phương thức để thực hiện các câu lệnh SQL trên một cơ sở dữ liệu. Để sử dụng interface này, bạn cần tạo một đối tượng của interface bằng cách gọi phương thức createStatement() trên đối tượng Connection. Sau đó, bạn có thể sử dụng các phương thức của interface để thực hiện các câu lệnh SQL như SELECT, INSERT, UPDATE, DELETE, và các câu lệnh khác.
```java
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE age > 18");
```
<br/>+ java.sql.PreparedStatement là một interface trong thư viện Java Database Connectivity (JDBC), được sử dụng để thực hiện các câu lệnh SQL trên một cơ sở dữ liệu. Nó tương tự như java.sql. Statement, nhưng có một số ưu điểm khác nhau.
<br/>
Điểm khác biệt chính giữa java.sql.Prepared Statement và java.sql. Statement là java.sql.Prepared Statement được dùng để thực hiện các câu lệnh SQL đã được biên dịch sẵn, trong khi java.sql. Statement thì không. Điều này có nghĩa là khi sử dụng java.sql.Prepared Statement, câu lệnh SQL sẽ được biên dịch trước đó và lưu trữ trong bộ nhớ, và khi bạn thực hiện câu lệnh, nó sẽ được thực thi ngay lập tức. Điều này có thể giúp tăng hiệu suất và giảm thời gian xử lý khi bạn thực hiện các câu lệnh SQL trên cơ sở dữ liệu.
```java
            // tạo khay chứa câu lệnh 
            PreparedStatement pre = connection.prepareStatement(sql);
            //tạo bảng chứa giá trị của câu lệnh
            ResultSet rs = pre.executeQuery();
```
<br/>+ java.sql.ResultSet là một interface trong Java trong gói java.sql. Nó là một đối tượng để lưu trữ kết quả từ câu lệnh SELECT truy vấn trong cơ sở dữ liệu. Nó cung cấp các phương thức để truy xuất các dòng dữ liệu và các cột trong kết quả từ câu lệnh SELECT.

Ví dụ, bạn có thể sử dụng phương thức next() để di chuyển đến dòng tiếp theo trong kết quả, hoặc sử dụng phương thức getInt() để lấy giá trị của một cột số nguyên trong dòng hiện tại.
```java
try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
     Statement stmt = conn.createStatement();
     ResultSet rs = stmt.executeQuery("SELECT id, name FROM employees")) {

   while (rs.next()) {
       int id = rs.getInt("id");
       String name = rs.getString("name");
       System.out.println(id + ": " + name);
   }
} catch (SQLException e) {
   e.printStackTrace();
}

```
## Tạo 1 class DBContext 
- [Đến Menu](#notebook_with_decorative_cover-Table-of-Contents)
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
- [Đến Menu](#notebook_with_decorative_cover-Table-of-Contents)
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
## Ví dụ riêng về Create
- [Đến Menu](#notebook_with_decorative_cover-Table-of-Contents) <br/>
Sử dụng try-with-resources để tự động đóng PreparedStatement: Try-with-resources là một tính năng trong Java 8 cho phép bạn tự động đóng các đối tượng cần đóng sau khi sử dụng, nhờ đó bạn không cần phải viết mã đóng riêng cho chúng. 
```java
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

        try ( PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setString(1, student.getName());
            java.sql.Date DateSql = new java.sql.Date(student.getDate().getTime());
            pre.setDate(2, DateSql);
            pre.setBoolean(3, student.isGender());
            pre.setInt(4, student.getClassRoom().getId());
            pre.executeUpdate();
        } catch (Exception e) {
            System.out.println("" + e);
        } 
    }
```
## Ví dụ riêng về Read
- [Đến Menu](#notebook_with_decorative_cover-Table-of-Contents) <br/>
Sử dụng try-with-resources để tự động đóng PreparedStatement: Try-with-resources là một tính năng trong Java 8 cho phép bạn tự động đóng các đối tượng cần đóng sau khi sử dụng, nhờ đó bạn không cần phải viết mã đóng riêng cho chúng. 
<br/>  Phương thức sau để lấy  <span style="color: red;">1  sinh viên</span>  theo   <span style="color: red;">ID</span>:
```java
public Student getStudentById(int id) {
    Student student = null;
    String sql = "SELECT * FROM [dbo].[Student] WHERE [Id] = ?";
    try (PreparedStatement pre = connection.prepare Statement(sql)) {
        pre.setInt(1, id);
        try (ResultSet rs = pre.execute Query()) {
            if (rs.next()) {
                String name = rs.getString("Name");
                Date date = rs.getDate("Date");
                boolean gender = rs.getBoolean("Gender");
                int classId = rs.getInt("ClassId");
                student = new Student(id, name, date, gender, classId);
            }
        }
    } catch (Exception e) {
        System.out.println("" + e);
    }
    return student;
}
```
<br/>  Phương thức sau để lấy lấy <span style="color: red;">danh sách lớp học</span> : 
```java
    public List<ClassRoom> getAllClassRooms() {
        List<ClassRoom> classRooms = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[ClassRoom]";
        try ( Statement stmt = connection.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                ClassRoom classRoom = new ClassRoom(id, name);
                classRooms.add(classRoom);
            }
        } catch (Exception e) {
            System.out.println("" + e);
        }
        return classRooms;
    } 
```
## Ví dụ riêng về Update
- [Đến Menu](#notebook_with_decorative_cover-Table-of-Contents) <br/>
Sử dụng try-with-resources để tự động đóng PreparedStatement: Try-with-resources là một tính năng trong Java 8 cho phép bạn tự động đóng các đối tượng cần đóng sau khi sử dụng, nhờ đó bạn không cần phải viết mã đóng riêng cho chúng. 
<br/> Phương thức sau để cập nhật thông tin sinh viên trong cơ sở dữ liệu:
```java
     public void updateStudent(Student student) {
        String sql = "UPDATE [dbo].[Student]\n"
                + "   SET [Name] = ?\n"
                + "      ,[Date] = ?\n"
                + "      ,[Gender] = ?\n"
                + "      ,[ClassId] = ?\n"
                + " WHERE [Id] = ?";
        try ( PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setString(1, student.getName());
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
}
```
## Ví dụ riêng về Delete
- [Đến Menu](#notebook_with_decorative_cover-Table-of-Contents) <br/>
Sử dụng try-with-resources để tự động đóng PreparedStatement: Try-with-resources là một tính năng trong Java 8 cho phép bạn tự động đóng các đối tượng cần đóng sau khi sử dụng, nhờ đó bạn không cần phải viết mã đóng riêng cho chúng. 
<br/> Xóa Sinh Viên theo id
```java
    public void deleteStudent(int id) {
    String sql = "DELETE FROM [dbo].[Student] WHERE [Id] = ?";
    try (PreparedStatement pre = connection.prepareStatement(sql)) {
        pre.setInt(1, id);
        pre.executeUpdate();
    } catch (Exception e) {
        System.out.println("" + e);
    }
}
```
