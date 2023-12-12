package AST1stPass;

public class ASTExpressionStatement extends ASTNode {
public final ASTNode left;

    public ASTExpressionStatement(ASTNode left) {
        this.left = left;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
