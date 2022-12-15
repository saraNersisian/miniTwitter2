
public interface UserGroupVisitor {
	
    public void visitUser(UserLeaf user);
    
    public void visitGroup(GroupContainer group);
    
}
