package visitor;

import AST1stPass.*;

public class ASTPrinter implements ASTVisitor<String> {


    @Override
    public String visit(ASTAddition astaddition) {
        return astaddition.left.accept(this) + " + " + astaddition.right.accept(this) ;
    }

    @Override
    public String visit(ASTSubtraction astsubtraction) {
        return astsubtraction.left.accept(this) + " - " + astsubtraction.right.accept(this) ;
    }

    @Override
    public String visit(ASTMultiplication astmultiplication) {
        return astmultiplication.left.accept(this) + " * " + astmultiplication.right.accept(this) ;
    }

    @Override
    public String visit(ASTDivision astdivision) {
        return astdivision.left.accept(this) + " / " + astdivision.right.accept(this) ;
    }

    @Override
    public String visit(ASTModulo astmodulo) {
        return astmodulo.left.accept(this) + " % " + astmodulo.right.accept(this) ;
    }

    @Override
    public String visit(ASTLeftShift astleftshift) {
        return astleftshift.left.accept(this) + " << " + astleftshift.right.accept(this) ;
    }

    @Override
    public String visit(ASTRightShift astrightshift) {
        return astrightshift.left.accept(this) + " >> " + astrightshift.right.accept(this) ;
    }

    @Override
    public String visit(ASTBitwiseAnd astbitwiseand) {
        return astbitwiseand.left.accept(this) + " & " + astbitwiseand.right.accept(this) ;
    }

    @Override
    public String visit(ASTBitwiseOr astbitwiseor) {
        return astbitwiseor.left.accept(this) + " | " + astbitwiseor.right.accept(this) ;
    }

    @Override
    public String visit(ASTBitwiseXor astbitwisexor) {
        return astbitwisexor.left.accept(this) + " ^ " + astbitwisexor.right.accept(this) ;
    }

    @Override
    public String visit(ASTLessThan astlessthan) {
        return astlessthan.left.accept(this) + " < " + astlessthan.right.accept(this) ;
    }

    @Override
    public String visit(ASTLessThanOrEqual astlessthanorequal) {
        return astlessthanorequal.left.accept(this) + " <= " + astlessthanorequal.right.accept(this) ;
    }

    @Override
    public String visit(ASTGreaterThan astgreaterthan) {
        return astgreaterthan.left.accept(this) + " > " + astgreaterthan.right.accept(this) ;
    }

    @Override
    public String visit(ASTGreaterThanOrEqual astgreaterthanorequal) {
        return astgreaterthanorequal.left.accept(this) + " >= " + astgreaterthanorequal.right.accept(this) ;
    }

    @Override
    public String visit(ASTEqual astequal) {
        return astequal.left.accept(this) + " == " + astequal.right.accept(this) ;
    }

    @Override
    public String visit(ASTNotEqual astnotequal) {
        return astnotequal.left.accept(this) + " != " + astnotequal.right.accept(this) ;
    }

    @Override
    public String visit(ASTAssignment astassignment) {
        return astassignment.left.accept(this) + " = " + astassignment.right.accept(this) + ";";
    }

    @Override
    public String visit(ASTMultiplicationAssignment astmultiplicationassignment) {
        return astmultiplicationassignment.left.accept(this) + " *= " + astmultiplicationassignment.right.accept(this) + ";";
    }

    @Override
    public String visit(ASTDivisionAssignment astdivisionassignment) {
        return astdivisionassignment.left.accept(this) + " /= " + astdivisionassignment.right.accept(this) + ";";
    }

    @Override
    public String visit(ASTModuloAssignment astmoduloassignment) {
        return astmoduloassignment.left.accept(this) + " %= " + astmoduloassignment.right.accept(this) + ";";
    }

    @Override
    public String visit(ASTAdditionAssignment astadditionassignment) {
        return astadditionassignment.left.accept(this) + " += " + astadditionassignment.right.accept(this) + ";";
    }

    @Override
    public String visit(ASTSubtractionAssignment astsubtractionassignment) {
        return astsubtractionassignment.left.accept(this) + " -= " + astsubtractionassignment.right.accept(this) + ";";
    }

    @Override
    public String visit(ASTLeftShiftAssignment astleftshiftassignment) {
        return astleftshiftassignment.left.accept(this) + " <<= " + astleftshiftassignment.right.accept(this) + ";";
    }

    @Override
    public String visit(ASTRightShiftAssignment astrightshiftassignment) {
        return astrightshiftassignment.left.accept(this) + " >>= " + astrightshiftassignment.right.accept(this) + ";";
    }

    @Override
    public String visit(ASTBitwiseAndAssignment astbitwiseandassignment) {
        return astbitwiseandassignment.left.accept(this) + " &= " + astbitwiseandassignment.right.accept(this) + ";";
    }

    @Override
    public String visit(ASTBitwiseOrAssignment astbitwiseorassignment) {
        return astbitwiseorassignment.left.accept(this) + " |= " + astbitwiseorassignment.right.accept(this) + ";";
    }

    @Override
    public String visit(ASTBitwiseXorAssignment astbitwisexorassignment) {
        return astbitwisexorassignment.left.accept(this) + " ^= " + astbitwisexorassignment.right.accept(this) + ";";
    }

    @Override
    public String visit(ASTLogicalAnd astlogicaland) {
        return astlogicaland.left.accept(this) + " && " + astlogicaland.right.accept(this) ;
    }

    @Override
    public String visit(ASTLogicalOr astlogicalor) {
        return astlogicalor.left.accept(this) + " || " + astlogicalor.right.accept(this) ;
    }

    @Override
    public String visit(ASTLogicalNot astlogicalnot) {
        return "!" + astlogicalnot.left.accept(this);
    }

    @Override
    public String visit(ASTBitwiseComplement astbitwisecomplement) {
        return "~" + astbitwisecomplement.left.accept(this);
    }

    @Override
    public String visit(ASTPrefixIncrement astprefixincrement) {
        return "++" + astprefixincrement.left.accept(this);
    }

    @Override
    public String visit(ASTPrefixDecrement astprefixdecrement) {
        return "--" + astprefixdecrement.left.accept(this);
    }

    @Override
    public String visit(ASTAddressOf astaddressof) {
        return "&" + astaddressof.left.accept(this);
    }

    @Override
    public String visit(ASTDereference astdereference) {
        return "*" + astdereference.left.accept(this);
    }

    @Override
    public String visit(ASTPostfixIncrement astpostfixincrement) {
        return astpostfixincrement.left.accept(this) + "++";
    }

    @Override
    public String visit(ASTPostfixDecrement astpostfixdecrement) {
        return astpostfixdecrement.left.accept(this) + "--";
    }

    @Override
    public String visit(ASTUnaryPlus astunaryplus) {
        return "+" + astunaryplus.left.accept(this);
    }

    @Override
    public String visit(ASTUnaryMinus astunaryminus) {
        return "-" + astunaryminus.left.accept(this);
    }

    @Override
    public String visit(ASTLogicalNegation astlogicalnegation) {
        return "!" + astlogicalnegation.left.accept(this);
    }

    @Override
    public String visit(ASTSizeOf astsizeof) {
        return "sizeof(" + astsizeof.left.accept(this) + ")";
    }

    @Override
    public String visit(ASTArgumentExpressionList astargumentexpressionlist) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < astargumentexpressionlist.arguments.size(); i++) {
            sb.append(astargumentexpressionlist.arguments.get(i).accept(this));
            if (i != astargumentexpressionlist.arguments.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    @Override
    public String visit(ASTArrayAccess astarrayaccess) {
        return astarrayaccess.left.accept(this) + "[" + astarrayaccess.right.accept(this) + "]";
    }

    @Override
    public String visit(ASTWhile astwhile) {
        return "while (" + astwhile.left.accept(this) + ") " + astwhile.right.accept(this);
    }

    int indent = 0;
    @Override
    public String visit(ASTBlock astblock)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        indent++;
        for (ASTNode node : astblock.statements) {
            sb.append("\t".repeat(indent));
            sb.append(node.accept(this));
            if(node instanceof ASTFunctionCall)
            {
                sb.append(";");
            }
            sb.append("\n");
        }
        indent--;
        sb.append("\n" + "\t".repeat(indent) + "}");
        return sb.toString();
    }

    @Override
    public String visit(ASTBreak astbreak) {
        return "break;";
    }

    @Override
    public String visit(ASTContinue astcontinue) {
        return "continue;";
    }

    @Override
    public String visit(ASTDoWhile astdowhile) {
        return "do " + astdowhile.left.accept(this) + " while (" + astdowhile.right.accept(this) + ");";
    }

    @Override
    public String visit(ASTExpressionStatement astexpressionstatement) {
        return astexpressionstatement.left.accept(this) + ";";
    }

    @Override
    public String visit(ASTCharacterConstant astcharacterconstant) {
        return "'" + astcharacterconstant.value + "'";
    }

    @Override
    public String visit(ASTFloatingConstant astfloatingconstant) {
        return String.valueOf(astfloatingconstant.value);
    }

    @Override
    public String visit(ASTIntegerConstant astintegerconstant) {
        return String.valueOf(astintegerconstant.value);
    }

    @Override
    public String visit(ASTStringLiteral aststringliteral) {
        return aststringliteral.value;
    }

    @Override
    public String visit(ASTComma astcomma) {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        for (int i = 0; i < astcomma.expressions.size(); i++) {
            sb.append(astcomma.expressions.get(i).accept(this));
            if (i != astcomma.expressions.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return sb.toString();
    }

    @Override
    public String visit(ASTDefault astdefault) {
        return "default:";
    }

    @Override
    public String visit(ASTGoto astgoto) {
        return "goto " + astgoto.label + ";";
    }

    @Override
    public String visit(ASTLabel astlabel) {
        return astlabel.label + ":";
    }

    @Override
    public String visit(ASTReturn astreturn) {
        if (astreturn.left == null) {
            return "return;";
        }
        return "return " + astreturn.left.accept(this) + ";";
    }

    @Override
    public String visit(ASTVariableDeclaration astvariabledeclaration) {
        if(astvariabledeclaration.initializer == null) {
            return printType(astvariabledeclaration.type) + astvariabledeclaration.name + ";";
        }
        return printType(astvariabledeclaration.type) + astvariabledeclaration.name + " = " + astvariabledeclaration.initializer.accept(this) + ";";
    }

    private String printType(CompilerType type) {
        StringBuilder sb = new StringBuilder();
        if (type.isConst) {
            sb.append("const ");
        }
        if (type.isVolatile) {
            sb.append("volatile ");
        }
        if (type.isStatic) {
            sb.append("static ");
        }
        if (type.isExtern) {
            sb.append("extern ");
        }
        if (type.isRegister) {
            sb.append("register ");
        }
        switch (type.baseType) {
            case UNDEFINED -> {
                throw new RuntimeException("Undefined type");
            }
            case TVOID -> {
                sb.append("void ");
            }
            case TINT -> {
                sb.append("int ");
            }
            case TSHORT -> {
                sb.append("short ");
            }
            case TCHAR -> {
                sb.append("char ");
            }
            case TLONG -> {
                sb.append("long ");
            }
            case TLONGLONG -> {
                sb.append("long long ");
            }
            case TUINT -> {
                sb.append("unsigned int ");
            }
            case TUSHORT -> {
                sb.append("unsigned short ");
            }
            case TULONG -> {
                sb.append("unsigned long ");
            }
            case TULONGLONG -> {
                sb.append("unsigned long long ");
            }
            case TFLOAT -> {
                sb.append("float ");
            }
            case TDOUBLE -> {
                sb.append("double ");
            }
            case TSTRUCT -> {
                sb.append("struct ");
                sb.append(type.name);
                return sb.toString();
            }
            case TARRAY -> {
                sb.append("[]");
            }
            case TPOINTER -> {
                sb.append(printType(type.insideType));
                sb.append("*");
            }
            case TUNION -> {
                sb.append("union ");
                sb.append(type.name);
                return sb.toString();
            }
            case TENUM -> {
                sb.append("enum ");
                sb.append(type.name);
                return sb.toString();
            }
        }
        return sb.toString();
    }

    @Override
    public String visit(ASTUnionDefinition astuniondefinition) {
        StringBuilder sb = new StringBuilder();
        sb.append("union ");
        sb.append(astuniondefinition.name);
        sb.append(" {\n");
        indent++;
        for (ASTNode node : astuniondefinition.declarations) {
            sb.append("\t".repeat(indent));
            sb.append(node.accept(this));
            sb.append("\n");
        }
        indent--;
        sb.append("};");
        return sb.toString();
    }

    @Override
    public String visit(ASTStructDefinition aststructdefinition) {
        StringBuilder sb = new StringBuilder();
        sb.append("struct ");
        sb.append(aststructdefinition.name);
        sb.append(" {\n");
        indent++;
        for (ASTNode node : aststructdefinition.declarations) {
            sb.append("\t".repeat(indent));
            sb.append(node.accept(this));
            sb.append("\n");
        }
        indent--;
        sb.append("};");
        return sb.toString();
    }

    @Override
    public String visit(ASTEnumDefinition astenumdefinition) {
        StringBuilder sb = new StringBuilder();
        sb.append("enum ");
        sb.append(astenumdefinition.name);
        sb.append(" {\n");
        indent++;
        for (ASTNode node : astenumdefinition.declarations) {
            sb.append("\t".repeat(indent));
            sb.append(node.accept(this));
            sb.append(",\n");
        }
        indent--;
        sb.append("};");
        return sb.toString();
    }

    @Override
    public String visit(ASTConditional astconditional) {
        return "(" + astconditional.condition.accept(this) + " ? " + astconditional.thenBranch.accept(this) + " : " + astconditional.elseBranch.accept(this) + ")";
    }

    @Override
    public String visit(ASTFunctionCall astfunctioncall) {
        StringBuilder sb = new StringBuilder();
        sb.append(astfunctioncall.left.accept(this));
        sb.append("(");
        for (int i = 0; i < astfunctioncall.arguments.size(); i++) {
            sb.append(astfunctioncall.arguments.get(i).accept(this));
            if (i != astfunctioncall.arguments.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();

    }

    @Override
    public String visit(ASTIf astif) {
        StringBuilder sb = new StringBuilder();
        sb.append("if (");
        sb.append(astif.condition.accept(this));
        sb.append(") ");
        sb.append(astif.thenBranch.accept(this));
        if (astif.elseBranch != null) {
            sb.append(" else ");
            sb.append(astif.elseBranch.accept(this));
        }
        return sb.toString();
    }

    @Override
    public String visit(ASTFor astfor) {
        StringBuilder sb = new StringBuilder();
        sb.append("for (");
        sb.append(astfor.init.accept(this));
        if(sb.charAt(sb.length() - 1) != ';') {
            sb.append("; ");
        }
        else {
            sb.append(" ");
        }
        sb.append(astfor.condition.accept(this));
        if(sb.charAt(sb.length() - 1) != ';') {
            sb.append("; ");
        }
        else {
            sb.append(" ");
        }
        sb.append(astfor.increment.accept(this));
        if(sb.charAt(sb.length() - 1) == ';') {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append(") ");
        sb.append(astfor.body.accept(this));
        return sb.toString();
    }

    @Override
    public String visit(ASTArrayDeclaration astarraydeclaration) {
        return printType(astarraydeclaration.type) + astarraydeclaration.name + "[" + astarraydeclaration.size + "];";
    }

    @Override
    public String visit(ASTTranslationUnit asttranslationunit) {
        StringBuilder sb = new StringBuilder();
        for (ASTNode node : asttranslationunit.declarations) {
            if (node != null) {
                sb.append(node.accept(this));
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public String visit(ASTSwitch astswitch) {
        return "switch (" + astswitch.left.accept(this) + ") " + astswitch.right.accept(this);
    }

    @Override
    public String visit(ASTCase astcase) {
        return "case " + astcase.left.accept(this) + ": " + astcase.right.accept(this);
    }

    boolean nosemi = false;
    @Override
    public String visit(ASTDeclaration astdeclaration) {
        StringBuilder sb = new StringBuilder();
        sb.append(printType(astdeclaration.type));
        for (int i = 0; i < astdeclaration.declarations.size(); i++) {
            ASTVariableDeclaration decl = (ASTVariableDeclaration) astdeclaration.declarations.get(i);
            sb.append(decl.name);
            if (decl.initializer != null) {
                sb.append(" = ");
                sb.append(decl.initializer.accept(this));
            }
            if (i != astdeclaration.declarations.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(";");
        return sb.toString();
    }

    @Override
    public String visit(ASTCast astcast) {
        return "(" + printType(astcast.type) + ") " + astcast.left.accept(this);
    }

    @Override
    public String visit(ASTStructPointerAccess aststructpointeraccess) {
        return aststructpointeraccess.left.accept(this) + "->" + aststructpointeraccess.right;
    }

    @Override
    public String visit(ASTStructAccess aststructaccess) {
        return aststructaccess.left.accept(this) + "." + aststructaccess.right;
    }

    @Override
    public String visit(ASTParameterList astparameterlist) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < astparameterlist.parameters.size(); i++) {
            sb.append(astparameterlist.parameters.get(i).accept(this));
            if (i != astparameterlist.parameters.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    @Override
    public String visit(ASTInitializerList astinitializerlist) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < astinitializerlist.initializers.size(); i++) {
            sb.append(astinitializerlist.initializers.get(i).accept(this));
            if (i != astinitializerlist.initializers.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    @Override
    public String visit(ASTIdentifier astidentifier) {
        return astidentifier.name;
    }

    @Override
    public String visit(ASTFunctionPointerDeclaration astfunctionpointerdeclaration) {
        StringBuilder sb = new StringBuilder();
        sb.append(printType(astfunctionpointerdeclaration.type));
        sb.append("(*");
        sb.append(astfunctionpointerdeclaration.name);
        sb.append(")(");
        for (int i = 0; i < astfunctionpointerdeclaration.parameters.size(); i++) {
            sb.append(astfunctionpointerdeclaration.parameters.get(i).accept(this));
            if (i != astfunctionpointerdeclaration.parameters.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(");");
        return sb.toString();
    }

    @Override
    public String visit(ASTFunctionDefinition astfunctiondefinition) {
        StringBuilder sb = new StringBuilder();
        sb.append(printType(astfunctiondefinition.type));
        sb.append(astfunctiondefinition.name);
        sb.append("(");
        for (int i = 0; i < astfunctiondefinition.parameters.size(); i++) {
            ASTVariableDeclaration decl = (ASTVariableDeclaration) astfunctiondefinition.parameters.get(i);
            sb.append(printType(decl.type));
            sb.append(decl.name);
            if (i != astfunctiondefinition.parameters.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(") ");
        sb.append(astfunctiondefinition.body.accept(this));
        return sb.toString();
    }

    @Override
    public String visit(ASTEnumerator astenumerator) {
        if (astenumerator.value == null) {
            return astenumerator.name;
        }
        return astenumerator.name + " = " + astenumerator.value;
    }

    @Override
    public String visit(ASTEnumeratorList astenumeratorlist) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < astenumeratorlist.enumerators.size(); i++) {
            sb.append(astenumeratorlist.enumerators.get(i).accept(this));
            if (i != astenumeratorlist.enumerators.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
