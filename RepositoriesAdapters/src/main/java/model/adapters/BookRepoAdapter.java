package model.adapters;

import model.entities.*;
import infrastructure.*;
import model.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static model.converters.BookConverter.convertBookToEnt;
import static model.converters.BookConverter.convertEntToBook;

public class BookRepoAdapter implements BookPort {
    @Inject
    private BookRepoEnt bookRepo;

    private List<BookEnt> books = bookRepo.getAllBooks();

    @Override
    public List<Book> getAllBooks() {
        List<Book> temp = Collections.synchronizedList(new ArrayList<Book>());
        for (BookEnt book: books) {
            temp.add(convertEntToBook(book));
        }
        return temp;
    }

    @Override
    public void addBook(Book b) {
        bookRepo.addBook(convertBookToEnt(b));
    }

    @Override
    public void removeBook(Book b) {
        bookRepo.removeBook(convertBookToEnt(b));
    }

    @Override
    public Book getBookViaUUID(String str) {
        for(BookEnt book: books) {
            if(book.getId().equals(str)){
                return convertEntToBook(book);
            }
        }
        return null;
    }

    @Override
    public Book getBook(Book b) {
        if (books.contains(b)) {
            return b;
        } else {
            return null;
        }
    }

    @Override
    public void updateSingleBook(Book bookToChange, Book bookWithData) {
        Book fromRepo = getBook(bookToChange);
        fromRepo.setTitle(bookWithData.getTitle());
        fromRepo.setAuthor(bookWithData.getAuthor());
        fromRepo.setPages(bookWithData.getPages());
        fromRepo.setRented(bookWithData.getRented());
        fromRepo.setRentalUserUUID(bookWithData.getRentalUserUUID());
        fromRepo.setRentalStart(bookWithData.getRentalStart());
        fromRepo.setRentalEnd(bookWithData.getRentalEnd());
    }
}
