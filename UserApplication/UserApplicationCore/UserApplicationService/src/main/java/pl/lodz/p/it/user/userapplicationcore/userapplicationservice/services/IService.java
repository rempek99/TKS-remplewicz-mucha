package pl.lodz.p.it.user.userapplicationcore.userapplicationservice.services;

import java.util.List;
import java.util.Optional;

public interface IService<E>  {
    E add(E instance);
    void update(E income, E outcome);
    void remove(E instance);
    E get(E instance);
    Optional<E> getViaUUID(String str);
    List<E> getAll();
}
