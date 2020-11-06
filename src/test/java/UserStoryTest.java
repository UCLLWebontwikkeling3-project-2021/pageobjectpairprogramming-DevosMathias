import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import sun.nio.cs.ArrayEncoder;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserStoryTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/Controller";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Eigenaar\\Documents\\Toegepaste informatica\\2020-2021\\Webontwikkeling 3\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(path+"?action=ContactOverview");
    }

    @After
    public void close() {
        driver.quit();
    }

    @Test
    public void Test_link_pressed_shows_same_page_with_new_link() {
        WebElement link = driver.findElement(By.id("link"));
        link.click();

        assertEquals("Contact Overview", driver.getTitle());
        ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("th"));
        assertTrue(containsWebElement(lis, "Show all contacts"));
    }

    @Test
    public void Test_link_show_all_contacts_pressed_shows_same_page_with_orginal_link() {
        driver.get(path + "?action=ContactOverview&value=yes");

        WebElement link = driver.findElement(By.id("link"));
        link.click();

        /*link = driver.findElement(By.id("link"));
        link.click();*/

        assertEquals("Contact Overview", driver.getTitle());
        ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("th"));
        assertTrue(containsWebElement(lis, "Show all unique contacts"));
    }

    @Test
    public void Test_contacts_added_with_same_information_but_different_date_or_time_displays_twice() {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");

        LocalDate date1 = LocalDate.now().minusDays(1);
        LocalDate date2 = LocalDate.now();

        LocalTime time = LocalTime.now();

        submitForm("Jos", "Swinnen", date1.format(formatter1), time.format(formatter2), "+320470864217", "josswinnen@hotmail.com");
        submitForm("Jos", "Swinnen", date2.format(formatter1), time.format(formatter2), "+320470864217", "josswinnen@hotmail.com");

        ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("td"));

        //first submit
        assertTrue(containsWebElement(lis, date1.format(formatter1)));
        assertTrue(containsWebElement(lis, time.format(formatter2)));
        assertTrue(containsWebElement(lis, "Jos Swinnen"));

        //second submit
        assertTrue(containsWebElement(lis, date2.format(formatter1)));
        assertTrue(containsWebElement(lis, time.format(formatter2)));
        assertTrue(containsWebElement(lis, "Jos Swinnen"));
    }

    @Test
    public void Test_contacts_added_with_same_information_but_different_date_or_time_displays_once_in_unique_table() {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");

        LocalDate date1 = LocalDate.now().minusDays(1);
        LocalDate date2 = LocalDate.now();

        LocalTime time = LocalTime.now();

        submitForm("Jos", "Swinnen", date1.format(formatter1), time.format(formatter2), "+320470864217", "josswinnen@hotmail.com");
        submitForm("Jos", "Swinnen", date2.format(formatter1), time.format(formatter2), "+320470864217", "josswinnen@hotmail.com");

        WebElement link = driver.findElement(By.id("link"));
        link.click();

        ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("td"));
        assertTrue(containsWebElement(lis, date2.format(formatter1)));
        assertTrue(containsWebElement(lis, time.format(formatter2)));
        assertTrue(containsWebElement(lis, "Jos Swinnen"));

    }

    /*@Test
    public void Test_link_pressed_twice_shows_same_page_with_original_link() {
        WebElement link = driver.findElement(By.id("link"));
        link.click();

        link = driver.findElement(By.id("link"));
        link.click();

        assertEquals("Contact Overview", driver.getTitle());
        ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("th"));
        assertEquals("Show all unique contacts", driver.findElement(By.id("link")).getText());
        assertTrue(containsWebElement(lis, "Show all unique contacts"));

    }*/

    private void fillOutField(String name,String value) {
        WebElement field=driver.findElement(By.id(name));
        field.clear();
        field.sendKeys(value);
    }

    private void submitForm(String fname, String lname,String date, String hour, String gsm, String email) {
        fillOutField("firstName", fname);
        fillOutField("lastName", lname);
        fillOutField("date", date);
        fillOutField("hour", hour);
        fillOutField("gsm", gsm);
        fillOutField("email", email);

        WebElement button = driver.findElement(By.id("addContact"));
        button.click();
    }

    private boolean containsWebElement(ArrayList<WebElement> lis, String element) {
        for (WebElement web : lis) {
            if (web.getText().equals(element)) {
                System.out.println(web.getText());
                System.out.println(element);

                return true;
            }
        }

        return false;
    }
}