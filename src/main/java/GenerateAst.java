import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class GenerateAst {
    public static void main(String[] args) throws IOException {
        ArrayList<String> types = new ArrayList<String>();
        if (args.length != 1) {
            System.err.println("Usage: generate_ast <output directory>");
            System.exit(64);
        }
        String outputDir = args[0];
        types.add(define(outputDir, "ASTAddition", Arrays.asList("ASTNode left", "ASTNode right"), "\"(\" + left + \" + \" + right + \")\""));
        types.add(define(outputDir, "ASTSubtraction", Arrays.asList("ASTNode left", "ASTNode right"), "\"(\" + left + \" - \" + right + \")\""));
        types.add(define(outputDir, "ASTMultiplication", Arrays.asList("ASTNode left", "ASTNode right"), "\"(\" + left + \" * \" + right + \")\""));
        types.add(define(outputDir, "ASTDivision", Arrays.asList("ASTNode left", "ASTNode right"), "\"(\" + left + \" / \" + right + \")\""));
        types.add(define(outputDir, "ASTModulo", Arrays.asList("ASTNode left", "ASTNode right"), "\"(\" + left + \" % \" + right + \")\""));
        types.add(define(outputDir, "ASTLeftShift", Arrays.asList("ASTNode left", "ASTNode right"), "\"(\" + left + \" << \" + right + \")\""));
        types.add(define(outputDir, "ASTRightShift", Arrays.asList("ASTNode left", "ASTNode right"), "\"(\" + left + \" >> \" + right + \")\""));
        types.add(define(outputDir, "ASTBitwiseAnd", Arrays.asList("ASTNode left", "ASTNode right"), "\"(\" + left + \" & \" + right + \")\""));
        types.add(define(outputDir, "ASTBitwiseOr", Arrays.asList("ASTNode left", "ASTNode right"), "\"(\" + left + \" | \" + right + \")\""));
        types.add(define(outputDir, "ASTBitwiseXor", Arrays.asList("ASTNode left", "ASTNode right"), "\"(\" + left + \" ^ \" + right + \")\""));
        types.add(define(outputDir, "ASTLessThan", Arrays.asList("ASTNode left", "ASTNode right"), "\"(\" + left + \" < \" + right + \")\""));
        types.add(define(outputDir, "ASTLessThanOrEqual", Arrays.asList("ASTNode left", "ASTNode right"), "\"(\" + left + \" <= \" + right + \")\""));
        types.add(define(outputDir, "ASTGreaterThan", Arrays.asList("ASTNode left", "ASTNode right"), "\"(\" + left + \" > \" + right + \")\""));
        types.add(define(outputDir, "ASTGreaterThanOrEqual", Arrays.asList("ASTNode left", "ASTNode right"), "\"(\" + left + \" >= \" + right + \")\""));
        types.add(define(outputDir, "ASTEqual", Arrays.asList("ASTNode left", "ASTNode right"), "\"(\" + left + \" == \" + right + \")\""));
        types.add(define(outputDir, "ASTNotEqual", Arrays.asList("ASTNode left", "ASTNode right"), "\"(\" + left + \" != \" + right + \")\""));
        types.add(define(outputDir, "ASTAssignment", Arrays.asList("ASTNode left", "ASTNode right"), "\"(\" + left + \" = \" + right + \")\""));
        types.add(define(outputDir, "ASTMultiplicationAssignment", Arrays.asList("ASTNode left", "ASTNode right"), "\"(\" + left + \" *= \" + right + \")\""));
        types.add(define(outputDir, "ASTDivisionAssignment", Arrays.asList("ASTNode left", "ASTNode right"), "\"(\" + left + \" /= \" + right + \")\""));
        types.add(define(outputDir, "ASTModuloAssignment", Arrays.asList("ASTNode left", "ASTNode right"), "\"(\" + left + \" %= \" + right + \")\""));
        types.add(define(outputDir, "ASTAdditionAssignment", Arrays.asList("ASTNode left", "ASTNode right"), "\"(\" + left + \" += \" + right + \")\""));
        types.add(define(outputDir, "ASTSubtractionAssignment", Arrays.asList("ASTNode left", "ASTNode right"), "\"(\" + left + \" -= \" + right + \")\""));
        types.add(define(outputDir, "ASTLeftShiftAssignment", Arrays.asList("ASTNode left", "ASTNode right"), "\"(\" + left + \" <<= \" + right + \")\""));
        types.add(define(outputDir, "ASTRightShiftAssignment", Arrays.asList("ASTNode left", "ASTNode right"), "\"(\" + left + \" >>= \" + right + \")\""));
        types.add(define(outputDir, "ASTBitwiseAndAssignment", Arrays.asList("ASTNode left", "ASTNode right"), "\"(\" + left + \" &= \" + right + \")\""));
        types.add(define(outputDir, "ASTBitwiseOrAssignment", Arrays.asList("ASTNode left", "ASTNode right"), "\"(\" + left + \" |= \" + right + \")\""));
        types.add(define(outputDir, "ASTBitwiseXorAssignment", Arrays.asList("ASTNode left", "ASTNode right"), "\"(\" + left + \" ^= \" + right + \")\""));
        types.add(define(outputDir, "ASTLogicalAnd", Arrays.asList("ASTNode left", "ASTNode right"), "\"(\" + left + \" && \" + right + \")\""));
        types.add(define(outputDir, "ASTLogicalOr", Arrays.asList("ASTNode left", "ASTNode right"), "\"(\" + left + \" || \" + right + \")\""));
        types.add(define(outputDir, "ASTLogicalNot", Arrays.asList("ASTNode left"), "\"(!\" + left + \")\""));
        types.add(define(outputDir, "ASTBitwiseComplement", Arrays.asList("ASTNode left"), "\"(~\" + left + \")\""));
        types.add(define(outputDir, "ASTPrefixIncrement", Arrays.asList("ASTNode left"), "\"(++\" + left + \")\""));
        types.add(define(outputDir, "ASTPrefixDecrement", Arrays.asList("ASTNode left"), "\"(--\" + left + \")\""));
        types.add(define(outputDir, "ASTAddressOf", Arrays.asList("ASTNode left"), "\"(&\" + left + \")\""));
        types.add(define(outputDir, "ASTDereference", Arrays.asList("ASTNode left"), "\"(*\" + left + \")\""));
        types.add(define(outputDir, "ASTPostfixIncrement", Arrays.asList("ASTNode left"), "\"(\" + left + \"++)\""));
        types.add(define(outputDir, "ASTPostfixDecrement", Arrays.asList("ASTNode left"), "\"(\" + left + \"--)\""));
        types.add(define(outputDir, "ASTUnaryPlus", Arrays.asList("ASTNode left"), "\"(+\" + left + \")\""));
        types.add(define(outputDir, "ASTUnaryMinus", Arrays.asList("ASTNode left"), "\"(-\" + left + \")\""));
        types.add(define(outputDir, "ASTLogicalNegation", Arrays.asList("ASTNode left"), "\"(!\" + left + \")\""));
        types.add(define(outputDir, "ASTSizeOf", Arrays.asList("ASTNode left"), "\"(sizeof \" + left + \")\""));
        types.add(define(outputDir, "ASTArgumentExpressionList", Arrays.asList("List<ASTNode> arguments"), "arguments.toString()"));
        types.add(define(outputDir, "ASTArrayAccess", Arrays.asList("ASTNode left", "ASTNode right"), "\"(\" + left + \"[\" + right + \"]\""));
        types.add(define(outputDir, "ASTWhile", Arrays.asList("ASTNode left", "ASTNode right"), "\"while(\" + left + \")\" + right"));
        types.add(define(outputDir, "ASTBlock", Arrays.asList("List<ASTNode> statements"), "statements.toString()"));
        types.add(define(outputDir, "ASTBreak", Arrays.asList(), "\"break\""));
        types.add(define(outputDir, "ASTContinue", Arrays.asList(), "\"continue\""));
        types.add(define(outputDir, "ASTDoWhile", Arrays.asList("ASTNode left", "ASTNode right"), "\"do \" + left + \" while(\" + right + \");\""));
        types.add(define(outputDir, "ASTExpressionStatement", Arrays.asList("ASTNode left"), "left.toString() + \";\""));
        types.add(define(outputDir, "ASTCharacterConstant", Arrays.asList("String value"), "value"));
        types.add(define(outputDir, "ASTFloatingConstant", Arrays.asList("double value"), "Double.toString(value)"));
        types.add(define(outputDir, "ASTIntegerConstant", Arrays.asList("long value"), "Long.toString(value)"));
        types.add(define(outputDir, "ASTStringLiteral", Arrays.asList("String value"), "value"));
        types.add(define(outputDir, "ASTComma", Arrays.asList("List<ASTNode> expressions"), "expressions.toString()"));
        types.add(define(outputDir, "ASTDefault", Arrays.asList("ASTNode left"), "\"default: \" + left"));
        types.add(define(outputDir, "ASTGoto", Arrays.asList("String label"), "\"goto \" + label + \";\""));
        types.add(define(outputDir, "ASTLabel", Arrays.asList("String label", "ASTNode left"), "label + \": \" + left"));
        types.add(define(outputDir, "ASTReturn", Arrays.asList("ASTNode left"), "\"return \" + (left != null ? left : \"\") + \";\""));
        types.add(define(outputDir, "ASTVariableDeclaration", Arrays.asList("CompilerType type", "String name", "ASTNode initializer"), "type + \" \" + name + (initializer != null ? \" = \" + initializer : \"\") + \";\""));
        types.add(define(outputDir, "ASTUnionDefinition", Arrays.asList("String name", "List<ASTNode> declarations"), "\"union \" + name + \" { \" + declarations.toString() + \" };\""));
        types.add(define(outputDir, "ASTStructDefinition", Arrays.asList("String name", "List<ASTNode> declarations"), "\"struct \" + name + \" { \" + declarations.toString() + \" };\""));
        types.add(define(outputDir, "ASTEnumDefinition", Arrays.asList("String name", "List<ASTNode> declarations"), "\"enum \" + name + \" { \" + declarations.toString() + \" };\""));
        types.add(define(outputDir, "ASTConditional", Arrays.asList("ASTNode condition", "ASTNode thenBranch", "ASTNode elseBranch"), "\"(\" + condition + \") ? \" + thenBranch + \" : \" + elseBranch"));
        types.add(define(outputDir, "ASTFunctionCall", Arrays.asList("ASTNode left", "List<ASTNode> arguments"), "left + \"(\" + arguments.toString() + \")\""));
        types.add(define(outputDir, "ASTIf", Arrays.asList("ASTNode condition", "ASTNode thenBranch", "ASTNode elseBranch"), "\"if(\" + condition + \")\" + thenBranch + (elseBranch != null ? \" else \" + elseBranch : \"\")"));
        types.add(define(outputDir, "ASTFor", Arrays.asList("ASTNode init", "ASTNode condition", "ASTNode increment", "ASTNode body"), "\"for(\" + init + \"; \" + condition + \"; \" + increment + \")\" + body"));
        types.add(define(outputDir, "ASTArrayDeclaration", Arrays.asList("CompilerType type", "Integer size", "String name"), "type + \" \" + name + \"[\" + size + \"]\""));
        types.add(define(outputDir, "ASTTranslationUnit", Arrays.asList("List<ASTNode> declarations"), "declarations.toString()"));
        types.add(define(outputDir, "ASTSwitch", Arrays.asList("ASTNode left", "ASTNode right"), "\"switch(\" + left + \")\" + right"));
        types.add(define(outputDir, "ASTCase", Arrays.asList("ASTNode left", "ASTNode right"), "\"case \" + left + \": \" + right"));
        types.add(define(outputDir, "ASTDeclaration", Arrays.asList("CompilerType type", "List<ASTNode> declarations"), "type + \" \" + declarations.toString()"));
        types.add(define(outputDir, "ASTCast", Arrays.asList("CompilerType type", "ASTNode left"), "\"(\" + type + \")\" + left"));
        types.add(define(outputDir, "ASTStructPointerAccess", Arrays.asList("ASTNode left", "String right"), "\"(\" + left + \"->\" + right + \")\""));
        types.add(define(outputDir, "ASTStructAccess", Arrays.asList("ASTNode left", "String right"), "\"(\" + left + \".\" + right + \")\""));
        types.add(define(outputDir, "ASTParameterList", Arrays.asList("List<ASTNode> parameters"), "parameters.toString()"));
        types.add(define(outputDir, "ASTInitializerList", Arrays.asList("List<ASTNode> initializers"), "initializers.toString()"));
        types.add(define(outputDir, "ASTIdentifier", Arrays.asList("String name"), "name"));
        types.add(define(outputDir, "ASTFunctionPointerDeclaration", Arrays.asList("CompilerType type", "List<ASTNode> parameters", "String name"), "type + \" (*\" + name + \")(\" + parameters.toString() + \")\""));
        types.add(define(outputDir, "ASTFunctionDefinition", Arrays.asList("CompilerType type", "String name", "List<ASTNode> parameters", "ASTNode body"), "type + \" \" + name + \"(\" + parameters.toString() + \")\" + body"));
        types.add(define(outputDir, "ASTEnumerator", Arrays.asList("String name", "Integer value"), "name + (value != null ? \" = \" + value : \"\")"));
        types.add(define(outputDir, "ASTEnumeratorList", Arrays.asList("List<ASTNode> enumerators"), "enumerators.toString()"));
        defineVisitor(outputDir, types);
    }


    private static String define(String outputDir, String name, List<String> types, String printMethod) throws IOException {
        defineAst(outputDir, name, types, printMethod);
        return name;
    }

    private static void defineVisitor(String outputDir, List<String> types)
            throws IOException {
        String path = outputDir + "/ASTVisitor.java";
        PrintWriter writer = new PrintWriter(path, "UTF-8");

        writer.println("package AST;");
        writer.println();
        writer.println("public interface ASTVisitor<T> {");

        for (String type : types) {
            writer.println("    T visit(" + type + " " + type.toLowerCase() + ");");
        }
        writer.println("}");
        writer.close();
    }


    private static void defineAst(
            String outputDir, String name, List<String> types, String printMethod)
            throws IOException {
        String path = outputDir + "/" + name + ".java";
        PrintWriter writer = new PrintWriter(path, "UTF-8");

        writer.println("package AST;");
        writer.println();
        writer.println("import java.util.List;");
        writer.println("import visitor.CompilerType;");
        writer.println();
        writer.println("public class " + name + " extends ASTNode {");

        for (String type : types) {
            writer.println("public final " + type + ";");
        }

        writer.println();
        writer.print("    public " + name + "(");
        for (int i = 0; i < types.size(); i++) {
            String type = types.get(i);
            writer.print(type);
            if (i != types.size() - 1) {
                writer.print(", ");
            }
        }

        writer.println(") {");
        for (String type : types) {
            String arg = type.split(" ")[1];
            writer.println("        this." + arg + " = " + arg + ";");
        }

        writer.println("    }");
        writer.println();
        writer.println("    public void accept(ASTVisitor visitor) {");
        writer.println("        visitor.visit(this);");
        writer.println("    }");
        writer.println();
        writer.println("}");
        writer.close();
    }


}
