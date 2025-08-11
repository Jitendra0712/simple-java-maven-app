package com.example;

import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        // Get env from system property; default to dev
        String env = System.getProperty("env", "dev");
        System.out.println("Running for environment: " + env);

        // Load config.yaml
        Yaml yaml = new Yaml();
        try (InputStream in = App.class.getResourceAsStream("/config.yaml")) {
            Map<String, Object> data = yaml.load(in);

            Map<String, Object> environments = (Map<String, Object>) data.get("environments");
            Map<String, Object> selectedEnv = (Map<String, Object>) environments.get(env);

            System.out.println("Server Host: " + ((Map) selectedEnv.get("server")).get("host"));
            System.out.println("Server Port: " + ((Map) selectedEnv.get("server")).get("port"));
            System.out.println("Database Name: " + ((Map) selectedEnv.get("database")).get("name"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
