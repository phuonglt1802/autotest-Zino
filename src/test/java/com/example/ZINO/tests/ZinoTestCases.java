package com.example.ZINO.tests;

import com.example.ZINO.model.*;
import junit.framework.TestCase;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


/**
 * The ZinoTestCases class represents sample tests for
 * Zino page.
 *
 * @author phuongkute
 *
 */
public class ZinoTestCases extends TestCase {
	private static WebDriver driver = null;
	private LoginTest loginTest;
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win32\\chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-gpu");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();

		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	/**
	 * Login successful with proper username and password
	 */
	public void testValidLogin() {
		// Khởi tạo Page Object
		loginTest = new LoginTest(driver);
		HomePageNew hp = new HomePageNew(driver);

		// Điều hướng đến trang đăng nhập
		loginTest.openLoginTest();

		// Thực hiện đăng nhập với thông tin hợp lệ
		loginTest.login("cbk", "cbk");

		hp.waitForPage();
		// verify that home page is visible
		Assert.assertTrue(hp.isPresent());
	}

	/**
	 * Login failed with incorrect username or password.
	 */
	public void testInvalidLogin() {
		// Khởi tạo Page Object
		loginTest = new LoginTest(driver);

		// Điều hướng đến trang đăng nhập
		loginTest.openLoginTest();

		// Thực hiện đăng nhập với thông tin không hợp lệ
		loginTest.login("aaa", "1234");
//		loginTest.enterUsername("aaa");
//		loginTest.enterPassword("1234");
//		loginTest.clickLoginButton();

		// Kiểm tra thông báo lỗi hiển thị đúng nội dung
		Assert.assertTrue("Thông báo lỗi không hiển thị khi đăng nhập không thành công", loginTest.isErrorMessageDisplayed());
		Assert.assertEquals("Không thể đăng nhập, vui lòng kiểm tra lại tên đăng nhập và mật khẩu",
				loginTest.getErrorMessageText());
	}

	/**
	 * Login failed when the username and password are empty
	 */
	public void testEmptyFields() {
		// Khởi tạo Page Object
		loginTest = new LoginTest(driver);

		// Điều hướng đến trang đăng nhập
		loginTest.openLoginTest();

		// Thực hiện đăng nhập không nhập thông tin
		loginTest.login("", "");

		//WebDriverWait wait = new WebDriverWait(driver, 500);
		//wait.until(ExpectedConditions.alertIsPresent());

		// Kiểm tra thông báo lỗi hiển thị đúng nội dung
		Assert.assertTrue("Thông báo lỗi không hiển thị khi thông tin đăng nhập trống",
                loginTest.isErrorMessageDisplayed());
		Assert.assertEquals("Không thể đăng nhập, vui lòng kiểm tra lại tên đăng nhập và mật khẩu",
				loginTest.getErrorMessageText());
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	public void tearDown() throws Exception {
		// TODO
//		if (driver != null) {
//			driver.quit();
//		}
	}

}
