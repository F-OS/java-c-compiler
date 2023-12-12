package AST1stPass;

public class ASTPostfixDecrement extends ASTNode {
public final ASTNode left;

    public ASTPostfixDecrement(ASTNode left) {
        this.left = left;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
