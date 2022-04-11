# heyrenee-airport-service

This HTTP service provides two endpoints that provide information about airports. It can also be used to calculate distance between airports. The service details can be found below.

## Getting Started

### Requirements for this project to run properly:
- Java 11 or greater
- A recent version of Maven
- Access to the internet for downloading dependencies from the Maven repository
- Basic knowledge of a command line utility for running commands
- Understanding of the basics of making HTTP requests

### Build and run the service
- First, you'll need to clone the github repository 
- Once cloned, open a command line utility and navigate to the root of the directory where the project is installed
- Next, you'll build the service with Maven with the following command
>mvn package
- Then run it with the following command
>mvn spring-boot:run
- Lastly, you can stop the service at any time by pressing **CTRL+C**
- From here, you can may skip to the Usage section that details how the service works
- If you need to start over or remove the service, run the following from the command line
>mvn clean

## Airport Service details
This service can be used to obtain airport information as well as calculate the distance between two airports
- Do note that a basic knowledge of the IATA and ICAO airport codes is required to use this service

### Getting Airport Data - getAirportInfo
Airport information can be obtained from the getAirportInfo method
- This method can be called by making an HTTP GET request
- The service method expects an IATA or ICAO code as the last segment of the URL
> curl http:localhost:8081/airport/info/{IATACode/ICAOCode}

- In response, you will receive JSON data about the airport. Please see an example below:
```    
    {
        "id": 10,
        "name": "Kotoka International Airport",
        "city": "Accra",
        "country": "Ghana",
        "iataCode": "ACC",
        "icaoCode": "DGAA",
        "latitude": 5.605189800262451,
        "longitude": -0.16678600013256073,
        "altitude": 205,
        "timeZone": "Africa/Accra"
    }
```

### Calculating the Distance between Airports - calculateDistance
Airport information can be obtained from the getAirportInfo method
- This method can be called by making an HTTP GET request
- The service method expects an IATA or ICAO code as the last segment of the URL
> curl http:localhost:8081/airport/distance
- You will need to supply data to the endpoint as part of your request. Please see an example below:
```
{
    "originCode": "ACC",
    "destinationCode": "JFK",
    "units": "miles"
}
```

- In response, you will receive JSON data that includes the distance in the appropriate units. Please see an example below:
```    
{
    "originCode": "ACC",
    "destinationCode": "JFK",
    "distance": 5108.66
    "units": "miles",
}
```
