package AST1stPass;

import java.util.List;

public class ASTParameterList extends ASTNode {
public final List<ASTNode> parameters;

    public ASTParameterList(List<ASTNode> parameters) {
        this.parameters = parameters;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
