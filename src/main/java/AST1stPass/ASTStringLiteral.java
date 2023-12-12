package AST1stPass;

public class ASTStringLiteral extends ASTNode {
public final String value;

    public ASTStringLiteral(String value) {
        this.value = value;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
