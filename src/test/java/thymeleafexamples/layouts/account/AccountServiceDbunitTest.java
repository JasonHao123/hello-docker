package thymeleafexamples.layouts.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;

import javax.sql.DataSource;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import thymeleafexamples.layouts.config.ApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@TestComponent
@ContextConfiguration(classes = {ApplicationConfig.class})
public class AccountServiceDbunitTest {
	
	private IDatabaseTester databaseTester;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private AccountService accountService;
	
	@Before
	public void setup() throws Exception {
		
		databaseTester = new DataSourceDatabaseTester(dataSource);
		IDataSet dataSet = new FlatXmlDataSetBuilder().build(new File("src/test/resources/dataset.xml"));
        databaseTester.setDataSet( dataSet );
	// will call default setUpOperation
        databaseTester.onSetup();
	}
	
	@After
	public void tearDown() throws Exception
    {
	// will call default tearDownOperation
        databaseTester.onTearDown();
    }
	
	@Test
	public void testCreateAccount() {
		Account account = new Account();
		account.setEmail("jason2@example.com");
		account.setPassword("helloworld");
		accountService.save(account);
		
		try {
			assertEquals(databaseTester.getConnection().getRowCount("account"),2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
		}
	}
}
