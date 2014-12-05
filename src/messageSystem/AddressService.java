package messageSystem;

import accountService.AccountService;
import frontEnd.FrontEnd;
import gameMechanics.GameMechanics;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author e.shubin
 */
public final class AddressService {
    private Address frontEnd;
    private Address gameMechanics;
    private Deque<Address> accountServiceList = new LinkedList<>();

    public void registerFrontEnd(FrontEnd frontEnd) {
            this.frontEnd = frontEnd.getAddress();
        }

    public void registerGameMechanics(GameMechanics gameMechanics) {
        this.gameMechanics = gameMechanics.getAddress();
    }

    public void registerAccountService(AccountService accountService) {
        accountServiceList.addLast(accountService.getAddress());
    }

    public Address getFrontEndAddress() {
        return frontEnd;
    }

    public Address getGameMechanicsAddress() {
        return gameMechanics;
    }

    public synchronized Address getAccountServiceAddress() {
        Address poll = accountServiceList.pollLast();
        accountServiceList.addFirst(poll);
        return poll;
    }
}
