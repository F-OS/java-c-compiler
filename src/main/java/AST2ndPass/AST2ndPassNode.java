package AST2ndPass;

/*
 * The second pass AST differs from the first pass AST in the following ways:
 * 1. All augmented assignment operators are replaced with their non-augmented
 *   counterparts. I.E x += 1 becomes x = x + 1.
 * 2. All pre and post increment and decrement operators are replaced with their non-incremented counterparts.
 * I.E x++ is desugared by replacing it with x and adding x = x + 1 to the end of the statement.
 * 3. All array accesses are replaced with pointer arithmetic. I.E x[1] becomes *(x + 1 * sizeof(x[0])).
 * 4. All struct accesses are replaced with pointer arithmetic. I.E x.y becomes *(x + offsetof(x, y)).
 * 5. Conditional expressions are replaced with if statements. I.E k = x ? y : z becomes
 * if(x)
 *    k = y;
 * else
 *    k = z;
 * 6. While loops are replaced with goto loops. I.E while(x) y; becomes
 * while1:
 * if(x) {
 *  y;
 * goto while1;
 * }
 *
 * 7. Do while loops are replaced with goto loops. I.E do y; while(x); becomes
 * do1:
 * y;
 * if(x) goto do1;
 *
 * 8. For loops are replaced with goto loops. I.E for(x; y; z) w; becomes
 * x;
 * for1:
 * if(y) {
 * w;
 * z;
 * goto for1;
 * }
 * 9. Break and continue statements are replaced with goto statements.
 * 10. Switch statements are replaced with if statements. I.E switch(x) { case 1: y; break; case 2: z; default: w; break; } becomes
 * if(x == 1) {
 * goto case1;
 * } else if(x == 2) {
 * goto case2;
 * } else {
 * goto default;
 * }
 * case1:
 * y;
 * goto endswitch;
 * case2:
 * z;
 * default:
 * w;
 * endswitch:
 * 11. Initializer lists are replaced with assignments. I.E int x[2] = {1, 2}; becomes
 * int x[2];
 * x[0] = 1;
 * x[1] = 2;
 *
 */
public class AST2ndPassNode
{
    public AST2ndPassNode()
    {
    }

    public Object accept(AST2ndPassVisitor visitor)
    {
        return visitor.visit(this);
    }
}