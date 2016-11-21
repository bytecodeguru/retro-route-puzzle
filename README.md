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
  - [ ] TDD approach.
  - [ ] Build a Docker container with runnable code inside so that we can mount a volume in it and test on different maps.
