package org.example.dam.virtual_library

fun main() {
    val library = Library("Central Library")
    val digitalBook = DigitalBook(
        "Kotlin in Action",
        "Dmitry Jemerov",
        2017,
        5,
        4.5,
        "PDF"
    )
    val physicalBook = PhysicalBook(
        "Clean Code",
        "Robert C. Martin",
        2008,
        3,
        650,
        true
    )
    val classicBook = PhysicalBook(
        "1984",
        "George Orwell",
        1949,
        2,
        400,
        false
    )
    library.addBook(digitalBook)
    library.addBook(physicalBook)
    library.addBook(classicBook)
    library.showBooks()
    println("\n--- Borrowing Books---")
    library.borrowBook("Clean Code")
    library.borrowBook("1984")
    library.borrowBook("1984")
    library.borrowBook("1984") // Should fail- no copies left
    println("\n--- Returning Books---")
    library.returnBook("1984")
    println("\n--- Search by Author---")
    library.searchByAuthor("George Orwell")

    println("\nTotal books added to all libraries: ${Library.totalBooksCreated}")



}


open class Book(
    val title: String,
    val author: String,
    val publicationYear: Int,
    avaliableCopies: Int
) {

    var avaliableCopies: Int = avaliableCopies
        set(value) {
            if (value < 0) {
                println("Available copies cannot be negative.")
            } else {
                field = value
            }
        }

    fun getPublicationYear(): String{
        if (publicationYear < 1980){
            return "Classic"
        } else if(publicationYear >= 1980 && publicationYear < 2010){
            return "Modern"
        } else{
            return "Contemporary"
        }
    }

    init {
        println("Book '$title' by $author has been added to the library.")
    }

    open fun getStorageInfo(): String {return ""}
}




class DigitalBook(title: String, author: String, publicationYear: Int, avaliableCopies: Int, val fileSize: Double, val format: String):
    Book(title, author, publicationYear, avaliableCopies) {

    override fun getStorageInfo(): String {
        return "Stored digitally: $fileSize MB, Format: $format"
    }

    override fun toString(): String {
        return "Title: $title, Author: $author, Era: ${getPublicationYear()}, Available: $avaliableCopies copies\n" +
                "Storage: ${getStorageInfo()}"
    }

}

class PhysicalBook(title: String, author: String, publicationYear: Int, avaliableCopies: Int, val weight: Int, val hasHardCover: Boolean = true):
    Book(title, author, publicationYear, avaliableCopies) {

    override fun getStorageInfo(): String {
        var hardCover = "No"
        when(hasHardCover){
            true -> hardCover = "Yes"
            else -> "No"
        }
        return "Physical book: $weight g, Hardcover: $hardCover"
    }

    override fun toString(): String {
        return "Title: $title, Author: $author, Era: ${getPublicationYear()}, Available: $avaliableCopies copies\n" +
                "Storage: ${getStorageInfo()}"
    }

}

class Library(name: String) {

    private val books = mutableListOf<Book>()

    companion object {
        var totalBooksCreated = 0
    }

    fun addBook(book: Book) {
        books.add(book)
        totalBooksCreated++
    }

    fun showBooks() {
        if (books.isEmpty()) {
            println("Library has no books.")
            return
        }

        println("---Library Catalog---")

        for (book in books) {
            println(book.toString())
        }
    }

    fun borrowBook(title: String) {
        val book = books.find { it.title.equals(title, false) }

        if (book != null) {
            if (book.avaliableCopies > 0) {
                book.avaliableCopies -= 1
                println("Successfully borrowed '${book.title}'. Copies remaining: ${book.avaliableCopies}")
            } else {
                println("Warning: Book is now out of stock!")
            }
        } else {
            println("Book not found.")
        }
    }

    fun returnBook(title: String) {
        val book = books.find { it.title.equals(title, ignoreCase = false) }

        if (book != null) {
            book.avaliableCopies += 1
            println("Book '${book.title}' returned successfully. Copies available: ${book.avaliableCopies}")
        } else {
            println("Book not found.")
        }
    }

    fun searchByAuthor(author: String) {
        val foundBooks = books.filter { it.author.equals(author, ignoreCase = false) }

        if (foundBooks.isEmpty()) {
            println("No books found by $author.")
        } else {
            println("Books by $author:")
            for (book in foundBooks) {
                var copy = "copy"
                when(book.avaliableCopies){
                    1 -> copy = "copy"
                    else -> copy = "copies"
                }
                println("- ${book.title} (${book.getPublicationYear()}, ${book.avaliableCopies} $copy available)")
            }
        }
    }



}

data class LibraryMember(
    val name: String,
    val membershipId: Int,
    val borrowedBooks: MutableList<String>
)
