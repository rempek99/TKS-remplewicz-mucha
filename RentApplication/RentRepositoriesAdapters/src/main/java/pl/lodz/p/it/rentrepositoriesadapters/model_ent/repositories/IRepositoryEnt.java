package pl.lodz.p.it.rentrepositoriesadapters.model_ent.repositories;

import pl.lodz.p.it.rentrepositoriesadapters.model_ent.repositories.RepositoryException;

import java.util.List;

public interface IRepositoryEnt<E>  {
    E add(E instance) throws RepositoryException;
    E update(E income, E outcome);
    void remove(E instance);
    E get(E instance);
    E getViaUUID(String str);
    List<E> getAll();
}

