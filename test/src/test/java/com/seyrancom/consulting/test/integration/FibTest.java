package com.seyrancom.consulting.test.integration;

import org.junit.Test;

public class FibTest {

    /*private WebDriver driver;*/

    @Test
    public void testSequence() throws Exception {

        assert true;
    }

    /*@BeforeClass
    public void startSelenium() {
        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        WebDriver driver = new FirefoxDriver();
    }

    @Test
    public void testSequence() throws Exception {

        // And now use this to visit Google
        driver.get("http://localhost:8080");
        //assert selenium.isTextPresent("55");
    }

    @Test
    public void testGoogle() throws Exception {
        // And now use this to visit Google
        driver.get("http://www.google.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("q"));

        // Enter something to search for
        element.sendKeys("Cheese!");

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());

        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("cheese!");
            }
        });

        // Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + driver.getTitle());

        // assert driver.isTextPresent("55");


    }*/

/*    @Test
    public void getLandLaordTest() {
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", "DoYlvMXN")
                .when()
                .get("/landlords/{id}")
                .then()
                .statusCode(200)
                .body("firstName", equalTo("Sam"))
                .body("trusted", equalTo(false));
    }*/

  /*  @AfterClass(alwaysRun = true)
    public void stopSelenium() {
        //Close the browser
        driver.quit();
    }

 public static final String REST_SERVICE_URI = "http://localhost:8080/Spring4MVCCRUDRestService";

*/
}