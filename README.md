# GameOfLife

Inspired by a challenge issued earlier in the week.

## Getting started

To get started clone this repo. The project is made to integrate with [IntelliJ idea](https://www.jetbrains.com/idea/).
There are several gradle integrated run configurations that will aid in a quick start (see `GameOfLife [run]`).
Running `gradlew.bat run`(windows) or `gradlew run`(linux)
will start the application via cli.

### Run Configurations

For now these are all set via inline code.

Further development is set up to enable features such as

- Setting the location of the viewport.
- Setting the scale of the viewport.
- Progression speed of the simulation age / tick-speed
- Pausing the simulation
- Loading start configurations (active/inactive status on the grid) from files

## Implementation

After an initial look over the linked
wikipage [Conways Game of Life.](http://en.wikipedia.org/wiki/Conway's_Game_of_Life)
An implementation seems to require logic to keep track of the entities, and a canvas to render a limited space out of
the total of the abstract 2d plane on which the game logic occurs across.

### Limitations on current implementation

The current implementation uses a `boolean[][]` type 2d array to store the abstract 2d plane.

The good: Primitive types when in arrays allow for very fast memory access. There is a great talk
here [Youtube - Scott Meyers Nokia talk](https://www.youtube.com/watch?v=WDIkqP4JbkE) that describes why a large data
set capable of being described by a and array of primitives should never be represented as an array of complex typed
objects. This could however likely be rewritten in some combination of logic to handle chunks
and arrays of 64bit integers for the best result.

The bad: This does however add a limitation where the size of the simulation space is neither infinite nor flexible.
Adding simulation space into rows lower than 0 or columns lower than requires a full copy of the array into a larger
array this is unideal.

### Ideas for further development

Move the implementation of the abstract grid containing an array of entities (see the class `EntityGrid`) into a spring
powered mvc app. This would allow multiple observers to simultaneously observe.

Change the method of simulation progression such that it relies on a queue of changes. This could also allow different
singular clients to stream/download and load the simulation asynchronously.

~~Extend the implementation of the rendering engine in java fx, to also cover a web app- perhaps angular with a 2d
rendering library.~~

## Dependencies

### Rendering Canvas / JavaFX

I have in previous projects had a fair bit of experience with both the swing and awt style interfaces. There is a nice
way to integrate with the draw lifecycle. I have not previously used javafx, regardless im sure it will work aptly to
render 2d sprites.

### Gradle

This is my first time implementing dependency management in java, i have used bundler with ruby, i have used npm and
yarn for angular, and hope gradle will do the same for java. Hopefully the implementation works out as it should
according to best practice. So far its seems to integrate nicely with the IDE and works well.

### Tests

Tests will be implemented in j-unit. Oneclick runners are implemented in the setup for the intellij idea ide. These
leverage the default task setup created with `gradle init`.