import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * author Baljit Singh & Mathias Devos
 */

public class SignUpPage extends Page {

    @FindBy(id="userid")
    private WebElement useridField;

    @FindBy(id="firstName")
    private WebElement firstNameField;

    @FindBy(id="lastName")
    private WebElement lastNameField;

    @FindBy(id="email")
    private WebElement emailField;

    @FindBy(id="password")
    private WebElement passwordField;

    @FindBy(id="signUp")
    private WebElement signUpButton;

    public SignUpPage (WebDriver driver) {
        super(driver);
        this.driver.get(getPath()+"?action=Register");
    }

    public void setUserid(String userid) {
        useridField.clear();
        useridField.sendKeys(userid);
    }

    public void setFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void setEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void setPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public HomePage submitValid() {
        signUpButton.click();
        return PageFactory.initElements(driver, HomePage.class);
    }

    public void submitInvalid() {
        signUpButton.click();
    }

    public boolean hasErrorMessage (String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        return (message.equals(errorMsg.getText()));
    }

    public boolean hasStickyLastName (String lastname) {
        return lastname.equals(lastNameField.getAttribute("value"));
    }

    public boolean hasStickyEmail (String email) {
        return email.equals(emailField.getAttribute("value"));
    }

    public boolean hasEmptyFirstName () {
        return firstNameField.getAttribute("value").isEmpty();
    }

}