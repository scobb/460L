<%@page import="blog.util.TestBlogPostLoad"%>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Blogzilla</title>
  </head>

  <body>
    <h1>Initial Data Loader</h1>
    <%=TestBlogPostLoad.addPosts()%>
    <hr>
    
    <%=TestBlogPostLoad.getBlogPosts()%>
    <a href="Blog.html">Back to Home</a>
  </body>
</html>