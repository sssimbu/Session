
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.io.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%  		



String name = (String)session.getAttribute("name");
session.setAttribute("name", name);

String address = (String)session.getAttribute("address");
session.setAttribute("address", address);

Class.forName("com.mysql.jdbc.Driver");

System.out.println(" loaded");

String url = "jdbc:mysql://localhost:3306/session";
String user = "root";
String password = "root";
String driverClass = "com.mysql.jdbc.Driver";

	Class.forName(driverClass);
Connection conn = DriverManager.getConnection(url, user, password);
System.out.println("Connection created");
Statement stmt = conn.createStatement();

ResultSet rs = stmt.executeQuery("select * from tsession where name='"+name+"'");%>



<div class="img">
<table align="center" border="1">

  <tr>
    <td>Profile</td>
    <td>Name</td>
    <td>Address</td>

  </tr>
   <% while (rs.next()) {%>
  <tr>
   <td><img src="<%=rs.getString("image")%>" width="150" height="150"/></td>
   <td><%=rs.getString("name") %></td>
   <td><%=rs.getString("address") %></td>
  </tr>
 <%}%>
</table>
</div>
</form>
</body>
</html>