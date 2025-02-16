package pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys; // Это использовалось только для того, чтобы переключиться с типа задачи "Задача" на "Ошибка"

import java.time.Duration;

public class ProjectPage extends BasePage {
    private final SelenideElement projectsClick = $(byText("Проекты"));
    private final SelenideElement testProjectLink = $(byText("Test (TEST)"));
    private final SelenideElement searchTaskButton = $("#quickSearchInput");
    private final SelenideElement clickSearchTaskButton = $x("//li[a/span[text()='TestSeleniumATHomework']]/a");
    private final SelenideElement checkWordsTask = $x("//*[contains(text(), 'Сделать')]");
    private final SelenideElement checkVersionTask = $x("//*[contains(text(), 'Version 2.0')]");
    private final SelenideElement clickTask = $("#create-menu");
    private final SelenideElement nameTask = $("#summary");
    private final SelenideElement clickMakeTask = $("#create-issue-submit");
    private final SelenideElement taskCounter = $x("//div[@class='issue-tools']//div[@class='showing']/span");
    private final SelenideElement clickVersion = $x("//select[@id='fixVersions']//option[contains(text(), 'Version 2.0')]");
    private final SelenideElement clickVersion1 = $x("//select[@id='versions']//option[contains(text(), 'Version 2.0')]");
    private final SelenideElement clickMainPage = $x("//span[@id='logo']//a[@href='https://edujira.ifellow.ru/secure/MyJiraHome.jspa']");
    private final SelenideElement inWorking = $(byText("В работе"));
    private final SelenideElement buisnessProcess = $("#opsbar-transitions_more");
    private final SelenideElement isDone = $(byText("Выполнено"));
    private final SelenideElement writeTag = $("#labels-textarea");
    private final SelenideElement clickTag = $("#labels-multi-select");
    private final SelenideElement clickListTask = $("#find_link");

    public void openProject() {
        projectsClick.click();
        testProjectLink.click();
    }

    public void findTask(String searchTask) {searchTaskButton.setValue(searchTask);}

    public void foundTask() {clickSearchTaskButton.click();}

    public String checkSentense() {return checkWordsTask.getText();}

    public String checkVersion() {return checkVersionTask.getText();}

    public int getTaskCount() {return Integer.parseInt(taskCounter.getText().split(" ")[2]);}

    public void createTask(String taskName) {
        clickTask.click();
        nameTask.setValue(taskName);
        clickMakeTask.click();
    }

    public void closeTask() {
        clickListTask.click();
        $x("//div[@id='issues_history_main']//li[2]//a").click();
        inWorking.shouldBe(visible, Duration.ofSeconds(4)).click();
        sleep(2000);
        buisnessProcess.click();
        sleep(2000);
        isDone.click();
        clickMainPage.click();
    }

    public void createTaskBug() {
        clickVersion.click();
        clickVersion1.click();
        clickMakeTask.click();
    }

    public void closeBugTask() {
        clickMainPage.click();
        clickListTask.click();
        $x("//div[@id='issues_history_main']//li[1]//a").click();
        inWorking.shouldBe(visible, Duration.ofSeconds(4)).click();
        sleep(2000);
        buisnessProcess.click();
        sleep(2000);
        isDone.click();
        clickMainPage.click();
        clickTask.click();
        $("#issuetype-single-select").click();
        actions().sendKeys(Keys.ENTER).perform();
    }

    public void selectErrorOption(String taskName, String description) {
        clickTask.click();
        $("#issuetype-single-select").click();
        actions().sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        nameTask.setValue(taskName);
        switchTo().frame($$("iframe[id*='mce']").first());
        $("body#tinymce").clear();
        $("body#tinymce").setValue(description);
        switchTo().defaultContent();
        clickTag.click();
        writeTag.setValue("bugs");
        actions().sendKeys("ENTER").perform();
        switchTo().frame($$("iframe[id*='mce']").get(1));
        $("body#tinymce").clear();
        $("body#tinymce").setValue("Java");
        switchTo().defaultContent();
    }
}
