package AST1stPass;

public class ASTContinue extends ASTNode {

    public ASTContinue() {
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
