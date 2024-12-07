import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import PageObjects.*;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class LoginTests {
    private WebDriver driver;
    private LoginPage loginPage;
    @Before
    public void setUp(){
        // Закомментируйте следующие 4 строчки кода, чтобы использовать только ChromeDriver

//        ChromeOptions options = new ChromeOptions();
//        options.setBinary("C:/Users/Dmitriy Uruzgaliev/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
//        System.setProperty("webdriver.chrome.driver", "C:/WebDriver/bin/yandexdriver.exe");
//        driver = new ChromeDriver(options);

        // Для тестов в YandexBrowser закомментируйте следующие 2 строчки и раскомментируйте верхние 4 строчки кода.
        System.setProperty("webdriver.chrome.driver", "C:/WebDriver/bin/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    @Test
    @Step("Открыть сайт и войти")
    @DisplayName("Войти с домашней страницы")
    public void loginFromHomePage() {
        driver.get("https://stellarburgers.nomoreparties.site");
        HomePage homePage = new HomePage(driver);
        homePage.clickButtonPersonalAccount();

        loginPage = new LoginPage(driver);
        loginPage.performLogin("aloxart@yandex.ru", "12345678");
    }
    @Test
    @Step("Открыть сайт и войти")
    @DisplayName("Вход со страницы личного кабинета")
    public void loginFromPersonalAccountPage() {
        driver.get("https://stellarburgers.nomoreparties.site");
        HomePage homePage = new HomePage(driver);
        homePage.clickButtonPersonalAccount();
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);

        loginPage = new LoginPage(driver);
        loginPage.performLogin("aloxart@yandex.ru", "12345678");
    }
    //вход через страницу регистрации
    @Test
    @Step("Открыть сайт и войти")
    @DisplayName("Войти со страницы регистрации")
    public void loginfromRegistrationPage(){
        driver.get("https://stellarburgers.nomoreparties.site/register");
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goToLoginPage();

        loginPage = new LoginPage(driver);
        loginPage.performLogin("aloxart@yandex.ru", "12345678");
    }
    @After
    public void tearDown() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isDisplayedCheckoutButton());
        driver.quit();
    }
}
