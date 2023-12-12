package AST1stPass;

import java.util.List;
import visitor.CompilerType;

public class ASTDeclaration extends ASTNode {
public final CompilerType type;
public final List<ASTNode> declarations;

    public ASTDeclaration(CompilerType type, List<ASTNode> declarations) {
        this.type = type;
        this.declarations = declarations;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
