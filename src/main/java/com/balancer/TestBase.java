package com.balancer;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import utils.DriverHelper;

public class TestBase {

    @BeforeTest
    public void setUp(){
        DriverHelper.getDriver().get("http://sdetchallenge.fetch.com/");
    }

    @AfterClass
    public void tearDown(){

        if(DriverHelper.getDriver() != null){
            DriverHelper.getDriver().quit();
        }

    }
}
