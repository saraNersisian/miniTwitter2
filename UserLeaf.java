package twitterdriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//User is the leaf from the composite pattern
//Also simultaneously the subject and observer from the observer pattern
public class UserLeaf extends UserSubject implements CompositeTree, UserObservers{
   //declare variables for UserLeaf functions
   private String userID;
   private List<UserLeaf> following = new ArrayList<>();
   private ObservableList<UserLeaf> followingList = FXCollections.observableList(following);
   private List<String> myTweets = new ArrayList<>();
   private List<String> newsFeed = new ArrayList<>(Arrays.asList());
   //According to oracle docs a ObservableList is a list that allows listeners 
   //to track changes when they occur, aka update user view's newsfeed
   private ObservableList<String> newsFeedList = FXCollections.observableList(newsFeed);
   private long createdUser;
   private long lastUpdatedTime=0;
    
   //Set ID of User and created User
    public UserLeaf(String newID) {
        this.userID = newID;
        this.createdUser=System.currentTimeMillis();
    }
    
    @Override
    public String getID() {
        return userID;
    }
    
    @Override
    public String toString() {
        return userID;
    }
    
    //setter and getter method for creationtime for user
    public long getCreationTime(){
        return createdUser;
    }
    public long getLastUpdatedTime(){
        return lastUpdatedTime;
    }

    @Override
    public void accept(UserGroupVisitor visitor) {
        visitor.visitUser(this);
    }
    //override observer class update
    //add tweet from user aka subject
    @Override
    public void update(UserSubject subject, String tweet) {
        if (subject instanceof UserLeaf) {
            this.newsFeedList.add("-" + ((UserLeaf) subject).getID() + " : " + tweet);
            //Updated the last updated time to others' (usubject) newsfeed
            lastUpdatedTime=System.currentTimeMillis();
            this.newsFeedList.add("Last Updated: " + lastUpdatedTime);
        }
    }
    public ObservableList<UserLeaf> getFollowingList() {
        return followingList;
    }
    //if follow user, add to following list
    public void addFollowingList(UserLeaf user) {
        followingList.add(user);
    }

    public List<String> getMyTweets() {
        return myTweets;
    }

    public ObservableList<String> getNewsFeedList() {
        return newsFeedList;
    }
    
    public void tweetMessage (String tweet){
        myTweets.add(tweet);
        //add tweet to self's newsfeed
        newsFeedList.add("-" + this.userID + " : " + tweet);
        //update last updated time to own's newfeed in real time
        lastUpdatedTime=System.currentTimeMillis();
        //set(index, tweet string)
        this.newsFeedList.add("Last Updated: " + lastUpdatedTime);
        //Also add tweet to other user's (followers) newsfeed
        updateFollowers(tweet);
    }
}