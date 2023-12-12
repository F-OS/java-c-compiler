package AST1stPass;

public class ASTLogicalAnd extends ASTNode {
public final ASTNode left;
public final ASTNode right;

    public ASTLogicalAnd(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
