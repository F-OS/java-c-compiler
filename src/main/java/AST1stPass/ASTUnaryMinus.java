package AST1stPass;

public class ASTUnaryMinus extends ASTNode {
public final ASTNode left;

    public ASTUnaryMinus(ASTNode left) {
        this.left = left;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
