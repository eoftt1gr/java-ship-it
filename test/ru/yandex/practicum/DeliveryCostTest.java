package ru.yandex.practicum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.*;

public class DeliveryCostTest {
    private static final int BASE_COST_STANDART = 2;
    private static final int BASE_COST_FRAGILE = 4;
    private static final int BASE_COST_PERISHABLE = 3;

    private static ParcelBox<StandardParcel> standartBox;
    private static ParcelBox<FragileParcel> fragileBox;
    private static ParcelBox<PerishableParcel> perishableBox;

    private static StandardParcel standardParcel;
    private static FragileParcel fragileParcel;
    private static PerishableParcel perishableParcel;

    @BeforeEach
    public void beforeEach() {
        standardParcel = new StandardParcel(1, "Россия", 10, "Багаж");
        fragileParcel = new FragileParcel(2, "Франция", 10, "Багаж");
        perishableParcel = new PerishableParcel(3, "Великобритания", 10, "Багаж", 2);

        standartBox = new ParcelBox<StandardParcel>(200);
        fragileBox = new ParcelBox<FragileParcel>(100);
        perishableBox = new ParcelBox<PerishableParcel>(50);
    }


    @Test
    void calculationCostStandartParcel() {
        int value = standardParcel.calculateDeliveryCost();
        Assertions.assertEquals(10 * BASE_COST_STANDART, value);
    }

    @Test
    void calculationCostFragileParcel() {
        int value = fragileParcel.calculateDeliveryCost();
        Assertions.assertEquals(10 * BASE_COST_FRAGILE, value);
    }

    @Test
    void calculationCostPerishableParcel() {
        int value = perishableParcel.calculateDeliveryCost();
        Assertions.assertEquals(10 * BASE_COST_PERISHABLE, value);

    }

    @Test
    void CheckingForPerishableTrue() {
        Assertions.assertTrue(perishableParcel.isExpired(6));
    }

    @Test
    void CheckingForPerishableFalse() {
        Assertions.assertFalse(perishableParcel.isExpired(5));
    }

    @Test
    void ShouldBeOverflowStandartBox201() {
        standartBox.addParcel(new StandardParcel(10, "Россия", 201, "Багаж"));
        Assertions.assertEquals(0, standartBox.currentWeight());
    }

    @Test
    void ShouldBeOverflowFragileBox101() {
        fragileBox.addParcel(new FragileParcel(20, "Франция", 101, "Багаж"));
        Assertions.assertEquals(0, fragileBox.currentWeight());
    }

    @Test
    void ShouldBeOverflowPerishableBox51() {
        perishableBox.addParcel(new PerishableParcel(30, "Великобритания", 51, "Багаж", 2));
        Assertions.assertEquals(0, perishableBox.currentWeight());
    }

    @Test
    void ShouldBeNotOverflowStandartBox() {
        standartBox.addParcel(new StandardParcel(10, "Россия", 50, "Багаж"));
        standartBox.addParcel(new StandardParcel(10, "Россия", 150, "Багаж"));
        Assertions.assertEquals(200, standartBox.currentWeight());
    }

    @Test
    void ShouldBeNotOverflowFragileBox() {
        fragileBox.addParcel(new FragileParcel(20, "Франция", 50, "Багаж"));
        fragileBox.addParcel(new FragileParcel(20, "Франция", 50, "Багаж"));
        Assertions.assertEquals(100, fragileBox.currentWeight());
    }

    @Test
    void ShouldBeNotOverflowPerishableBox() {
        perishableBox.addParcel(new PerishableParcel(30, "Великобритания", 25, "Багаж", 2));
        perishableBox.addParcel(new PerishableParcel(30, "Великобритания", 25, "Багаж", 2));
        Assertions.assertEquals(50, perishableBox.currentWeight());
    }

}
