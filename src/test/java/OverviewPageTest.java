import domain.model.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;

/**
 * author Baljit Singh & Mathias Devos
 */

public class OverviewPageTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Eigenaar\\Documents\\Toegepaste informatica\\2020-2021\\Webontwikkeling 3\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void Test_Logged_In_As_Admin_Shows_OverviewPage() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setUseridField("admin");
        homePage.setPasswordField("t");

        homePage.submitValid();

        PersonOverviewPage personOverviewPage = PageFactory.initElements(driver, PersonOverviewPage.class);
        assertEquals("Overview", personOverviewPage.getTitle());
        assertTrue(personOverviewPage.containsUserWithEmail("admin@ucll.be"));
    }

    @Test
    public void Test_Logged_Out_User_Cannot_See_OverviewPage() {
        //geeft als return de homePage aangezien we als een bezoeker geen rechten hebben om deze pagina te bekijken
        PersonOverviewPage personOverviewPage = PageFactory.initElements(driver, PersonOverviewPage.class);
        assertEquals("Home", personOverviewPage.getTitle());
    }

    @Test
    public void Test_Logged_In_User_Cannot_See_OverviewPage() {
        SignUpPage signUpPage = PageFactory.initElements(driver, SignUpPage.class);

        String userid = generateRandomUseridInOrderToRunTestMoreThanOnce("arthur");

        signUpPage.setUserid(userid);
        signUpPage.setEmail("arthur.king@camalot.be");
        signUpPage.setFirstName("Arthur");
        signUpPage.setLastName("Cochet");
        signUpPage.setPassword("000");
        signUpPage.submitValid();

        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setUseridField(userid);
        homePage.setPasswordField("000");
        homePage.submitValid();

        //geeft als return de homePage aangezien we als een user geen rechten hebben om deze pagina te bekijken
        PersonOverviewPage personOverviewPage = PageFactory.initElements(driver, PersonOverviewPage.class);
        assertEquals("Home", personOverviewPage.getTitle());
    }

    @After
    public void clean() {
        driver.quit();
    }

    private String generateRandomUseridInOrderToRunTestMoreThanOnce(String component) {
        int random = (int)(Math.random() * 1000 + 1);
        return random+component;
    }

}
