package AST1stPass;

public class ASTSizeOf extends ASTNode {
public final ASTNode left;

    public ASTSizeOf(ASTNode left) {
        this.left = left;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
