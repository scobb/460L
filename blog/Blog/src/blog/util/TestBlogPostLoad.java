package blog.util;

import java.util.List;

import blog.dao.BlogDAO;
import blog.entity.BlogPost;

/** 
 * @author Steve Cobb 
 * scc2448
 * Sep 20, 2014
 * */
public class TestBlogPostLoad
{
	public static boolean addPosts(){
		String author = "Steve Cobb";
		String title = "1";
		String body = "Test body";
		
		
		return BlogDAO.INSTANCE.saveBlogPost(author, title, body);
	}
	public static String markAsNotNew() { 
		BlogPost bp = BlogDAO.INSTANCE.getBlogPost("1");
		if ( BlogDAO.INSTANCE.markAsNotNew(bp) ) { 
			return "Marked as not new.";
		}
		return "Marking as not new FAILED";
	}
	public static String getBlogPost() {
		return BlogDAO.INSTANCE.getBlogPost("1").displayHtml();
	}
	
	public static String getBlogPosts() { 
		String retStr = "";
		List<BlogPost> posts = BlogDAO.INSTANCE.getBlogPosts();
		for (BlogPost post: posts) { 
			retStr += post.displayHtml();
		}
		
		return retStr;
	}
	
	public static String deleteAllPosts() { 
		return BlogDAO.INSTANCE.deleteAllPosts();
	}
	
	public static String getNewPosts() { 
		List<BlogPost> newPosts = BlogDAO.INSTANCE.getNewBlogPosts();
		if (newPosts != null) { 
			String ret_str = "";
			for (BlogPost post : newPosts) { 
				ret_str += post.displayHtml();
			}
			return ret_str;
		}
		return "No new posts.";
	}

}
