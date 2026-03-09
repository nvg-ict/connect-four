package connect.four

interface Console {
    fun println(message: String)
    fun print(message: String)
    fun readLine(): String?
}

class SystemConsole : Console {
    override fun println(message: String) = kotlin.io.println(message)
    override fun print(message: String) = kotlin.io.print(message)
    override fun readLine(): String? = readlnOrNull()
}
