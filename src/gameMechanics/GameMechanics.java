package gameMechanics;

import messageSystem.Abonent;
import messageSystem.Address;
import messageSystem.MessageSystem;

/**
 * @author e.shubin
 */
public final class GameMechanics implements Abonent, Runnable {
    private final Address address = new Address();
    private final MessageSystem messageSystem;

    public static final int DEFAULT_SCORE = 50;
    private static final int MAX_SCORE = 100;
    private static final int MIN_SCORE = 0;

    public GameMechanics(MessageSystem messageSystem) {
        this.messageSystem = messageSystem;
        messageSystem.addService(this);
        messageSystem.getAddressService().registerGameMechanics(this);
    }

    public MessageSystem getMessageSystem() {
        return messageSystem;
    }

    public void increaseScore(UserScore score, int delta) {
        int newScore = score.getScore() + delta;

        if (newScore > MAX_SCORE) {
            newScore = MAX_SCORE;
        }

        if (newScore < MIN_SCORE) {
            newScore = MIN_SCORE;
        }

        score.setScore(newScore);
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public void run() {
        while (true) {
            messageSystem.execForAbonent(this);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
