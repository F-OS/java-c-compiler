package AST1stPass;

import java.util.List;

public class ASTStructDefinition extends ASTNode {
public final String name;
public final List<ASTNode> declarations;

    public ASTStructDefinition(String name, List<ASTNode> declarations) {
        this.name = name;
        this.declarations = declarations;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
