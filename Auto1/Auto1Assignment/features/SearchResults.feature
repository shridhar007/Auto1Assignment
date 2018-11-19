Feature: Auto1 search function

@Scenario001
Scenario: Search and verify results
Given user can navigate to www.autohero.com/de/search/
Then user applies first registration filter FROM 2015
Then user applies filter
Then user sorts cars by price decending
And all cars are filtered by registration from 2015 onwards
And all cars are sorted by price decending 	