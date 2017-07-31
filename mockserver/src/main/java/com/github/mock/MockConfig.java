package com.github.mock;

import com.github.mock.util.NameUtils;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
class MockConfig {
    private static final String PREFIX = "mock.";
    @Getter
    private List<Config> configs = new ArrayList<>();

    MockConfig() {
        try {
            loadProperties();
            checkConfigs();
        } catch (Exception e) {
            throw new IllegalArgumentException("load config error! error={}", e);
        }
    }

    private void checkConfigs() {
        if (configs.isEmpty()) {
            log.error("there must be have properties! such as : mock.tenant[...], mock.port[...] and so on");
            throw new IllegalArgumentException();
        }

        Set<Integer> ports = configs.stream().map(Config::getPort).collect(Collectors.toSet());
        if (ports.size() != configs.size()) {
            log.error("port must be not same of each tenant!");
            throw new IllegalArgumentException();
        }
    }

    private void loadProperties() throws Exception {
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("app.properties")) {
            Properties properties = new Properties();
            properties.load(is);
            createConfigs(properties);
        }
    }

    private void createConfigs(Properties properties) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        long len = properties.stringPropertyNames().stream().filter(str -> str.startsWith("mock.tenant[")).count();

        for (int i = 0; i < len; i++) {
            Config config = new Config();
            for (Field field : config.getClass().getDeclaredFields()) {
                String propertiesKey = propertiesKey(field.getName(), i);
                if (properties.containsKey(propertiesKey)) {
                    Method method = config.getClass().getDeclaredMethod(NameUtils.setMethoodName(field.getName()), field.getType());
                    method.setAccessible(true);
                    Object value = NameUtils.convertValueType(field.getType(), properties.getProperty(propertiesKey));
                    method.invoke(config, value);
                } else {
                    log.warn("properties doesn't contains propertiesKey {}", propertiesKey);
                }
            }
            configs.add(config);
        }
    }

    private String propertiesKey(String fieldName, int index) {
        return PREFIX + fieldName + "[" + index + "]";
    }

    @Data
    @Accessors(chain = true)
    static class Config {
        private String tenant;
        private Integer port;
    }
}
