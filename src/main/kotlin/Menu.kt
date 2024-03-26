import java.util.Scanner

public class Menu {
    fun showMenu(menuItems: List<String>): Int {
        val scanner = Scanner(System.`in`)
        val userInput: String = scanner.next()

        return try {
            userInput.toInt()
        } catch (e: NumberFormatException) {
            println("Пожалуйста, введите номер")
            showMenu(menuItems)
        }
    }
}
