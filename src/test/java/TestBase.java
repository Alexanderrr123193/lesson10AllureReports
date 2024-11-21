import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    static final String issueName = "ghskfdkaslfd2133";
    static final String repoPath = "eroshenkoam/allure-example";
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 7000;
    }
    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }
}