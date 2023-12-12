package visitor;

import AST1stPass.*;

import java.util.ArrayList;
import java.util.Objects;

// Evaluates a tree which consists only of primitive operations, constants, and constant conditionals with no side effects.
// Used for case blocks.
// I.E switch(x) { case 1 + 2: ... } will be evaluated to case 3: ...
// It is an error for floating point numbers, function calls, or variables to be present in the tree.
public class QuickEvaluator
{
    boolean complain = false;
    boolean madeChanges = true;
    ArrayList<EnumItem> enumItems;

    public QuickEvaluator(boolean complain, ArrayList<EnumItem> enumItems) {
        this.complain = complain;
        this.enumItems = enumItems;
    }

    public QuickEvaluator(ArrayList<EnumItem> enumItems) {
        this(true, enumItems);
    }

    public boolean madeChanges() {
        return madeChanges;
    }

    public static long quickEvaluate(ASTNode visit, ArrayList<EnumItem> enumItems) {
        QuickEvaluator eval = new QuickEvaluator(enumItems);
        return eval.visit(visit);
    }

    public static Long quickEvaluateQuiet(ASTNode visit, ArrayList<EnumItem> enumItems) {
        QuickEvaluator eval = new QuickEvaluator(false, enumItems);
        try {
            return eval.visit(visit);
        } catch (Exception e) {
            return Long.MIN_VALUE;
        }
    }

    public Long visit(ASTNode node)
    {
        if(complain) {
            throw new RuntimeException("Quick evaluator called on non-constant");
        } else {
            madeChanges = false;
            return Long.MIN_VALUE;
        }
    }
    public Long visit(ASTAddition astaddition) {
        return visit(astaddition.left) + visit(astaddition.right);
    }

    public Long visit(ASTSubtraction astsubtraction) {
        return visit(astsubtraction.left) - visit(astsubtraction.right);
    }

    public Long visit(ASTMultiplication astmultiplication) {
        return visit(astmultiplication.left) * visit(astmultiplication.right);
    }

    public Long visit(ASTDivision astdivision) {
        return visit(astdivision.left) / visit(astdivision.right);
    }

    public Long visit(ASTModulo astmodulo) {
        return visit(astmodulo.left) % visit(astmodulo.right);
    }

    public Long visit(ASTLeftShift astleftshift) {
        return visit(astleftshift.left) << visit(astleftshift.right);
    }

    public Long visit(ASTRightShift astrightshift) {
        return visit(astrightshift.left) >> visit(astrightshift.right);
    }

    public Long visit(ASTBitwiseAnd astbitwiseand) {
        return visit(astbitwiseand.left) & visit(astbitwiseand.right);
    }

    public Long visit(ASTBitwiseOr astbitwiseor) {
        return visit(astbitwiseor.left) | visit(astbitwiseor.right);
    }

    public Long visit(ASTBitwiseXor astbitwisexor) {
        return visit(astbitwisexor.left) ^ visit(astbitwisexor.right);
    }

    public Long visit(ASTLessThan astlessthan) {
        return (long) (visit(astlessthan.left) < visit(astlessthan.right) ? 1 : 0);
    }

    public Long visit(ASTLessThanOrEqual astlessthanorequal) {
        return (long) (visit(astlessthanorequal.left) <= visit(astlessthanorequal.right) ? 1 : 0);
    }

    public Long visit(ASTGreaterThan astgreaterthan) {
        return (long) (visit(astgreaterthan.left) > visit(astgreaterthan.right) ? 1 : 0);
    }

    public Long visit(ASTGreaterThanOrEqual astgreaterthanorequal) {
        return (long) (visit(astgreaterthanorequal.left) >= visit(astgreaterthanorequal.right) ? 1 : 0);
    }

    public Long visit(ASTEqual astequal) {
        return (long) (visit(astequal.left) == visit(astequal.right) ? 1 : 0);
    }

    public Long visit(ASTNotEqual astnotequal) {
        return (long) (visit(astnotequal.left) != visit(astnotequal.right) ? 1 : 0);
    }

    public Long visit(ASTLogicalAnd astlogicaland) {
        return (long) ((visit(astlogicaland.left) != 0 && visit(astlogicaland.right) != 0) ? 1 : 0);
    }

    public Long visit(ASTLogicalOr astlogicalor) {
        return (long) ((visit(astlogicalor.left) != 0 || visit(astlogicalor.right) != 0) ? 1 : 0);
    }

    public Long visit(ASTLogicalNot astlogicalnot) {
        return (long) (visit(astlogicalnot.left) == 0 ? 1 : 0);
    }

    public Long visit(ASTBitwiseComplement astbitwisecomplement) {
        return ~visit(astbitwisecomplement.left);
    }

    public Long visit(ASTUnaryPlus astunaryplus) {
        return visit(astunaryplus.left);
    }


    public Long visit(ASTUnaryMinus astunaryminus) {
        return -visit(astunaryminus.left);
    }

    public Long visit(ASTLogicalNegation astlogicalnegation) {
        return (long) (visit(astlogicalnegation.left) == 0 ? 1 : 0);
    }

    public Long visit(ASTCharacterConstant astcharacterconstant) {
        char c = switch(astcharacterconstant.value) {
            case "'\\n'" -> '\n';
            case "'\\t'" -> '\t';
            case "'\\r'" -> '\r';
            case "'\\b'" -> '\b';
            case "'\\f'" -> '\f';
            case "'\\\\'" -> '\\';
            case "'\\''" -> '\'';
            case "'\\\"'" -> '\"';
            case "'\\0'" -> '\0';
            case "\\0x" -> (char) Integer.parseInt(astcharacterconstant.value.substring(3), 16);
            default -> astcharacterconstant.value.charAt(1);
        };
        return (long) c;

    }

    public Long visit(ASTFloatingConstant astfloatingconstant) {
        throw new RuntimeException("Floating constant in quick evaluator");
    }

    public Long visit(ASTIntegerConstant astLongconstant) {
        return astLongconstant.value;
    }

    public Long visit(ASTComma astcomma) {
        return visit(astcomma.expressions.getLast());
    }

    public Long visit(ASTConditional astconditional) {
        if (visit(astconditional.condition) != 0) {
            return visit(astconditional.thenBranch);
        } else {
            return visit(astconditional.elseBranch);
        }
    }

    public Long visit(ASTIdentifier astidentifier) {
        for (EnumItem item : enumItems) {
            if (Objects.equals(item.name, astidentifier.name)) {
                return (long) item.value;
            }
        }
        if (complain) {
            throw new RuntimeException("Identifier in quick evaluator");
        } else {
            madeChanges = false;
            return Long.MIN_VALUE;
        }
    }
}
