/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
    }// </editor-fold>
}
