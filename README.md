# GameOfLife

Inspired by a challenge issued earlier in the week.

## Implementation

After an initial lookover the linked wikipage [Conways Game of Life](http://en.wikipedia.org/wiki/Conway's_Game_of_Life). An implementation
seems to require logic to keep track of the entities, and a canvas to render a limited space out of the total of the
abstract 2d plane on which the game logic occurs across.

### Ideas for further development

Move the implementation of the abstract grid containing an array of entities in series of classes into an spring powered mvc app.

Extend the implementation of the rendering engine in java fx, to also cover a web app- perhaps angular with a 2d rendering library.

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