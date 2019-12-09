# Test_task_openweathermap_to_mongodb

This is application, which send request to OpenWeatherMap API, recieve response with data about weather in JSON format and save it in MongoDB database

## Build

Clone project from Github repository https://github.com/pij1989/test_task_openweathermap_to_mongodb.
Go to the project directory:
```
cd .../test_task_openweathermap_to_mongodb
```
Perform command in command line:
```
mvn package
```

## Deploy

After building go to the target directory and launch the executable JAR file from the command line:
```
java -jar test-task-openweathermap-to-mongodb-1.0-SNAPSHOT.jar
```

## Configuration

Configuration files - application.properties and configuration.properties.
For example:
* application.properties:
```
logging.file=logs/mylog.log
spring.data.mongodb.uri=mongodb://localhost:27017/test
```
* configuration.properties:
```
api.request.url=http://api.openweathermap.org/data/2.5/weather?q=Minsk,by&units=metric&APPID={APIKEY}
api.key=d5304699f3bc41da25e68055e327278e
schedule.cron=0/5 * * * * *
```

