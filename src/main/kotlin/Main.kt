import connect.four.App
import connect.four.SystemConsole

fun main() {
    val app = App(SystemConsole())

    with(app) {
        showStartup()
        waitForPlayerToStart()

        do {
            startGame()
            playCurrentGame()
        } while (askToPlayAgain())
    }
}
