package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import pages.LoginPage;
import pages.ProjectPage;

public class Tests {
    private LoginPage loginPage;
    private ProjectPage projectPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\konno\\IdeaProjects\\Test_5\\src\\driver\\chromedriver.exe");
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        open("https://edujira.ifellow.ru");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        loginPage = new LoginPage();
        projectPage = new ProjectPage();
    }

    @Test(priority = 1)
    public void testLogin() {
        loginPage.login("AT9", "Qwerty123");
        Assert.assertTrue(url().contains("Dashboard"), "Авторизация не выполнена");
    }

    @Test(priority = 2)
    public void testOpenProject() {
        loginPage.login("AT9", "Qwerty123");
        projectPage.openProject();
        Assert.assertTrue(url().contains("TEST"), "Не найден раздел TEST");
    }

    @Test(priority = 3)
    public void testTaskCounter() {
        loginPage.login("AT9", "Qwerty123");
        projectPage.openProject();
        int initialTaskCount = projectPage.getTaskCount();
        projectPage.createTask("Новая тема");
        projectPage.openProject();
        int updatedTaskCount = projectPage.getTaskCount();
        projectPage.closeTask();
        Assert.assertEquals(updatedTaskCount, initialTaskCount + 1, "Счётчик задач не обновился!");
    }

    @Test(priority = 4)
    public void testOpenTask() {
        loginPage.login("AT9", "Qwerty123");
        projectPage.openProject();
        projectPage.createTask("Новая тема");
        projectPage.openProject();
        projectPage.closeTask();
        projectPage.findTask("TestSeleniumATHomework");
        projectPage.foundTask();
        String status = projectPage.checkSentense();
        Assert.assertEquals(status, "СДЕЛАТЬ", "Статус задачи неверный");
        String version = projectPage.checkVersion();
        Assert.assertEquals(version, "Version 2.0", "Версия исправления неверная");
    }

    @Test(priority = 5)
    public void testCreateBug() {
        loginPage.login("AT9", "Qwerty123");
        projectPage.openProject();
        projectPage.createTask("Новая тема");
        projectPage.openProject();
        projectPage.closeTask();
        projectPage.findTask("TestSeleniumATHomework");
        projectPage.foundTask();
        projectPage.selectErrorOption("Это баг", "Новый баг");
        projectPage.createTaskBug();
        Assert.assertTrue(url().contains("Dashboard"), "Не создана форма бага");
    }

    @AfterClass public void tearDown() {closeWebDriver();}
}
