package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Selenide.$;

public class PageVer {
    private final SelenideElement code = $("[data-test-id='code'] input");
    private final SelenideElement button = $("[data-test-id = action-verify] .button__content");
    public PageVer() {
        code.shouldBe(Condition.visible);
    }

    public PageDashboard validVer(DataGenerator.VerificationCode verificationCode) {
        code.setValue(verificationCode.getCode());
        button.click();
        return new PageDashboard();
    }
}
