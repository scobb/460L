package blog;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.dao.BlogDAO;
import blog.dao.SubscriberDAO;
import blog.entity.BlogPost;
import blog.entity.Subscriber;

public class SendEmailServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// TODO: called by crontask - should send e-mail of all "new" posts to all subscribers, then mark all posts as not new
		List<Subscriber> subscribers = SubscriberDAO.INSTANCE.getSubscribers();
		List<BlogPost> newPosts = BlogDAO.INSTANCE.getNewBlogPosts();
		
		String emailBody = "Hello subscriber,\nHere are the new blog posts from our travel blog:\n";
		
		for (BlogPost post: newPosts) { 
			// TODO : do we actually want to send html here? or some plaintext version
			emailBody += post.displayHtml();
			
			// persist that this blog post is no longer new
			BlogDAO.INSTANCE.markAsNotNew(post);
		}
		
		for (Subscriber subscriber: subscribers) {
			// TODO: send email
		}
		
		
		

	}
}
