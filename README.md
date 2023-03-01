[![CircleCI](https://dl.circleci.com/status-badge/img/gh/Sin2Cos2/spring6-restAPI-extremeSports/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/Sin2Cos2/spring5-restAPI-extremeSports/tree/master)

# Description
REST API for managing a service for a site that will help you find extreme adventure trips where you can spend holidays.
All available endpoints are listed below. If a particular endpoint can accept query params, they will be found below, along with the data type they accept. 
A query param is required if it ends with *.
A more extensive description of each endpoint is available at /swagger-ui/index.html

---

## Functionality

### GET request

- /api/v1/countries
    > page = number
    >
    > name = string
- /api/v1/countries/{countryId}                         

- /api/v1/regions
    > countryId = number
    >
    > page = number
    >
    > name = string
- /api/v1/regions/{regionId}                            

- /api/v1/locations
    > countryId = number
    >
    > regionId = number
    >
    > page = number
    >
    > name = string
- /api/v1/locations/{locationId}                        

- /api/v1/sports
    > page = number
    >
    > name = string
- /api/v1/sports/{sportId}

- /api/v1/trips
    > startDate = yyyy-mm-dd           
    >
    > endDate = yyyy-mm-dd            
    >
    > locationId = number
    >
    > sportId = number
    >
    > page = number

- /api/v1/trips/{tripId}


### DELETE request

- /api/v1/countries/{countryId}  

- /api/v1/regions
    > countryId = number                    
- /api/v1/regions/{regionId} 

- /api/v1/locations
    > countryId = number
    >
    > regionId = number
- /api/v1/locations/{locationId}

- /api/v1/sports/{sportId}   

- /api/v1/trips
    > startDate = yyyy-mm-dd           
    >
    > endDate = yyyy-mm-dd             
    >
    > locationId = number
    >
    > sportId = number                  
- /api/v1/trips/{tripId}                            

### POST request

- /api/v1/countries

- /api/v1/regions
    > countryId = number*

- /api/v1/locations
    > countryId = number*
    >
    > regionId = number*

- /api/v1/sports

- /api/v1/trips
    > sportId = number*
    >
    > locationId = number*

### PUT request

- /api/v1/countries/{countryId}

- /api/v1/regions/{regionId}

- /api/v1/locations/{locationId}

- /api/v1/sports/{sportId}

- /api/v1/trips/{tripId}

### PATCH request

- /api/v1/trips/{tripId}
