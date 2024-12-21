package com.gog.tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import util.Language;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class LocalizationTests extends TestBase {
    static Stream<Arguments> checkCategoryFilterGenreTranslationOnLanguageSwitchTest() {
        return Stream.of(
                Arguments.of(
                        Language.RU,
                        "Жанры",
                        List.of("Гонки", "Приключение","Ролевая игра","Симулятор","Спорт","Стратегия","Шутер","Экшн")
                ),
                Arguments.of(
                        Language.EN,
                        "Genres",
                        List.of("Action", "Adventure", "Racing", "Role-playing", "Shooter", "Simulation", "Sports", "Strategy")
                )
        );
    }

    @Tag("WEB")
    @MethodSource
    @ParameterizedTest(name = "Список итемов в фильтре {1} в {0} локали")
    void checkCategoryFilterGenreTranslationOnLanguageSwitchTest(Language language, String filterName, List<String> filterList) {
        open("/" + language.link + "/games/");
        $("[selenium-id='filterGenresName']")
                .shouldHave(text(filterName));
        $$("[selenium-id='filterGenres'] .filter-option__label")
                .filterBy(visible)
                .shouldHave(texts(filterList));
    }
}
