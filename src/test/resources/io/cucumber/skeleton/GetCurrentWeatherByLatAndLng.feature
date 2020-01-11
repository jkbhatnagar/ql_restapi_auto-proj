Feature: Weatherbit weather api for current weather

  Scenario: get current weather data using correct for Sydney
	Given I have correct latitude -33.87 and longitude 151.21
	When I request Weatherbit weather api for current weather
	Then the status code is 200
	And response includes the count
	| count 	 		        | 1 			    |
    And response includes the timezone
	| data[0].timezone			| Australia/Sydney	|
    And response includes the coordinates
	| data[0].lon 				| 151.21		    |
	| data[0].lat 				| -33.81		    |
    And response includes other mandatory parameters
	| data[0].wind_spd		|
	| data[0].clouds		|
	| data[0].temp		    |
	| data[0].uv		    |
