class GameTest extends GroovyTestCase {
    private Game game

    @Override
    protected void setUp() throws Exception {
        game = new Game()
    }

    void rollMany(int times, int pins) throws Exception {
        for (int i=0; i < times; ++i) {
            game.roll(pins)
        }
    }

    void rollSpare() {
        game.roll(5)
        game.roll(5)
    }

    void rollStrike() {
        game.roll(10)
    }

    void testGutterGame() throws Exception {
        rollMany(20, 0)
        assertEquals(0, game.score())
    }

    void testAllOne() throws Exception {
        rollMany(20, 1)
        assertEquals(20, game.score())
    }

    void testOneSpare() throws Exception {
        rollSpare()
        game.roll(3)
        rollMany(17, 0)
        assertEquals(16, game.score())
    }

    void testOneStrike() throws Exception {
        rollStrike()
        game.roll(3)
        game.roll(4)
        rollMany(17, 0)
        assertEquals(24, game.score())
    }

    void testPerfectGame() throws Exception {
        rollMany(11,10)
        assertEquals(300, game.score())
    }

    void testStrikeAndSpare() throws Exception {
        rollStrike()
        rollMany(6, 1)
        rollSpare()
        rollMany(8, 1)
        rollStrike()
        rollMany(3, 1)
        assertEquals(52, game.score())
    }
}
