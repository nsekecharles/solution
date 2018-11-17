Feature: analyse sales

  Scenario:
    Given a product LMUSB|20|Lance-missile USB
    And a product MKB|200|Clavier mécanique
    And a product T127|14.99|T-shirt 'no place like 127.0.0.1'
    And a sale Paris|Bob|LMUSB|1
    And a sale Lyon|Alice|MKB|1
    And a sale Lyon|Alice|T127|2
    And a sale Paris|Bob|T127|1
    And a sale Paris|Chuck|T127|1
    When analyse sells
    Then the analysis result should display
    |TOPSALE\|T127\|T-shirt 'no place like 127.0.0.1'\|4|
    |TOPSELLER\|Lyon\|Alice\|229.98|