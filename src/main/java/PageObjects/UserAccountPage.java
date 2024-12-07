package PageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserAccountPage {
    private final WebDriver webDriver;
    private final By logoutButton = By.xpath(".//button[text()='Выход']");
    private final By constructorHeaderLink = By.xpath(".//p[text()='Конструктор']");
    private final By homeLogo = By.xpath(".//a[@href='/']");
    private final By assembleBurgerHeading = By.xpath(".//h1[text()='Соберите бургер']");

    public UserAccountPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Выйти из учетной записи")
    public void clickLogoutButton() {
        webDriver.findElement(logoutButton).click();
    }

    @Step("Перейти к конструктору")
    public void clickConstructorHeader() {
        webDriver.findElement(constructorHeaderLink).click();
    }

    @Step("Перейдите на домашнюю страницу с помощью логотипа")
    public void clickHomeLogo() {
        webDriver.findElement(homeLogo).click();
    }

    public boolean isAssembleBurgerHeadingDisplayed() {
        return webDriver.findElement(assembleBurgerHeading).isDisplayed();
    }
}
