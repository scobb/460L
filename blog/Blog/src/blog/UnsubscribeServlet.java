package blog;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.dao.SubscriberDAO;

public class UnsubscribeServlet extends HttpServlet{

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String email = req.getParameter("email");
		if (!SubscriberDAO.INSTANCE.removeSubscriber(email)) {
			// TODO: redirect to page that directed us here, say email wasn't found
		}
		
		// TODO: redirect to confirmation page
		
	}

}
