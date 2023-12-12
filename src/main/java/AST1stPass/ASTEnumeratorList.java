package AST1stPass;

import java.util.List;

public class ASTEnumeratorList extends ASTNode {
public final List<ASTNode> enumerators;

    public ASTEnumeratorList(List<ASTNode> enumerators) {
        this.enumerators = enumerators;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
