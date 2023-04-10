package ru.netology.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


class CardDeliveryTest {

    @BeforeEach
    void setUpEach() {
        open("http://localhost:9999");
    }

    public String createDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void shouldPositiveTestForm() {
        $x("//input[@placeholder='Город']").setValue("Рязань");
        $x("//input[@placeholder='Дата встречи']").doubleClick().sendKeys("BACKSPACE");
        String meetingDate = createDate(5, "dd.MM.yyyy");
        $x("//input[@placeholder='Дата встречи']").sendKeys(meetingDate);
        $x("//input[@name='name']").sendKeys("Алибабаев Василий");
        $x("//input[@name='phone']").sendKeys("+79269998877");
        $("[data-test-id=agreement]").click();
        $x("//span[@class='button__text']").click();
        $("[data-test-id=notification").shouldBe(appear, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + createDate(5, "dd.MM.yyyy"))).shouldBe(visible);
    }
}


