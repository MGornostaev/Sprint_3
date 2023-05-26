package ru.praktikum.selenium.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.praktikum.selenium.step.MakeOrderSteps;

import static org.junit.Assert.*;
import static ru.praktikum.selenium.config.AppConfig.APP_URL;

public class MainPage {
    WebDriver webDriver;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriver.get(APP_URL);
    }

    private By makeOrderButtonTop = By.xpath(".//button[@class='Button_Button__ra12g']");
    private By getMakeOrderButtonBottom = By.xpath(".//div[5]/button[text() = 'Заказать']");
    private By acceptCookiesButton = By.className("App_CookieButton__3cvqF");

    public MakeOrderSteps clickMakeOrderButton(String button){
        switch (button){
            case "top":
                WebElement topButton = webDriver.findElement(makeOrderButtonTop);
                new WebDriverWait(webDriver, 3)
                        .until(ExpectedConditions.elementToBeClickable(topButton));
                topButton.click();
                return new MakeOrderSteps(webDriver);
            case "bottom":
                WebElement bottomButton = webDriver.findElement(getMakeOrderButtonBottom);
                ((JavascriptExecutor)webDriver).
                        executeScript("arguments[0].style.visibility='hidden'",  webDriver.findElement(By.className("Header_Header__214zg")));
                new WebDriverWait(webDriver, 3)
                        .until(ExpectedConditions.elementToBeClickable(bottomButton));
                ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();",  bottomButton);
                bottomButton.click();
                return new MakeOrderSteps(webDriver);
            default:
                throw new RuntimeException("Order button position is not specified");
        }
    }

    public MainPage clickAcceptCookiesButton(){
        WebElement element = webDriver.findElement(acceptCookiesButton);
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.elementToBeClickable(element));
        webDriver.findElement(acceptCookiesButton).click();
        return new MainPage(webDriver);
    }

    public MainPage clickAccordionItemHeading(String elementNumber){
        By accordionItemHeading = By.id(String.format("accordion__heading-%s",elementNumber));
        WebElement element = webDriver.findElement(accordionItemHeading);
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();",  element);
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        return this;
    }

    public MainPage checkAccordionItemText(String elementNumber, String result){
        By accordionItem = By.id(String.format("accordion__panel-%s",elementNumber));
        WebElement element = webDriver.findElement(accordionItem);
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();",  element);
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.visibilityOf(element));
        String text = element.getText();
        assertEquals("Некорректный текст элемента",result, text);
        return this;
    }
}
