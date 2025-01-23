package ru.netology.test;


import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.page.PageDashboard;
import ru.netology.page.PageLogin;
import ru.netology.page.PageTransit;
import ru.netology.page.PageVer;


import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataGenerator.getAutInfo;
import static ru.netology.data.DataGenerator.getVerCode;

public class TransferTest {
    PageDashboard pageDashboard;

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");

        Configuration.holdBrowserOpen = true;

        var autInfo = getAutInfo();

        var loginPage = new PageLogin();
        loginPage.validLogin(autInfo);

        var verificationCode = getVerCode();
        var verPage = new PageVer();
        pageDashboard = verPage.validVer(verificationCode);
    }

    @Test
    void positiveTransitFromFirstToSecond() {

        var infoSecondCard = DataGenerator.getInfoSecondCard();
        var infoFirstCard = DataGenerator.getInfoFirstCard();
        var infoBalanceFirstCard = pageDashboard.getCardBalance(infoFirstCard);
        var infoBalanceSecondCard = pageDashboard.getCardBalance(infoSecondCard);
        var moneyTransit = DataGenerator.getValidAmountTransit(infoBalanceFirstCard);

        pageDashboard.selectTransitCard(infoSecondCard);

        var pageTransit = new PageTransit();
        pageTransit.moneyValidTransit(infoFirstCard, String.valueOf(moneyTransit));

        var expectedBalanceFirstCard = infoBalanceFirstCard - moneyTransit;
        var expectedBalanceSecondCard = infoBalanceSecondCard + moneyTransit;
        var actualBalanceFirstCard = pageDashboard.getCardBalance(infoFirstCard);
        var actualBalanceSecondCard = pageDashboard.getCardBalance(infoSecondCard);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }

    @Test
    void positiveTransitFromSecondToFirst() {

        var infoSecondCard = DataGenerator.getInfoSecondCard();
        var infoFirstCard = DataGenerator.getInfoFirstCard();
        var infoBalanceFirstCard = pageDashboard.getCardBalance(infoFirstCard);
        var infoBalanceSecondCard = pageDashboard.getCardBalance(infoSecondCard);
        var moneyTransit = DataGenerator.getValidAmountTransit(infoBalanceSecondCard);

        pageDashboard.selectTransitCard(infoFirstCard);

        var pageTransit = new PageTransit();
        pageTransit.moneyValidTransit(infoSecondCard, String.valueOf(moneyTransit));

        var expectedBalanceFirstCard = infoBalanceFirstCard + moneyTransit;
        var expectedBalanceSecondCard = infoBalanceSecondCard - moneyTransit;
        var actualBalanceFirstCard = pageDashboard.getCardBalance(infoFirstCard);
        var actualBalanceSecondCard = pageDashboard.getCardBalance(infoSecondCard);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }
    @Test
    void negativeTransitMoneyMoreBalance() {

        var infoSecondCard = DataGenerator.getInfoSecondCard();
        var infoFirstCard = DataGenerator.getInfoFirstCard();
        var infoBalanceFirstCard = pageDashboard.getCardBalance(infoFirstCard);
        var moneyTransit = DataGenerator.getInvalidAmountTransit(infoBalanceFirstCard);

        pageDashboard.selectTransitCard(infoSecondCard);

        var pageTransit = new PageTransit();
        pageTransit.moneyTransit(infoFirstCard, String.valueOf(moneyTransit));
        pageTransit.massageError("Сумма перевода превышает баланс карты");
    }

    @Test
    void positiveTransitMoneyFloat() {

        var infoSecondCard = DataGenerator.getInfoSecondCard();
        var infoFirstCard = DataGenerator.getInfoFirstCard();
        var infoBalanceFirstCard = pageDashboard.getCardBalance(infoFirstCard);
        var infoBalanceSecondCard = pageDashboard.getCardBalance(infoSecondCard);
        var moneyTransit = DataGenerator.getValidFloatAmountTransit(infoBalanceFirstCard);

        pageDashboard.selectTransitCard(infoSecondCard);

        var pageTransit = new PageTransit();
        pageTransit.moneyValidTransit(infoFirstCard, String.valueOf(moneyTransit));

        var expectedBalanceFirstCard = infoBalanceFirstCard - moneyTransit;
        var expectedBalanceSecondCard = infoBalanceSecondCard + moneyTransit;
        var actualBalanceFirstCard = pageDashboard.getCardBalance(infoFirstCard);
        var actualBalanceSecondCard = pageDashboard.getCardBalance(infoSecondCard);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }

    @Test
    void positiveTransitMoneyCancel() {
        var infoSecondCard = DataGenerator.getInfoSecondCard();
        var infoFirstCard = DataGenerator.getInfoFirstCard();
        var infoBalanceFirstCard = pageDashboard.getCardBalance(infoFirstCard);
        var infoBalanceSecondCard = pageDashboard.getCardBalance(infoSecondCard);
        var moneyTransit = DataGenerator.getValidAmountTransit(infoBalanceFirstCard);

        pageDashboard.selectTransitCard(infoSecondCard);

        var pageTransit = new PageTransit();
        pageTransit.moneyValidCancelTransfer(infoFirstCard, String.valueOf(moneyTransit));

        var expectedBalanceFirstCard = infoBalanceFirstCard;
        var expectedBalanceSecondCard = infoBalanceSecondCard;
        var actualBalanceFirstCard = pageDashboard.getCardBalance(infoFirstCard);
        var actualBalanceSecondCard = pageDashboard.getCardBalance(infoSecondCard);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }

    @Test
    void shouldBeEmptyFormAfterCancel() {
        var infoSecondCard = DataGenerator.getInfoSecondCard();
        var infoFirstCard = DataGenerator.getInfoFirstCard();
        var infoBalanceFirstCard = pageDashboard.getCardBalance(infoFirstCard);
        var infoBalanceSecondCard = pageDashboard.getCardBalance(infoSecondCard);
        var moneyTransit = DataGenerator.getValidAmountTransit(infoBalanceFirstCard);

        pageDashboard.selectTransitCard(infoSecondCard);

        var pageTransit = new PageTransit();
        pageTransit.moneyValidCancelTransfer(infoFirstCard, String.valueOf(moneyTransit));

        var expectedBalanceFirstCard = infoBalanceFirstCard;
        var expectedBalanceSecondCard = infoBalanceSecondCard;
        var actualBalanceFirstCard = pageDashboard.getCardBalance(infoFirstCard);
        var actualBalanceSecondCard = pageDashboard.getCardBalance(infoSecondCard);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);

        pageDashboard.selectTransitCard(infoSecondCard);

        var actual = pageTransit.shouldBeEmptyForm();
        var expected = "";
        assertEquals(expected, actual);
    }

}
