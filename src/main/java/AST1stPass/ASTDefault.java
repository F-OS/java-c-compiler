package AST1stPass;

public class ASTDefault extends ASTNode {
public final ASTNode left;

    public ASTDefault(ASTNode left) {
        this.left = left;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
