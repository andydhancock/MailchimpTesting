Feature: User Registration on Mailchimp
  As a new user
  I want to register an account on Mailchimp

  Scenario Outline: User registration scenarios, Chrome
    Given I am using "Chrome" browser
    And I am on the Mailchimp registration page
    When I enter a username "<username>"
    And I enter an email "<email>"
    And I enter a password "<password>"
    And I click the "Sign up" button
    Then I should see a "<result_message>"

    Examples:
      | username                                                                                                                         | email                    | password | result_message                                                                    |
      | AUniqueUsername                                                                                                                  | validuser2@callycode.com | Test123! | Account created successfully                                                      |
      | LongUser123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890 | longuser@callycode.com   | Test123! | Enter a value less than 100 characters long                                       |
      | Andy                                                                                                                             | existing@callycode.com   | Test123! | Great minds think alike - someone already has this username. If it's you, log in. |
      | NoEmailUser                                                                                                                      |                          | Test123! | An email address must contain a single @.                                         |


  Scenario Outline: User registration scenarios, Firefox
    Given I am using "Firefox" browser
    And I am on the Mailchimp registration page
    When I enter a username "<username>"
    And I enter an email "<email>"
    And I enter a password "<password>"
    And I click the "Sign up" button
    Then I should see a "<result_message>"

    Examples:
      | username                                                                                                                         | email                    | password | result_message                                                                    |
      | AUniqueUsername                                                                                                                  | validuser2@callycode.com | Test123! | Account created successfully                                                      |
      | LongUser123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890 | longuser@callycode.com   | Test123! | Enter a value less than 100 characters long                                       |
      | Andy                                                                                                                             | existing@callycode.com   | Test123! | Great minds think alike - someone already has this username. If it's you, log in. |
      | NoEmailUser                                                                                                                      |                          | Test123! | An email address must contain a single @.                                         |
