Feature: Get all products from the fakestore api

  @api1
  Scenario: Verify the GET api for the products
    Given User enter endpoint url for GET products api
    When User make request for GET products api
    Then User receive the response code as 200 Ok
  @api2
  Scenario: Verify the rate of the first product
    Given User enter endpoint url for GET products api
    When User make request for GET products api
    Then User receive the response code as 200 Ok