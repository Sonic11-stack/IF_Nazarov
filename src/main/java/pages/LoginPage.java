package pages;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.visible;

public class LoginPage {

    private final String usernameField = "#login-form-username";
    private final String passwordField = "#login-form-password";
    private final String loginButton = "#login";

    public void openLoginPage(String url) {open(url);}

    public void login(String username, String password) {
        $(usernameField).shouldBe(visible).setValue(username);
        $(passwordField).shouldBe(visible).setValue(password);
        $(loginButton).shouldBe(visible).click();
    }

}

