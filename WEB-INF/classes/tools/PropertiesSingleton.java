package tools;

import java.io.IOException;
import java.util.Properties;

public class PropertiesSingleton {

    private static PropertiesSingleton config;
    private Properties props;
    private static String configFile = "/resources/config.properties";

    private PropertiesSingleton(String configFile) throws IOException {

        this.configFile = configFile;
        props = new Properties();

        String myPath = this.getClass().getResource(configFile).toString();
        props.load(this.getClass().getResourceAsStream(configFile));
    }

    public static PropertiesSingleton getInstance(String configFile) throws IOException {

        if(config == null) {
            config = new PropertiesSingleton(configFile);
        }
        else if(!configFile.equals(config.getConfigFile())) {
            return new PropertiesSingleton(configFile);
        }

        return config;
    }

    public String getConfigFile() {

        return this.configFile;
    }

    public static PropertiesSingleton getInstance() {

        if(config == null) {
            try {
                config = getInstance(configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return config;
    }

    public String getProperty(String key) {

        return props.getProperty(key);
    }
}