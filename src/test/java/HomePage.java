import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Baljit Singh & Mathias Devos
 */

public class HomePage extends Page {

    @FindBy(id="userid")
    private WebElement useridField;

    @FindBy(id="password")
    private WebElement passwordField;

    @FindBy(id="logIn")
    private WebElement logInButton;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver.get(getPath()+"?action=Home");
    }

    public void setUseridField(String useridField) {
        this.useridField.clear();
        this.useridField.sendKeys(useridField);
    }

    public void setPasswordField(String passwordField) {
        this.passwordField.clear();
        this.passwordField.sendKeys(passwordField);
    }

    public HomePage submitValid() {
        logInButton.click();
        return PageFactory.initElements(driver, HomePage.class);
    }

    public void submitInvalid() {
        logInButton.click();
    }
}
