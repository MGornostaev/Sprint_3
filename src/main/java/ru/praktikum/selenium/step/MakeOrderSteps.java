package ru.praktikum.selenium.step;

import org.openqa.selenium.WebDriver;
import ru.praktikum.selenium.pageobject.OrderProcessingPage;

public class MakeOrderSteps {
    WebDriver webDriver;

    public MakeOrderSteps(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public OrderProcessingPage fillUserData(String name, String surname, String address, String station, String phone){
        new OrderProcessingPage(webDriver)
                .inputName(name)
                .inputSurname(surname)
                .inputAddress(address)
                .clickMetroStationSelector()
                .clickMetroStationSelectorElement(station)
                .inputPhone(phone);
        return new OrderProcessingPage(webDriver);
    }

    public OrderProcessingPage fillRentData(String date, String option, String color, String comment){
        new OrderProcessingPage(webDriver)
                .clickDateInputField()
                .clickDateInputFieldElement(date)
                .clickRentTimeInputField()
                .clickRentTimeInputFieldElement(option)
                .clickColorCheckBoxBlack(color)
                .inputComment(comment);
        return new OrderProcessingPage(webDriver);
    }
}
