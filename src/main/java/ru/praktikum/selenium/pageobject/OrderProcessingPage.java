package ru.praktikum.selenium.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.praktikum.selenium.step.MakeOrderSteps;

import static org.junit.Assert.assertTrue;

public class OrderProcessingPage {
    WebDriver webDriver;

    public OrderProcessingPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private By nameInputField = By.xpath(".//input[@placeholder='* Имя']");
    private By surnameInputField = By.xpath(".//input[@placeholder='* Фамилия']");
    private By addressInputField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private By phoneInputField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By metroStationSelector = By.className("select-search__input");
    private By goNextButton = By.xpath(".//button[text() = 'Далее']");
    private By dateInputField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private By rentTimeInputField = By.xpath(".//div[text() = '* Срок аренды']");
    private By colorCheckBoxBlack = By.xpath(".//input[@id = 'black']");
    private By colorCheckBoxGrey = By.xpath(".//input[@id = 'grey']");
    private By commentInputField = By.xpath(".//input[@placeholder = 'Комментарий для курьера']");
    private By makeOrderButton = By.xpath(".//div[3]/button[text()='Заказать']");
    private By yesButton = By.xpath(".//div[2]/button[text() = 'Да']");

    public OrderProcessingPage inputName(String name){
        WebElement element = webDriver.findElement(nameInputField);
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(name);
        return this;
    }

    public OrderProcessingPage inputSurname(String surname){
        webDriver.findElement(surnameInputField).sendKeys(surname);
        return this;
    }

    public OrderProcessingPage inputAddress(String address){
        webDriver.findElement(addressInputField).sendKeys(address);
        return this;
    }

    public OrderProcessingPage inputPhone(String phone){
        webDriver.findElement(phoneInputField).sendKeys(phone);
        return this;
    }

    public OrderProcessingPage clickMetroStationSelector(){
        webDriver.findElement(metroStationSelector).click();
        return this;
    }

    public OrderProcessingPage clickMetroStationSelectorElement(String station){
        By metroStationSelectorElement = By.xpath(String.format(".//li[@data-value='%s']", station));
        webDriver.findElement(metroStationSelectorElement).click();
        return this;
    }

    public MakeOrderSteps clickGoNextButton(){
        webDriver.findElement(goNextButton).click();
        return new MakeOrderSteps(webDriver);
    }

    public OrderProcessingPage clickDateInputField(){
        WebElement element = webDriver.findElement(dateInputField);
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        return this;
    }

    public OrderProcessingPage clickDateInputFieldElement(String date){
        By dateInputFieldElement = By.xpath(String.format(".//div[text() = '%s']",date));
        WebElement element = webDriver.findElement(dateInputFieldElement);
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        return this;
    }

    public OrderProcessingPage clickRentTimeInputField(){
        webDriver.findElement(rentTimeInputField).click();
        return this;
    }

    public OrderProcessingPage clickRentTimeInputFieldElement(String option){
        By rentTimeInputFieldElement = By.xpath(String.format(".//div[text() = '%s']",option));
        webDriver.findElement(rentTimeInputFieldElement).click();
        return this;
    }

    public OrderProcessingPage clickColorCheckBoxBlack(String color){
        switch (color){
            case "black":
                webDriver.findElement(colorCheckBoxBlack).click();
                return this;
            case "grey":
                webDriver.findElement(colorCheckBoxGrey).click();
                return this;
            default:
                throw new RuntimeException("Invalid scooter color");
        }
    }

    public OrderProcessingPage inputComment(String comment){
        webDriver.findElement(commentInputField).sendKeys(comment);
        return this;
    }

    public OrderProcessingPage clickMakeOrderButton(){
        WebElement element = webDriver.findElement(makeOrderButton);
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        return this;
    }

    public OrderProcessingPage clickYesButton(){
        WebElement element = webDriver.findElement(yesButton);
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        return this;
    }

    public void checkOrderStatusButton(){
        var isDisplayed = webDriver.findElement(By.xpath(".//button[text() = 'Посмотреть статус']")).isDisplayed();
        assertTrue("Не найдена кнопка Посмотреть статус",isDisplayed);
    }
}
