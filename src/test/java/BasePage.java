import com.thoughtworks.gauge.Logger;
import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class BasePage extends BaseTest{

    @Step("<int> saniye kadar bekle")
    public void waitForSecond(int s) throws InterruptedException{
        Thread.sleep(1000*s);
    }
    @Step("<id> elementin sayfada gorunur oldugunu kontrol et ve tıkla")
    public void findByelementEndclick(String id){
        MobileElement element = appiumDriver.findElement(By.id(id));
        if(element.isDisplayed()){
            element.click();
        }else{
            System.out.println("Kontrol edilen element Görünür olmadı");
        }
    }
    @Step("Sayfayı asağı doğru kaydır")
    public void swipeUp(){
        final int ANIMATION_TIME = 200; // ms
        final int PRESS_TIME = 200; // ms
        int edgeBorder = 10;
        PointOption pointOptionStart, pointOptionEnd;
        Dimension dims = appiumDriver.manage().window().getSize();
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
        pointOptionEnd = PointOption.point(dims.width / 2, dims.height / 6);
        try {
            new TouchAction(appiumDriver)
                    .press(pointOptionStart)
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
        }
    }
    @Step("Xpath <xpath> li elementi bul ve tıkla")
    public void clickByxpath(String xpath){
        appiumDriver.findElement(By.xpath(xpath)).click();
        Logger.info("Kontrol edildi");
    }

    @Step("Id <id> li elementi bul ve tıkla")
    public void clickByid(String id){
        appiumDriver.findElement(By.id(id)).click();
    }

    @Step("Random <xpath> olarak ürün seçimi yap")
    public void randomProduct(String xpath){
        List<MobileElement> elements = appiumDriver.findElements(By.xpath(xpath));
        Random rnd = new Random();
        int rndInt = rnd.nextInt(elements.size());
        elements.get(rndInt).click();
    }
    @Step("Random <xpath> olarak beden seçimi yap")
    public void randomSize(String xpath){
        List<MobileElement> elements = appiumDriver.findElements(By.xpath(xpath));
        Random rnd = new Random();
        int rndInt = rnd.nextInt(elements.size());
        elements.get(rndInt).click();
    }
    @Step("<id> kayıtlı kullanıcı bilgilerini <text> gir")
    public void sendKeys(String id, String text){
        System.out.println("Giris yapma sayfasi acildi");
        MobileElement element = appiumDriver.findElement(By.id(id));
        if (element.isDisplayed()) {
            element.sendKeys(text);
        }
        else { System.out.println("Giriş yapma sayfası açılamadı.");
        }
    }
}
