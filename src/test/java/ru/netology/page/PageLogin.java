package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Selenide.$;

public class PageLogin {
    private final SelenideElement login = $("[data-test-id=login] input");
    private final SelenideElement password = $("[data-test-id=password] input");
    private final SelenideElement button = $("[data-test-id=action-login] .button__content");

    public PageVer validLogin(DataGenerator.AutInfo autInfo) {
        login.setValue(autInfo.getLogin());
        password.setValue(autInfo.getPassword());
        button.click();
        return new PageVer();
    }
}
