package ru.yandex.practicum.delivery;

import java.util.ArrayList;

public class ParcelBox<T extends Parcel> {
    ArrayList<T> parcels = new ArrayList<>();
    int maxWeight;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void addParcel(T parcel) {
        int weight = parcel.getWeight();
        if (weight > maxWeight) {
            System.out.println("Посылка слишком тяжелая!");
            return;
        }
        if (weight + currentWeight() <= maxWeight) {
            parcels.add(parcel);
        } else {
            System.out.println("Коробка заполнена!");
        }

    }

    public int currentWeight() {
        int weight = 0;
        for (T pack : parcels) {
            weight += pack.getWeight();
        }
        return weight;
    }

    public ArrayList<T> getAllParcels() {
        return parcels;
    }
}
