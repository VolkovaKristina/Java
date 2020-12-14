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

public class WeatherBit {
    private static WeatherBit instance;
    private final String apiKey;
    private final String currentUrl;
    private final String dailyUrl;
    private JsonNode currentNode;
    private JsonNode dailyNode;

    private WeatherBit() {
        apiKey = "233829918841473987484a760d832e6b";
        currentUrl = "https://api.weatherbit.io/v2.0/current";
        dailyUrl = "https://api.weatherbit.io/v2.0/forecast/daily";
    }

    public static WeatherBit getInstance() {
        if (instance == null) {
            instance = new WeatherBit();
        }
        return instance;
    }

    public WeatherObject getCurrent(String city) throws ApiException {
        update(city);
        WeatherObject weatherObject = new WeatherObject();
        JsonNode node = currentNode.get("data").elements().next();

        weatherObject.setDateTime(WeatherObject.secToMilli(node.get("ts").asLong()));
        weatherObject.setTemp(node.get("temp").asDouble());
        weatherObject.setFeelsLike(node.get("app_temp").asDouble());
        weatherObject.setPressure(WeatherObject.mbToMmHg(node.get("pres").asDouble()));
        weatherObject.setHumidity(node.get("rh").asInt());
        weatherObject.setWindSpeed(node.get("wind_spd").asDouble());
        weatherObject.setWindDir(node.get("wind_cdir").asText());
        weatherObject.setDescription(node.get("weather").get("description").asText());

        return weatherObject;
    }

    public ArrayList<WeatherObject> getDaily(String city) throws ApiException {
        update(city);

        ArrayList<WeatherObject> weatherObjects = new ArrayList<>();
        Iterator<JsonNode> elements = dailyNode.get("data").elements();
        while (elements.hasNext()) {
            WeatherObject weatherObject = new WeatherObject();
            JsonNode node = elements.next();

            weatherObject.setDateTime(WeatherObject.secToMilli(node.get("ts").asLong()));

            weatherObject.setMornTemp(node.get("temp").asDouble());
            weatherObject.setDayTemp(node.get("max_temp").asDouble());
            weatherObject.setEveTemp(node.get("temp").asDouble());
            weatherObject.setNightTemp(node.get("min_temp").asDouble());

            weatherObject.setMornFeelsLike(node.get("min_temp").asDouble());
            weatherObject.setDayFeelsLike(node.get("app_max_temp").asDouble());
            weatherObject.setEveFeelsLike(node.get("low_temp").asDouble());
            weatherObject.setNightFeelsLike(node.get("app_min_temp").asDouble());

            weatherObject.setPressure(WeatherObject.mbToMmHg(node.get("pres").asDouble()));
            weatherObject.setHumidity(node.get("rh").asInt());
            weatherObject.setWindSpeed(node.get("wind_spd").asDouble());
            weatherObject.setWindDir(node.get("wind_cdir").asText());
            weatherObject.setDescription(node.get("weather").get("description").asText());

            weatherObjects.add(weatherObject);
        }

        return weatherObjects;
    }

    protected void update(String city) throws ApiException {
        String response = "";
        HashMap<String, String> params = getParams(city);
        HttpRequester requester = new HttpRequester();

        try {
            response = requester.get(currentUrl, params);
            ObjectMapper mapper = new ObjectMapper();
            currentNode = mapper.readTree(response);

            response = requester.get(dailyUrl, params);
            dailyNode = mapper.readTree(response);
        } catch (IOException | InterruptedException e) {
            throw new ApiException();
        }
    }

    protected HashMap<String, String> getParams(String city) {
        Double[] coords = DataBase.getInstance().getCoordsByCity(city);
        String lat = coords[0].toString();
        String lon = coords[1].toString();

        return new HashMap<>() {{
            put("lat", lat);
            put("lon", lon);
            put("units", "M");
            put("key", apiKey);
        }};
    }
}
