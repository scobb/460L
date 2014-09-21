package blog;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendEmailServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// TODO: called by crontask - should send e-mail of all "new" posts to all subscribers, then mark all posts as not new
		
		
		
		

	}
}
