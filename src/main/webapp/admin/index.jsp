<%
   /* 默认index页面重定向 使浏览器重新发起请求
    首先tomcat会默认加载webapp下的index.jsp  现在tomcat中配置，然后重定向*/
    response.sendRedirect("../admin_category_list");
%>