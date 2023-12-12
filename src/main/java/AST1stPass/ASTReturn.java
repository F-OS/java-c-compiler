package AST1stPass;

public class ASTReturn extends ASTNode {
public final ASTNode left;

    public ASTReturn(ASTNode left) {
        this.left = left;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
