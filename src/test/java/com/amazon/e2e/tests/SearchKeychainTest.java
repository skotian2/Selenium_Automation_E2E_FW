package com.amazon.e2e.tests;

import com.amazon.e2e.base.BaseTest;
import com.amazon.e2e.config.ConfigReader;
import com.amazon.e2e.data.TestData;
import com.amazon.e2e.pages.HomePage;
import com.amazon.e2e.pages.SearchResultsPage;
import org.testng.annotations.Test;

public class SearchKeychainTest extends BaseTest {

    @Test(description = "Launch amazon.com, search 'key chain' and take a screenshot")
    public void searchKeychainAndScreenshot() {
        // Step 1: launch Amazon
        HomePage home = new HomePage(page).launch().verifyLoaded();

        // Step 2: search for key chain
        SearchResultsPage results = home.search(TestData.SEARCH_KEYCHAIN);

        // Step 3: verify results and capture screenshot
        results.verifyResultsDisplayed().takeScreenshot("keychain-search-results");
        log.info("START [{}] {}");
    }
}
