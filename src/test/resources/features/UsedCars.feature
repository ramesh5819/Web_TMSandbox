Feature: User avails the feature of browsing or buying used cars
  As a customer, I want to browse and buy used cars so that I can choose my favourite car


  Background: User launches Trade Me Home page
    Given I launch Trade me home page

  @usedcars
  Scenario: User should see at least one product under used cars
    And I select "Motors" category
    When I choose "UsedCars" under Motors
    Then I should see at least one listing available

  @usedcars
  Scenario: Validate the existence of Kia category under used cars
    And I select "Motors" category
    When I choose "UsedCars" under Motors
    Then I should see that "Kia" category exists

  @usedcars
  Scenario: Check the details shown for used cars
    And I select "Motors" category
    When I choose "UsedCars" under Motors
    And I choose a used car
    Then I should be able to see the car details