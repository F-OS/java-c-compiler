package AST1stPass;

import java.util.List;

public class ASTTranslationUnit extends ASTNode {
public final List<ASTNode> declarations;

    public ASTTranslationUnit(List<ASTNode> declarations) {
        this.declarations = declarations;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
