package AST1stPass;

public class ASTGoto extends ASTNode {
public final String label;

    public ASTGoto(String label) {
        this.label = label;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
