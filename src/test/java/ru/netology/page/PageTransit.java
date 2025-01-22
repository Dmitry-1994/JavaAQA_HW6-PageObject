package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PageTransit {
    private final SelenideElement headText = $(byText("Пополнение карты"));
    private final SelenideElement amount = $("[data-test-id=amount] input");
    private final SelenideElement from = $("[data-test-id=from] input");
    private final SelenideElement execute= $("[data-test-id=action-transfer] .button__content");
    private final SelenideElement massageError = $(byText("Ошибка!"));
    public PageTransit() {
        headText.shouldBe(Condition.visible);
    }

    public void moneyTransit(DataGenerator.InfoCard infoCard, String amountMoneyTransit) {
        amount.setValue(amountMoneyTransit);
        from.setValue(infoCard.getNumber());
        execute.click();
    }

    public PageDashboard moneyValidTransit(DataGenerator.InfoCard infoCard, String amountMoneyTransit) {
        moneyTransit(infoCard, amountMoneyTransit);
        return new PageDashboard();
    }

    public void massageError(String massage) {
        massageError.shouldHave(exactText(massage)).shouldBe(Condition.visible);
    }
}
