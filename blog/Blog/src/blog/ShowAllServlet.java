package blog;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowAllServlet extends HttpServlet {
	private static final Logger _logger = Logger.getLogger(NewPostServlet.class.getName());


	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// redirect to the jsp
		Cookie cookie = new Cookie("showAll", "true");
		cookie.setMaxAge(3);
		resp.addCookie(cookie);
		resp.sendRedirect("blog.jsp");
	}
}
