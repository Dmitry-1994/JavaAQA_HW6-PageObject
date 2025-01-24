package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PageDashboard {
    private final SelenideElement header = $("[data-test-id=dashboard]");
    private final ElementsCollection cards = $$(".list__item div");
    private final String action = "[data-test-id=action-deposit]";
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public PageDashboard() {
        header.shouldBe(Condition.visible).shouldHave(Condition.text("Личный кабинет"));
    }

    public int getCardBalance(DataGenerator.InfoCard infoCard) {
        String text = cards.findBy(Condition.attribute("data-test-id", infoCard.getId())).getText();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public PageTransit selectTransitCard(DataGenerator.InfoCard infoCard) {
        cards.findBy(Condition.attribute("data-test-id", infoCard.getId())).$(action).click();
        return new PageTransit();
    }
}
