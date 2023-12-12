package AST1stPass;

public class ASTModulo extends ASTNode {
public final ASTNode left;
public final ASTNode right;

    public ASTModulo(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
