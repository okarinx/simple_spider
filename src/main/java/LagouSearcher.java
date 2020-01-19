import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LagouSearcher {
    public static void main(String[] args) {
        // 设置 webdriver 路径
        System.setProperty("webdriver.chrome.driver", LagouSearcher.class.getClassLoader().getResource("chromedriver.exe").getPath());

        // 创建 webdriver
        WebDriver webDriver = new ChromeDriver();

        webDriver.get("https://www.lagou.com/zhaopin/Java/?labelWords=label");

        webDriver.findElement(By.xpath("//div[@class=]"))

        WebElement chosenElement = webDriver.findElement(By.xpath("//li[@class='multi-chosen']//span[contains(text(),'工作经验')]"));
        WebElement optionElement = chosenElement.findElement(By.xpath("../a[contains(text(),'应届毕业生')]"));
        optionElement.click();
    }
}
