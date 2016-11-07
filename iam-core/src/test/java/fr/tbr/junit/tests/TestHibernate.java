/**
 * 
 */
package fr.tbr.junit.tests;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.tbr.iamcore.datamodel.Identity;
import fr.tbr.iamcore.exception.DAOSaveException;
import fr.tbr.iamcore.exception.DAOUpdateException;
import fr.tbr.iamcore.service.dao.DAODeleteException;
import fr.tbr.iamcore.service.dao.IdentityDAOInterface;

/**
 * @author tbrou
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class TestHibernate {

	@Inject
	DataSource ds;

	@Inject
	SessionFactory sf;

	@Inject
	IdentityDAOInterface dao;

	@Test
	public void testDataSource() throws SQLException {
		Assert.assertNotNull(ds);
		Connection connection = ds.getConnection();
		connection.close();
	}

	@Test
	public void testHibernate() {
		Assert.assertNotNull(sf);
		Session session = sf.openSession();
		session.close();// TODO do not do that outside of the test case
	}

	@Test
	public void testHibernateSaveOrUpdate() {
		Identity identity = new Identity();
		identity.setDisplayName("Thomas");
		identity.setBirthDate(new Date());
		Session session = sf.openSession();
		session.saveOrUpdate(identity);

		session.close();// TODO do not do that outside of the test case
	}

	@Test
	public void testHibernateAllInARow() {
		Identity identity = new Identity();

		identity.setDisplayName("Thomas" + Math.random());
		identity.setBirthDate(new Date());
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(identity);
		tx.commit();

		tx = session.beginTransaction();
		identity.setEmail("tbr@tbr.com");
		session.update(identity);
		tx.commit();

		tx = session.beginTransaction();
		session.delete(identity);
		tx.commit();

		session.close();// TODO do not do that outside of the test case
	}
	
	@Test
	public void testHibernateQueryLanguage() {
		String hqlString = "from Identity";

		Session session = sf.openSession();
		Query query = session.createQuery(hqlString);
		List<Object> list = query.list();
		System.out.println(list);
		
		session.close();// TODO do not do that outside of the test case
	}


	@Test
	public void testHibernateDAO() throws DAOSaveException, DAOUpdateException, DAODeleteException {
		Identity identity = new Identity();
		identity.setDisplayName("Thomas" + Math.random());
		identity.setBirthDate(new Date());
		dao.save(identity);
		identity.setEmail("cserr@tbr.com");
		dao.update(identity);
		dao.delete(identity);

	}

}
