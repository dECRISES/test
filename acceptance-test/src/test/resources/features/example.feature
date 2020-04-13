@Test
Feature: User would like to get tests

  Scenario: User should be able to get all tests
    Given the following tests exists in the library
      | description                 |
      | Twinkle twinkle little star |
    When user requests for all tests
    Then the user gets the following tests
      | description                 |
      | Twinkle twinkle little star |
