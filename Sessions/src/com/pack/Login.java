package com.pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// request.getRequestDispatcher("link.html").include(request, response);

		String name = request.getParameter("name");
		String password = request.getParameter("password");
		try {

			// loading drivers for mysql
			Class.forName("com.mysql.jdbc.Driver");

			// creating connection with the database
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/session", "root", "root");
			System.out.println("Database connected");

			Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select * from tsession where name='" + name + "' and password='" + password + "'");

			rs.next();

			HttpSession session1 = request.getSession();
			session1.setAttribute("name", name);
			
			System.out.println(rs.getString("name"));
			
			
			if (rs != null) {
				response.sendRedirect("Display");
			}

		} catch (Exception se) {
			out.println("Invalid user");
			response.sendRedirect("login.html");
		}
	}
}
