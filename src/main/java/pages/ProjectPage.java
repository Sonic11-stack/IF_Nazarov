package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ProjectPage extends BasePage {

    @FindBy(xpath = "//a[text()='Проекты']") private WebElement projectsClick;

    @FindBy(xpath = "//a[text()='Test (TEST)']") private WebElement testProjectLink;

    @FindBy(xpath = "//*[@id=\"quickSearchInput\"]") private WebElement searchTaskButton;

    @FindBy(xpath = "//li[a/span[text()='TestSeleniumATHomework']]/a\n") private WebElement clickSearchTaskButton;

    @FindBy(xpath = "//*[contains(text(), 'Сделать')]") private WebElement checkWordsTask;

    @FindBy(xpath = "//*[contains(text(), 'Version 2.0')]") private WebElement checkVersionTask;

    @FindBy(xpath = "//*[@id=\"create-menu\"]") private WebElement clickTask;

    @FindBy(xpath = "//*[@id=\"summary\"]") private WebElement nameTask;

    @FindBy(xpath = "//*[@id=\"create-issue-submit\"]") private WebElement clickMakeTask;

    @FindBy(xpath = "//div[@class='issue-tools']//div[@class='showing']/span\n") private WebElement taskCounter;

    @FindBy(xpath = "//select[@id='fixVersions']//option[contains(text(), 'Version 2.0')]\n") private WebElement clickVersion;

    @FindBy(xpath = "//select[@id='versions']//option[contains(text(), 'Version 2.0')]\n") private WebElement clickVersion1;

    @FindBy(xpath = "//span[@id='logo' and .//a[@href='https://edujira.ifellow.ru/secure/MyJiraHome.jspa']]\n") private WebElement clickMainPage;

    @FindBy(xpath = "//span[@class='trigger-label' and text()='В работе']\n") private WebElement inWorking;

    @FindBy(xpath = "//*[@id=\"opsbar-transitions_more\"]") private WebElement buisnessProcess;

    @FindBy(xpath = "//span[@class='trigger-label' and text()='Выполнено']\n") private WebElement isDone;

    @FindBy(xpath = "//*[@id='10003' and //a[starts-with(text(),’TEST-17’)']\n") private WebElement findTaskBug;

    @FindBy(xpath = "//*[@id=\"labels-textarea\"]") private WebElement writeTag;

    @FindBy(xpath = "//*[@id=\"labels-multi-select\"]") private WebElement clickTag;

    @FindBy(xpath = "//*[@id=\"find_link\"]") private WebElement clickListTask;

    public ProjectPage(WebDriver driver) {super(driver);}

    public void openProject() {
        projectsClick.click();
        testProjectLink.click();
    }

    public void findTask(String searchTask) {searchTaskButton.sendKeys(searchTask);}

    public void foundTask() {clickSearchTaskButton.click();}

    public String checkSentense() {return checkWordsTask.getText();}

    public String checkVersion() {return checkVersionTask.getText();}

    public int getTaskCount() {
        String counterText = taskCounter.getText();
        String[] parts = counterText.split(" ");
        return Integer.parseInt(parts[2]);
    }

    public void createTask(String taskName) {
        clickTask.click();
        nameTask.sendKeys(taskName);
        clickMakeTask.click();
    }

    public void closeTask() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        clickListTask.click();
        WebElement firstTaskLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='issues_history_main']//li[2]//a")));
        firstTaskLink.click();
        inWorking.click();
        buisnessProcess.click();
        isDone.click();
        clickMainPage.click();
    }

    public void createTaskBug() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(clickVersion)).click();
        wait.until(ExpectedConditions.elementToBeClickable(clickVersion1)).click();
        wait.until(ExpectedConditions.elementToBeClickable(clickMakeTask)).click();
        wait.until(ExpectedConditions.elementToBeClickable(clickMainPage)).click();
        clickListTask.click();
        WebElement firstTaskLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='issues_history_main']//li[1]//a")));
        firstTaskLink.click();
        inWorking.click();
        buisnessProcess.click();
        isDone.click();
        wait.until(ExpectedConditions.elementToBeClickable(clickMainPage)).click();
        clickTask.click();
        WebElement taskDropdown = driver.findElement(By.xpath("//*[@id='issuetype-single-select']"));
        taskDropdown.click();
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).perform();
    }

    public void selectErrorOption(String taskName, String description) {
        clickTask.click();
        WebElement taskDropdown = driver.findElement(By.xpath("//*[@id='issuetype-single-select']"));
        taskDropdown.click();
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(nameTask));
        nameTask.click();
        nameTask.sendKeys(taskName);
        List<WebElement> iframes = driver.findElements(By.xpath("//iframe[contains(@id, 'mce')]"));
        driver.switchTo().frame(iframes.get(0));
        WebElement firstWriteText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[@id='tinymce']")));
        firstWriteText.clear();
        firstWriteText.sendKeys(description);
        driver.switchTo().defaultContent();
        clickTag.click();
        writeTag.sendKeys("bugs");
        actions.sendKeys(Keys.ENTER).perform();
        driver.switchTo().frame(iframes.get(1));
        WebElement secondWriteText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[@id='tinymce']")));
        secondWriteText.clear();
        secondWriteText.sendKeys("Java");
        driver.switchTo().defaultContent();
    }
}


