package com.pack;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Final")
public class Final extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 PrintWriter out = response.getWriter();
	        response.setContentType("text/html");
	        out.println("<html><body>");

	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/session","root","root");
	            System.out.println("retrieve");
	            Statement stmt = con.createStatement();
	            ResultSet rs = stmt.executeQuery("select image from imgtable");
	            out.println("<table border=1 width=50% height=50%>");
	            out.println("<tr><th>Image</th><tr>");
	            while (rs.next()) {
	          out.println( "<tr><td><img src='rs.getString('image')' width='600' height='400'/></tr>");
	                
	               // out.println("<tr><td>" + image +"</td></tr>"); 
	            }
	            out.println("</table>");
	            out.println("</html></body>");
	            con.close();
	           }
	            catch (Exception e) {
	            out.println("error");
	        }
	    }
        }