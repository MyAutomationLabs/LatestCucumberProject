Feature: Shopping Cart and Contact Page Validation

  @test1
  Scenario: Validate mandatory field errors on Contact page
    Given User is on the Jupiter Toys home page
    When User navigates to the Contact page
    And User clicks the Submit button without entering any details
    Then Error messages should be displayed for all mandatory fields
    When User fills in all mandatory fields
    And User clicks the Submit button again
    Then Error messages should disappear

  @test2
  Scenario Outline: Validate successful submission on Contact page
    Given User is on the Jupiter Toys home page
    When User navigates to the Contact page
    And User fills in all mandatory fields
    And User clicks the Submit button again
    Then Successful submission message should be displayed

    Examples:
      | run |
      | 1   |
      | 2   |
      | 3   |
      | 4   |
      | 5   |

  @test3
  Scenario: Verify cart calculations
    Given User is on the Jupiter Toys shop page
    When User adds products to cart
    And User goes to the cart page
    Then Verify subtotal and price for each product
    And Verify total equals sum of subtotals