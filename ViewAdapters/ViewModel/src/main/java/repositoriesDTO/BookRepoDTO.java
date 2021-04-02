package repositoriesDTO;

import modelDTO.BookDTO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class BookRepoDTO implements IRepository<BookDTO> {

    private List<BookDTO> books = Collections.synchronizedList(new ArrayList<BookDTO>());

    @PostConstruct
    private void insertInitData() {
        books.add(new BookDTO("Doctor Sleep", "Stephen King", 656, false));
        books.add(new BookDTO("Nineteen Eighty-Four", "George Orwell", 360, false));
        books.add(new BookDTO("A Brief History of Time", "Stephen Hawking", 296, false));
        books.add(new BookDTO("One Flew Over the Cuckoo’s Nest", "Ken Kesey", 368, false));
        books.add(new BookDTO("The Lord of the Rings", "J.R.R. Tolkien", 1422, false));
        books.add(new BookDTO("Bieguni", "Olga Tokarczuk", 456 , false));
        books.add(new BookDTO("Animal Farm", "George Orwell", 176, false));
        books.add(new BookDTO("Solaris", "Stanisław Lem", 340, false));
        books.add(new BookDTO("Harry Potter and the Goblet of Fire", "J.K. Rowling", 765, false));
        books.add(new BookDTO("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 322, false));
    }

    public List<BookDTO> getAll() {
        return Collections.unmodifiableList(books);
    }

    public BookDTO add(BookDTO b) {
        books.add(b);
        b.setId(UUID.randomUUID().toString());
        return b;
    }

    public void remove(BookDTO b) {
        books.remove(b);
    }


    public BookDTO getViaUUID(String str) {
        for(BookDTO book: books) {
            if(book.getId().equals(str)){
                return book;
            }
        }
        return null;
    }

    public BookDTO get(BookDTO b) {
        if (books.contains(b)) {
            return b;
        } else {
            return null;
        }
    }

    public BookDTO update(BookDTO bookToChange, BookDTO bookWithData) {
        BookDTO fromRepo = get(bookToChange);
        fromRepo.setTitle(bookWithData.getTitle());
        fromRepo.setAuthor(bookWithData.getAuthor());
        fromRepo.setPages(bookWithData.getPages());
        fromRepo.setRented(bookWithData.isRented());
        return fromRepo;
    }
}
