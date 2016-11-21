retro-route-puzzle
==================

_A-Maze-ingly Retro Route Puzzle_

This is a JSON variant of [the problem described by this blog post](http://www.jonarcher.com/2010_01_01_archive.html).

Problem
-------

Write a program that will output a valid route one could follow to collect all specified items within a map.
The map is a json description of set of rooms with allowed path and contained object.

Exercise starts with an input of:
  - json reppresentation of map
  - starting room
  - list of object to collect
  
```
Room type allowed fields

  id: Integer
  name: String
  north: Integer //referring to a connected room
  south: Integer //referring to a connected room
  west: Integer //referring to a connected room
  east: Integer //referring to a connected room
  objects: List //of Objects
  
Object type allowed fields
  name: String //Object Name
```

Example
-------

Map
```json
{
  "rooms": [
    { "id": 1, "name": "Hallway", "north": 2, "objects": [] },
    { "id": 2, "name": "Dining Room", "south": 1, "west": 3, "east": 4, "objects": [] },
    { "id": 3, "name": "Kitchen","east":2, "objects": [ { "name": "Knife" } ] },
    { "id": 4, "name": "Sun Room","west":2, "objects": [ { "name": "Potted Plant" } ] }
  ]
}
```

Input
```
Start Room ID = 2
Objects To Collect = Knife, Potted Plant
```

Output

| ID | Room | Object collected|
|----|------|-----------------|
|0|Dining Room|None|
|1|Hallway|None|
|2|Dining Room|None|
|3|Kitchen|Knife|
|2|Dining Room|None|
|4|Sun Room|Potted Plant|

Additional Goals
----------------
  - [x] TDD approach.
  - [x] Build a Docker container with runnable code inside so that we can mount a volume in it and test on different maps.

Build instructions
------------------

To build a runnable jar:
```
mvn clean package
```

To build a runnable docker image:
```
mvn clean package docker:build
```

Usage
-----

Example:
```
java -jar target/retro-route-puzzle-<version>.jar -m map.json -r 2 -i "Knife" "Potted Plant"
```

Example using Docker:
```
docker run -v `pwd`:`pwd` -w `pwd` retro-route-puzzle -m map.json -r 2 -i "Knife" "Potted Plant"
```

Implementation notes
--------------------

The search algorithm is a greedy multi step graph visit, where each step uses Breadth First Search or Depth First Search.
In either case the complexity is _O(R)_ where _R_ is the number of rooms, but in general BFS should result in shorter routes.
