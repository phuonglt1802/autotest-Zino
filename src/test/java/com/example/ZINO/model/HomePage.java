package com.example.ZINO.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	private WebDriver driver;
	private WebDriverWait wait;

	// Sử dụng By thay vì @FindBy
	private final By userProfileIcon = By.cssSelector(".user-profile");
	private final By logoutButton = By.cssSelector(".logout-button");
	private final By dashboardTitle = By.cssSelector(".dashboard-title");

	// Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 5000);
	}
	public boolean isAt() {
		return driver.getTitle().contains("Home");
	}
	// Kiểm tra xem đã đăng nhập thành công chưa
	public boolean isLoggedIn() {
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(userProfileIcon)) != null;
		} catch (Exception e) {
			return false;
		}
	}

	// Thực hiện đăng xuất
	public LoginTest logout() {
		wait.until(ExpectedConditions.elementToBeClickable(userProfileIcon));
		driver.findElement(userProfileIcon).click();

		wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
		driver.findElement(logoutButton).click();

		return new LoginTest(driver);
	}

	// Lấy tiêu đề dashboard
	public String getDashboardTitle() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardTitle));
		return driver.findElement(dashboardTitle).getText();
	}
}