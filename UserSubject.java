package twitterdriver;

import java.util.ArrayList;
import java.util.List;

//Observer pattern's subject are the user. The followers observe the newsfeed of
//those they are following then attach the observor (user) to following
public class UserSubject {
    private List<UserObservers> followers = new ArrayList<>();

    public void attach(UserObservers observer) {
        followers.add(observer);
    }
    //When something is tweeted, also update the followers each time
    public void updateFollowers(String message) {
        for(UserObservers observer : this.followers) {
            observer.update(this, message);
        }
    }

}