import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class LagouSearcher {
    public static void main(String[] args) {
        // 设置 webdriver 路径
        System.setProperty("webdriver.chrome.driver", LagouSearcher.class.getClassLoader().getResource("chromedriver.exe").getPath());

        // 创建 webdriver
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.lagou.com/zhaopin/Java/?labelWords=label");

        // 关掉弹窗
        WebElement btnElement = webDriver.findElement(By.xpath("//div[@class='body-box']//div[@class='body-btn']"));
        btnElement.click();

        // 选中元素
        clickOption(webDriver, "工作经验", "应届毕业生");
        clickOption(webDriver, "学历要求", "本科");
        clickOption(webDriver, "融资阶段", "不限");
        clickOption(webDriver, "公司规模", "不限");
        clickOption(webDriver, "行业领域", "移动互联网");

        // 解析页面元素
        extractJobsByPagination(webDriver);

    }

    private static void extractJobsByPagination(WebDriver webDriver) {
        List<WebElement> jobElements = webDriver.findElements(By.className("con_list_item"));
        for (WebElement jobElement : jobElements) {
            WebElement moneyElement = jobElement.findElement(By.className("position")).findElement(By.className("money"));
            String companyName = jobElement.findElement(By.className("company_name")).getText();
            System.out.println(companyName + ":" + moneyElement.getText());
        }
        WebElement nextPageBtn = webDriver.findElement(By.className("pager_next"));
        if(!nextPageBtn.getAttribute("class").contains("disabled")){
            nextPageBtn.click();
            try {
                Thread.sleep(1000L);
            }catch (InterruptedException e){
            }
            extractJobsByPagination(webDriver);
        }

    }

    private static void clickOption(WebDriver webDriver, String choseTitle, String optionTitle) {
        WebElement chosenElement = webDriver.findElement(By.xpath("//li[@class='multi-chosen']//span[contains(text(),'" + choseTitle + "')]"));
        WebElement optionElement = chosenElement.findElement(By.xpath("../a[contains(text(),'" + optionTitle + "')]"));
        optionElement.click();
    }
}
