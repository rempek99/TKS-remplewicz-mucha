package pl.lodz.p.it.applicationcore.domainmodel.repositories;

import java.util.List;

public interface IRepository<E>  {
    E add(E instance);
    E update(E income, E outcome);
    void remove(E instance);
    E get(E instance);
    E getViaUUID(String str);
    List<E> getAll();
}
