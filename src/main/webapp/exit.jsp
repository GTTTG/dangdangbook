<%
session.removeAttribute("logined");
response.sendRedirect("login.jsp");
%>