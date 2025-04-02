package com.example.ZINO.model;

import com.example.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageNew extends Page {

    private final String TITLE = "Tất cả tin tức";

    /**
     * Constructor
     *
     * @param driver
     */
    public HomePageNew(WebDriver driver) {
        super(driver);
    }

    /* (non-Javadoc)
     * @see com.example.model.hotmail.Page#getTitle()
     */
    @Override
    public String getTitle() {
        return TITLE;
    }


}