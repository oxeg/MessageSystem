package messageSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author e.shubin
 */
public final class MessageSystem {
    private Map<Address, ConcurrentLinkedQueue<Message>> messages = new HashMap<>();
    private AddressService addressService;

    public MessageSystem(AddressService addressService) {
        this.addressService = addressService;
    }

    public AddressService getAddressService() {
        return addressService;
    }

    public void addService(Abonent abonent) {
        messages.put(abonent.getAddress(), new ConcurrentLinkedQueue<>());
    }

    public void sendMessage(Message message) {
        messages.get(message.getTo()).add(message);
    }

    public void execForAbonent(Abonent abonent) {
        ConcurrentLinkedQueue<Message> queue = messages.get(abonent.getAddress());
        while (!queue.isEmpty()) {
            Message message = queue.poll();
            message.exec(abonent);
        }
    }
}
