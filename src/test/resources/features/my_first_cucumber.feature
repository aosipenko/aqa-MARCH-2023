Feature: my first Cucumber Feature

#  Scenario: retrieve, store and google random person
#    Given Load google page
#    Given i generate 2 random person as "mob1"
#    And store persons data to DB from "mob1"
#    And i pick random user from DB as "person_1"
#    And i pick random user from DB as "person_2"
#    And i pick random user from DB as "person_3"
#    And Search for user "person_1"
#    Given Load google page
#    And Search for user "person_2"
#    Given Load google page
#    And Search for user "person_3"

  Scenario: retrieve, store and google random person using Spring
    Given i generate 2 random person as "mob1"
    And I store persons data using Spring to DB from "mob1"
    And i print all from db
