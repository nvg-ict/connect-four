import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

class TechDebtPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val debtFile = project.layout.projectDirectory.file("TECH_DEBT.md").asFile

        // Matches: // TODO(TECHDEBT): something
        val debtTodoRegex = Regex("""TODO\(TECHDEBT\):\s*(.+)""")

        fun codeDebtTodos(root: File): Set<String> {
            val ignoredDirs = setOf(".git", ".gradle", "build", "out", "buildSrc")
            val exts = setOf("kt", "kts", "java")

            return root.walkTopDown()
                .filter { it.isFile }
                .filter { it.extension in exts }
                .filterNot { f -> ignoredDirs.any { "/$it/" in f.invariantSeparatorsPath } }
                .flatMap { f ->
                    debtTodoRegex.findAll(f.readText())
                        .map { it.groupValues[1].trim() }
                        .asSequence()
                }
                .toSet()
        }

        project.tasks.register("techDebtTodoCheck") {
            group = "verification"
            description = "Fails if TODO(TECHDEBT) exists in code but is not listed in TECH_DEBT.md"

            doLast {
                val todos = codeDebtTodos(project.projectDir)
                if (todos.isEmpty()) {
                    println("✅ No TODO(TECHDEBT) markers found.")
                    return@doLast
                }

                if (!debtFile.exists()) {
                    error("Found TODO(TECHDEBT) in code but TECH_DEBT.md does not exist.")
                }

                val registerText = debtFile.readText()
                val missing = todos.filterNot { registerText.contains(it) }

                if (missing.isNotEmpty()) {
                    error(
                        buildString {
                            appendLine("These TODO(TECHDEBT) items are not listed in TECH_DEBT.md:")
                            missing.sorted().forEach { appendLine(" - $it") }
                            appendLine()
                            appendLine("Fix: add them to TECH_DEBT.md (copy/paste the text).")
                        }
                    )
                }

                println("✅ techDebtTodoCheck passed. Tracked items: ${todos.size}")
            }
        }
    }
}