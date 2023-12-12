package AST1stPass;

public class ASTBitwiseAndAssignment extends ASTNode {
public final ASTNode left;
public final ASTNode right;

    public ASTBitwiseAndAssignment(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
