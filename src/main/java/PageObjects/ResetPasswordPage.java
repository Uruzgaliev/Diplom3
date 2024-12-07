package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResetPasswordPage {
    private final WebDriver webDriver;
    private final By recoveryButton = By.className("Auth_link__1fOlj");

    public ResetPasswordPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void clickRecoveryButton() {
        webDriver.findElement(recoveryButton).click();
    }
}
