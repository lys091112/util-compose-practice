package com.github.mock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {

    public static void main(String... args) throws Exception {

        log.info("service starting");

        new MockConfig().getConfigs().forEach(config -> {
            MockServer aiMockServer = new MockServer(config);
            aiMockServer.start();
        });

        log.info("server started success!");
    }

}

