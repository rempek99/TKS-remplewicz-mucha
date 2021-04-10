package pl.lodz.p.it.repositoriesadapters.aggregates.converters;

import pl.lodz.p.it.applicationcore.domainmodel.model.Book;
import pl.lodz.p.it.repositoriesadapters.model_ent.entities.BookEnt;

public class BookConverter {

    private BookConverter() {
    }

    public static BookEnt convertBookToEnt(Book book){
        String title = book.getTitle();
        String author = book.getAuthor();
        int pages = book.getPages();
        boolean rented = book.isRented();
        String id = book.getId();

        BookEnt newBook = new BookEnt(title, author, pages, rented);
        newBook.setId(id);

        return newBook;
    }

    public static Book convertEntToBook(BookEnt book){
        String title = book.getTitle();
        String author = book.getAuthor();
        int pages = book.getPages();
        boolean rented = book.isRented();
        String id = book.getId();

        Book newBook = new Book(title, author, pages, rented);
        newBook.setId(id);

        return newBook;
    }
}
