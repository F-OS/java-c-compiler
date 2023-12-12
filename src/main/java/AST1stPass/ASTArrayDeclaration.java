package AST1stPass;

import visitor.CompilerType;

public class ASTArrayDeclaration extends ASTNode {
public final CompilerType type;
public final Integer size;
public final String name;

    public ASTArrayDeclaration(CompilerType type, Integer size, String name) {
        this.type = type;
        this.size = size;
        this.name = name;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
