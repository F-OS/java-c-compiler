package AST1stPass;

public class ASTBitwiseComplement extends ASTNode {
public final ASTNode left;

    public ASTBitwiseComplement(ASTNode left) {
        this.left = left;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
