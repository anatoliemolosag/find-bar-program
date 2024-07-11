package com.balancer.tests;

import com.balancer.TestBase;
import com.balancer.pages.BarsPage;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import utils.DriverHelper;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;



public class FindFakeGoldBarTests extends TestBase {

    private static final Logger log = LoggerFactory.getLogger(FindFakeGoldBarTests.class);
    BarsPage barsPage = new BarsPage(DriverHelper.getDriver());
    WebDriverWait wait;

    @Test
    public void testFindFakeGoldBar() {

        int fakeBar = findFakeBar();

        barsPage.clickGoldButton(fakeBar);

        Alert alert = DriverHelper.getDriver().switchTo().alert();

        Assert.assertEquals("Yay! You find it!", alert.getText());

        alert.accept();

        log.info("Fake bar number: " + fakeBar);

    }

    public int findFakeBar() {

        List<Integer> bars = Arrays.asList(0,1,2,3,4,5,6,7,8);

        List<Integer> group1 = bars.subList(0,3);
        List<Integer> group2 = bars.subList(3,6);
        List<Integer> group3 = bars.subList(6,9);

        wait = new WebDriverWait(DriverHelper.getDriver(), Duration.ofSeconds(20));

        barsPage.resetScale();
        barsPage.weighBars(group1,group2);
        wait.until(ExpectedConditions.visibilityOf(barsPage.getFirstWeigh()));
        String result1 = barsPage.getWeightResult();

        List<Integer> suspectedGroup;
        if (result1.equals("=")){
            suspectedGroup = group3;
        } else if (result1.equals(">")) {
            suspectedGroup = group2;
        }else {
            suspectedGroup = group1;
        }

        barsPage.resetScale();
        barsPage.weighBars(Arrays.asList(suspectedGroup.get(0)),Arrays.asList(suspectedGroup.get(1)));
        wait.until(ExpectedConditions.visibilityOf(barsPage.getSecondWeight()));
        String result2 = barsPage.getWeightResult();

        if(result2.equals("=")){
            return suspectedGroup.get(2);
        }else if (result2.equals(">")){
            return suspectedGroup.get(1);
        }else{
            return suspectedGroup.get(0);
        }

    }

}
