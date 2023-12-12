package AST1stPass;

public class ASTCharacterConstant extends ASTNode {
public final String value;

    public ASTCharacterConstant(String value) {
        this.value = value;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
