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
		String body = req.getParameter("body");
		String title = req.getParameter("title");
		if (body == null)
			throw new IOException("Post body is required.");
		if (title == null)
			throw new IOException("Title is required.");
		try {
			if (!body.isEmpty() && !title.isEmpty()) {
				// create post object
				if (!BlogDAO.INSTANCE.saveBlogPost(author, title, body)) {
					// Handle case where title already exists... maybe as simple as a redirect and ask for a new title?
				};
			}
		} catch (Exception e) {
			throw new IOException(e.getMessage());

		}
	}
}
