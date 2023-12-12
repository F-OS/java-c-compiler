package AST1stPass;

import visitor.CompilerType;

public class ASTCast extends ASTNode {
public final CompilerType type;
public final ASTNode left;

    public ASTCast(CompilerType type, ASTNode left) {
        this.type = type;
        this.left = left;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
