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

}
