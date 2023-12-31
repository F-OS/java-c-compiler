package AST1stPass;

public interface ASTVisitor<T> {
    T visit(ASTAddition astaddition);
    T visit(ASTSubtraction astsubtraction);
    T visit(ASTMultiplication astmultiplication);
    T visit(ASTDivision astdivision);
    T visit(ASTModulo astmodulo);
    T visit(ASTLeftShift astleftshift);
    T visit(ASTRightShift astrightshift);
    T visit(ASTBitwiseAnd astbitwiseand);
    T visit(ASTBitwiseOr astbitwiseor);
    T visit(ASTBitwiseXor astbitwisexor);
    T visit(ASTLessThan astlessthan);
    T visit(ASTLessThanOrEqual astlessthanorequal);
    T visit(ASTGreaterThan astgreaterthan);
    T visit(ASTGreaterThanOrEqual astgreaterthanorequal);
    T visit(ASTEqual astequal);
    T visit(ASTNotEqual astnotequal);
    T visit(ASTAssignment astassignment);
    T visit(ASTMultiplicationAssignment astmultiplicationassignment);
    T visit(ASTDivisionAssignment astdivisionassignment);
    T visit(ASTModuloAssignment astmoduloassignment);
    T visit(ASTAdditionAssignment astadditionassignment);
    T visit(ASTSubtractionAssignment astsubtractionassignment);
    T visit(ASTLeftShiftAssignment astleftshiftassignment);
    T visit(ASTRightShiftAssignment astrightshiftassignment);
    T visit(ASTBitwiseAndAssignment astbitwiseandassignment);
    T visit(ASTBitwiseOrAssignment astbitwiseorassignment);
    T visit(ASTBitwiseXorAssignment astbitwisexorassignment);
    T visit(ASTLogicalAnd astlogicaland);
    T visit(ASTLogicalOr astlogicalor);
    T visit(ASTLogicalNot astlogicalnot);
    T visit(ASTBitwiseComplement astbitwisecomplement);
    T visit(ASTPrefixIncrement astprefixincrement);
    T visit(ASTPrefixDecrement astprefixdecrement);
    T visit(ASTAddressOf astaddressof);
    T visit(ASTDereference astdereference);
    T visit(ASTPostfixIncrement astpostfixincrement);
    T visit(ASTPostfixDecrement astpostfixdecrement);
    T visit(ASTUnaryPlus astunaryplus);
    T visit(ASTUnaryMinus astunaryminus);
    T visit(ASTLogicalNegation astlogicalnegation);
    T visit(ASTSizeOf astsizeof);
    T visit(ASTArgumentExpressionList astargumentexpressionlist);
    T visit(ASTArrayAccess astarrayaccess);
    T visit(ASTWhile astwhile);
    T visit(ASTBlock astblock);
    T visit(ASTBreak astbreak);
    T visit(ASTContinue astcontinue);
    T visit(ASTDoWhile astdowhile);
    T visit(ASTExpressionStatement astexpressionstatement);
    T visit(ASTCharacterConstant astcharacterconstant);
    T visit(ASTFloatingConstant astfloatingconstant);
    T visit(ASTIntegerConstant astintegerconstant);
    T visit(ASTStringLiteral aststringliteral);
    T visit(ASTComma astcomma);
    T visit(ASTDefault astdefault);
    T visit(ASTGoto astgoto);
    T visit(ASTLabel astlabel);
    T visit(ASTReturn astreturn);
    T visit(ASTVariableDeclaration astvariabledeclaration);
    T visit(ASTUnionDefinition astuniondefinition);
    T visit(ASTStructDefinition aststructdefinition);
    T visit(ASTEnumDefinition astenumdefinition);
    T visit(ASTConditional astconditional);
    T visit(ASTFunctionCall astfunctioncall);
    T visit(ASTIf astif);
    T visit(ASTFor astfor);
    T visit(ASTArrayDeclaration astarraydeclaration);
    T visit(ASTTranslationUnit asttranslationunit);
    T visit(ASTSwitch astswitch);
    T visit(ASTCase astcase);
    T visit(ASTDeclaration astdeclaration);
    T visit(ASTCast astcast);
    T visit(ASTStructPointerAccess aststructpointeraccess);
    T visit(ASTStructAccess aststructaccess);
    T visit(ASTParameterList astparameterlist);
    T visit(ASTInitializerList astinitializerlist);
    T visit(ASTIdentifier astidentifier);
    T visit(ASTFunctionPointerDeclaration astfunctionpointerdeclaration);
    T visit(ASTFunctionDefinition astfunctiondefinition);
    T visit(ASTEnumerator astenumerator);
    T visit(ASTEnumeratorList astenumeratorlist);
}
