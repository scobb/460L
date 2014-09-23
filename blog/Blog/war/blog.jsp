<%@ page import="blog.entity.*"%>
<%@ page import="blog.dao.*"%>
<%@ page import="java.util.List"%>
<%@ page import="blog.entity.*"%>
<%@ page import="com.google.appengine.api.datastore.Entity"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>Blog Posts</title>
<link rel="stylesheet" type="text/css" href="Blog.css" media="screen" />
</head>
<body id="background">
	<%
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
	%>
	<div style="float: right;">
		<%
			if (user != null) {
				pageContext.setAttribute("user_name", user.getNickname());
		%>
		<p class="lesserWhiteText">
			${fn:escapeXml(user_name)} | <b><a style="color: #FFFFFF"
				href="<%=userService.createLogoutURL(request.getRequestURI())%>">sign
					out</a></b>
		</p>
		<%
			} else {
		%>
		<a class="lesserWhiteText"
			href="<%=userService.createLoginURL(request.getRequestURI())%>">Sign
			in</a>
		<%
			}
		%>
	</div>
	<h1 class="titleWhiteText">Travel Blog</h1>

	<%
		List<BlogPost> blogPosts = BlogDAO.INSTANCE.getBlogPosts();
		if (!blogPosts.isEmpty()) {
			for (BlogPost blogPost : blogPosts) {
				pageContext.setAttribute("post_body", blogPost.getBody());

				if (blogPost.getAuthor() == null) {
					// not good--shouldn't be able to post
				}

				else {
					pageContext.setAttribute("post_author",
							blogPost.getAuthor());
					pageContext.setAttribute("post_title", blogPost
							.getTitle().toString());
	%>
	<div class="post">
		<h3 class="emphasized">${fn:escapeXml(post_title)}</h3>
		<p class="postBody">${fn:escapeXml(post_body)}</p>
		<p style="text-align: right">
			-<i>${fn:escapeXml(post_author)}</i>
		</p>
	</div>
	<%
		}
			}

		} else {
	%>
	<h3>There are no posts.</h3>
	<%
		}
	%>
</body>
</html>