all: build

build:
	mvn -T1.5C clean package -DskipTests

run: 
	java -cp target/vrp-1.0-SNAPSHOT.jar com.vrp.app.wrapper.VRP
	
generate:
	java -cp target/vrp-1.0-SNAPSHOT.jar com.vrp.app.wrapper.InstanceHandler

test: 
	mvn test

offline:
	mvn -T1.5C -o clean package 

clean: 
	mvn clean