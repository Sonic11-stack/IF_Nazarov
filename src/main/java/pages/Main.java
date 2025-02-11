package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] arg) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\konno\\IdeaProjects\\Test_5\\src\\main\\java\\pages\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com");
    }
}

