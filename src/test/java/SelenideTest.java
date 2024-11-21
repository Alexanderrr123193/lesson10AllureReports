import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
public class SelenideTest {
    @DisplayName("Проверка названия Issues обычный тест")
    @Test
    public void SelenideIssueTest(){
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/");

        $(".header-search-button").click();
        $("#query-builder-test").sendKeys("eroshenkoam/allure-example");
        $("#query-builder-test").submit();

        $(linkText("eroshenkoam/allure-example")).click();
        $("#issues-tab").click();
        $(withText("#95")).should(Condition.exist);
    }


    @DisplayName("Проверка названия Issues с помощью Лямбда")
    @Test
    public void LambdaIssueTest(){
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открыть Гитхаб", () -> {
            open("https://github.com/");
        });
        step("Осуществить поиск" + TestBase.repoPath, () -> {
            $(".header-search-button").click();
            $("#query-builder-test").sendKeys(TestBase.repoPath);
            $("#query-builder-test").submit();
        });
        step("Клик по ссылке репозитория " + TestBase.repoPath, () -> {
            $(linkText(TestBase.repoPath)).click();
        });
        step("Открыть вкладку Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверить наличие Issue с именем " + TestBase.issueName, () -> {
            $(withText(TestBase.issueName)).should(Condition.exist);
        });

    }
    @DisplayName("Проверка названия Issues с помощью steps")
    @Test
    void StepIssueTest(){
        SelenideLogger.addListener("allure", new AllureSelenide());
        Steps steps = new Steps();

        steps.openMainPage();
        steps.searchForRepository(TestBase.repoPath);
        steps.clickOnRepositoryLink(TestBase.repoPath);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(TestBase.issueName);

    }
}
