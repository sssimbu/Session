package com.pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Registers")
public class Registers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String name = request.getParameter("name");

		String password = request.getParameter("password");

		String address = request.getParameter("address");

		try {

			// loading drivers for mysql
			Class.forName("com.mysql.jdbc.Driver");

			// creating connection with the database
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/session", "root", "root");
			System.out.println("Database connected");
			PreparedStatement ps = con.prepareStatement("insert into tsession(name,password,address) values(?,?,?)");

			ps.setString(1, name);
			ps.setString(2, password);
			ps.setString(3, address);
			
			HttpSession session = request.getSession();
			  session.setAttribute("name",name );
			  session.setAttribute("password",password );
			  session.setAttribute("address",address );

			
			int i = ps.executeUpdate();

			if (i > 0) {
				
				response.sendRedirect("login.html");
			}

		} catch (Exception se) {
			se.printStackTrace();
		}

	}
}
