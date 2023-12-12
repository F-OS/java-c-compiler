package AST1stPass;

public class ASTAddressOf extends ASTNode {
public final ASTNode left;

    public ASTAddressOf(ASTNode left) {
        this.left = left;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
