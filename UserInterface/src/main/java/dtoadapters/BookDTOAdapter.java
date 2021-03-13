package dtoadapters;

import controller.*;
import dtoconverters.*;
import dtomodel.BookDTO;
import model.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static dtoconverters.BookDTOConverter.convertBookToDTO;
import static dtoconverters.BookDTOConverter.convertDTOToBook;

public class BookDTOAdapter {
    final BookService bookService;
    final BookDTOConverter BookDTOConverter;

    public BookDTOAdapter(BookService bookService, dtoconverters.BookDTOConverter bookDTOConverter) {
        this.bookService = bookService;
        BookDTOConverter = bookDTOConverter;
    }

    public List<BookDTO> getAllBooks() {
        List<BookDTO> temp = Collections.synchronizedList(new ArrayList<BookDTO>());
        List<Book> temp2 = bookService.getAllBooks();
        for (Book book: temp2) {
            temp.add(convertBookToDTO(book));
        }
        return temp;
    }

    public void addBook(BookDTO b) {
        bookService.addBook(convertDTOToBook(b));
    }

    public void removeBook(BookDTO b) {
        bookService.removeBook(convertDTOToBook(b));
    }

    public BookDTO getBookViaUUID(String str) {
        List<BookDTO> temp = getAllBooks();
        for(BookDTO book: temp) {
            if(book.getId().equals(str)){
                return book;
            }
        }
        return null;
    }

    public BookDTO getBook(BookDTO b) {
        List<BookDTO> temp = getAllBooks();
        if (temp.contains(b)) {
            return b;
        } else {
            return null;
        }
    }

    public void updateSingleBook(BookDTO bookToChange, BookDTO bookWithData) {
        BookDTO fromRepo = getBook(bookToChange);
        fromRepo.setTitle(bookWithData.getTitle());
        fromRepo.setAuthor(bookWithData.getAuthor());
        fromRepo.setPages(bookWithData.getPages());
        fromRepo.setRented(bookWithData.getRented());
        fromRepo.setRentalUserUUID(bookWithData.getRentalUserUUID());
        fromRepo.setRentalStart(bookWithData.getRentalStart());
        fromRepo.setRentalEnd(bookWithData.getRentalEnd());
    }
}
