package pl.lodz.p.it.rentrepositoriesadapters.model_ent.repositories;

import pl.lodz.p.it.rentrepositoriesadapters.model_ent.entities.BookEnt;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class BookEntRepo implements IRepositoryEnt<BookEnt> {

    private final List<BookEnt> books = Collections.synchronizedList(new ArrayList<BookEnt>());

    @PostConstruct
    private void insertInitData() {
        try {
            add(new BookEnt("Doctor Sleep", "Stephen King", 656, false));
            add(new BookEnt("Nineteen Eighty-Four", "George Orwell", 360, false));
            add(new BookEnt("A Brief History of Time", "Stephen Hawking", 296, false));
            add(new BookEnt("One Flew Over the Cuckoo’s Nest", "Ken Kesey", 368, false));
            add(new BookEnt("The Lord of the Rings", "J.R.R. Tolkien", 1422, false));
            add(new BookEnt("Bieguni", "Olga Tokarczuk", 456, false));
            add(new BookEnt("Animal Farm", "George Orwell", 176, false));
            add(new BookEnt("Solaris", "Stanisław Lem", 340, false));
            add(new BookEnt("Harry Potter and the Goblet of Fire", "J.K. Rowling", 765, false));
            add(new BookEnt("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 322, false));
        } catch (RepositoryException e) {
            e.printStackTrace();
        }

    }

    public List<BookEnt> getAll() {
        return Collections.unmodifiableList(books);
    }

    public BookEnt add(BookEnt b) throws RepositoryException {
        if (books
                .stream()
                .anyMatch(
                        book -> book.getAuthor().equals(b.getAuthor())
                                &&
                                book.getTitle().equals(b.getTitle())
                ))
            throw new RepositoryException(RepositoryException.DUPLICATED);
        b.setId(UUID.randomUUID().toString());
        books.add(b);
        return b;

    }

    public void setBookRented(BookEnt b, boolean value) {
        for (BookEnt book : books) {
            if (book.getId().equals(b.getId())) {
                book.setRented(value);
            }
        }
    }

    public void remove(BookEnt b) {
        books.remove(b);
    }


    public BookEnt getViaUUID(String str) {
        for (BookEnt book : books) {
            if (book.getId().equals(str)) {
                return book;
            }
        }
        return null;
    }

    public BookEnt get(BookEnt b) {
        Optional<BookEnt> bookEnt = books.stream()
                .filter(x -> x.equals(b))
                .findFirst();
        return bookEnt.orElse(null);
    }

    public BookEnt update(BookEnt bookToChange, BookEnt bookWithData) {
        BookEnt fromRepo = get(bookToChange);
        fromRepo.setTitle(bookWithData.getTitle());
        fromRepo.setAuthor(bookWithData.getAuthor());
        fromRepo.setPages(bookWithData.getPages());
        fromRepo.setRented(bookWithData.isRented());
        return fromRepo;
    }
}
