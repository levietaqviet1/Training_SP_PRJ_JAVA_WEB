/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.ClassRoom;
import model.Student;
 
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
