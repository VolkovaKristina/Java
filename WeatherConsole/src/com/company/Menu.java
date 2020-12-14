package com.company;

import com.company.database.DataBase;
import com.company.database.User;
import com.company.exceptions.ApiException;
import com.company.exceptions.CityNotFoundException;
import com.company.weather.OpenWeatherMap;
import com.company.weather.WeatherBit;
import com.company.weather.WeatherObject;
import com.company.weather.YandexWeather;

import java.util.*;

public class Menu {
    private User user;
    private TreeMap<Integer, String> menu;
    private Map<Integer, Integer> commands;

    public void run() {
        DataBase db = DataBase.getInstance();
        System.out.println("* Погодный консольный сервис *\n");

        String name = nameInput();
        user = db.getUser(name);
        System.out.println("\n" + user.getUsername() + ", добро пожаловать в личный кабинет!\n");

        menu();
    }

    private String input(String message) {
        Scanner in = new Scanner(System.in);
        System.out.println(message);
        System.out.print(">>> ");
        return in.nextLine();
    }

    private String nameInput() {
        String name = input("Введите ваше имя:");

        String validCharacters = "_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (char ch: name.toCharArray()) {
            if (validCharacters.indexOf(ch) == -1) {
                System.out.println("Имя содержит недопустимые символы. Попробуйте ещё раз.\n");
                return nameInput();
            }
        }
        return name;
    }

    private void menu() {
        update();
        print();
        Integer point = null;
        try {
            point = Integer.parseInt(input("Выберите пункт меню:"));
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод. Попробуйте ещё раз.\n");
            menu();
            return;
        }

        if (commands.containsKey(point)) {
            Integer command = commands.get(point);

            try {
                if (command == 1) {
                    setCity();
                } else if (command == 2) {
                    setService();
                } else if (command == 3) {
                    run();
                } else if (command == 4) {
                    setCity();
                } else if (command == 5) {
                    setService();
                } else if (command == 13) {
                    WeatherObject weatherObject = OpenWeatherMap.getInstance().getCurrent(user.getCity());
                    System.out.println(weatherObject.currentWeather());
                } else if (command == 12) {
                    ArrayList<WeatherObject> weatherObjects = OpenWeatherMap.getInstance().getHourly(user.getCity());
                    for (WeatherObject weatherObject : weatherObjects) {
                        System.out.println(weatherObject.hourlyWeather());
                        System.out.println();
                        System.out.println("+ ----------------------- +");
                    }
                } else if (command == 11) {
                    ArrayList<WeatherObject> weatherObjects = OpenWeatherMap.getInstance().getDaily(user.getCity());
                    for (WeatherObject weatherObject : weatherObjects) {
                        System.out.println(weatherObject.dailyWeather());
                        System.out.println();
                        System.out.println("+ ----------------------- +");
                    }
                } else if (command == 23) {
                    WeatherObject weatherObject = YandexWeather.getInstance().getCurrent(user.getCity());
                    System.out.println(weatherObject.currentWeather());
                } else if (command == 22) {
                    ArrayList<WeatherObject> weatherObjects = YandexWeather.getInstance().getHourly(user.getCity());
                    for (WeatherObject weatherObject : weatherObjects) {
                        System.out.println(weatherObject.hourlyWeather());
                        System.out.println();
                        System.out.println("+ ----------------------- +");
                    }
                } else if (command == 21) {
                    ArrayList<WeatherObject> weatherObjects = YandexWeather.getInstance().getDaily(user.getCity());
                    for (WeatherObject weatherObject : weatherObjects) {
                        System.out.println(weatherObject.dailyWeather());
                        System.out.println();
                        System.out.println("+ ----------------------- +");
                    }
                } else if (command == 33) {
                    WeatherObject weatherObject = WeatherBit.getInstance().getCurrent(user.getCity());
                    System.out.println(weatherObject.currentWeather());
                } else if (command == 31) {
                    ArrayList<WeatherObject> weatherObjects = WeatherBit.getInstance().getDaily(user.getCity());
                    for (WeatherObject weatherObject : weatherObjects) {
                        System.out.println(weatherObject.dailyWeather());
                        System.out.println();
                        System.out.println("+ ----------------------- +");
                    }
                } else {
                    System.out.println("Некорректный ввод. Попробуйте ещё раз.\n");
                    menu();
                    return;
                }
            } catch (ApiException e) {
                System.out.println("Временные проблемы с API. Повторите запрос позже.");
            }
        }

        System.out.println("\n");
        menu();
    }

    private void update() {
        menu = new TreeMap<>();
        if (user.getCity() == null || user.getCity().equals("")) {
            menu.put(1, "Выбрать город");
        }
        if (user.getServiceId() == null || user.getServiceId() == 0) {
            menu.put(2, "Выбрать погодный сервис");
        } else if (user.getCity() != null && !user.getCity().equals("")) {
            if (user.getServiceId() == 1) {
                menu.put(13, "Текущая погода (Open Weather)");
                menu.put(12, "Почасовой прогноз (48 часов) (Open Weather)");
                menu.put(11, "Прогноз на 7 дней (Open Weather)");
            } else if (user.getServiceId() == 2) {
                menu.put(23, "Текущая погода (Яндекс.Погода)");
                menu.put(22, "Почасовой прогноз (24 часа) (Яндекс.Погода)");
                menu.put(21, "Прогноз на 7 дней (Яндекс.Погода)");
            } else if (user.getServiceId() == 3) {
                menu.put(33, "Текущая погода (WeatherBit)");
                menu.put(31, "Прогноз на 16 дней (WeatherBit)");
            }
            menu.put(4, "Сменить город");
            menu.put(5, "Сменить погодный сервис");
        }

        menu.put(3, "Сменить пользователя");
    }

    private void print() {
        commands = new HashMap<>();
        int i = 1;
        for (Integer key : menu.descendingKeySet()) {
            String value = menu.get(key);
            System.out.println(i + ". " + value);
            commands.put(i++, key);
        }
    }

    private void setCity() {
        String city = input("Введите название города:\n");
        try {
            city = DataBase.getInstance().getCity(city);
        } catch (CityNotFoundException e) {
            System.out.println("Некорректный город. Попробуйте ещё раз.\n");
            setCity();
            return;
        }

        user.setCity(city);
        DataBase.getInstance().setCity(user);
    }

    private void setService() {
        System.out.println("1. Open Weather");
        System.out.println("2. Яндекс.Погода");
        System.out.println("3. WeatherBit");
        int serviceId = 0;

        try {
            serviceId = Integer.parseInt(input("Выберите сервис:\n"));
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод. Попробуйте ещё раз.\n");
            setService();
            return;
        }

        user.setServiceId(serviceId);
        DataBase.getInstance().setServiceId(user);
    }
}
