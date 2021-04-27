package pl.lodz.p.it.userinterface.rest.account;

import pl.lodz.p.it.rentviewadapters.adapters.AccountServiceAdapter;
import pl.lodz.p.it.rentviewmodel.modelDTO.AccountDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@RequestScoped
@Path("accounts")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class AccountAPI {

    @Inject
    AccountServiceAdapter accountAdapter;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccFromStorage() {
        return Response
                .ok()
                .entity(accountAdapter.getAllAccounts())
                .status(Response.Status.OK)
                .build();
    }

    @GET
    @Path("{str}")
    public Response getSingleAccFromStorage(@PathParam("str") String str) {
        return Response
                .ok()
                .entity(accountAdapter.getAccountViaUUID(str))
                .status(Response.Status.OK)
                .build();
    }

    @POST
    public Response addSingleAccToStorage(@Valid AccountDTO account) {
        AccountDTO accountDTO = accountAdapter.addAccount(account);
        return Response
                .ok()
                .entity(accountDTO)
                .status(Response.Status.CREATED)
                .build();
    }

    @DELETE
    @Path("del/{str}")
    public Response removeSingleAccFromStorage(@PathParam("str") String str) {
        Optional<AccountDTO> acc = Optional.ofNullable(accountAdapter.getAccountViaUUID(str));
        if(acc.isPresent()){
            accountAdapter.removeAccount(acc.get());
            return Response
                    .ok()
                    .entity("Success")
                    .status(Response.Status.OK)
                    .build();
        }
        else{
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .entity("Wrong input data")
                    .build();
        }
    }

    @PUT
    @Path("update/{str}")
    public Response updateSingleAccount(@PathParam("str") String str, @Valid AccountDTO desiredAccount) {
        AccountDTO accountToChange = accountAdapter.getAccountViaUUID(str);
        // todo usprawnić logike update, tak aby pola null nie byly nullowane
        accountAdapter.updateSingleAccount(accountToChange, desiredAccount);
        return Response
                .ok()
                .entity("Account updated succesfully!")
                .status(Response.Status.OK)
                .build();
    }


}
