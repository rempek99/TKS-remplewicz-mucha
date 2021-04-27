package main.java.pl.lodz.p.it.applicationports.usecase.book;

import pl.lodz.p.it.applicationcore.domainmodel.model.Book;

public interface UpdateSingleBookUsecase {
    void update(Book income, Book outcome);
}
