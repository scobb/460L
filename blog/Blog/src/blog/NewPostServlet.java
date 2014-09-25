package blog;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.dao.BlogDAO;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class NewPostServlet extends HttpServlet {
	private static final Logger _logger = Logger.getLogger(NewPostServlet.class.getName());

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		String author = null;
		if (user != null) {
			// get poster's name
			author = user.getNickname();
		} else {
			// User is anonymous and cannot post; should not be on this page
			resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
		}
		String body = "";
		body = req.getParameter("body");
		String title = "";
		title = req.getParameter("title");
		if (body.equals("") || title.equals(""))
			resp.sendRedirect("/post_unsuccessful.jsp");
		try {
			if (!body.isEmpty() && !title.isEmpty()) {
				// create post object
				if (!BlogDAO.INSTANCE.saveBlogPost(author, title, body)) {
					resp.sendRedirect("/post_unsuccessful.jsp");
				};
			}
		} catch (Exception e) {
			throw new IOException(e.getMessage());

		}
		resp.sendRedirect("/post_success.jsp");
	}
}
