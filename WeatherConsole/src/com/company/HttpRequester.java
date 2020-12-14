package com.company;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class HttpRequester {
    private final HttpClient client;

    public HttpRequester() {
        client = HttpClient.newHttpClient();
    }

    public String get(String url) throws IOException, InterruptedException {
        HttpResponse<String> response = send(url);

        if (response.statusCode() != 200) {
            throw new IOException();
        }

        return response.body();
    }

    public String get(String url, Map<String, String> params) throws IOException, InterruptedException {
        url = buildUrl(url, params);
        HttpResponse<String> response = send(url);

        if (response.statusCode() != 200) {
            throw new IOException();
        }

        return response.body();
    }

    public String get(String url, Map<String, String> params, Map<String, String> headers) throws IOException, InterruptedException {
        url = buildUrl(url, params);
        HttpResponse<String> response = send(url, headers);

        if (response.statusCode() != 200) {
            throw new IOException();
        }

        return response.body();
    }

    private HttpResponse<String> send(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET().build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private HttpResponse<String> send(String url, Map<String, String> headers) throws IOException, InterruptedException {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET();

        for (Map.Entry<String, String> header: headers.entrySet()) {
            builder.setHeader(header.getKey(), header.getValue());
        }

        HttpRequest request = builder.build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private String buildUrl(String url, Map<String, String> params) {
        StringBuilder urlBuilder = new StringBuilder(url);
        urlBuilder.append("?");

        for (Map.Entry<String, String> entry: params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            urlBuilder.append(key).append("=").append(value).append("&");
        }

        return urlBuilder.toString();
    }
}
