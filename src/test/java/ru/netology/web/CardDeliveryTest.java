package ru.netology.web;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

class CardDeliveryTest {
    @Test
    void shouldPositiveTestForm() {
        open("http://localhost:9999");
        Configuration.holdBrowserOpen = true;
        $x("//input[@placeholder=\"Город\"]").setValue("Рязань");
        $x("//input[@placeholder=\"Дата встречи\"]").doubleClick().sendKeys("BACKSPACE");
        String meetingDate = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        $x("//input[@placeholder=\"Дата встречи\"]").sendKeys(meetingDate);
        $x("//input[@name]").sendKeys("Алибабаев Василий");
        $x("//input[@name=\"phone\"]").sendKeys("+79269998877");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).should(appear, Duration.ofSeconds(15));
    }
}

