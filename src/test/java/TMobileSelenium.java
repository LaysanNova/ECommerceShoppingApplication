import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TMobileSelenium {

    static WebDriver driver;
    private static WebDriverWait wait;
    private final static String ALL_SUB_FILTERS = "//div[@role='group']//span[@class='filter-display-name']";


    public static void main(String[] args) throws InterruptedException {

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        driver.get("https://www.t-mobile.com/tablets");
        Thread.sleep(5000);

//       selectFilter("Brands", "Apple", "Samsung", "TCL");

//        selectFilter("Brands", "TCL");
//        selectFilter("Deals", "New", "Special offer");
//        selectFilter("Operating System", "iPadOS", "Android");
        selectFilter("Brands", "All");
    }

    static void clickCheckBox(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }

    static List<String> getSubFilters() {

        return driver.findElements(
                            By.xpath(ALL_SUB_FILTERS))
                    .stream().map(WebElement::getText).toList();
    }

    private static void selectFilter(String filter, String... subFilters) {

        WebElement filterElement = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//legend[contains(text(), '" + filter + "')]")));

        clickCheckBox(filterElement);

        if (subFilters[0].equals("All")) {
            subFilters = getSubFilters().toArray(new String[0]);
        }

        for (String subFilter : subFilters) {

            WebElement filterDisplayName = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@role='group']//span[contains(text(),'" + subFilter + "')]")));

            clickCheckBox(filterDisplayName);
        }
    }
}
