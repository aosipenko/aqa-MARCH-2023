Feature: my first Cucumber Feature

  Scenario: retrieve, store and google random person
    Given Load google page
    Given i generate 2 random person
    And store persons data to DB
    And i pick random user from DB
    Given Search for active user