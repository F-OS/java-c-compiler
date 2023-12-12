package AST1stPass;

public class ASTBitwiseOrAssignment extends ASTNode {
public final ASTNode left;
public final ASTNode right;

    public ASTBitwiseOrAssignment(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
