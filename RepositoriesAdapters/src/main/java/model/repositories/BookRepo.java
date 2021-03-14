package model.repositories;

import model.entities.BookEnt;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class BookRepo {

    private List<BookEnt> books = Collections.synchronizedList(new ArrayList<BookEnt>());

    @PostConstruct
    private void insertInitData() {
        addBook(new BookEnt("Doctor Sleep", "Stephen King", 656, false));
        addBook(new BookEnt("Nineteen Eighty-Four", "George Orwell", 360, false));
        addBook(new BookEnt("A Brief History of Time", "Stephen Hawking", 296, false));
        addBook(new BookEnt("One Flew Over the Cuckoo’s Nest", "Ken Kesey", 368, false));
        addBook(new BookEnt("The Lord of the Rings", "J.R.R. Tolkien", 1422, false));
        addBook(new BookEnt("Bieguni", "Olga Tokarczuk", 456 , false));
        addBook(new BookEnt("Animal Farm", "George Orwell", 176, false));
        addBook(new BookEnt("Solaris", "Stanisław Lem", 340, false));
        addBook(new BookEnt("Harry Potter and the Goblet of Fire", "J.K. Rowling", 765, false));
        addBook(new BookEnt("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 322, false));
    }

    public List<BookEnt> getAllBooks() {
        return books;
    }

    public void addBook(BookEnt b) {
        b.setId(UUID.randomUUID().toString());
        books.add(b);
        printState();
    }

    public void removeBook(BookEnt b) {
        books.remove(b);
        printState();
    }


    public BookEnt getBookViaUUID(String str) {
        for(BookEnt book: books) {
            if(book.getId().equals(str)){
                return book;
            }
        }
        return null;
    }

    public BookEnt getBook(BookEnt b) {
        if (books.contains(b)) {
            return b;
        } else {
            return null;
        }
    }

    public void updateSingleBook(BookEnt bookToChange, BookEnt bookWithData) {
        BookEnt fromRepo = getBook(bookToChange);
        fromRepo.setTitle(bookWithData.getTitle());
        fromRepo.setAuthor(bookWithData.getAuthor());
        fromRepo.setPages(bookWithData.getPages());
        fromRepo.setRented(bookWithData.isRented());
        fromRepo.setRentalUserUUID(bookWithData.getRentalUserUUID());
        fromRepo.setRentalStart(bookWithData.getRentalStart());
        fromRepo.setRentalEnd(bookWithData.getRentalEnd());
    }

    private void printState() {
        System.out.println(Arrays.toString(books.toArray()));
    }
}
