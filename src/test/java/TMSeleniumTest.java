import dev.failsafe.internal.util.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class TMSeleniumTest {

    static WebDriver driver = new ChromeDriver();
    static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    private final static String ALL_SUB_FILTERS = "//div[@role='group']//span[@class='filter-display-name']";

    void selectFilter(String filter, String... subFilters) {

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

    @Test
    public void testSelectOption1() throws InterruptedException {

        List<String> expectedSubFilters = List.of("Apple", "Samsung", "TCL");

        driver.get("https://www.t-mobile.com/tablets");

        Thread.sleep(5000);

        selectFilter("Brands", expectedSubFilters.get(0), expectedSubFilters.get(1), expectedSubFilters.get(2));

        List<String> checkedSubFilters = driver.findElements(
                By.xpath(
         "//label[@class='mat-checkbox-layout']//input[@aria-checked='true']/parent::span/following-sibling::span/span[@class='filter-display-name']"))
                .stream()
                .map(WebElement::getText).toList();

        Assertions.assertEquals(expectedSubFilters, checkedSubFilters);
    }
}
