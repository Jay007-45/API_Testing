Feature: Amazon Search

  @Smoke
  Scenario: Search a Product MacBook air
    Given I have a search field on Amazon Page
    When I search for a product with name "Apple MacBook Air" and price 200
    Then Product with name "Apple MacBook Pro" should be displayed
