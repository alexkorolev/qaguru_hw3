import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class demoqaTests {
    @BeforeEach
    void beforeMethod(){
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 5000;
    }

    @Test
    void fillThePracticeForm() {
        open("https://demoqa.com/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("[id=firstName]").setValue("FirstName");
        $("[id=lastName]").setValue("LastName");
        $("[id=userEmail]").setValue("test@test.com");
        $("[id=gender-radio-1]").doubleClick();
        $("[id=userNumber]").setValue("9990002233");

        $("[id=dateOfBirthInput]").click();
        $("select[class*='month-select']").selectOptionByValue("9");
        $("select[class*='year-select']").selectOptionByValue("1985");
        $("div[aria-label*='October 30th']").click();

        $("[id=subjectsContainer] input").click();
        $("[id=subjectsContainer] input").setValue("English").pressEnter();

        $("[id=hobbiesWrapper]").$(byText("Reading")).click();

        $("[id=uploadPicture]").uploadFile(new File("src/test/data/my.png"));

        $("[id=currentAddress]").setValue("Lenina street 100 , flat 101");

        $("[id=state] input").val("NCR").pressEnter();
        $("[id=city] input").val("Noida").pressEnter();
        $("#submit").click();

        $(".table-responsive").
                shouldHave(text("FirstName LastName")).
                shouldHave(text("test@test.com")).
                shouldHave(text("Male")).
                shouldHave(text("9990002233")).
                shouldHave(text("30 October,1985")).
                shouldHave(text("English")).
                shouldHave(text("Reading")).
                shouldHave(text("my.png")).
                shouldHave(text("Lenina street 100 , flat 101")).
                shouldHave(text("NCR Noida"));
    }
}
