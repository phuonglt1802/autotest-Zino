package com.example.ZINO.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPassword {
    private WebDriver driver;
    private WebDriverWait wait;

    // Định nghĩa locators sử dụng By
    private final By emailField = By.id("email");
    private final By submitButton = By.xpath("//button[@type='submit']");
    private final By successMessage = By.cssSelector(".success-message");
    private final By errorMessage = By.cssSelector(".error-message");
    private final By backToLoginLink = By.linkText("Quay lại đăng nhập");

    /**
     * Constructor để khởi tạo WebDriver instance
     * @param driver WebDriver instance
     */
    public ForgotPassword(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
       //this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Nhập địa chỉ email để lấy lại mật khẩu
     * @param email Địa chỉ email người dùng
     * @return ForgotPasswordPage instance
     */
    public ForgotPassword enterEmail(String email) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        element.clear();
        element.sendKeys(email);
        return this;
    }

    /**
     * Click nút gửi yêu cầu
     * @return ForgotPasswordPage instance
     */
    public ForgotPassword clickSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        return this;
    }

    /**
     * Thực hiện gửi yêu cầu quên mật khẩu
     * @param email Địa chỉ email người dùng
     * @return ForgotPasswordPage instance
     */
    public ForgotPassword submitForgotPasswordRequest(String email) {
        enterEmail(email);
        return clickSubmitButton();
    }

    /**
     * Click vào link quay lại đăng nhập
     * @return LoginPage instance
     */
    public LoginTest clickBackToLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(backToLoginLink)).click();
        return new LoginTest(driver);
    }

    /**
     * Kiểm tra xem thông báo thành công có hiển thị không
     * @return true nếu thông báo thành công hiển thị
     */
    public boolean isSuccessMessageDisplayed() {
        try {
            return driver.findElement(successMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Lấy nội dung thông báo thành công
     * @return Chuỗi nội dung thông báo thành công
     */
    public String getSuccessMessageText() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return element.getText();
    }

    /**
     * Kiểm tra xem thông báo lỗi có hiển thị không
     * @return true nếu thông báo lỗi hiển thị
     */
    public boolean isErrorMessageDisplayed() {
        try {
            return driver.findElement(errorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Lấy nội dung thông báo lỗi
     * @return Chuỗi nội dung thông báo lỗi
     */
    public String getErrorMessageText() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return element.getText();
    }
}
