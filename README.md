##TechSchool

TechSchool is a SpringBoot project using Java 8 and Spring libraries.

####Build And Deployment

Go to project location ./TechSchool and use the below commands. 

#####Build
```
gradlew clean build
```
#####Deploy
```
gradlew bootRun
```

####Curl Commands
```
curl http://localhost:7077/courses
curl --request PUT "http://localhost:7077/courses/100000?author=JosephCale01&publish=false"
curl --request PUT "http://localhost:7077/courses/1?author=JosephCale01&publish=true"
curl http://localhost:7077/users
curl --header "Content-Type: application/json" --request POST --data '{"loginName": "CalvinKlien1","firstName": "Calvin","lastName": "Klien","age": 44}' http://localhost:7077/users
```

####Entities:
* User
* Course
* Section
* Lesson
* Contents
* Authors

####Relationships

| Entity | Relationship | Entity | Mapping Table |
|--------|--------------|--------|---------------|
|User|	*->*	|Course|	Yes|
|Course|	1->*|	Section|	No|
|Section|	1->*|	Lesson|	No|
|Author|	*->*|	Course|	Yes|
|Course|	1->*|	Contents|	Yes|
|Section|	1->*|	Contents|	Yes|
|Lesson|	1->*|	Contents|	Yes|