package AST1stPass;

public class ASTPostfixIncrement extends ASTNode {
public final ASTNode left;

    public ASTPostfixIncrement(ASTNode left) {
        this.left = left;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
