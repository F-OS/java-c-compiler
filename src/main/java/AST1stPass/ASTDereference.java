package AST1stPass;

public class ASTDereference extends ASTNode {
public final ASTNode left;

    public ASTDereference(ASTNode left) {
        this.left = left;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
