package com.pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.Request;


@WebServlet("/Display")
public class Display extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        PrintWriter out = res.getWriter();
        res.setContentType("text/html");
        out.println("<html><body>");
        
        HttpSession session=req.getSession();
        String name =(String)session.getAttribute("name");
        
		session.setAttribute("name", name);

        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/session","root","root");
            System.out.println("retrieve");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tsession where name='"+name+"'");
            out.print("welcome  "+name);
           
            out.println("<table border=1 width=50% height=50%>");
            out.println("<form action='Image' method='post' enctype='multipart/form-data'");
            out.println("<tr><th>name</th><th>Address</th></tr>");
            rs.next();
                
                
                out.println("<tr><td>" + rs.getString(2) + "</td><td>" +rs.getString(4)+"</td></tr>"); 
            
            
            out.println("<tr><td> Image<input type='file' name='image' ></td></tr>");
            out.println("<tr><td><input type='submit' value='upload' > </form></td></tr>");
            out.println("</table>");
            out.println("</html></body>");
            con.close();
           }
            catch (Exception e) {
            out.println("error");
        }
    }
}
