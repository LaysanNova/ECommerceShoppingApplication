import com.microsoft.playwright.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TMobilePlaywright {

    static Page page;
    static Playwright playwright;

    public static void main(String[] args) {

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false).setSlowMo(1000));

            BrowserContext context = browser.newContext();
            page = context.newPage();

            page.navigate("https://www.t-mobile.com/tablets");

//          selectFilter("Brands", "Apple", "Samsung", "TCL");

//          selectFilter("Brands", "TCL");
//          selectFilter("Deals", "New", "Special offer");
//          selectFilter("Operating System", "iPadOS", "Android");
            selectFilter("Brands", "All");
        }

    }

    private static void selectFilter(String filter, String... subFilters) {

        playwright.selectors().setTestIdAttribute("data-testid");

        page.getByTestId("desktop-filter-group-name").filter(new Locator.FilterOptions().setHasText(filter)).click();

//        WebElement filterElement = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//legend[contains(text(), '" + filter + "')]")));
//
//        clickCheckBox(filterElement);
//
//        if (subFilters[0].equals("All")) {
//            subFilters = getSubFilters().toArray(new String[0]);
//        }
//
//        for (String subFilter : subFilters) {
//            WebElement filterDisplayName = driver.findElement(
//                    By.xpath("//div[@role='group']//span[contains(text(),'" + subFilter + "')]"));
//
//            clickCheckBox(filterDisplayName);
//        }
    }
}
