package pl.lodz.p.it.rentrepositoriesadapters.aggregates.converters;

import pl.lodz.p.it.rentapplicationcore.rentdomainmodel.model.Client;
import pl.lodz.p.it.rentrepositoriesadapters.model_ent.entities.ClientEnt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientConverterTest {

    private final ClientEnt accountEnt = new ClientEnt("pl.lodz.p.it.viewports.pl.lodz.p.it.rentviewports.account", "Testowy", "user", true, "pl.lodz.p.it.viewports.pl.lodz.p.it.rentviewports.account", "account123");
    private final Client account = new Client("pl.lodz.p.it.viewports.pl.lodz.p.it.rentviewports.account", "Testowy", "user", true, "pl.lodz.p.it.viewports.pl.lodz.p.it.rentviewports.account", "account123");

    @BeforeEach
    void init() {
        accountEnt.setId("212c5de0-ad07-444a-9100-fd422b4bff93");
        account.setId("212c5de0-ad07-444a-9100-fd422b4bff93");
    }

    @Test
    void convertClientToEnt() {
        ClientEnt check = ClientConverter.convertClientToEnt(account);
        assertEquals(check, accountEnt);
    }

    @Test
    void convertEntToClient() {
        Client check = ClientConverter.convertEntToClient(accountEnt);
        assertEquals(check, account);
    }
}