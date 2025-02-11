package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//*[@id=\"login-form-username\"]") private WebElement usernameField;

    @FindBy(xpath = "//*[@id=\"login-form-password\"]") private WebElement passwordField;

    @FindBy(xpath = "//*[@id=\"login\"]") private WebElement loginButton;

    public LoginPage(WebDriver driver) {super(driver);}

    public void login(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }
}
