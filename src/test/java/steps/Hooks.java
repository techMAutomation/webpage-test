package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.log4j.xml.DOMConfigurator;

public class Hooks {

    private String driverPath = "./drivers/";
    public static WebDriver driver;

    @Before
    public void initializeTest() {
        DOMConfigurator.configure("log4j.xml");
        System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver");
        driver = new ChromeDriver();
    }

    @After
    public void quitDriver() {
        if (driver != null)
            driver.quit();
    }

}
