<%@ page import="blog.entity.*"%>
<%@ page import="blog.dao.*"%>
<%@ page import="java.util.List"%>
<%@ page import="blog.entity.*"%>
<%@ page import="com.google.appengine.api.datastore.Entity"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ page import="javax.servlet.http.Cookie"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>Unsubscribe</title>
<link rel="stylesheet" type="text/css" href="Blog.css" media="screen" />
</head>
<body id="background">
	<%
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
	%>
	<div style="float: right;">
		<table class="darkBackground">
			<tr>
				<td class="lesserWhiteText"><b><a style="color: #FFFFFF"
						href="blog.jsp">home</a></b></td>
				<%
					if (user != null) {
						String email = user.getEmail();
						Boolean subscribed = false;
						
						List<Subscriber> subscribers = SubscriberDAO.INSTANCE
								.getSubscribers();
						for (Subscriber sub : subscribers) {
							if ((sub.getEmail().getName()).equals(email)) {
								subscribed = true;
								break;
							}
						}
				%>
				<td class="lesserWhiteText"><b><a style="color: #FFFFFF"
						href="new_post.jsp">new post</a></b></td>
				<%
					if (subscribed) {
				%>
				<td class="lesserWhiteText"><b><a style="color: #FFFFFF"
						href="unsubscribe.jsp">unsubscribe</a></b></td>
				<%
					} else {
				%>
				<td class="lesserWhiteText"><b><a style="color: #FFFFFF"
						href="subscribe.jsp">subscribe</a></b></td>
				<%
					}
						pageContext.setAttribute("user_name", user.getNickname());
				%>
				<td class="lesserWhiteText">${fn:escapeXml(user_name)} |<b><a
						style="color: #FFFFFF"
						href="<%=userService.createLogoutURL(request.getRequestURI())%>">
							sign out</a></b>
				</td>
				<%
					} else {
				%><td class="lesserWhiteText"><b><a style="color: #FFFFFF;"
						href="<%=userService.createLoginURL(request.getRequestURI())%>">Sign
							in</a></b></td>
				<%
					}
				%>
			</tr>
		</table>
	</div>
	<h1 class="titleWhiteText">Travel Blog</h1>

	<div class="darkBackground">
		<h1 class="headingWhiteText" style="color: #FFFFFF">Unsubscribe</h1>
		<form action="/unsubscribe" method="post">
			<div>
				<textarea name="email" class="form-control" rows="1"
					placeholder="Email"></textarea>
			</div>
			<br>
			<div>
				<button type="submit" class="btn btn-default" style="float: right">Unsubscribe</button>
			</div>
		</form>
	</div>
</body>
</html>