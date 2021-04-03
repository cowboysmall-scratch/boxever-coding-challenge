
# Boxever Coding Challenge



## The Challenge


You run an airline that has planes that fly to different destinations around the world. Given a
task to fly between two airports you must ensure that each airplane takes the shortest time
to reach its destination.


Given that the following routes are available:


| Departure airport iata codes | Arrival Airport iata codes | Duration in Hours |
| ---------------------------- | -------------------------- | ----------------- |
| DUB                          | LHR                        | 1                 |
| DUB                          | CDG                        | 2                 |
| CDG                          | BOS                        | 6                 |
| CDG                          | BKK                        | 9                 |
| ORD                          | LAS                        | 2                 |
| LHR                          | NYC                        | 5                 |
| NYC                          | LAS                        | 3                 |
| BOS                          | LAX                        | 4                 |
| LHR                          | BKK                        | 9                 |
| BKK                          | SYD                        | 11                |
| LAX                          | LAS                        | 2                 |
| DUB                          | ORD                        | 6                 |
| LAX                          | SYD                        | 13                |
| LAS                          | SYD                        | 14                |




1. Write a program where two airports can be entered at the command line, eg. DUB - SYD  


2. The program returns the shortest route (duration) between the two airports in the following format:  


   DUB -- LHR ( 1 )  
   LHR -- BKK ( 9 )  
   BKK -- SYD ( 11 )  
   time: 21


When you are submitting your program please provide a brief description of the approach in a 
README.md file as part of a buildable project with source code, appropriate tests and a URL 
for where to find the repository.



## The Solution

This is a classic shortest path problem. The most well known algorithms associated with this 
problem are:


1. [Dijkstra](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm) (single-source, positive edge weights)
2. [Bellman-Ford](https://en.wikipedia.org/wiki/Bellman%E2%80%93Ford_algorithm) (single-source, positive and negative edge weights, no negative cycles)
3. [Floyd-Warshall](https://en.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm) (all-pairs, positive and negative edge weights, no negative cycles)


In the context of the problem - duration of travel between airports as the weights - 
Dijkstra's algorithm seemed like a good fit as it is pretty efficient, and it's only 
requirement is that there are no negative edge weights - negative travel durations do 
not make sense. 



## The Code

I adhered faithfully to the classic implementation of Dijkstra's Algorithm - the priority queue 
variation - pseudocode can be found at [here](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm#Using_a_priority_queue). 
I tried to make my implementation as generic as possible to facilitate flexibility - 
it should be relatively easy to use the graph implementation with any object that fulfills the 
basic equals / hash code contract, and also provides a sensible to string implementation.


I used a max value that was less than the integer max value to prevent overflow - which would 
/ could have resulted in negative calculations, and negative edge weights, rendering the algorithm 
broken.


You will need to clone the project:


```shell

> git clone git@github.com:cowboysmall-scratch/boxever-coding-challenge.git  

```


Then cd into the directory:


```shell

> cd boxever-coding-challenge

```


The project comes bundled with maven wrapper. To build the project execute the following:


```shell

> ./mvnw clean package

```


Once the project has completed building you can run it as follows:


```shell

> java -jar -Dspring.main.banner-mode=console target/boxever-coding-challenge-0.0.1-SNAPSHOT.jar --directed -s DUB -d SYD 

  ____                                  ____          _ _                ____ _           _ _
 | __ )  _____  _______   _____ _ __   / ___|___   __| (_)_ __   __ _   / ___| |__   __ _| | | ___ _ __   __ _  ___
 |  _ \ / _ \ \/ / _ \ \ / / _ \ '__| | |   / _ \ / _` | | '_ \ / _` | | |   | '_ \ / _` | | |/ _ \ '_ \ / _` |/ _ \
 | |_) | (_) >  <  __/\ V /  __/ |    | |__| (_) | (_| | | | | | (_| | | |___| | | | (_| | | |  __/ | | | (_| |  __/
 |____/ \___/_/\_\___| \_/ \___|_|     \____\___/ \__,_|_|_| |_|\__, |  \____|_| |_|\__,_|_|_|\___|_| |_|\__, |\___|
                                                                |___/                                    |___/


Built with Spring Boot ::  (v2.3.9.RELEASE)


route between DUB and SYD

DUB -- LHR (1)
LHR -- BKK (9)
BKK -- SYD (11)
time: 21



```


To get help with the options execute the following:

```shell

> java -jar -Dspring.main.banner-mode=console target/boxever-coding-challenge-0.0.1-SNAPSHOT.jar -h 

  ____                                  ____          _ _                ____ _           _ _
 | __ )  _____  _______   _____ _ __   / ___|___   __| (_)_ __   __ _   / ___| |__   __ _| | | ___ _ __   __ _  ___
 |  _ \ / _ \ \/ / _ \ \ / / _ \ '__| | |   / _ \ / _` | | '_ \ / _` | | |   | '_ \ / _` | | |/ _ \ '_ \ / _` |/ _ \
 | |_) | (_) >  <  __/\ V /  __/ |    | |__| (_) | (_| | | | | | (_| | | |___| | | | (_| | | |  __/ | | | (_| |  __/
 |____/ \___/_/\_\___| \_/ \___|_|     \____\___/ \__,_|_|_| |_|\__, |  \____|_| |_|\__,_|_|_|\___|_| |_|\__, |\___|
                                                                |___/                                    |___/


Built with Spring Boot ::  (v2.3.9.RELEASE)


usage: java -jar /path/to/boxever-coding-challenge.jar OPTIONS

 -d,--destination <arg>   the destination
    --directed            construct a directed graph
 -h,--help                help information
 -s,--source <arg>        the source



```
