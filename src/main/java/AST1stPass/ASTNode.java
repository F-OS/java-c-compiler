package AST1stPass;

public abstract class ASTNode implements ASTNodeInterface
{
    public ASTNode()
    {
    }

    public abstract Object accept(ASTVisitor visitor);
}
