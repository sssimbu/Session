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

import com.oreilly.servlet.MultipartRequest;

@WebServlet("/Image")
public class Image extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();

		String name = (String)session.getAttribute("name");

		session.setAttribute("name", name);
		
		System.out.println("name");
		
		String location = "D:/wynx/Sessions/WebContent/images/";
		MultipartRequest m = new MultipartRequest(request, location, 1000000000);
		String a = m.getFilesystemName("image");
		String picture = location.concat(a);
		picture = picture.replace("D:/wynx/Sessions/WebContent/i", "i");

		try {
			// System.out.println(id);

			// loading drivers for mysql
			Class.forName("com.mysql.jdbc.Driver");

			// creating connection with the database
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/session", "root", "root");
			System.out.println("Database connected");

			/*if (picture == null) {

				PreparedStatement ps = con.prepareStatement("insert into tsession(image) values(?)");

				// PreparedStatement ps = con.prepareStatement("update imgtable
				// set image='"+picture+"'");

				ps.setString(1, picture);
				out.print(picture);
				int i = ps.executeUpdate();
System.out.println("excuted");
				if (i > 0) {
					response.sendRedirect("Retrieve.jsp");
					System.out.println("saved");

				}

			}*/
			//if (picture != null) {

				// PreparedStatement ps = con.prepareStatement("insert into
				// imgtable(image) values(?)");

				PreparedStatement ps = con.prepareStatement("update tsession set image='" + picture + "' where name='"+name+"'");

				// ps.setString(1, picture);
				out.print(picture);
				int i = ps.executeUpdate();

				if (i > 0) {
					response.sendRedirect("Retrieve.jsp");
					System.out.println("saved");

				}

			//}

		} catch (Exception se) {
			se.printStackTrace();
		}

	}
}
