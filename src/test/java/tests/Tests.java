package tests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProjectPage;
import java.time.Duration;

public class Tests {
    private WebDriver driver;
    private LoginPage loginPage;
    private ProjectPage projectPage;


    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\konno\\IdeaProjects\\Test_5\\src\\main\\java\\pages\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://edujira.ifellow.ru");
        loginPage = new LoginPage(driver);
        projectPage = new ProjectPage(driver);
    }

    @Test(priority = 1)
    public void testLogin() throws InterruptedException {
        loginPage.login("AT9", "Qwerty123");
        Assert.assertTrue(driver.getCurrentUrl().contains("Dashboard"), "Авторизация не выполнена");
    }

    @Test(priority = 2)
    public void testOpenProject() throws InterruptedException {
        loginPage.login("AT9", "Qwerty123");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        projectPage.openProject();
        Assert.assertTrue(driver.getCurrentUrl().contains("TEST"), "Не найден раздел TEST");
    }

    @Test(priority = 3)
    public void testTaskCounter() {
        loginPage.login("AT9", "Qwerty123");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        projectPage.openProject();
        int initialTaskCount = projectPage.getTaskCount();
        projectPage.createTask("Новая тема");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        projectPage.openProject();
        int updatedTaskCount = projectPage.getTaskCount();
        Assert.assertEquals(updatedTaskCount, initialTaskCount + 1, "Счётчик задач не обновился!");
    }

    @Test(priority = 4)
    public void testOpenTask() {
        loginPage.login("AT9", "Qwerty123");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        projectPage.openProject();
        int initialTaskCount = projectPage.getTaskCount();
        projectPage.createTask("Новая тема");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        projectPage.openProject();
        int updatedTaskCount = projectPage.getTaskCount();
        projectPage.findTask("TestSeleniumATHomework");
        projectPage.foundTask();
        String status = projectPage.checkSentense();
        Assert.assertEquals(status, "СДЕЛАТЬ", "Статус задачи неверный");
        String version = projectPage.checkVersion();
        Assert.assertEquals(version, "Version 2.0", "Версия исправления неверная");
    }

    @Test(priority = 5)
    public void testCreateBug() throws InterruptedException  {
        loginPage.login("AT9", "Qwerty123");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        projectPage.openProject();
        int initialTaskCount = projectPage.getTaskCount();
        projectPage.createTask("Новая тема");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        projectPage.openProject();
        int updatedTaskCount = projectPage.getTaskCount();
        projectPage.findTask("TestSeleniumATHomework");
        projectPage.foundTask();
        String status = projectPage.checkSentense();
        String version = projectPage.checkVersion();
        projectPage.selectErrorOption("Это баг", "новый баг");
        projectPage.createTaskBug();
        Assert.assertTrue(driver.getCurrentUrl().contains("Dashboard"), "Не создана форма бага");
    } //Я так и не понял, как реализовать закрытие статуса заданий, т.к. появляются проблемы с xpath

    @AfterClass
    public void tearDown() {
        if (driver != null) {driver.quit();}
    }
}
