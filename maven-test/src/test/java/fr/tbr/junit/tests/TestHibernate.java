/**
 * 
 */
package fr.tbr.junit.tests;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author tbrou
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class TestHibernate {
	
	@Autowired
	DataSource ds;
	
	
	@Test
	public void testDataSource() throws SQLException{
		Assert.assertNotNull(ds);
		Connection connection = ds.getConnection();
		connection.close();
	}
	
	
	
//	@Test
//	public void testHibernate(){
//		
//	}

}
