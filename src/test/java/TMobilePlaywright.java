import com.microsoft.playwright.*;
public class TMobilePlaywright {

    static Page page;
    static String GROUP_NAME = "desktop-filter-group-name";


    public static void main(String[] args) {

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false).setSlowMo(5000));

            BrowserContext context = browser.newContext();
            page = context.newPage();

            page.navigate("https://www.t-mobile.com/tablets");

 //         selectFilter("Brands", "Apple", "Samsung", "TCL");

//          selectFilter("Brands", "TCL");
//          selectFilter("Deals", "New", "Special offer");
//          selectFilter("Operating System", "iPadOS", "Android");
          selectFilter("Operating System", "All");
//            selectFilter("Brands", "All");
        }

    }

    private static void selectFilter(String filter, String... subFilters) {

        page.getByTestId(GROUP_NAME).filter(new Locator.FilterOptions().setHasText(filter)).click();

        if (subFilters[0].equals("All")) {
            page.locator(".filter-display-name").all().forEach(Locator::check);
        } else {
            for (String subFilter : subFilters) {
                page.locator(".filter-display-name").getByText(subFilter).check();
            }
        }
    }
}


//PWDEBUG=1
