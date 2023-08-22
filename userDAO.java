package rkm.ecom;

import java.io.IOException;
import java.util.Date;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class userDAO {

	   @Autowired
	    private SessionFactory sessionFactory;

	    public User findUser(String username) {
	        try {
	            String sql = "Select e from " + User.class.getName() + " e Where e.username =:username ";

	            Session session = this.sessionFactory.getCurrentSession();
	            Query<User> query = session.createQuery(sql, User.class);
	            query.setParameter("userName", username);
	            return (User) query.getSingleResult();
	        } catch (NoResultException e) {
	            return null;
	        }
	    }
	    
	    
	    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	    public void saveUser(User user) {

	        Session session = this.sessionFactory.getCurrentSession();
	        String userName = user.getUsername();

	        User usernew = new User();

	        boolean isNew = false;
	        
	        if (userName != null) {
	        	
	        	isNew = true;
	       	    System.out.println("Inside  userDAO.saveUser method for userName "+userName); 

	            //usernew = this.findUser(userName);
	       	 try {
	 	        usernew.setUserName(userName);
	 	        usernew.setFirstName(user.getFirstName());
	 	        usernew.setLastName(user.getLastName());
	 	        usernew.setAddressLine1(user.getAddressLine1());
	 	        usernew.setAddressLine2(user.getAddressLine2());
	 	        usernew.setZipCode(user.getZipCode());
	 	        usernew.setCity(user.getCity());
	 	        usernew.setState(user.getState());
	 	        usernew.setEmail(user.getEmail());
	 	        usernew.setCountry(user.getCountry());
	 	        usernew.setPassword(user.getPassword());
	 	        usernew.setMobile(user.getMobile());
	 	        usernew.setRole(user.getRole());

	 	   	      
	 	        if (isNew) {
	 	            session.persist(usernew);
	 	        }
	 	        }
	 	        catch(Exception e)
	 	        {
	 	        	System.out.println(e.getMessage());
	 	        }

	        }
	        if (userName == null) {
	        	
	       	    System.out.println("Inside  userDAO.saveUser method for userName is null : "+userName); 

	        	
	       	    System.out.println("Start Calling userDAO.saveUser method in userDAO "); 

	            isNew = true;
	            usernew = new User();
	         }
	        
	        	        // If error in DB, Exceptions will be thrown out immediately
	        //session.flush();
	    }



}
