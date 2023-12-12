package AST1stPass;

import java.util.List;
import visitor.CompilerType;

public class ASTFunctionDefinition extends ASTNode {
public final CompilerType type;
public final String name;
public final List<ASTNode> parameters;
public final ASTNode body;

    public ASTFunctionDefinition(CompilerType type, String name, List<ASTNode> parameters, ASTNode body) {
        this.type = type;
        this.name = name;
        this.parameters = parameters;
        this.body = body;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
