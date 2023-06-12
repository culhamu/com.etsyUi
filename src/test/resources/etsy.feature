
@etsy
Feature: Etsy website Ui test


  Scenario Outline: CrossBrowser test

    Given User checks etsy homepage can be opened with different "<browser>"
    Examples:
      | browser |
      | edge    |
      | chrome  |

  Scenario: Login test

    Given User logs into "etsyUrl" homepage
    Then User completes the login process
    And User adds the product to the shopping cart
    Then User makes payment transactions
    And User enters Card information
    Then User closes the browser