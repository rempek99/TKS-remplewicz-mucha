package pl.lodz.p.it.topicmodels.events;

import pl.lodz.p.it.topicmodels.dtos.BookDTO;

public class BookEvent extends GeneralEvent {
    public static final String CREATE_BOOK = "CreateBook";
    public static final String REMOVE_BOOK = "RemoveBook";

    private BookDTO object;

    public BookEvent(String eventKey, BookDTO object) {
        super(eventKey);
        this.object = object;
    }

    public static BookEvent createBookEvent(BookDTO value) {
        return new BookEvent(CREATE_BOOK, value);
    }
}
