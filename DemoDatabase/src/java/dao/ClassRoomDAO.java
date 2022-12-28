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
 
public class ClassRoomDAO {

    Connection connection;
    DBContext db;

    public ClassRoomDAO() {
        try {
            //lay connection ra 
            db = new DBContext();
            connection = db.getConnection();
            System.out.println("ok");
        } catch (Exception e) {
            System.out.println("Loi : " + e);
        }
    }

    public List<ClassRoom> getAll() {
        List<ClassRoom> list = new ArrayList<ClassRoom>();
        String sql = "select * from [Class]";
        try {
            // tạo khay chứa câu lệnh 
            PreparedStatement pre = connection.prepareStatement(sql);
            //tạo bảng chứa giá trị của câu lệnh
            ResultSet rs = pre.executeQuery();
            // lặp theo từng dòng
            while (rs.next()) {
                //lấy giá trị theo từng cột
                int classId = rs.getInt(1);
                String nameClass = rs.getString(2);
                //add giá trị đã lấy vào object
                ClassRoom classRoom = new ClassRoom(classId, nameClass);
                //add giá trị vào list 
                list.add(classRoom);
            }

        } catch (Exception e) {
            System.out.println("" + e);
        }

        return list;
    }
}
