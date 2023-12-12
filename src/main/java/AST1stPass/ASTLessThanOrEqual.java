package AST1stPass;

public class ASTLessThanOrEqual extends ASTNode {
public final ASTNode left;
public final ASTNode right;

    public ASTLessThanOrEqual(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
