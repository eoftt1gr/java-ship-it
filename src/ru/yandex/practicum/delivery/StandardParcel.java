package ru.yandex.practicum.delivery;

public class StandardParcel extends Parcel {
    private static final int BASE_COST = 2;

    public StandardParcel(int sendDay, String deliveryAddress, int weight, String description) {
        super(sendDay, deliveryAddress, weight, description);
    }

    @Override
    public void packageItem() {
        printPackMessage();
    }

    @Override
    protected int getBaseCost() {
        return BASE_COST;
    }
}
