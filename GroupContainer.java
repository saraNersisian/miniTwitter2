
import java.util.ArrayList;
import java.util.List;

//composite pattern
public class GroupContainer implements CompositeTree{
    
    private List<CompositeTree> groupUsers= new ArrayList<>();
    private String groupID;
    private Long createdGroup;
    
    public GroupContainer(String newID) {
        this.groupID = newID;
        this.createdGroup=System.currentTimeMillis();
    }
    
    @Override
    public String getID() {
        return this.groupID;
    }
    public List<CompositeTree> getGroupUsers(){
        return groupUsers;
    }

    @Override
    public String toString() {
        return groupID;
    }

    public long getCreationTime(){
        return createdGroup;
    }
    public void setCreationTime(){
        createdGroup=System.currentTimeMillis();
    }
    
    @Override
    public void accept(UserGroupVisitor visitor) {
        visitor.visitGroup(this);
        for(CompositeTree members : groupUsers) {
            if (members instanceof UserLeaf) {
                members.accept(visitor);
            } else if (members instanceof GroupContainer) {
                members.accept(visitor);
            }
        }
    }
    public void addGroupUsers(CompositeTree newGroup){
        this.groupUsers.add(newGroup);
    }
    public Boolean containsUser(String UserID){
        for (CompositeTree members : groupUsers) {
            if (members instanceof UserLeaf) {
                if (members.getID().equals(UserID)) {
                    return true;
                }
            }
            else if (members instanceof GroupContainer) {
                if (((GroupContainer) members).containsUser(UserID)) {
                    return true;
                }
            }
        }
        return false;
    }
    public Boolean containsGroup(String memberID){
        for (CompositeTree members : groupUsers) {
            if (members instanceof UserLeaf) {
                continue;
            }
     
            else if (members instanceof GroupContainer) {
                if (members.getID().equals(memberID)){
                    return true;
                }
               
                else {
                    if(((GroupContainer) members).containsGroup(memberID)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public UserLeaf getUser(String userID){
        for (CompositeTree members : groupUsers) {
            if (members instanceof UserLeaf) {
                if (members.getID().equals(userID)){
                    return (UserLeaf) members;
                }
            }
            else if (members instanceof GroupContainer) {
          
                if (((GroupContainer) members).containsUser(userID)) {
                    return ((GroupContainer) members).getUser(userID);
                }
            }
        }
        return null;
    } 
}