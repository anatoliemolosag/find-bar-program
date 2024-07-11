package com.balancer.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverHelper;
import java.util.List;

public class BarsPage {

    WebDriver driver;

    public BarsPage(WebDriver driver){
        this.driver = DriverHelper.getDriver();
        PageFactory.initElements(driver,this);
    }


    @FindBy(id = "weigh")
    private WebElement weighButton;

    @FindBy(xpath = "//button[contains(text(),'Reset')]")
    private WebElement resetButton;

    @FindBy(xpath = "//div[@class='result']/button")
    private WebElement resultText;

    @FindBy(xpath = "//li[1]")
    private WebElement firstWeigh;

    @FindBy(xpath = "//li[2]")
    private WebElement secondWeight;



    public String getWeightResult(){
        return resultText.getText();
    }

    public void resetScale(){
        resetButton.click();
    }

    public WebElement getFirstWeigh() {
        return firstWeigh;
    }

    public WebElement getSecondWeight(){
        return secondWeight;
    }



    public void weighBars(List<Integer> leftBowlBars, List<Integer> rightBowlBars) {

        for(int i = 0; i < leftBowlBars.size(); i++){
            WebElement leftBowl = DriverHelper.getDriver().findElement(By.id("left_" + i ));
            leftBowl.sendKeys(String.valueOf(leftBowlBars.get(i)));
        }

       for(int i = 0; i < rightBowlBars.size(); i++){
           WebElement rightBowl = DriverHelper.getDriver().findElement(By.id("right_" + i ));
           rightBowl.sendKeys(String.valueOf(rightBowlBars.get(i)));
       }
        weighButton.click();
    }

    public void clickGoldButton(int barNumber){
        WebElement goldBar = driver.findElement(By.id("coin_" + barNumber));
        goldBar.click();
    }

}
