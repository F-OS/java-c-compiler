package AST1stPass;

public class ASTDivision extends ASTNode {
public final ASTNode left;
public final ASTNode right;

    public ASTDivision(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
