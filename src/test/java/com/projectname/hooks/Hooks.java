package com.projectname.hooks;

import com.projectname.keywords.utils.Utils;
import io.cucumber.java.After;

public class Hooks {

    @After
    public void closeBrowser() {
        Utils.closeBrowser();
    }

}
