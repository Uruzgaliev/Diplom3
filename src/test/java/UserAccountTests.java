import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.UserAccountPage;

import java.time.Duration;

public class UserAccountTests {
    private WebDriver driver;
    private LoginPage userLoginPage;

    @Before
    @Step("Инициализация браузера, переход на сайт и вход в систему")
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        driver.get("https://stellarburgers.nomoreparties.site/");

        HomePage mainPage = new HomePage(driver);
        mainPage.clickButtonPersonalAccount();

        userLoginPage = new LoginPage(driver);
        userLoginPage.performLogin("aloxart@yandex.ru", "12345678");

        mainPage.clickButtonPersonalAccount();
    }

    @Test
    @DisplayName("Выйти из учетной записи пользователя")
    public void logoutTest() throws InterruptedException {
        UserAccountPage accountPage = new UserAccountPage(driver); //
        accountPage.clickLogoutButton();
        userLoginPage = new LoginPage(driver);
        Assert.assertTrue(userLoginPage.isLoginHeaderDisplayed());
    }

    @Test
    @DisplayName("Перейдите из учетной записи пользователя в конструктор по ссылке «Конструктор».")
    public void goToConstructorFromAccount() {
        UserAccountPage accountPage = new UserAccountPage(driver);
        accountPage.clickConstructorHeader();
        Assert.assertTrue(accountPage.isAssembleBurgerHeadingDisplayed());
    }

    @Test
    @DisplayName("Перейдите из учетной записи пользователя в конструктор, щелкнув по логотипу")
    public void goToConstructorFromLogo() {
        UserAccountPage accountPage = new UserAccountPage(driver);
        accountPage.clickHomeLogo();
        Assert.assertTrue(accountPage.isAssembleBurgerHeadingDisplayed());
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}
