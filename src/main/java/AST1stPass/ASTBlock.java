package AST1stPass;

import java.util.List;

public class ASTBlock extends ASTNode {
public final List<ASTNode> statements;

    public ASTBlock(List<ASTNode> statements) {
        this.statements = statements;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
