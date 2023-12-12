package AST1stPass;

public class ASTStructAccess extends ASTNode {
public final ASTNode left;
public final String right;

    public ASTStructAccess(ASTNode left, String right) {
        this.left = left;
        this.right = right;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
