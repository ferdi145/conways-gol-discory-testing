import org.junit.jupiter.api.Test

class WorldClockTest {

    @Test
    fun `no time left when zero iterations given`() {
        val worldClock = WorldClock(0, 100)

        val timeLeft = worldClock.isTimeLeft()

        assert(timeLeft).equals(false)
    }

    @Test
    fun `time left when iteration not zero`() {
        val worldClock = WorldClock(1, 100)

        val timeLeft = worldClock.isTimeLeft()

        assert(timeLeft).equals(true)
    }
}
