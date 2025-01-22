package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.page.PageDashboard;
import ru.netology.page.PageLogin;
import ru.netology.page.PageVer;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataGenerator.getAutInfo;
import static ru.netology.data.DataGenerator.getVerCode;

public class TransferTest {

    @Test
    void setUp() {
        open("http://localhost:9999");
        Configuration.holdBrowserOpen = true;
        var autInfo = getAutInfo();
        var loginPage = new PageLogin();
        loginPage.validLogin(autInfo);
        var verificationCode = getVerCode();
        var verPage = new PageVer();
        verPage.validVer(verificationCode);


    }

}
