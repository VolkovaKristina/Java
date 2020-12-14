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

public class OpenWeatherMap {
    private static OpenWeatherMap instance;
    private final String apiKey;
    private final String apiUrl;
    private JsonNode currentNode;
    private JsonNode hourlyNode;
    private JsonNode dailyNode;

    private OpenWeatherMap() {
        apiKey = "a685ececafa85a7a72e75a86f499cb14";
        apiUrl = "https://api.openweathermap.org/data/2.5/onecall";
    }

    public static OpenWeatherMap getInstance() {
        if (instance == null) {
            instance = new OpenWeatherMap();
        }
        return instance;
    }

    public WeatherObject getCurrent(String city) throws ApiException {
        update(city);
        WeatherObject weatherObject = new WeatherObject();

        weatherObject.setDateTime(WeatherObject.secToMilli(currentNode.get("dt").asLong()));
        weatherObject.setTemp(currentNode.get("temp").asDouble());
        weatherObject.setFeelsLike(currentNode.get("feels_like").asDouble());
        weatherObject.setPressure(WeatherObject.pHaToMmHg(currentNode.get("pressure").asDouble()));
        weatherObject.setHumidity(currentNode.get("humidity").asInt());
        weatherObject.setWindSpeed(currentNode.get("wind_speed").asDouble());
        weatherObject.setWindDir(WeatherObject.windDegToDir(currentNode.get("wind_deg").asInt()));
        weatherObject.setDescription(currentNode.get("weather").elements().next().get("description").asText());

        return weatherObject;
    }

    public ArrayList<WeatherObject> getHourly(String city) throws ApiException {
        update(city);
        ArrayList<WeatherObject> weatherObjects = new ArrayList<>();
        Iterator<JsonNode> elements = hourlyNode.elements();
        while (elements.hasNext()) {
            WeatherObject weatherObject = new WeatherObject();
            JsonNode node = elements.next();

            weatherObject.setDateTime(WeatherObject.secToMilli(node.get("dt").asLong()));
            weatherObject.setTemp(node.get("temp").asDouble());
            weatherObject.setFeelsLike(node.get("feels_like").asDouble());
            weatherObject.setPressure(WeatherObject.pHaToMmHg(node.get("pressure").asDouble()));
            weatherObject.setHumidity(node.get("humidity").asInt());
            weatherObject.setWindSpeed(node.get("wind_speed").asDouble());
            weatherObject.setWindDir(WeatherObject.windDegToDir(node.get("wind_deg").asInt()));
            weatherObject.setDescription(node.get("weather").elements().next().get("description").asText());

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

            weatherObject.setDateTime(WeatherObject.secToMilli(node.get("dt").asLong()));

            JsonNode temp = node.get("temp");
            weatherObject.setDayTemp(temp.get("day").asDouble());
            weatherObject.setNightTemp(temp.get("night").asDouble());
            weatherObject.setEveTemp(temp.get("eve").asDouble());
            weatherObject.setMornTemp(temp.get("morn").asDouble());

            JsonNode feelsLike = node.get("feels_like");
            weatherObject.setDayFeelsLike(feelsLike.get("day").asDouble());
            weatherObject.setNightFeelsLike((feelsLike.get("night").asDouble()));
            weatherObject.setEveFeelsLike((feelsLike.get("eve").asDouble()));
            weatherObject.setMornFeelsLike((feelsLike.get("morn").asDouble()));

            weatherObject.setPressure(WeatherObject.pHaToMmHg(node.get("pressure").asDouble()));
            weatherObject.setHumidity(node.get("humidity").asInt());
            weatherObject.setWindSpeed(node.get("wind_speed").asDouble());
            weatherObject.setWindDir(WeatherObject.windDegToDir(node.get("wind_deg").asInt()));
            weatherObject.setDescription(node.get("weather").elements().next().get("description").asText());

            weatherObjects.add(weatherObject);
        }

        return weatherObjects;
    }

    private void update(String city) throws ApiException {
        String response = "";
        HashMap<String, String> params = getParams(city);
        HttpRequester requester = new HttpRequester();

        try {
            response = requester.get(apiUrl, params);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode mainNode = mapper.readTree(response);
            currentNode = mainNode.get("current");
            hourlyNode = mainNode.get("hourly");
            dailyNode = mainNode.get("daily");
        } catch (IOException | InterruptedException e) {
            throw new ApiException();
        }
    }

    public HashMap<String, String> getParams(String city) {
        Double[] coords = DataBase.getInstance().getCoordsByCity(city);
        String lat = coords[0].toString();
        String lon = coords[1].toString();

        return new HashMap<>() {{
            put("lat", lat);
            put("lon", lon);
            put("exclude", "minutely");
            put("units", "metric");
            put("appid", apiKey);
        }};
    }
}
