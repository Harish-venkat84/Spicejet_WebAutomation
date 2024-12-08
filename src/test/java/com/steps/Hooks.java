package com.steps;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import utils.BaseClass;

public class Hooks extends BaseClass {

    @BeforeAll
    public static void Browser() {

        System.out.println(" ==========> Test started <=========== ");

        launchBrowser("Chrome", "");
    }

    @AfterAll
    public static void afterAllTest(){

        browserClose();

        System.out.println(" =======> Test completed successfully <=============");
    }

}
