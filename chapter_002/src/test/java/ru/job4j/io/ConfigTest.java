package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenAppProperties() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("hibernate.connection.driver_class"),
                is("org.postgresql.Driver")
        );
    }
}
