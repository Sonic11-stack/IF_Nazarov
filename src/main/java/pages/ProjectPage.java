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

public class ProjectPage extends BasePage {

    @FindBy(xpath = "//a[text()='Проекты']") private WebElement projectsClick;

    @FindBy(xpath = "//a[text()='Test (TEST)']") private WebElement testProjectLink;

    @FindBy(xpath = "//*[@id=\"quickSearchInput\"]") private WebElement searchTaskButton;

    @FindBy(xpath = "/html/body/div[1]/header/nav/div/div[2]/ul/li[1]/form/div[1]/div[1]/ul/li[1]") private WebElement clickSearchTaskButton;

    @FindBy(xpath = "//*[contains(text(), 'Сделать')]") private WebElement checkWordsTask;

    @FindBy(xpath = "//*[contains(text(), 'Version 2.0')]") private WebElement checkVersionTask;

    @FindBy(xpath = "//*[@id=\"create-menu\"]") private WebElement clickTask;

    @FindBy(xpath = "//*[@id=\"summary\"]") private WebElement nameTask;

    @FindBy(xpath = "//*[@id=\"create-issue-submit\"]") private WebElement clickMakeTask;

    @FindBy(xpath = "/html/body/div[1]/div/div[1]/div/main/div/div/div/div/div/div/div[2]/div[2]/div/div[1]/div/div[1]/span") private WebElement taskCounter;

    @FindBy(xpath = "/html/body/section/div/div[1]/div/div/form/div/div[2]/div[4]/select/optgroup/option[2]") private WebElement clickVersion;

    @FindBy(xpath = "/html/body/div[1]/header/nav/div/div[1]/span/a") private WebElement clickMainPage;

    @FindBy(xpath = "//body[@id='tinymce']/p") private WebElement writeText;

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

    public void createTaskBug() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(clickVersion)).click();
        wait.until(ExpectedConditions.elementToBeClickable(clickMakeTask)).click();
        wait.until(ExpectedConditions.elementToBeClickable(clickMainPage)).click();
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
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@id, 'mce')]")));
        wait.until(ExpectedConditions.visibilityOf(writeText));
        writeText.clear();
        writeText.sendKeys(description);
        driver.switchTo().defaultContent();
    }
}


