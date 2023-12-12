package AST1stPass;

public class ASTLogicalNot extends ASTNode {
public final ASTNode left;

    public ASTLogicalNot(ASTNode left) {
        this.left = left;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
