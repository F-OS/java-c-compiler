package AST1stPass;

public class ASTPrefixDecrement extends ASTNode {
public final ASTNode left;

    public ASTPrefixDecrement(ASTNode left) {
        this.left = left;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
