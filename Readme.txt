--------------------------LogicStudioTest1 Space Trip Automation Tests--------------------------
This repository contains automated functional tests for the space trip booking process on the demo.testim.io website. The tests are developed using Selenium WebDriver with Java and TestNG.

-------------------------------------------Requirements-----------------------------------------
1. Java installed on your system.
2. Selenium WebDriver configured in your project.
3. TestNG configured in your project.
4. ChromeDriver downloaded and configured on your system.

------------------------------------------Getting Started---------------------------------------
1. Clone this repository to your local machine using: git clone https://github.com/csorto2263/LogicStudioTest1.git
2. Configure the paths and dependencies in the project as needed for your setup.
3. Open the Test1.java file located in src/test/java/ to explore the implemented test cases.

----------------------------------------Test Cases Overview-------------------------------------
Each test case corresponds to a step in the space trip booking process as outlined in the provided instructions:

1. Validate Page Title: Ensure the page title is "Space & Beyond | Testim.io demo".
2. Check Destination "Madan": Verify the existence of the "Madan" space destination.
3. Select Departure Date: Choose the departure date as 28/04/2023.
4. Choose Ticket Details: Select 2 adults and 1 child for the ticket.
5. Filter by Blue Planets: Filter destinations by blue planets.
6. Reserve Planet Tayabamba: Reserve the "Tayabamba" planet (should be marked as booked).
7. Complete Checkout: Fill out all details in the checkout form; Social Security number: 111-11-1111; Phone: 2124567890;     Upload Vaccination Card: Upload a vaccination card image; Apply Promotional Code: Apply the promotional code "30076" and Finalize Payment: Complete the payment process.