package AST1stPass;

import java.util.List;

public class ASTInitializerList extends ASTNode {
public final List<ASTNode> initializers;

    public ASTInitializerList(List<ASTNode> initializers) {
        this.initializers = initializers;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
