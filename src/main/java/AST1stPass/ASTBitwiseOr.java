package AST1stPass;

public class ASTBitwiseOr extends ASTNode {
public final ASTNode left;
public final ASTNode right;

    public ASTBitwiseOr(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
