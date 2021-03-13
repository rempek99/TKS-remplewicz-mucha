package controller;

import dtoadapters.*;
import dtomodel.*;

import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ListBooksController implements Serializable{
    private BookDTOAdapter bookDTOAdapter;
    private List<BookDTO> filteredBooks;

    public List<BookDTO> getFilteredBooks() {
        return filteredBooks;
    }

    public void setFilteredBooks(List<BookDTO> filteredBooks) {
        this.filteredBooks = filteredBooks;
    }

    public List<BookDTO> getBooks() {
        return bookDTOAdapter.getAllBooks();
    }

    public void removeSelectedBook(BookDTO b) {
        bookDTOAdapter.removeBook(b);
    }
}
