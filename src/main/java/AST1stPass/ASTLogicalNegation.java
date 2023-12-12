package AST1stPass;

public class ASTLogicalNegation extends ASTNode {
public final ASTNode left;

    public ASTLogicalNegation(ASTNode left) {
        this.left = left;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
