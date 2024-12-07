import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import PageObjects.LoginPage;
import PageObjects.RegistrationPage;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class RegistrationTests {
    static
    Faker faker = new Faker();
    private WebDriver driver;
    private final String name;
    private final String email;
    private final String password;

    public RegistrationTests(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    @Parameterized.Parameters(name = "name: {0}, email: {1}, password: {2}")
    public static Object[][] questionsAndAnswers() {
        return new Object[][]{
                {faker.name().firstName(), faker.internet().emailAddress(), faker.internet().password(6,10)}, //успешная регистрация
                {faker.name().firstName(), faker.internet().emailAddress(), faker.internet().password(3,5)}, //регистрация не произойдет - появится сообщение "Некорректный пароль" (пароль менее 6 символов)
        };
    }
    @Before
    @Step("Open site")
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }
    @Test
    @DisplayName("1 successfully and 1 unsuccessfully (password shorter than 6 symbols) registration")
    public void setData() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registerUser(name, email, password);
        LoginPage loginPage = new LoginPage(driver);
        if (password.length() >= 6) {
            assertTrue(loginPage.isLoginHeaderDisplayed());
        } else {
            assertEquals("Некорректный пароль", driver.findElement(By.xpath(".//p[text()='Некорректный пароль']")).getText());
        }
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}