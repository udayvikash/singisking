/**
 * 
 */
package fr.tbr.junit.tests;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.tbr.iam.datamodel.Identity;

/**
 * @author tbrou
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class TestHibernate {
	
	@Autowired
	DataSource ds;
	
	@Autowired
	SessionFactory sf;
	
	@Test
	public void testDataSource() throws SQLException{
		Assert.assertNotNull(ds);
		Connection connection = ds.getConnection();
		connection.close();
	}
	
	
	
	@Test
	public void testHibernate(){
		Assert.assertNotNull(sf);
		Session session = sf.openSession();
		session.close();//TODO do not do that outside of the test case
	}
	
	
	
	@Test
	public void testHibernateSaveOrUpdate(){
		Identity identity = new Identity();
		identity.setDisplayName("Thomas");
		identity.setBirthDate(new Date());
		Session session = sf.openSession();
		session.saveOrUpdate(identity);
		
		session.close();//TODO do not do that outside of the test case
	}


}
