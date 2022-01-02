class GameOfLife(
    private val worldCreator: WorldCreator,
    private val worldClock: WorldClock,
    private val display: Display,
    private val worldReplacer: WorldReplacer
) {

    fun play() {
        var world = worldCreator.newWorld()

        while (worldClock.isTimeLeft()) {
           world = worldReplacer.replace(world)
        }

        display.show(world)
    }

}
