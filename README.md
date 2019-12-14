# RentApp
## Build
```sh
javac -sourcepath src -classpath classes:lib/jade.jar  src/*.java -d classes
```
or
```sh
javac -classpath lib/jade.jar -d classes src/*.java
```
## Run
```sh
java -cp lib/jade.jar:classes jade.Boot -gui -agents hello1:rentapp.agents.HelloWorldAgent
```