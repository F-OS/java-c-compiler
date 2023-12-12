package AST1stPass;

public class ASTLabel extends ASTNode {
public final String label;
public final ASTNode left;

    public ASTLabel(String label, ASTNode left) {
        this.label = label;
        this.left = left;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
