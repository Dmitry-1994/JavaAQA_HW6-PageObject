package ru.netology.data;

import lombok.Value;

import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static AutInfo getAutInfo() {
        return new AutInfo("vasya", "qwerty123");
    }

    public static VerificationCode getVerCode() {
        return new VerificationCode("12345");
    }

    public static InfoCard getInfoFirstCard() {
        return new InfoCard("5559 0000 0000 0001", "92df3f1c-a033-48e6-8390-206f6b1f56c0");
    }

    public static InfoCard getInfoSecondCard() {
        return new InfoCard("5559 0000 0000 0002", "0f3f5c2a-249e-4c3d-8287-09f7a039391d");
    }

    @Value
    public static class AutInfo {
        String login;
        String password;
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    @Value
    public static class InfoCard {
        String number;
        String id;
    }

    public static int getValidAmountTransit(int cardBalance) {
        return new Random().nextInt(Math.abs(cardBalance) + 1);
    }

    public static int getInvalidAmountTransit(int cardBalance) {
        return Math.abs(cardBalance) + 1;
    }

    public static float getValidFloatAmountTransit(int cardBalance) {
        float floatRandom = new Random().nextFloat();
        float roundedNumber = Math.round(floatRandom * 100);
        return (new Random().nextInt(Math.abs(cardBalance)) + roundedNumber) / 100;
    }

}
