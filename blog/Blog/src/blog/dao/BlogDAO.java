package blog.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import blog.entity.BlogPost;
import blog.services.PMF;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;


public enum BlogDAO
{
	INSTANCE;

	public List<BlogPost> getBlogPosts()
	{
		List<BlogPost> blogPosts;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(BlogPost.class);
		try
		{
			blogPosts = (List<BlogPost>) query.execute();
		} finally
		{
			pm.close();
		}
		return blogPosts;

	}
	
	public String getBlogPost(String title){

		Key key = KeyFactory.createKey(BlogPost.class.getSimpleName(), title);
		
		PersistenceManager pm1 = PMF.get().getPersistenceManager();
		BlogPost found = null;
		try
		{
			found = pm1.getObjectById(BlogPost.class, key);
		}
		catch (Exception e){
			// something went wrong
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			return "Exception in search: " + sw.toString(); // stack trace as a string
			
		}
		finally
		{
			pm1.close();
		}
		if (found != null) { 
			return "Successful search";
		}
		return "Unsuccessful search";
	}

	public String saveBlogPost(String author, String title, String body)
	{
		/**
		 * returns true on successful save, false if post with same title already
		 * exists
		 **/
		// TODO: using title as a key - is this okay?
		Key key = KeyFactory.createKey(BlogPost.class.getSimpleName(), title);

		// check if this title already exists

		// create new object
		BlogPost newPost = new BlogPost(author, key, body);

		

		// now we can save
		synchronized (this)
		{

			PersistenceManager pm = PMF.get().getPersistenceManager();

			BlogPost result = null;
			try
			{
				pm.makePersistent(newPost);
				// result = pm.getObjectById(BlogPost.class, key);
			} catch (Exception e)
			{
				// something went wrong
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				return "Exception in add: " + sw.toString(); // stack trace as a string
			} finally
			{
				pm.close();

			}

		}

		// success
		return "Successfully persisted, didn't successfully search";

	}

}
