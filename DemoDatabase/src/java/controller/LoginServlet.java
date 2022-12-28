/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
