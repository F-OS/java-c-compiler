package AST1stPass;

public interface ASTNodeInterface {
    public Object accept(ASTVisitor visitor);
}

