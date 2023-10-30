import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DemoQaTests {
    @BeforeEach
    void beforeMethod(){
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillThePracticeForm() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue("FirstName");
        $("#lastName").setValue("LastName");
        $("#userEmail").setValue("test@test.com");
        $("#gender-radio-1").doubleClick();
        $("#userNumber").setValue("9990002233");

        $("#dateOfBirthInput").click();
        $("select[class*='month-select']").selectOptionByValue("9");
        $("select[class*='year-select']").selectOptionByValue("1985");
        $("div[aria-label*='October 30th']").click();

        $("#subjectsContainer input").click();
        $("#subjectsContainer input").setValue("English").pressEnter();

        $("#hobbiesWrapper").$(byText("Reading")).click();

        $("#uploadPicture").uploadFromClasspath("my.png");
        //$("[#uploadPicture]").uploadFile(new File("src/test/data/my.png"));

        $("#currentAddress").setValue("Lenina street 100 , flat 101");

        $("#state input").val("NCR").pressEnter();
        $("#city input").val("Noida").pressEnter();
        $("#submit").click();

        $(".table-responsive").shouldHave(text("FirstName LastName"));
        $(".table-responsive").shouldHave(text("test@test.com"));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("9990002233"));
        $(".table-responsive").shouldHave(text("30 October,1985"));
        $(".table-responsive").shouldHave(text("English"));
        $(".table-responsive").shouldHave(text("Reading"));
        $(".table-responsive").shouldHave(text("my.png"));
        $(".table-responsive").shouldHave(text("Lenina street 100 , flat 101"));
        $(".table-responsive").shouldHave(text("NCR Noida"));
    }
}
