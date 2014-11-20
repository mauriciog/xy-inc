# xy-inc

A simple application to register 'point of interests', and retrieve then by distance filtering.

### Installation
* Uncompress the "zip" file with the project or checkout the code via git.

### Running
* ./activator run

### Testing
* ./activator test

### Using Services
* List all POIs : http://localhost:9000/poi
* Query POI by ID: http://localhost:9000/poi/:id
* Query POI neighbors: http://localhost:9000/poi/neighbors?x=:x&y=:y&distance=:distance

### Using UI
* http://localhost:9000
