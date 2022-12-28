/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect.DBContext2;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import model.ClassRoom;
import model.Student;
import model.User;
 
public class UserDAO extends DBContext2 {
    
    public User getUser(String username, String password) {
        User u = null;
        String sql = "select * from [User] where username=? and password=? ";
        try {
            // tạo khay chứa câu lệnh 
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, username);
            pre.setString(2, password);
            //tạo bảng chứa giá trị của câu lệnh
            ResultSet rs = pre.executeQuery();
            // lặp theo từng dòng
            while (rs.next()) {
                //lấy giá trị theo từng cột
                int id = rs.getInt(1);
                String user = rs.getString(2);
                String pass = rs.getString(3);
                u = new User(id, username, password);
            }
            
        } catch (Exception e) {
            System.out.println("" + e);
        }
        
        return u;
    }
    
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        System.out.println(""+dao.getUser("", ""));
    }
}
