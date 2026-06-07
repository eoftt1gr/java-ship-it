package ru.yandex.practicum.delivery;

import java.util.Scanner;
import java.util.ArrayList;

import ru.yandex.practicum.delivery.*;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static ArrayList<Parcel> allParcels = new ArrayList<>();
    private static ParcelBox<StandardParcel> standartBox = new ParcelBox<StandardParcel>(200);
    private static ParcelBox<FragileParcel> fragileBox = new ParcelBox<FragileParcel>(100);
    private static ParcelBox<PerishableParcel> perishableBox = new ParcelBox<PerishableParcel>(50);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("0 — Завершить");
    }

    private static Parcel inputParcel(int type) {
        System.out.println("Введите день отправки:");
        int sendDay = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите адрес доставки:");
        String deliveryAddress = scanner.nextLine();

        System.out.println("Введите вес посылки:");
        int weight = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите содежание посылки:");
        String description = scanner.nextLine();

        switch (type) {
            case 1:
                if (standartBox.getMaxWeight() >= weight + standartBox.currentWeight()) {
                    allParcels.add(new StandardParcel(sendDay, deliveryAddress, weight, description));
                }
                return new StandardParcel(sendDay, deliveryAddress, weight, description);
            case 2:
                if (fragileBox.getMaxWeight() >= weight + fragileBox.currentWeight()) {
                    allParcels.add(new FragileParcel(sendDay, deliveryAddress, weight, description));
                }
                return new FragileParcel(sendDay, deliveryAddress, weight, description);
            case 3:
                System.out.println("Введите срок годности посылки:");
                int timeToLive = Integer.parseInt(scanner.nextLine());
                if (perishableBox.getMaxWeight() >= weight + perishableBox.currentWeight()) {
                    allParcels.add(new PerishableParcel(sendDay, deliveryAddress, weight, description, timeToLive));
                }
                return new PerishableParcel(sendDay, deliveryAddress, weight, description, timeToLive);
        }
        return new StandardParcel(sendDay, deliveryAddress, weight, description);
    }


    // реализуйте методы ниже

    private static void addParcel() {
        // Подсказка: спросите тип посылки и необходимые поля, создайте объект и добавьте в allParcels
        System.out.println("Какой тип поссылки вы хотите отправить?");
        System.out.println("1 - Простой");
        System.out.println("2 - Хрупкий");
        System.out.println("3 - Скоропортящийся");
        int nameBox = Integer.parseInt(scanner.nextLine());
        switch (nameBox) {
            case 1:
                standartBox.addParcel((StandardParcel) inputParcel(1));
                printParcelBox(1);
                break;
            case 2:
                fragileBox.addParcel((FragileParcel) inputParcel(2));
                printParcelBox(2);
                break;
            case 3:
                perishableBox.addParcel((PerishableParcel) inputParcel(3));
                printParcelBox(3);
                break;
            default:
                System.out.println("Такого типа нет!");
        }

    }

    private static void printParcelBox(int type) {
        System.out.println("Хотите посмотреть содержимое коробки?");
        System.out.println("1 - Да");
        System.out.println("2 - Нет");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice != 1) {
            return;
        }
        switch (type) {
            case 1:
                ArrayList<StandardParcel> standartList = standartBox.getAllParcels();
                for (StandardParcel parcel : standartList) {
                    parcel.packageItem();
                }
                break;
            case 2:
                ArrayList<FragileParcel> fragileList = fragileBox.getAllParcels();
                for (FragileParcel parcel : fragileList) {
                    parcel.packageItem();
                }
                break;
            case 3:
                ArrayList<PerishableParcel> perishableList = perishableBox.getAllParcels();
                for (PerishableParcel parcel : perishableList) {
                    parcel.packageItem();
                }
                break;
        }
    }

    private static void sendParcels() {
        // Пройти по allParcels, вызвать packageItem() и deliver()
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        // Посчитать общую стоимость всех доставок и вывести на экран
        int sum = 0;
        for (Parcel parcel : allParcels) {
            sum += parcel.calculateDeliveryCost();
        }
        System.out.println("Общая стоимость всех доставок: " + sum);
    }

}
