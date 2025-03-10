package com.projectname.hooks;

import com.projectname.keywords.Utils;
import io.cucumber.java.After;

public class Hooks {

    @After
    public void closeBrowser() {
        Utils.closeBrowser();
    }

}
