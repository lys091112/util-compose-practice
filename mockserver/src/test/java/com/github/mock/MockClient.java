package com.github.mock;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

class MockClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(100, TimeUnit.SECONDS).
                connectTimeout(5, TimeUnit.SECONDS).writeTimeout(100, TimeUnit.SECONDS).build();

        String url = "http://localhost:18002/crescent/login.do";
        int count = 0;
        for (int i = 0; i < 1000000; i++) {
            Request request = new Request.Builder().url(url).get().build();
            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    count++;
                }
            }
        }

        System.out.println("success Count: " + count);
        Thread.sleep(10000);
    }
}

