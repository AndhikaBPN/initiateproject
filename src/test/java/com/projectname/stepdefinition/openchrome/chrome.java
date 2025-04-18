package com.projectname.stepdefinition.openchrome;

import com.projectname.keywords.utils.ConfigReader;
import com.projectname.keywords.utils.Platform;
import com.projectname.keywords.utils.TestLogger;
import com.projectname.keywords.utils.Utils;
import io.cucumber.java.en.Given;

public class chrome {

    String url = ConfigReader.getProperty("baseUrl");

    @Given("user open google page")
    public void user_open_google_page() {
        Utils.openBrowser(Platform.CHROME, url);
    }

}
