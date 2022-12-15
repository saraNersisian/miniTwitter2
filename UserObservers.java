package twitterdriver;

//Observor interface for the oberver pattern in which users are the observers
public interface UserObservers {
    
    public void update(UserSubject subject, String message);
}
