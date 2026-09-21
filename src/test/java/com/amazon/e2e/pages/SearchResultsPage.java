package com.amazon.e2e.pages;

import com.amazon.e2e.base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SearchResultsPage extends BasePage {
    private final Locator results;

    public SearchResultsPage(Page page) {
        super(page);
        results = page.locator("[data-component-type='s-search-result']");
    }

    public SearchResultsPage verifyResultsDisplayed() {
        assertThat(results.first()).isVisible();
        return this;
    }
}
