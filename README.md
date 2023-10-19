* How to compile and run:
1. Clone source code
2. Move to directory uwave-bus-station and run command
`mvnw spring-boot:run`. Note that program need internet connection to download maven libs. And need to install jdk, jre version 11 on your local machine before run command
3. The program will run at port 4040
4. Access to url http://localhost:4040/swagger-ui.html to see API definition
* About estimate time of incomming bus, I suppose we have speed(Km/h) of each bus is "bearing", so we calculate distance between requested bus stop and bus location, calculated result divide "bearing" to get arrival time(hour)
