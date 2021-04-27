package pl.lodz.p.it.rentapplicationports.usecase.client;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.*;

public interface GetSingleBookSelectionUsecase {
    Client getSingleBookSelection(BookRental b);
}
