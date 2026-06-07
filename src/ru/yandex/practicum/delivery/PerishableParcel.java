package ru.yandex.practicum.delivery;

public class PerishableParcel extends Parcel {
    private static final int BASE_COST = 3;
    private int timeToLive;

    public PerishableParcel(int sendDay, String deliveryAddress, int weight, String description, int timeToLive) {
        super(sendDay, deliveryAddress, weight, description);
        this.timeToLive = timeToLive;
    }

    @Override
    public void packageItem() {
        printPackMessage();
    }

    @Override
    protected int getBaseCost() {
        return BASE_COST;
    }

    public boolean isExpired(int currentDay) {
        return getSendDay() + timeToLive < currentDay;
    }
}
