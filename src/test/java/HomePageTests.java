import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import PageObjects.HomePage; //
import org.openqa.selenium.chrome.ChromeOptions;


import java.time.Duration;

public class HomePageTests {
    private WebDriver driver;
    private HomePage homePage;

    @Before
    @Step("Открыть сайт")
    public void setUp() {
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
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Test
    @Step("Клик на раздел булочек")
    @DisplayName("Тестирование разделов конструктора - булочек")
    public void testClickBunsSection() {
        homePage = new HomePage(driver);
        homePage.activityCheckCurrentBunSection(); // Проверяем, активен ли раздел "Булки"
    }

    @Test
    @Step("Клик на раздел соусов")
    @DisplayName("Тестирование разделов конструктора - соусы")
    public void testClickSaucesSection() {
        homePage = new HomePage(driver);
        homePage.clickSaucesSection();
        homePage.activityCheckCurrentSauceSection(); // Проверяем, активен ли раздел "Соусы"
    }

    @Test
    @Step("Клик на раздел начинки")
    @DisplayName("Тестирование раздела конструктора - наполнение")
    public void testClickStuffingSection() {
        homePage = new HomePage(driver);
        homePage.clickStuffingSection();
        homePage.activityCheckStuffingBunSection(); // Проверяем, активен ли раздел "Начинки"
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}