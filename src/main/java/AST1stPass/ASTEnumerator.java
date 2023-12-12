package AST1stPass;

public class ASTEnumerator extends ASTNode {
public final String name;
public final Integer value;

    public ASTEnumerator(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}
