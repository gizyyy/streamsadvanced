# Kafka Streams with Joins
This is an application implemented to play with Kafka Streams Joins and KTable.

Problem:

A chocolate factory has 5 production lines. There is a heat measure and sugar density measure for each production line.

We need to track those measures because when heat is more than 25 C and density is more than 3, that chocolate is defective. We would like to track defective
chocolate serial numbers, in order to dispose them. Also we would like to track the health of production lines. Healts check criteria is 50 defect in 5 minutes.
Seperate listener counts defects and alert if necesarry.


Solution
![image](https://user-images.githubusercontent.com/45252874/184673419-843c355f-7557-4217-9dc4-88265e2acc88.png)



### Installing
1. Clone this repository anywhere on your machine:
```
git clone git@github.com:gizyyy/streamsadvanced.git
```

2. Run docker compose build
```
docker-compose up -d --build
```

## Installing dependencies
```bash
./gradlew build
```

## Tests and checks
To run all tests:
```bash
./gradlew test
```


