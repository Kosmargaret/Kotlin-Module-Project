import java.util.Scanner

class NotesApp {
    private val archives: MutableList<Archive> = mutableListOf()
    private val menu = Menu()

    fun startApp() {
        showArchivesMenu()
    }

    private fun showArchivesMenu() {
        while (true) {
            println("Архивы:")
            archives.forEachIndexed { index, archive ->
                println("$index. ${archive.name}")
            }
            println("${archives.size}. Cоздать архив")
            println("${archives.size + 1}. Выход")

            val choice = menu.showMenu((0..archives.size + 1).toList().map { it.toString() })

            when {
                choice == archives.size -> createArchive()
                choice == archives.size + 1 -> return
                choice in archives.indices -> showNotesMenu(archives[choice])
                else -> println("Не верный выбор. Попробуйте еще раз")
            }
        }
    }

    private fun createArchive() {
        val scanner = Scanner(System.`in`)
        while(true){
            println("Введите имя архива:")
            val archiveName = scanner.nextLine()
            if(validString(archiveName)){
                archives.add(Archive(archiveName, mutableListOf()))
                break
            }

        }
    }

    private fun showNotesMenu(archive: Archive) {
        while (true) {
            println("Заметки в ${archive.name}:")
            archive.notes.forEachIndexed { index, note ->
                println("$index. ${note.name}")
            }
            println("${archive.notes.size}. Создать заметку")
            println("${archive.notes.size + 1}. Выход")

            val choice = menu.showMenu((0..archive.notes.size + 1).toList().map { it.toString() })

            when {
                choice == archive.notes.size -> createNote(archive)
                choice == archive.notes.size + 1 -> return
                choice in archive.notes.indices -> showNoteContent(archive.notes[choice])
                else -> println("Не верный выбор. Попробуйте еще раз")
            }
        }
    }

    private fun createNote(archive: Archive) {
        val scanner = Scanner(System.`in`)
        while(true){
            println("Введите имя заметки")
            val noteName = scanner.nextLine()
            if(validString(noteName)){
                while(true){
                    println("Введите текст заметки")
                    val noteText = scanner.nextLine()
                    if(validString(noteText)){
                        archive.notes.add(Note(noteName, noteText))
                        break
                    }
                }
                break
            }
        }

    }
    private fun validString(a: String): Boolean {
        if(a.trim().isEmpty()) {
            println("Пустая строка не допустима: ")
            return false
        }
        return true

    }

    private fun showNoteContent(note: Note) {
        println("Текст заметки: \n${note.noteText}")
    }
}