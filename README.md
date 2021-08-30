# Virtual Zoo

It implements a "Virtual Zoo" based on the tasks of the preliminary test.  
The following endpoints are exposed:

- myZoo/animals/all (task 3.1)
- myZoo/animals/species (extra task 3.1.1)
- myZoo/animals/{animalId}/doTrick (task 3.2)
- myZoo/animals/{animalId}/learnTrick (extra task 3.2.1)

### Build & Run

0. go to project folder
1. ./mvnw install
2. docker build --build-arg JAR_FILE=target/*.jar -t gt/zoo .
3. docker run -p 8080:8080 gt/zoo

### Notes

- At start-up the application creates and populates the database automatically.  
  The relative code is located in src/util/DatabaseLoader.java, in case more data is needed.
- The animal - species relation is modeled as a ManyToOne, while the relation animal - tricks as a ManyToMany leveraging
  an auxiliary table. A custom ResultTransformer converts the raw results to entities, without firing extra queries.