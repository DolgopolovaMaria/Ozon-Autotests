package helpers;

import com.codeborne.selenide.Configuration;
import config.Credentials;

public class DriverSettings {
    public static void configure() {
        Configuration.baseUrl = "https://www.ozon.ru";
        Configuration.browserSize = System.getProperty("size", "1920x1080");
        Configuration.browser = System.getProperty("browser", "chrome");

        if (Credentials.isRemoteWebDriver()) {
            String user = Credentials.config.user();
            String password = Credentials.config.password();
            String remote = Credentials.config.remote();
            Configuration.remote = "https://" + user + ":" + password + "@" + remote;
        }
    }
}
