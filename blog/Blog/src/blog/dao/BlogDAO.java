package blog.dao;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import blog.entity.BlogPost;
import blog.services.PMF;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;


public enum BlogDAO {
	INSTANCE;
	
	public List<BlogPost> getBlogPosts() { 
		List<BlogPost> blogPosts;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(BlogPost.class);
		try {
			blogPosts = (List<BlogPost>) query.execute();
		}
		finally { 
			pm.close();
		}
		return blogPosts;
		
	}
	
	public boolean saveBlogPost(String author, String title, String body) { 
		/** returns true on successful save, false if post with same title already exists **/
		// TODO: using title as a key - is this okay?
		Key key = KeyFactory.createKey(BlogPost.class.getSimpleName(), title);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		// check if this title already exists
		BlogPost result = pm.getObjectById(BlogPost.class, key);
		if (result != null) {
			// post with this title already exists
			return false;
		}
		
		// create new object
		BlogPost newPost = new BlogPost(author, key, body);
		
		// now we can save
		synchronized(this) { 
			try { 
				pm.makePersistent(newPost);
			}
			catch (Exception e){
				// something went wrong
				return false;
			}
			finally { 
				pm.close();
			}
		}
		
		// success
		return true;
		
	}

}
