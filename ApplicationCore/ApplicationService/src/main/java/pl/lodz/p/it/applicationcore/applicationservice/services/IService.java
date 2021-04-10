package pl.lodz.p.it.applicationcore.applicationservice.services;

import java.util.List;

public interface IService<E>  {
    void add(E instance);
    void update(E income, E outcome);
    void remove(E instance);
    E get(E instance);
    E getViaUUID(String str);
    List<E> getAll();
}
