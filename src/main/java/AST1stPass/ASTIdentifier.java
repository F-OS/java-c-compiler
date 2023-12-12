package AST1stPass;

public class ASTIdentifier extends ASTNode {
public final String name;

    public ASTIdentifier(String name) {
        this.name = name;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
