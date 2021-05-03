package pl.lodz.p.it.rentapplicationcore.rentdomainmodel.repositories;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Book;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.repositories.IRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class BookRepo implements IRepository<Book> {

    private List<Book> books = Collections.synchronizedList(new ArrayList<Book>());

    @PostConstruct
    private void insertInitData() {
        books.add(new Book("Doctor Sleep", "Stephen King", 656, false));
        books.add(new Book("Nineteen Eighty-Four", "George Orwell", 360, false));
        books.add(new Book("A Brief History of Time", "Stephen Hawking", 296, false));
        books.add(new Book("One Flew Over the Cuckoo’s Nest", "Ken Kesey", 368, false));
        books.add(new Book("The Lord of the Rings", "J.R.R. Tolkien", 1422, false));
        books.add(new Book("Bieguni", "Olga Tokarczuk", 456 , false));
        books.add(new Book("Animal Farm", "George Orwell", 176, false));
        books.add(new Book("Solaris", "Stanisław Lem", 340, false));
        books.add(new Book("Harry Potter and the Goblet of Fire", "J.K. Rowling", 765, false));
        books.add(new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 322, false));
    }

    public List<Book> getAll() {
        return Collections.unmodifiableList(books);
    }

    public Book add(Book b) {
        books.add(b);
        b.setId(UUID.randomUUID().toString());
        return b;
    }

    public void remove(Book b) {
        books.remove(b);
    }


    public Book getViaUUID(String str) {
        for(Book book: books) {
            if(book.getId().equals(str)){
                return book;
            }
        }
        return null;
    }

    public Book get(Book b) {
        if (books.contains(b)) {
            return b;
        } else {
            return null;
        }
    }

    public Book update(Book bookToChange, Book bookWithData) {
        Book fromRepo = get(bookToChange);
        fromRepo.setTitle(bookWithData.getTitle());
        fromRepo.setAuthor(bookWithData.getAuthor());
        fromRepo.setPages(bookWithData.getPages());
        fromRepo.setRented(bookWithData.isRented());
        return fromRepo;
    }
}
