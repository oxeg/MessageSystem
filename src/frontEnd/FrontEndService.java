package frontEnd;

/**
 * @author e.shubin
 */
public interface FrontEndService {
    void register(String name, String password);

    boolean isRegistered(String name);

    String authenticate(String name, String password);

    boolean isAuthenticated(String sessionId);

    int getScore(String sessionId);

    void updateScore(String sessionId, int delta);
}
