package ru.netology.data;

import lombok.Value;

public class DataGenerator {
    private DataGenerator() {}

    @Value
    public static class AutInfo {
        String login;
        String password;
    }

    public static AutInfo getAutInfo() {
        return new AutInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static VerificationCode getVerCode() {
        return new VerificationCode("12345");
    }
    @Value
    public static class InfoCard {
        String number;
        String id;
    }

    public static InfoCard getInfoFirstCard() {
        return new InfoCard("5559 0000 0000 0001", "92df3f1c-a033-48e6-8390-206f6b1f56c0");
    }

    public static InfoCard getInfoSecondCard() {
        return new InfoCard("5559 0000 0000 0002", "0f3f5c2a-249e-4c3d-8287-09f7a039391d");
    }

}
