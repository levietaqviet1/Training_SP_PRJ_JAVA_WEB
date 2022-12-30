  <h3>Training_SP_PRJ_JAVA_WEB</h3><br/>
  <a href="https://docs.google.com/document/d/1Mi02YIsiQXHejgpaFJeA2SNmVsmLNbUC/edit?usp=sharing&ouid=105808482418084757102&rtpof=true&sd=true" target="_blank">
   Tải phần mềm code và set up môi trường
  </a>  
  
Ở Trên có 1 bài đầy đủ CRUD tên Project là DemoDatabase ( Bài demo nên code có thể chưa được tối ưu, nên xem tài liệu dưới làm lại )
<br/>
<!-- Table of Contents -->
# :notebook_with_decorative_cover: Table of Contents
- [Lý Thuyết](#Lý-Thuyết)
- [Lý Thuyết MVC](#Lý-Thuyết-MVC)
- [Ví dụ về một ứng dụng web sử dụng mô hình MVC (Model-View-Controller) ](#ví-dụ-về-một-ứng-dụng-web-sử-dụng-mô-hình-mvc-model-view-controller)
- [Tạo 1 class DBContext](#Tạo-1-class-DBContext)
- [Ví dụ về 1 hàm class DAO có đẩy đủ CRUD](#Ví-dụ-về-1-hàm-class-DAO-có-đẩy-đủ-CRUD)
   * [Ví dụ riêng về Create ( tạo )](#Ví-dụ-riêng-về-Create) 
   * [Ví dụ riêng về Read ( đọc dữ liệu )](#Ví-dụ-riêng-về-Read)  
   * [Ví dụ riêng về Update ( sửa dữ liệu )](#Ví-dụ-riêng-về-Update)  
   * [Ví dụ riêng về Delete ( tạo )](#Ví-dụ-riêng-về-Delete)
- [Ví dụ về 1 class Servlet Login Basic](#Ví-dụ-về-1-class-Servlet-Login-Basic)
- [Ví dụ về 1 class Servlet Trả về 1 List Danh Sách](#Ví-dụ-về-1-class-Servlet-Trả-về-1-List-Danh-Sách)
## Lý Thuyết
- [Đến Menu](#notebook_with_decorative_cover-Table-of-Contents)

`Java Servlets` là một kỹ thuật lập trình được sử dụng để xây dựng các ứng dụng web trên máy chủ. Servlets là các lớp Java được chạy trên máy chủ và có thể xử lý yêu cầu HTTP từ trình duyệt web của người dùng và trả lại các trang `HTML`, `XML` hoặc các dữ liệu khác đến trình duyệt.

JavaServer Pages (`JSP`) là một kỹ thuật lập trình được sử dụng để xây dựng các trang web động trên máy chủ. `JSP` là các tệp HTML được mở rộng bằng các thẻ JSP và chứa mã Java để xử lý các yêu cầu từ trình duyệt web của người dùng và trả lại các trang HTML hoặc dữ liệu khác đến trình duyệt.

`Servlets` và `JSP` có thể được sử dụng cùng nhau trong một ứng dụng web để xử lý yêu cầu HTTP và trả lại các trang HTML hoặc dữ liệu khác đến trình duyệt web. Servlets có thể được sử dụng để xử lý các yêu cầu cơ bản và làm việc với cơ sở dữ liệu hoặc các tài nguyên khác trên máy chủ
 
`Những thư viện sẽ dùng trong lúc làm việc với cơ sở dữ liệu ( SQL Server, ... ) có thể đọc sau khi cài xong code`

+ java.sql.Statement là một interface trong thư viện JDBC cung cấp các phương thức để thực hiện các câu lệnh SQL trên một cơ sở dữ liệu. Để sử dụng interface này, bạn cần tạo một đối tượng của interface bằng cách gọi phương thức createStatement() trên đối tượng Connection. Sau đó, bạn có thể sử dụng các phương thức của interface để thực hiện các câu lệnh SQL như SELECT, INSERT, UPDATE, DELETE, và các câu lệnh khác.
```java
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE age > 18");
```

+ java.sql.PreparedStatement là một interface trong thư viện Java Database Connectivity (JDBC), được sử dụng để thực hiện các câu lệnh SQL trên một cơ sở dữ liệu. Nó tương tự như java.sql. Statement, nhưng có một số ưu điểm khác nhau.

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
## Lý Thuyết MVC
- [Đến Menu](#notebook_with_decorative_cover-Table-of-Contents)

- Mô hình MVC (Model-View-Controller) là một kiến trúc phần mềm được sử dụng trong lập trình web để phân chia mã nguồn của một ứng dụng web thành ba phần chính:

+ Model: Phần của ứng dụng chịu trách nhiệm quản lý dữ liệu và các thao tác liên quan đến dữ liệu, như lấy dữ liệu từ cơ sở dữ liệu, lưu dữ liệu vào cơ sở dữ liệu, xóa dữ liệu, v.v.

+ View: Phần của ứng dụng chịu trách nhiệm hiển thị dữ liệu và các thao tác người dùng cho người dùng, như trang web, giao diện người dùng, v.v.

+ Controller: Phần của ứng dụng chịu trách nhiệm điều khiển các yêu cầu từ trình duyệt web và giao tiếp với model để lấy dữ liệu từ cơ sở dữ liệu hoặc cập nhật dữ liệu trong cơ sở dữ liệu.

Khi người dùng thực hiện một hành động trên trang web, controller nhận được yêu cầu từ trình duyệt web và sẽ giao tiếp với model để lấy dữ liệu từ cơ sở dữ liệu hoặc cập nhật dữ liệu trong

## Ví dụ về một ứng dụng web sử dụng mô hình MVC (Model-View-Controller)
- [Đến Menu](#notebook_with_decorative_cover-Table-of-Contents)

`Note`: Xem hướng dẫn cài môi trường và mở code DemoDatabase xem trước

Đây là một ví dụ về một ứng dụng web sử dụng mô hình MVC (Model-View-Controller) với servlets và JSP:

Package Model: Tạo class User
```java
public class User {
  private int id;
  private String name;
  private String email;
  // constructors, getters, setters
}

public class UserDao {
  public User getUser(int id) {
    // code to retrieve user from database
  }

  public void updateUser(User user) {
    // code to update user in database
  }
}
```
Lớp `User` là một model định nghĩa thông tin về một người dùng, bao gồm các thuộc tính id, name và email. Lớp `UserDao` là một model khác định nghĩa các phương thức để lấy thông tin người dùng từ cơ sở dữ liệu và cập nhật thông tin người dùng trong cơ sở dữ liệu.

Package Dao: Tạo class tên UserDao 

```java
public class UserDao {
  // code to establish connection to database

  public User getUser(int id) {
    User user = null;
    // code to execute SQL query to retrieve user from database
    // and populate user object with data from result set
    return user;
  }

  public void updateUser(User user) {
    // code to execute SQL query to update user in database
  }

  // code to close connection to database
}
```
Trong ví dụ này, phương thức `getUser` sử dụng một câu lệnh `SQL` để lấy thông tin người dùng từ cơ sở dữ liệu dựa trên mã người dùng được truyền vào. Phương thức updateUser sử dụng một câu lệnh SQL khác để cập nhật thông tin người dùng trong cơ sở dữ liệu dựa trên thông tin trong đối tượng User được truyền vào. Các phương thức này cũng có thể bao gồm các lệnh để mở kết nối và đóng kết nối đến cơ sở dữ liệu.
Package Controller: Tạo Servlet tên UserServlet 
```java 
@WebServlet("/users")
public class UserServlet extends HttpServlet {
  private UserDao userDao = new UserDao();

  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    int userId = Integer.parseInt(request.getParameter("id"));
    User user = userDao.getUser(userId);
    request.setAttribute("user", user);
    request.getRequestDispatcher("user.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    int userId = Integer.parseInt(request.getParameter("id"));
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    User user = new User(userId, name, email);
    userDao.updateUser(user);
    response.sendRedirect("/users?id=" + userId);
  }
}
```
Servlet `UserServlet` là controller trong mô hình `MVC`, chịu trách nhiệm điều khiển các yêu cầu từ trình duyệt web và giao tiếp với model để lấy dữ liệu từ cơ sở dữ liệu hoặc cập nhật dữ liệu trong cơ sở dữ liệu. Servlet UserServlet có hai phương thức chính là doGet và doPost để xử lý các yêu cầu `GET` và `OST` từ trình duyệt web. Trong phương thức `doGet`, servlet sẽ lấy thông tin người dùng từ cơ sở dữ liệu bằng cách sử dụng lớp UserDao và đặt thông tin người dùng này làm

Tạo 1 view  JSP
```jsp
<%@ page import="com.example.model.User" %>
<html>
  <body>
    <form action="/users" method="post">
      <input type="hidden" name="id" value="<%= request.getAttribute("user").getId() %>">
      Name: <input type="text" name="name" value="<%= request.getAttribute("user").getName() %>"><br>
      Email: <input type="text" name="email" value="<%= request.getAttribute("user").getEmail() %>"><br>
      <input type="submit" value="Update">
    </form>
  </body>
</html>
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
## Ví dụ về 1 class Servlet Login Basic
```java

import dao.*;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*; 
import model.*;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {
            response.sendRedirect("list");
        } else {
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //cookie 
        Cookie u = new Cookie("user", username);
        Cookie p = new Cookie("pass", password);
        //set time 
        u.setMaxAge(60);
        p.setMaxAge(60);
        //add cookie
        response.addCookie(u);
        response.addCookie(p);

        UserDAO dao = new UserDAO();
        User user = dao.getUser(username, password);
        if (user != null) {
            //login sucess
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("list");

        } else {
            //login fail   
            request.setAttribute("mess", "username or password incorrect");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
```
Đây là code để xử lý việc đăng nhập của một ứng dụng web. Trong code trên, có một số khái niệm cần giải thích:

```request``` và ```response``` là đối tượng của lớp ```HttpServletRequest``` và ```HttpServletResponse``` tương ứng, được sử dụng để lấy thông tin từ yêu cầu ```request``` và trả lại câu trả lời ```response``` cho người dùng.

Cookie là một đối tượng để lưu trữ thông tin trên trình duyệt của người dùng. Khi người dùng truy cập lại trang web, các cookie có thể được sử dụng để hiển thị các thông tin tương ứng. Trong code trên, chúng ta tạo hai đối tượng ```Cookie``` lưu trữ tên đăng nhập và mật khẩu, và đặt thời gian sống của chúng bằng phương thức ```setMaxAge(60)```.

```UserDAO``` là một lớp có chức năng lấy thông tin người dùng từ cơ sở dữ liệu. Trong code trên, chúng ta tạo một đối tượng dao và gọi phương thức ```getUser(username, password)``` để lấy thông tin người dùng từ cơ sở dữ liệu.

```HttpSession``` là một đối tượng để lưu trữ thông tin trên máy chủ, và có thể được sử dụng để lưu trữ thông tin về người dùng sau khi đăng nhập thành công. Trong một ứng dụng web, mỗi người dùng sẽ có một ```HttpSession``` riêng, và chúng ta có thể sử dụng nó để lưu trữ các thông tin về người dùng như tên người dùng, quyền hạn, v.v.

Trang Web gửi lên servlet demo trên
```jsp
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <%
    if (request.getSession().getAttribute("user")!=null) {
            response.sendRedirect("list");
        }
    %>
        <h1>Login!</h1>
        <form action="login" method="post" >
            Username : <input type="text" name="username" value="${cookie.user.value}" ><br>
            Pass : <input type="text" name="password" value="${cookie.pass.value}" ><br>
            <input type="submit" value="Login" >
            ${requestScope.mess}
        </form>
    </body>
</html>
```
Đây là một trang JSP (Java Server Pages) để hiển thị một form đăng nhập cho người dùng. Trong trang này, có một số khái niệm cần giải thích:
``` <%@page contentType="text/html" pageEncoding="UTF-8"%> ```là một directive để xác định kiểu nội dung của trang và mã hóa ký tự.

```<form action="login" method="post" > ```là một thẻ HTML để tạo ra một form đăng nhập. Thuộc tính action xác định đường dẫn đến servlet sẽ xử lý yêu cầu, và thuộc tính method xác định phương thức gửi yêu cầu (trong trường hợp này là post).

``` ${cookie.user.value} và ${cookie.pass.value} ```là các EL (Expression Language) để lấy giá trị của cookie tên đăng nhập và mật khẩu.
``` <% if (request.getSession().getAttribute("user")!=null) { %> ```là một khối scriptlet để kiểm tra xem người dùng có đăng nhập hay không. Nếu đã đăng nhập, sẽ chuyển hướng người dùng đến trang danh sách bằng cách sử dụng phương thức ```sendRedirect("list")``` của đối tượng HttpServletResponse.

``` <h1>Login!</h1> ```là một thẻ HTML để tạo ra một tiêu đề cấp 1 cho trang.

``` <input type="text" name="username" value="${cookie.user.value}" > ```và ```<input type="text" name="password" value="${cookie.pass.value}" > ```là các thẻ HTML để tạo ra các ô nhập cho người dùng nhập tên đăng nhập và mật khẩu. Các thuộc tính name và value đều được sử dụng để xác định tên của trường và giá trị mặc định của trường.

``` <input type="submit" value="Login" > ```là một thẻ HTML để tạo ra một nút đăng nhập. Người dùng có thể bấm vào nút này để gửi yêu cầu đăng nhập đến servlet.
## Ví dụ về 1 class Servlet Trả về 1 List Danh Sách
- [Đến Menu](#notebook_with_decorative_cover-Table-of-Contents) <br/>
```java
import dao.*; 
import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import model.*;
 
public class ListServlet extends HttpServlet {
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         // kiểm tra xem đăng nhập chưa
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("Login.jsp");
            return;
        } 
        //lay gia tri tu tầng DAO 
        StudentDAO dao = new StudentDAO();
        List<Student> listStudent = dao.getAll();
        request.setAttribute("list", listStudent);
        request.getRequestDispatcher("List_JSTL.jsp").forward(request, response);
    } 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// 
}
```
Đây là một ```servlet``` để xử lý yêu cầu lấy danh sách sinh viên từ cơ sở dữ liệu và hiển thị ra trình duyệt. Servlet này có các phương thức ```doGet()``` và ```doPost()``` để xử lý yêu cầu tương ứng.

Trong phương thức ```doGet()```, đầu tiên servlet sẽ kiểm tra xem người dùng có đăng nhập hay không bằng cách lấy đối tượng ```HttpSession``` và kiểm tra xem có một thuộc tính ```user``` hay không. ```Nếu không có```, servlet sẽ ```chuyển hướng người dùng``` đến trang đăng nhập bằng cách sử dụng phương thức ```sendRedirect("Login.jsp")```.
Sau đó, servlet sẽ tạo một đối tượng ```StudentDAO``` và sử dụng phương thức ```getAll()``` để lấy danh sách tất cả sinh viên từ cơ sở dữ liệu. Sau đó, servlet sẽ lưu danh sách sinh viên vào ```request``` bằng cách sử dụng phương thức ```setAttribute("list", listStudent)``` và sử dụng phương thức ```forward(request, response)``` để chuyển hướng người dùng đến trang hiển thị danh sách sinh viên (trang ```JSP List_JSTL.jsp```).

Trang Web được servlet demo trên gửi sang
```jsp
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello : <a href="logout" ><button>Loggout</button> </a> </h1>
        <h1>List Student</h1>  <a href="next-add" ><button>Add new</button> </a>
        <table border="1" >
            <tr>
                <td>Id</td>
                <td>Name</td>
                <td>Date</td>
                <td>Gender</td>
                <td>Class name</td>
                <td>Operation</td>
            </tr>
            <c:forEach items="${requestScope.list}" var="f" >
                <tr>
                    <td>${f.id}</td>
                    <td>${f.name} || ${fn:toUpperCase(f.name)} </td>
                    <td>${f.date}</td>
                    <td>
                        <c:if test="${f.gender == true}">
                            <c:out value="Nam" />
                        </c:if>
                        <c:if test="${f.gender != true}">
                            <c:out value="Nữ" />
                        </c:if>
                    </td>
                    <td>${f.classRoom.name}</td>
                    <td>
                        <a href="next-edit?id=${f.id}" ><button>Edit</button></a>
                        <a href="delete?id=${f.id}" ><button>Delete</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
```
Trong code trên, trang `JSP` hiển thị một bảng danh sách sinh viên và cho phép người dùng thực hiện các thao tác như sửa, xoá sinh viên.
Cụ thể:

Dòng `<h1>Hello : <a href="logout" ><button>Loggout</button> </a> </h1>` hiển thị một nút đăng xuất. Khi người dùng bấm vào nút này, trình duyệt sẽ gửi yêu cầu đăng xuất đến `servlet` tương ứng.

Dòng `<h1>List Student</h1> <a href="next-add" ><button>Add new</button> </a>` hiển thị tiêu đề "List Student" và một nút "Add new" để cho phép người dùng thêm sinh viên mới.

Dòng `<c:forEach items="${requestScope.list}" var="f" >` sử dụng tag `<c:forEach>` của `JSTL` để `lặp qua từng phần tử trong danh sách sinh viên` (biến `requestScope.list`) và gán từng phần tử vào `biến f`.

Các dòng `<td>${f.id}</td>, <td>${f.name} || ${fn:toUpperCase(f.name)} </td>, <td>${f.date}</td>, <td>${f.classRoom.name}</td>` sử dụng `EL` để lấy các thuộc tính của sinh viên (id, tên, ngày sinh, lớp học) và hiển thị chúng trong các ô tương ứng trong bảng. Trong dòng `<td>${f.name} || ${fn:toUpperCase(f.name)} </td>`,...

Hàm `fn:toUpperCase` là một hàm của `JSTL Functions`, nó sẽ chuyển chuỗi được truyền vào `thành chữ hoa`. Ví dụ, khi sử dụng `${fn:toUpperCase("hello")}`, hàm này sẽ trả về chuỗi `"HELLO"`.

Trong code trên, hàm này được sử dụng để chuyển tên của sinh viên thành chữ hoa và hiển thị kết quả cùng với tên gốc của sinh viên dòng `<td>${f.name} || ${fn:toUpperCase(f.name)} </td>)`.

Dòng `<td> <c:if test="${f.gender == true}"> <c:out value="Nam" /> </c:if> <c:if test="${f.gender != true}"> <c:out value="Nữ" /> </c:if> </td>` sử dụng tag `<c:if>` để kiểm tra giá trị của thuộc tính gender của sinh viên.Nếu thuộc tính gender của sinh viên có giá trị là true (tức là sinh viên là `Nam`), thì tag `<c:if>` sẽ hiển thị chuỗi "Nam" trong ô tương ứng của bảng. Ngược lại, nếu thuộc tính gender có giá trị là false (tức là sinh viên là `Nữ`), thì tag `<c:if>` sẽ hiển thị chuỗi "Nữ" trong ô tương ứng của bảng.

Dòng `<td> <a href="next-edit?id=${f.id}" ><button>Edit</button></a> <a href="delete?id=${f.id}" ><button>Delete</button> </td>` hiển thị hai nút "Edit" và "Delete" cho phép người dùng thực hiện các thao tác sửa và xoá sinh viên tương ứng.
