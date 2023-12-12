package AST1stPass;

public class ASTPrefixIncrement extends ASTNode {
public final ASTNode left;

    public ASTPrefixIncrement(ASTNode left) {
        this.left = left;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
