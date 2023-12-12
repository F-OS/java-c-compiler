package AST1stPass;

import java.util.List;
import visitor.CompilerType;

public class ASTFunctionPointerDeclaration extends ASTNode {
public final CompilerType type;
public final List<ASTNode> parameters;
public final String name;

    public ASTFunctionPointerDeclaration(CompilerType type, List<ASTNode> parameters, String name) {
        this.type = type;
        this.parameters = parameters;
        this.name = name;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
