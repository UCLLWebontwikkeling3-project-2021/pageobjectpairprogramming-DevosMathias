import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

/**
 * @author Baljit Singh & Mathias Devos
 */

public class PersonOverviewPage extends Page {

    @FindBy(id="emailPerson")
    private WebElement email;

    @FindBy(id="firstnamePerson")
    private WebElement firstname;

    @FindBy(id="lastnamePerson")
    private WebElement lastname;

    public PersonOverviewPage(WebDriver driver) {
        super(driver);
        this.driver.get(getPath()+"?action=Overview");
    }

    public boolean containsUserWithEmail(String email) {
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) this.driver.findElements(By.cssSelector("td"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains(email)) {
                found=true;
            }
        }
        return found;
    }

    public WebElement getEmail() {
        return email;
    }

    public WebElement getFirstname() {
        return firstname;
    }

    public WebElement getLastname() {
        return lastname;
    }
}
