package ru.praktikum.selenium;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.selenium.pageobject.MainPage;

@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {
    private final String position;
    private final String name;
    private final String surname;
    private final String address;
    private final String phone;
    private final String station;
    private final String date;
    private final String option;
    private final String color;
    private final String comment;

    public OrderTest(String position, String name, String surname, String address, String station,
                     String phone, String date, String option, String color, String comment){
        this.position = position;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.station = station;
        this.date = date;
        this.option = option;
        this.color = color;
        this.comment = comment;

    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                { "bottom", "Игорь", "Тестировочков","Москва, ул. Пушкина, д. 12",
                        "2","+79251123400","29","двое суток","black","Позвоните за час до доставки"},
                { "top", "Ира", "Ли","Нарьян-Мар, ул. Шишкина, д. 12, кв. 77",
                        "4","89331123410","30","сутки","grey",""},
        };
    }

    @Test
    public void checkMakeOrderWithBottomButtonValidData() {
        new MainPage(webDriver)
                .clickAcceptCookiesButton()
                .clickMakeOrderButton(position)
                .fillUserData(name, surname, address, station, phone)
                .clickGoNextButton()
                .fillRentData(date, option, color, comment)
                .clickMakeOrderButton()
                .clickYesButton()
                .checkOrderStatusButton();
    }
}
