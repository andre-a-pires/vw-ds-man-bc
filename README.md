# VW DS MAN backend challenge
## Data Model
One can find the data model inside ``bll` package, each subpackage containing the entity being modelled.
* ``Code`` which wraps each parsed code value. ``SoftwareCode`` and ``HardwareCode`` extends its superclass for proper
 classification.
* ``Feature`` wraps the sets of codes for software and hardware that tells if that feature is compatible or not
* ``Vehicle` aggregates the vin code with the codes, establishing the relationship one is looking for when accessing the 
 endpoints.
## How to build the code and tests
Makefile is available with a help menu.
Two steps to take:
* ```$ make clean build``` removes existent jar files and build a new one with gradle wrapper.automatic tests run during
 the process.
* ```$ make docker-build-run``` build the app into an image, then uses docker compose to create its container and the 
database one (postgresql), and runs the whole container app.
if you are looking for daemon mode, run ```$ make docker-build && make docker-run```

Tests: 
* ```$ make test``` will run all automatic via gradle wrapper 

No docker
* if you´re not into running the app the containerized style and you´re providing a database, you can run the jar directly
 using ``$ make run``
 
## Comments
* Several ```TODOs``` and a few ```FIXMEs``` where left over the code, highlighting improvements or enhancements