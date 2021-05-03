package pl.p.lodz.it.RestTest.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Client;
import pl.lodz.p.it.rentviewadapters.converters.ClientConverter;
import pl.lodz.p.it.rentviewmodel.modelDTO.ClientDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientRestConverterTest {

    private final ClientDTO accountDTO = new ClientDTO("pl.lodz.p.it.viewports.pl.lodz.p.it.rentviewports.account", "Testowy", "user", true, "pl.lodz.p.it.viewports.pl.lodz.p.it.rentviewports.account", "account123");
    private final Client account = new Client("pl.lodz.p.it.viewports.pl.lodz.p.it.rentviewports.account", "Testowy", "user", true, "pl.lodz.p.it.viewports.pl.lodz.p.it.rentviewports.account", "account123");

    @BeforeEach
    void init() {
        accountDTO.setId("212c5de0-ad07-444a-9100-fd422b4bff93");
        account.setId("212c5de0-ad07-444a-9100-fd422b4bff93");
    }

    @Test
    void convertClientToEnt() {
        ClientDTO check = ClientConverter.convertClientToDTO(account);
        assertEquals(check, accountDTO);
    }

    @Test
    void convertEntToClient() {
        Client check = ClientConverter.convertDTOToClient(accountDTO);
        assertEquals(check, account);
    }
}