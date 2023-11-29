import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
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

       selectFilter("Brands", "Apple", "Samsung", "TCL");
//
//        selectFilter("Brands", "TCL");
        selectFilter("Deals", "New", "Special offer");
        selectFilter("Operating System", "iPadOS", "Android");
//        selectFilter("Brands", "All");
    }

    private static void selectFilter(String filter, String... subFilters) {

        WebElement filterElement = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//legend[normalize-space() = '" + filter + "']")));

        filterElement.click();

        List<WebElement> actualSubFilters = driver.findElements(
                By.xpath(ALL_SUB_FILTERS));

        if (subFilters.length == 1 && subFilters[0].equals("All")) {
            for (WebElement element : actualSubFilters) {
               element.click();
            }
        } else if (subFilters.length > 0 && !Arrays.toString(subFilters).contains("All"))  {

            for (String subFilter : subFilters) {
                WebElement filterDisplayName = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//span[@class='filter-display-name'][normalize-space() = '" + subFilter + "']")));
                filterDisplayName.click();
            }
        }
    }
}
