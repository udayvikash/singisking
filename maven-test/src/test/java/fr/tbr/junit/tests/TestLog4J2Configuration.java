/**
 * 
 */
package fr.tbr.junit.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * @author tbrou
 *
 */
public class TestLog4J2Configuration {
	private static final Logger logger = LogManager.getLogger(TestLog4J2Configuration.class);
	
	@Test
	public void testInit(){
		logger.debug("test debug");
		logger.info("test info");
		logger.trace("low level");
		logger.error("error test");
		
		logger.de
		
	}
	

}
