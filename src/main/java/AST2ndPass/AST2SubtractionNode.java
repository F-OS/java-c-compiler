package AST2ndPass;

public class AST2SubtractionNode extends AST2ndPassNode
{
    public final AST2ndPassNode left;
    public final AST2ndPassNode right;

    public AST2SubtractionNode(AST2ndPassNode left, AST2ndPassNode right)
    {
        this.left = left;
        this.right = right;
    }

    public Object accept(AST2ndPassVisitor visitor)
    {
        return visitor.visit(this);
    }

    public String toString()
    {
        return left.toString() + " - " + right.toString();
    }
}