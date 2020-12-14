package com.company.weather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WeatherObject {

    private long dateTime;  // unix milli
    private double temp;  // Celsius
    private double feelsLike;  // Celsius
    private double pressure;  // mmHg
    private int humidity;  // %
    private double windSpeed;  // metre/sec
    private String windDir;  // degrees
    private String description;

    private double dayTemp;  // Celsius
    private double nightTemp;  // Celsius
    private double eveTemp;  // Celsius
    private double mornTemp;  // Celsius
    private double dayFeelsLike;  // Celsius
    private double nightFeelsLike;  // Celsius
    private double eveFeelsLike;  // Celsius
    private double mornFeelsLike;  // Celsius

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir.toUpperCase();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDayTemp() {
        return dayTemp;
    }

    public void setDayTemp(double dayTemp) {
        this.dayTemp = dayTemp;
    }

    public double getNightTemp() {
        return nightTemp;
    }

    public void setNightTemp(double nightTemp) {
        this.nightTemp = nightTemp;
    }

    public double getEveTemp() {
        return eveTemp;
    }

    public void setEveTemp(double eveTemp) {
        this.eveTemp = eveTemp;
    }

    public double getMornTemp() {
        return mornTemp;
    }

    public void setMornTemp(double mornTemp) {
        this.mornTemp = mornTemp;
    }

    public double getDayFeelsLike() {
        return dayFeelsLike;
    }

    public void setDayFeelsLike(double dayFeelsLike) {
        this.dayFeelsLike = dayFeelsLike;
    }

    public double getNightFeelsLike() {
        return nightFeelsLike;
    }

    public void setNightFeelsLike(double nightFeelsLike) {
        this.nightFeelsLike = nightFeelsLike;
    }

    public double getEveFeelsLike() {
        return eveFeelsLike;
    }

    public void setEveFeelsLike(double eveFeelsLike) {
        this.eveFeelsLike = eveFeelsLike;
    }

    public double getMornFeelsLike() {
        return mornFeelsLike;
    }

    public void setMornFeelsLike(double mornFeelsLike) {
        this.mornFeelsLike = mornFeelsLike;
    }

    public static long secToMilli(long sec) {
        return sec * 1000;
    }

    public static double pHaToMmHg(double pHa) {
        return pHa * 0.750064;
    }

    public static String windDegToDir(int deg) {
        double range = 45 / 2.0;
        int normalizedDeg = deg % 360;
        if (360 - range <= normalizedDeg || 0 <= normalizedDeg && normalizedDeg <= range) {
            return "N";
        } else if (45 - range <= normalizedDeg && normalizedDeg <= 45 + range) {
            return "NE";
        } else if (90 - range <= normalizedDeg && normalizedDeg <= 90 + range) {
            return  "E";
        } else if (135 - range <= normalizedDeg && normalizedDeg <= 135 + range) {
            return "SE";
        } else if (180 - range <= normalizedDeg && normalizedDeg <= 180 + range) {
            return "S";
        } else if (225 - range <= normalizedDeg && normalizedDeg <= 225 + range) {
            return "SW";
        } else if (270 - range <= normalizedDeg && normalizedDeg <= 270 + range) {
            return "W";
        } else {
            return "NW";
        }
    }

    public static double mbToMmHg(double pres) {
        return pres / 1.3332239;
    }

    public String currentWeather() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM HH:mm");
        Date dateTime = new Date(this.dateTime);
        String temp = String.format("%.0f°С",this.temp);
        String feelsLike = String.format("%.0f°С",this.feelsLike);
        String pressure = String.format("%.0f mmHg", this.pressure);
        String humidity = String.format("%d%%", this.humidity);
        String windSpeed = String.format("%.1f m/s", this.windSpeed);

        return "\nСейчас: " + format.format(dateTime) +
                "\nТемпература: " + temp +
                "\nОщущается: " + feelsLike +
                "\nДавление: " + pressure +
                "\nВлажность: " + humidity +
                "\nСкорость ветра: " + windSpeed +
                "\nНаправление ветра: " + windDir;
    }

    public String hourlyWeather() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        Date dateTime = new Date(this.dateTime);
        String temp = String.format("%.0f°С",this.temp);
        String feelsLike = String.format("%.0f°С",this.feelsLike);
        String pressure = String.format("%.0f mmHg", this.pressure);
        String humidity = String.format("%d%%", this.humidity);
        String windSpeed = String.format("%.1f m/s", this.windSpeed);

        return "\nВремя: " + format.format(dateTime) +
                "\nТемпература: " + temp +
                "\nОщущается: " + feelsLike +
                "\nДавление: " + pressure +
                "\nВлажность: " + humidity +
                "\nСкорость ветра: " + windSpeed +
                "\nНаправление ветра: " + windDir;
    }

    public String dailyWeather() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM", Locale.ENGLISH);
        Date dateTime = new Date(this.dateTime);

        String mornTemp = String.format("%.0f°С",this.mornTemp);
        String dayTemp = String.format("%.0f°С",this.dayTemp);
        String eveTemp = String.format("%.0f°С",this.eveTemp);
        String nightTemp = String.format("%.0f°С",this.nightTemp);

        String pressure = String.format("%.0f mmHg", this.pressure);
        String humidity = String.format("%d%%", this.humidity);
        String windSpeed = String.format("%.1f m/s", this.windSpeed);

        return "\nДень: " + format.format(dateTime) +
                "\nУтро: " + mornTemp +
                "\nДень: " + dayTemp +
                "\nВечер: " + eveTemp +
                "\nНочь: " + nightTemp +
                "\nДавление: " + pressure +
                "\nВлажность: " + humidity +
                "\nСкорость ветра: " + windSpeed +
                "\nНаправление ветра: " + windDir;
    }

    @Override
    public String toString() {
        return "WeatherObject{" +
                "dateTime=" + dateTime +
                ", temp=" + temp +
                ", feelsLike=" + feelsLike +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", windSpeed=" + windSpeed +
                ", windDir='" + windDir + '\'' +
                ", description='" + description + '\'' +
                ", dayTemp=" + dayTemp +
                ", nightTemp=" + nightTemp +
                ", eveTemp=" + eveTemp +
                ", mornTemp=" + mornTemp +
                ", dayFeelsLike=" + dayFeelsLike +
                ", nightFeelsLike=" + nightFeelsLike +
                ", eveFeelsLike=" + eveFeelsLike +
                ", mornFeelsLike=" + mornFeelsLike +
                '}';
    }
}
