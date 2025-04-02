package com.example.ZINO.model;

import com.example.Page;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class LoginTest extends Page {
    private WebDriverWait wait;

    // URL của trang đăng nhập
    private static final String LOGIN_URL = "https://demand.inotev.net/";

    // Định nghĩa locators sử dụng By thay cho @FindBy
    private final By usernameField = By.id("inp_Username");
    private final By passwordField = By.id("inp_Password");
    private final By loginButton = By.xpath("//div[@id='login_page']");
    private final By errorMessage = By.cssSelector(".notifyjs-container");
    private final By forgotPasswordLink = By.linkText("btn_Forgot_Pw");

    private final String TITLE = "ZINO";

    @Override
    public String getTitle() {
        return TITLE;
    }

    /**
     * Constructor để khởi tạo WebDriver instance
     * @param driver WebDriver instance
     */
    public LoginTest(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, 5000);
    }

    /**
     * Mở trang đăng nhập
     * @return LoginPage instance (return this để hỗ trợ method chaining)
     */
    public void openLoginTest() {
        driver.get(LOGIN_URL);
        wait.until((ExpectedCondition<Boolean>) (WebDriver driver) -> {
            return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        });
        //return this;
    }

    /**
     * Nhập username
     * @param username Tên đăng nhập
     * @return LoginPage instance
     */
    public void enterUsername(String username) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(usernameField));

        //WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        element.clear();
        element.sendKeys(username);
        //return this;
    }

    /**
     * Nhập password
     * @param password Mật khẩu
     * @return LoginPage instance
     */
    public void enterPassword(String password) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        element.clear();
        element.sendKeys(password);
        //return this;
    }

    /**
     * Click nút đăng nhập
     * @return Đối tượng của trang tiếp theo, thường là HomePage sau khi đăng nhập thành công
     */
    public void clickLoginButton() {
        //wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        WebElement submitButton = driver.findElement(By.id("btn_Submit"));
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();

        //return new HomePage(driver);
    }

    /**
     * Thực hiện quá trình đăng nhập với username và password
     * @param username Tên đăng nhập
     * @param password Mật khẩu
     * @return HomePage instance sau khi đăng nhập thành công
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    /**
     * Click vào link quên mật khẩu
     * @return ForgotPasswordPage instance
     */
    public ForgotPassword clickForgotPasswordLink() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink)).click();
        return new ForgotPassword(driver);
    }

    /**
     * Kiểm tra xem thông báo lỗi có hiển thị không
     * @return true nếu thông báo lỗi hiển thị
     */
    public boolean isErrorMessageDisplayed() {
        try {
            WebElement notifyjsContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            WebElement spanElement = notifyjsContainer.findElement(By.tagName("span"));

            String errorMessage = spanElement.getText();

            return !errorMessage.isEmpty();
            //(new WebDriverWait(driver, 20))
                 //   .until((ExpectedCondition<Boolean>) d -> driver.findElement(errorMessage).isDisplayed());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Lấy nội dung thông báo lỗi
     * @return Chuỗi nội dung thông báo lỗi
     */
    public String getErrorMessageText() {
        try
        {
            WebElement notifyjsContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            WebElement spanElement = notifyjsContainer.findElement(By.tagName("span"));

            return spanElement.getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Kiểm tra xem người dùng đã ở trang đăng nhập chưa
     * @return true nếu đang ở trang đăng nhập
     */
    public boolean isAt() {
        return driver.getCurrentUrl().contains(LOGIN_URL);
    }
}