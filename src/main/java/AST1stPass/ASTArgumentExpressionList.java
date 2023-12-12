package AST1stPass;

import java.util.List;

public class ASTArgumentExpressionList extends ASTNode {
public final List<ASTNode> arguments;

    public ASTArgumentExpressionList(List<ASTNode> arguments) {
        this.arguments = arguments;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
