<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Hello Gopi! My first JSP page
	
	<%!
		public int add(int a, int b) {
			return a+b;
		}
	
	%>
	
	<% 
		int i = 10; int j =20;
		int k = add(i,j);
		int l = i+j+k;
		out.println("Value of k : " + k);
	%>
	Value of L : <%=l %> Value of M : <%=add(k,l) %>
	
	<% for(i=0;i<5;i++){ %>
		<br>	
		I is <%=i %>
	<% } %>
	
</body>
</html>