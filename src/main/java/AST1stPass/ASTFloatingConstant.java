package AST1stPass;

public class ASTFloatingConstant extends ASTNode {
public final double value;

    public ASTFloatingConstant(double value) {
        this.value = value;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
