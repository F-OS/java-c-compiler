package AST1stPass;

import visitor.CompilerType;

public class ASTVariableDeclaration extends ASTNode {
public final CompilerType type;
public final String name;
public final ASTNode initializer;

    public ASTVariableDeclaration(CompilerType type, String name, ASTNode initializer) {
        this.type = type;
        this.name = name;
        this.initializer = initializer;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
