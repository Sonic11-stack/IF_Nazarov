package pages;

import static com.codeborne.selenide.Selenide.*;

public abstract class BasePage {

    public BasePage() {}

    public void openPage(String url) {open(url);}
}
