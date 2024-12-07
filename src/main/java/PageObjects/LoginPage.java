package PageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver webDriver;
    private final By emailInputField = By.xpath(".//input[@type='text' and @name='name']");
    private final By passwordInputField = By.xpath(".//input[@type='password']");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By loginHeaderText = By.xpath(".//h2[text()='Вход']");

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Perform login")
    public void performLogin(String email, String password) {
        webDriver.findElement(emailInputField).sendKeys(email);
        webDriver.findElement(passwordInputField).sendKeys(password);
        webDriver.findElement(loginButton).click();
    }

    @Step("Check if the header text 'Вход' is displayed")
    public boolean isLoginHeaderDisplayed() {
        return webDriver.findElement(loginHeaderText).isDisplayed();
    }
}