package AST1stPass;

public class ASTBreak extends ASTNode {

    public ASTBreak() {
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
