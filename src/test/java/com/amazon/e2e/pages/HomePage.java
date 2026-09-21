package com.amazon.e2e.pages;

import com.amazon.e2e.base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HomePage extends BasePage {
    private final Locator searchBox;
    private final Locator searchButton;
    private final Locator continueShopping;

    public HomePage(Page page) {
        super(page);
        searchBox = page.locator("#twotabsearchtextbox");
        searchButton = page.locator("#nav-search-submit-button");
        // Amazon sometimes shows a "Continue shopping" interstitial
        continueShopping = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Continue shopping"));
    }

    public HomePage launch() {
        open("/");
        if (continueShopping.isVisible()) {
            click(continueShopping, "Continue shopping");
        }
        return this;
    }

    public HomePage verifyLoaded() {
        assertThat(searchBox).isVisible();
        return this;
    }

    public SearchResultsPage search(String term) {
        type(searchBox, term, "search box");
        click(searchButton, "search button");
        return new SearchResultsPage(page);
    }
}
