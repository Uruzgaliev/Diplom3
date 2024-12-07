package PageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    private final WebDriver webDriver;
    private final By nameInputField = By.xpath(".//*[text()='Имя']/../*[@type='text']");
    private final By emailInputField = By.xpath("//*[text()='Email']/../*[@type='text']");
    private final By passwordInputField = By.xpath(".//*[@type='password']");
    private final By registerButton = By.xpath(".//*[text()='Зарегистрироваться']");
    private final By loginPageLink = By.className("Auth_link__1fOlj");

    public RegistrationPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Регистрация пользователя")
    public void registerUser(String name, String email, String password) {
        webDriver.findElement(nameInputField).sendKeys(name);
        webDriver.findElement(emailInputField).sendKeys(email);
        webDriver.findElement(passwordInputField).sendKeys(password);
        webDriver.findElement(registerButton).click();
    }

    @Step("Перейти на страницу входа")
    public void goToLoginPage() {
        webDriver.findElement(loginPageLink).click();
    }
}

