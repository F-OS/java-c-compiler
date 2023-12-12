package AST1stPass;

public class ASTIf extends ASTNode {
public final ASTNode condition;
public final ASTNode thenBranch;
public final ASTNode elseBranch;

    public ASTIf(ASTNode condition, ASTNode thenBranch, ASTNode elseBranch) {
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
