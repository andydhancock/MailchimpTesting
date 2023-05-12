import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MailchimpRegistrationSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    @Given("I am using {string} browser")
    public void setBrowser(String browser) {
        if (browser.equals("Chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equals("Firefox")) {
            driver = new FirefoxDriver();
        }
    }

    @Given("I am on the Mailchimp registration page")
    public void navigateToRegistrationPage() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://login.mailchimp.com/signup/");
    }

    @When("I enter a username {string}")
    public void enterUsername(String username) {

        if(username.equals("AUniqueUsername")){
            username = username + System.currentTimeMillis();
        }

        WebElement usernameField = driver.findElement(By.id("new_username"));
        usernameField.sendKeys(username);
    }

    @When("I enter an email {string}")
    public void enterEmail(String email) {
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys(email);
    }

    @When("I enter a password {string}")
    public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(By.id("new_password"));
        passwordField.sendKeys(password);
    }

    @When("I click the {string} button")
    public void clickSignUpButton(String buttonName) {
        WebElement signUpButton = driver.findElement(By.id("create-account-enabled"));
        scrollToElement(signUpButton);
        wait.until(ExpectedConditions.elementToBeClickable(signUpButton));
        signUpButton.click();
    }

    @Then("I should see a {string}")
    public void verifyResultMessage(String resultMessage) {
        if (resultMessage.equals("Account created successfully")) {
            wait.until(ExpectedConditions.urlContains("https://login.mailchimp.com/signup/success/"));
            WebElement successMessageElement = waitForElement(By.tagName("h1"));
            String actualMessage = successMessageElement.getText();
            assertEquals("Check your email", actualMessage);
        } else {
            waitForElement(By.className("invalid-error"));
            List<WebElement> errorElements = driver.findElements(By.className("invalid-error"));
            boolean messageFound = false;
            for (WebElement errorElement : errorElements) {
                if (errorElement.getText().equals(resultMessage)) {
                    messageFound = true;
                    break;
                }
            }
            assertTrue(messageFound, "Expected error message not found");
        }
    }

    private void scrollToElement(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView();", element);
    }

    private WebElement waitForElement(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}
