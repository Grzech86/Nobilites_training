package Nobilities.ex63;

//# Read Config file
//        Create an application that reads and print to console a configuration file:
//        ```json
//        {
//        "name" : "application0",
//        "data" : "../source/data",
//        "mask" : "*.dat",
//        "target": {
//        "data" : "../target/data",
//        "delay" : "5s",
//        "overwrite" : true
//      }
//        }
//        ```
//        Note: configuration must be modelled as Java class (i.e. not a Map, not a string).
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;

public class ConfigEx {


 record Config( String name, String data, String mask, Target target) {}
 record Target (String data, String delay, boolean overwrite) {}

    private final static ObjectMapper mapper = new ObjectMapper();


    private static void printConfig(Config config) {
        System.out.println(config);
    }

    public static void main(String[] args) {

        final var config = decodeConfig();
        printConfig(config);
    }

    private static Config decodeConfig() {
        try {
            return mapper.readValue(Path.of("config.json").toFile(), Config.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
class ConfigExV2 {
    // POJO
    public static class Config {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getMask() {
            return mask;
        }

        public void setMask(String mask) {
            this.mask = mask;
        }

        public Target getTarget() {
            return target;
        }

        public void setTarget(Target target) {
            this.target = target;
        }

        @Override
        public String toString() {
            return "Config{" +
                    "name='" + name + '\'' +
                    ", data='" + data + '\'' +
                    ", mask='" + mask + '\'' +
                    ", target=" + target +
                    '}';
        }

        private String data;
        private String mask;
        private Target target;
    }

    public static class Target {
        @Override
        public String toString() {
            return "Target{" +
                    "data='" + data + '\'' +
                    ", delay='" + delay + '\'' +
                    ", overwrite=" + overwrite +
                    '}';
        }

        private String data;
        private String delay;
        private boolean overwrite;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getDelay() {
            return delay;
        }

        public void setDelay(String delay) {
            this.delay = delay;
        }

        public boolean isOverwrite() {
            return overwrite;
        }

        public void setOverwrite(boolean overwrite) {
            this.overwrite = overwrite;
        }
    }

    private final static ObjectMapper mapper = new ObjectMapper();


    private static void printConfig(Config config) {
        System.out.println(config);
    }

    public static void main(String[] args) {

        final var config = decodeConfig();
        config.toString();
        printConfig(config);
    }

    private static Config decodeConfig() {
        try {
            return mapper.readValue(Path.of("config.json").toFile(), Config.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}