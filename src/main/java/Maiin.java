import org.apache.log4j.PropertyConfigurator;

public class Maiin {
    public static void main(String[] args) {
        String log4jConfPath = "/path/to/log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
    }
}
