package AST1stPass;

public class ASTFor extends ASTNode {
public final ASTNode init;
public final ASTNode condition;
public final ASTNode increment;
public final ASTNode body;

    public ASTFor(ASTNode init, ASTNode condition, ASTNode increment, ASTNode body) {
        this.init = init;
        this.condition = condition;
        this.increment = increment;
        this.body = body;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
