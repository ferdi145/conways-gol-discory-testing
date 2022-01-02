import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameOfLifeTest {
    private val worldCreator: WorldCreator = mockk()
    private val worldClock: WorldClock = mockk()
    private val display: Display = mockk()
    private val worldReplacer: WorldReplacer = mockk()

    @BeforeEach
    fun setupMocks() {
        clearAllMocks()
        every { worldCreator.newWorld() } returns World()
        every { worldClock.isTimeLeft() } returns false
        every { display.show(any()) } just Runs
        every { worldReplacer.replace(any()) } returns World()
    }

    @Test
    fun `it calls worldCreator when playing`() {
        val gameOfLife = gameOfLife()

        gameOfLife.play()

        verify(exactly = 1) { worldCreator.newWorld() }
    }

    @Test
    fun `it calls worldClock once when there is no time left`() {
        val gameOfLife = gameOfLife()

        gameOfLife.play()

        verify(exactly = 1) { worldClock.isTimeLeft() }
    }

    @Test
    fun `it calls worldClock once more when there is time left`() {
        every { worldClock.isTimeLeft() } returns true andThen false
        val gameOfLife = gameOfLife()

        gameOfLife.play()

        verify(exactly = 2) { worldClock.isTimeLeft() }
    }

    @Test
    fun `it prints initial world when there is no iteration`() {
        val initialWorld = World()
        every { worldCreator.newWorld() } returns initialWorld
        val gameOfLife = gameOfLife()

        gameOfLife.play()

        verify(exactly = 1) { display.show(initialWorld) }
    }

    @Test
    fun `it prints replaced world when there is at least one iteration`() {
        every { worldCreator.newWorld() } returns World()
        every { worldClock.isTimeLeft() } returns true andThen false
        val replacedWorld = World()
        every { worldReplacer.replace(any()) } returns replacedWorld
        val gameOfLife = gameOfLife()

        gameOfLife.play()

        verify(exactly = 1) { display.show(replacedWorld) }
    }

    @Test
    fun `it calls replacer when there is time left`() {
        every { worldClock.isTimeLeft() } returns true andThen false
        val gameOfLife = gameOfLife()

        gameOfLife.play()

        verify(exactly = 1) { worldReplacer.replace(any()) }
    }

    private fun gameOfLife() = GameOfLife(worldCreator, worldClock, display, worldReplacer)
}
