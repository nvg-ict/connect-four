package connect.four

class FakeConsole(
    inputs: List<String> = emptyList()
) : Console {

    private val inputQueue = ArrayDeque(inputs)
    val output = mutableListOf<String>()

    override fun println(message: String) {
        output += message
    }

    override fun print(message: String) {
        output += message
    }

    override fun readLine(): String? =
        if (inputQueue.isEmpty()) null else inputQueue.removeFirst()
}
