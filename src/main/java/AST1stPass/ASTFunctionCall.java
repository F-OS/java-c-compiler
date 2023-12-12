package AST1stPass;

import java.util.List;

public class ASTFunctionCall extends ASTNode {
public final ASTNode left;
public final List<ASTNode> arguments;

    public ASTFunctionCall(ASTNode left, List<ASTNode> arguments) {
        this.left = left;
        this.arguments = arguments;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
