
package twitterdriver;
//Interface for composite pattern, accept visitor from visitor pattern
public interface CompositeTree {
    public String getID();
    
    public String toString();
    
    public void accept(UserGroupVisitor visitor);
}
