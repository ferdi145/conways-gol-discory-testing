class WorldClock(private val iterations: Int, private val iterationInMillis: Int) {
    fun isTimeLeft(): Boolean {
        return iterations > 0
    }

}
