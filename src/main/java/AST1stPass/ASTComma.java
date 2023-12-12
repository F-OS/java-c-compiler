package AST1stPass;

import java.util.List;

public class ASTComma extends ASTNode {
public final List<ASTNode> expressions;

    public ASTComma(List<ASTNode> expressions) {
        this.expressions = expressions;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
