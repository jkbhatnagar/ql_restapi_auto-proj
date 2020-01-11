Feature: Weatherbit weather api for current weather

  Scenario: get current weather data using correct coordinates for Sydney
	Given I have set response type to json
	And I have APIKEY
	And I have latitude -33.87
	And I have longitude 151.21
	When I request Weatherbit weather api for current weather
	Then the status code is 200
	And response includes correct headers
	| Content-Type 	 		    | application/json; charset=utf-8 			    |
	And response includes the following integer values
	| count 	 		    | 1 			    |
    And response includes the following string values
	| data[0].timezone		| Australia/Sydney	|
    And response includes the following float values
	| data[0].lat 			| -33.87		    |
	| data[0].lon 			| 151.21		    |
    And response includes other mandatory parameters
	| data[0].wind_spd		|
	| data[0].clouds		|
	| data[0].temp		    |
	| data[0].uv		    |

  Scenario: get current weather data using correct coordinates for Los Angeles
	Given I have set response type to json
	And I have APIKEY
	And I have latitude 34.05
	And I have longitude -118.24
	When I request Weatherbit weather api for current weather
	Then the status code is 200
	And response includes correct headers
	| Content-Type 	 		    | application/json; charset=utf-8 			    |
	And response includes the following integer values
	| count 	 		    | 1 			        |
    And response includes the following string values
	| data[0].timezone	    | America/Los_Angeles   |
    And response includes the following float values
	| data[0].lat 			| 34.05 		        |
	| data[0].lon 			| -118.24  		        |
    And response includes other mandatory parameters
	| data[0].wind_spd		|
	| data[0].clouds		|
	| data[0].temp		    |
	| data[0].uv		    |

  Scenario: Get HTTP400 error when missing latitude
	Given I have set response type to json
	And I have APIKEY
	And I have longitude -118.24
	When I request Weatherbit weather api for current weather
	Then the status code is 400
	And response includes error "Invalid Parameters supplied."

  Scenario: Get HTTP400 error when missing longitude
	Given I have set response type to json
	And I have APIKEY
	And I have latitude 34.05
	When I request Weatherbit weather api for current weather
	Then the status code is 400
	And response includes error "Invalid Parameters supplied."

  Scenario: Get HTTP403 error when missing APIKEY
	Given I have set response type to json
	And I have latitude 34.05
	And I have longitude -118.24
	When I request Weatherbit weather api for current weather
	Then the status code is 403
	And response includes error "API key is required."

  Scenario: Get HTTP403 error when string latitude and string longitude
	Given I have set response type to json
	And I have APIKEY
	And I have latitude aaa
	And I have longitude bbb
	When I request Weatherbit weather api for current weather
	Then the status code is 400
	And response includes error "Invalid lat/lon supplied."

  Scenario: get current weather data using AU country and AU post code
	Given I have set response type to json
	And I have APIKEY
	And I have country code AU
	And I have post code 2000
	When I request Weatherbit weather api for current weather
	Then the status code is 200
	And response includes correct headers
	| Content-Type 	 		    | application/json; charset=utf-8 			    |
	And response includes the following integer values
	| count 	 		    | 1 			    |
    And response includes the following string values
	| data[0].timezone		| Australia/Sydney	|
    And response includes the following float values
	| data[0].lat 			| -33.8561		    |
	| data[0].lon 			| 151.2077		    |
    And response includes other mandatory parameters
	| data[0].wind_spd		|
	| data[0].clouds		|
	| data[0].temp		    |
	| data[0].uv		    |

  Scenario: get current weather data using US country and US post code
	Given I have set response type to json
	And I have APIKEY
	And I have country code US
	And I have post code 90210
	When I request Weatherbit weather api for current weather
	Then the status code is 200
	And response includes correct headers
	| Content-Type 	 		    | application/json; charset=utf-8 			    |
	And response includes the following integer values
	| count 	 		    | 1 			    |
    And response includes the following string values
	| data[0].timezone		| America/Los_Angeles	|
    And response includes the following float values
	| data[0].lat 			| 34.0901		    |
	| data[0].lon 			| -118.4065		    |
    And response includes other mandatory parameters
	| data[0].wind_spd		|
	| data[0].clouds		|
	| data[0].temp		    |
	| data[0].uv		    |

  Scenario: Get HTTP400 error when missing post code
	Given I have set response type to json
	And I have APIKEY
	And I have country code US
	When I request Weatherbit weather api for current weather
	Then the status code is 400
	And response includes error "Invalid Parameters supplied."

  Scenario: Get HTTP204 when invalid postcode
	Given I have set response type to json
	And I have APIKEY
	And I have country code AU
	And I have post code aaaaa
	When I request Weatherbit weather api for current weather
	Then the status code is 204

  Scenario: Get HTTP204 when invalid country code
	Given I have set response type to json
	And I have APIKEY
	And I have country code aaaaa
	And I have post code 2000
	When I request Weatherbit weather api for current weather
	Then the status code is 204
