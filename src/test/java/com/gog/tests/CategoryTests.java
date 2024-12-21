package com.gog.tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class CategoryTests extends TestBase{

    @Tags({
            @Tag("WEB"),
            @Tag("SMOKE")
    })
    @CsvFileSource(resources = "/test_data/storeURLOpensCategoriesWithRespectiveFilterTest.csv")
    @ParameterizedTest(name = "При выборе меню магазина {0} на странице категорий выбран фильтр {1}")
    void storeURLChecksRespectiveFilterTest(String linkName, String filterName) {
        open("/ru/");
        $("[hook-test='menuStore']").hover();
        $$(".menu-submenu-item").findBy(text(linkName)).click();
        $("[selenium-id='filterClearingList']").shouldHave(text(filterName));
    }

    @CsvSource(value = {
            "Жанры, Гонки",
            "Теги, Приключение",
            "Операционные системы, Windows",
            "Функции, Одиночная игра"
    })

    @Tags({
            @Tag("WEB"),
            @Tag("SMOKE")
    })
    @ParameterizedTest(name = "Выбор фильтра {0} в категории {1}")
    void categoryCheckboxFilterTest(String filterName, String filterItem) {
        open("/ru/games");
        $$(".filter__header")
                .findBy(text(filterName))
                .scrollIntoView("{behavior: \"instant\"}").click();
        $$(".filter__header")
                .findBy(text(filterName))
                .closest(".filters__item")
                .$$(".filter-option__label")
                .findBy(text(filterItem)).click();
        $("[selenium-id='filterClearingItem']").shouldHave(text(filterName)).shouldHave(text(filterItem));
    }


}
