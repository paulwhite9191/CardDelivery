package ru.netology.web;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CardDeliveryTest {

    @BeforeEach
    void setUpEach() {
        open("http://localhost:9999");


    }


    static void setUp() {
        Configuration.headless = true;

    }

    public String dateCreate(long addDays, String pattern) {
        return
                LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }


    @Test
    void shouldPositiveTestForm() {
        $x("//input[@placeholder=\"Город\"]").setValue("Рязань");
        $x("//input[@placeholder=\"Дата встречи\"]").doubleClick().sendKeys("BACKSPACE");
        String meetingDate = dateCreate(5, "dd.MM.yyyy");
        $x("//input[@placeholder=\"Дата встречи\"]").sendKeys(meetingDate);
        $x("//input[@name]").sendKeys("Алибабаев Василий");
        $x("//input[@name=\"phone\"]").sendKeys("+79269998877");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).should(appear, Duration.ofSeconds(15));

    }
}


