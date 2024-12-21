package com.gog.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://www.gog.com/";
        Configuration.browserSize = "1920x1080";

    }
}
