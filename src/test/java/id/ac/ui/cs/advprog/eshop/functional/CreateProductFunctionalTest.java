package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void testCreateProduct_isCorrect(ChromeDriver driver) throws Exception {
        // Buka halaman list product
        driver.get(baseUrl + "/product/list");

        // Klik tombol "Create Product"
        driver.findElement(By.linkText("Create Product")).click();

        // Isi form
        WebElement nameInput = driver.findElement(By.name("productName"));
        nameInput.clear();
        nameInput.sendKeys("Sampo Cap Otomatis");

        WebElement quantityInput = driver.findElement(By.name("productQuantity"));
        quantityInput.clear();
        quantityInput.sendKeys("50");

        // Klik tombol Submit
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Balik ke list dan produknya ada
        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains("Sampo Cap Otomatis"));
        assertTrue(pageSource.contains("50"));
    }
}