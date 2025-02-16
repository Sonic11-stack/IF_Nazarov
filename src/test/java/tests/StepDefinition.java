package tests;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.cucumber.java.ru.И;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import pages.LoginPage;
import pages.ProjectPage;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class StepDefinition {
    private LoginPage loginPage = new LoginPage();
    private ProjectPage projectPage = new ProjectPage();
    private int initialTaskCount;
    private int updatedTaskCount;

    @Допустим("я открыл главную страницу Jira")
    public void openJira() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\konno\\IdeaProjects\\Test_5\\src\\driver\\chromedriver.exe");
        Configuration.browser = "chrome";
        open("https://edujira.ifellow.ru");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        loginPage = new LoginPage();
        projectPage = new ProjectPage();
    }

    @Когда("ввожу логин {string} и пароль {string} в специальные формы")
    public void enterCredentials(String login, String password) {loginPage.login(login, password);}

    @Тогда("я вижу страницу с url-адресом, в котором присутствует Dashboard")
    public void verifyDashboard() {Assert.assertTrue(url().contains("Dashboard"), "Авторизация не выполнена");}

    @Допустим("я нахожусь в Jira")
    public void inJira() {if (!url().contains("Dashboard")) {throw new AssertionError("Ссылка не содержит Dashboard! Текущий URL: " + url());}}

    @Когда("открываю раздел Test \\(TEST)")
    public void enterTest() {projectPage.openProject();}

    @Тогда("я вижу страницу проекта")
    public void seeProject() {Assert.assertTrue(url().contains("TEST"), "Не найден раздел TEST");}

    @Допустим("я нахожусь в разделе Test \\(TEST)")
    public void inTest() {if (!url().contains("TEST")) {throw new AssertionError("Ссылка не содержит TEST! Текущий URL: " + url());}}

    @Когда("создаю задачу с названием Новая Тема")
    public void makeTask() {
        initialTaskCount = projectPage.getTaskCount();
        projectPage.createTask("Новая тема");
        projectPage.openProject();
        updatedTaskCount = projectPage.getTaskCount();
    }

    @И("закрываю статус задачи до Выполнено")
    public void closeThisTask() {projectPage.closeTask();}

    @Тогда("количество задач должно быть увеличено")
    public void seeCountTasks() {Assert.assertEquals(updatedTaskCount, initialTaskCount + 1, "Счётчик задач не обновился!");}

    @Допустим("я нахожусь в разделе Dashboard")
    public void inTest1() {if (!url().contains("Dashboard")) {throw new AssertionError("Ссылка не содержит TEST! Текущий URL: " + url());}}

    @Когда("ввожу в поисковик слово {string}")
    public void searchWord(String searchName) {
        projectPage.findTask(searchName);
        projectPage.foundTask();
    }

    @Тогда("статус должен быть СДЕЛАТЬ")
    public void checkStatus() {
        String status = projectPage.checkSentense();
        Assert.assertEquals(status, "СДЕЛАТЬ", "Статус задачи неверный");
    }

    @И("версия должна быть Version 2.0.")
    public void checkVersion() {
        String version = projectPage.checkVersion();
        Assert.assertEquals(version, "Version 2.0", "Версия исправления неверная");
    }

    @Допустим("я нахожусь в разделе TestSeleniumATHomework")
    public void inTestSelenium() {if (!url().contains("TEST-121544")) {throw new AssertionError("Ссылка не содержит TestSeleniumATHomework! Текущий URL: " + url());}}

    @Когда("создаю форму Бага: название {string}, описание {string}")
    public void createTaskBug(String nameTask, String description) {
        projectPage.selectErrorOption(nameTask, description);
        projectPage.createTaskBug();
    }

    @Тогда("автоматически закрываю форму")
    public void addInfo() {projectPage.closeBugTask();}

    @И("проверяю, возвращен ли на главную страницу")
    public void closeBug() {Assert.assertTrue(url().contains("Dashboard"), "Не создана форма бага");}

    @AfterClass
    public void tearDown() {closeWebDriver();}
}