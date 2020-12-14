package com.company.weather;

import com.company.HttpRequester;
import com.company.database.DataBase;
import com.company.exceptions.ApiException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class YandexWeather {
    private static YandexWeather instance;
    private final String apiKey;
    private final String apiUrl;
    private JsonNode mainNode;
    private JsonNode currentNode;
    private JsonNode hourlyNode;
    private JsonNode dailyNode;

    private YandexWeather() {
        apiKey = "3dab4908-5c3c-43a0-bdf2-c377d94456b6";
        apiUrl = "https://api.weather.yandex.ru/v2/forecast";
    }

    public static YandexWeather getInstance() {
        if (instance == null) {
            instance = new YandexWeather();
        }
        return instance;
    }

    public WeatherObject getCurrent(String city) throws ApiException {
        update(city);
        WeatherObject weatherObject = new WeatherObject();

        weatherObject.setDateTime(WeatherObject.secToMilli(mainNode.get("now").asLong()));
        weatherObject.setTemp(currentNode.get("temp").asDouble());
        weatherObject.setFeelsLike(currentNode.get("feels_like").asDouble());
        weatherObject.setPressure(currentNode.get("pressure_mm").asDouble());
        weatherObject.setHumidity(currentNode.get("humidity").asInt());
        weatherObject.setWindSpeed(currentNode.get("wind_speed").asDouble());
        weatherObject.setWindDir(currentNode.get("wind_dir").asText());
        weatherObject.setDescription(currentNode.get("condition").asText());

        return weatherObject;
    }

    public ArrayList<WeatherObject> getHourly(String city) throws ApiException {
        update(city);
        ArrayList<WeatherObject> weatherObjects = new ArrayList<>();
        Iterator<JsonNode> elements = hourlyNode.elements();
        while (elements.hasNext()) {
            WeatherObject weatherObject = new WeatherObject();
            JsonNode node = elements.next();

            weatherObject.setDateTime(WeatherObject.secToMilli(node.get("hour_ts").asLong()));
            weatherObject.setTemp(node.get("temp").asDouble());
            weatherObject.setFeelsLike(node.get("feels_like").asDouble());
            weatherObject.setPressure(node.get("pressure_mm").asDouble());
            weatherObject.setHumidity(node.get("humidity").asInt());
            weatherObject.setWindSpeed(node.get("wind_speed").asDouble());
            weatherObject.setWindDir(node.get("wind_dir").asText());
            weatherObject.setDescription(node.get("condition").asText());

            weatherObjects.add(weatherObject);
        }

        return weatherObjects;
    }

    public ArrayList<WeatherObject> getDaily(String city) throws ApiException {
        update(city);
        ArrayList<WeatherObject> weatherObjects = new ArrayList<>();
        Iterator<JsonNode> elements = dailyNode.elements();
        while (elements.hasNext()) {
            WeatherObject weatherObject = new WeatherObject();
            JsonNode node = elements.next();

            weatherObject.setDateTime(WeatherObject.secToMilli(node.get("date_ts").asLong()));

            JsonNode parts = node.get("parts");
            weatherObject.setDayTemp(parts.get("day").get("temp_avg").asDouble());
            weatherObject.setNightTemp(parts.get("night").get("temp_avg").asDouble());
            weatherObject.setEveTemp(parts.get("evening").get("temp_avg").asDouble());
            weatherObject.setMornTemp(parts.get("morning").get("temp_avg").asDouble());

            weatherObject.setDayFeelsLike(parts.get("day").get("feels_like").asDouble());
            weatherObject.setNightFeelsLike(parts.get("night").get("feels_like").asDouble());
            weatherObject.setEveFeelsLike(parts.get("evening").get("feels_like").asDouble());
            weatherObject.setMornFeelsLike(parts.get("morning").get("feels_like").asDouble());

            weatherObject.setPressure(parts.get("day").get("pressure_mm").asDouble());
            weatherObject.setHumidity(parts.get("day").get("humidity").asInt());
            weatherObject.setWindSpeed(parts.get("day").get("wind_speed").asDouble());
            weatherObject.setWindDir(parts.get("day").get("wind_dir").asText());
            weatherObject.setDescription(parts.get("day").get("condition").asText());

            weatherObjects.add(weatherObject);
        }

        return weatherObjects;
    }

    private void update(String city) throws ApiException {
        String response = "";
        HashMap<String, String> params = getParams(city);
        HashMap<String, String> headers = getHeaders();
        HttpRequester requester = new HttpRequester();

        try {
            response = requester.get(apiUrl, params, headers);
            ObjectMapper mapper = new ObjectMapper();
            mainNode = mapper.readTree(response);
            currentNode = mainNode.get("fact");
            hourlyNode = mainNode.get("forecasts").elements().next().get("hours");
            dailyNode = mainNode.get("forecasts");
        } catch (IOException | InterruptedException e) {
            throw new ApiException();
        }
    }

    private HashMap<String, String> getParams(String city) {
        Double[] coords = DataBase.getInstance().getCoordsByCity(city);
        String lat = coords[0].toString();
        String lon = coords[1].toString();

        return new HashMap<>() {{
            put("lat", lat);
            put("lon", lon);
            put("lang", "en_US");
            put("limit", "7");
            put("hours", "true");
        }};
    }

    private HashMap<String, String> getHeaders() {
        return new HashMap<>() {{
            put("X-Yandex-API-Key", apiKey);
        }};
    }
}
