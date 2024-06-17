package com.app.tester;
import java.time.LocalDate;
import static com.app.utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;
import org.hibernate.type.LocalDateTimeType;

import com.app.dao.BlogPostDao;
import com.app.dao.BlogPostDaoImpl;
import com.app.dao.CategoryDaoImpl;
import com.app.dao.UserDao;
import com.app.dao.UserDaoImpl;
import com.app.entities.BlogPost;
import com.app.entities.Category;
import com.app.entities.Role;
import com.app.entities.User;
import static java.time.LocalDate.parse;
import static com.app.entities.Role.valueOf;
/*
 * Sample data
 * a1 b1 a1@gmail.com 1234 1990-10-20 345678909 admin 0
 * a2 b2 a2@gmail.com 2234 1991-10-20 675678909 blogger 1000
 */
/*
 * --------------+--------------+------+-----+---------+----------------+
| id           | bigint       | NO   | PRI | NULL    | auto_increment |
| creationDate | date         | YES  |     | NULL    |                |
| updatedOn    | date         | YES  |     | NULL    |                |
| content      | varchar(500) | YES  |     | NULL    |                |
| description  | varchar(100) | YES  |     | NULL    |                |
| title        | varchar(50)  | YES  | UNI | NULL    |                |
| author_id    | bigint       | NO   | MUL | NULL    |                |
| category_i
 * 
 * 
 */
public class AddNewBlogPostTester {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory();
				Scanner sc = new Scanner(System.in)) {
			//create user dao instance
			
			CategoryDaoImpl cat=new CategoryDaoImpl();
			Category category=cat.getCategory("Lifestyle & Wellness");
			//String firstName, String lastName, String email, String password, LocalDate dob, String phoneNo,
			//Role role
			UserDaoImpl  userDao=new UserDaoImpl();
			User user=userDao.authenticateUser("a2@gmail.com","123");
			BlogPostDao blogPost=new BlogPostDaoImpl();
			System.out
					.println("Enter user details - title,  " + "description,  content");
			
			//String title, String description, String content, Category chosenCategory, User author) {
		
			BlogPost transientUser = new BlogPost(sc.nextLine(), sc.nextLine(), sc.nextLine(),category,user);
			
			System.out.println(blogPost.addNewBlogPost(transientUser));
		} // JVM sf.close() => cleaning up of DBCP
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
