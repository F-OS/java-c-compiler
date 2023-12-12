package AST1stPass;

public class ASTIntegerConstant extends ASTNode {
public final long value;

    public ASTIntegerConstant(long value) {
        this.value = value;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
