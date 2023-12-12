package AST1stPass;

public class ASTUnaryPlus extends ASTNode {
public final ASTNode left;

    public ASTUnaryPlus(ASTNode left) {
        this.left = left;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
