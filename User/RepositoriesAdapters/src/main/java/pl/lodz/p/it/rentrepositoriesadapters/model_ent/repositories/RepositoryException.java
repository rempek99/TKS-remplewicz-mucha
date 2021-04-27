package pl.lodz.p.it.rentrepositoriesadapters.model_ent.repositories;

public class RepositoryException extends Throwable {
    static final public String DUPLICATED = "Duplicated";
    public RepositoryException(Throwable cause) {
        super(cause);
    }

    public RepositoryException(String message) {
        super(message);
    }
}
