package passes;

import AST1stPass.*;
import AST2ndPass.*;
import AST2ndPass.AST2ndPassNode;
import visitor.CompilerType;

import java.util.ArrayList;


public class Desugar implements ASTVisitor<AST2ndPassNode>
{

    @Override
    public AST2ndPassNode visit(ASTAddition astaddition) {
        AST2ndPassNode left = (AST2ndPassNode) astaddition.left.accept(this);
        AST2ndPassNode right = (AST2ndPassNode) astaddition.right.accept(this);
        return new AST2AdditionNode(left, right);
    }

    @Override
    public AST2ndPassNode visit(ASTSubtraction astsubtraction) {
        AST2ndPassNode left = (AST2ndPassNode) astsubtraction.left.accept(this);
        AST2ndPassNode right = (AST2ndPassNode) astsubtraction.right.accept(this);
        return new AST2SubtractionNode(left, right);
    }

    @Override
    public AST2ndPassNode visit(ASTMultiplication astmultiplication) {
        AST2ndPassNode left = (AST2ndPassNode) astmultiplication.left.accept(this);
        AST2ndPassNode right = (AST2ndPassNode) astmultiplication.right.accept(this);
        return new AST2MultiplicationNode(left, right);
    }

    @Override
    public AST2ndPassNode visit(ASTDivision astdivision) {
        AST2ndPassNode left = (AST2ndPassNode) astdivision.left.accept(this);
        AST2ndPassNode right = (AST2ndPassNode) astdivision.right.accept(this);
        return new AST2DivisionNode(left, right);
    }

    @Override
    public AST2ndPassNode visit(ASTModulo astmodulo) {
        AST2ndPassNode left = (AST2ndPassNode) astmodulo.left.accept(this);
        AST2ndPassNode right = (AST2ndPassNode) astmodulo.right.accept(this);
        return new AST2ModuloNode(left, right);
    }

    @Override
    public AST2ndPassNode visit(ASTLeftShift astleftshift) {
        AST2ndPassNode left = (AST2ndPassNode) astleftshift.left.accept(this);
        AST2ndPassNode right = (AST2ndPassNode) astleftshift.right.accept(this);
        return new AST2LeftShiftNode(left, right);
    }

    @Override
    public AST2ndPassNode visit(ASTRightShift astrightshift) {
        AST2ndPassNode left = (AST2ndPassNode) astrightshift.left.accept(this);
        AST2ndPassNode right = (AST2ndPassNode) astrightshift.right.accept(this);
        return new AST2RightShiftNode(left, right);
    }

    @Override
    public AST2ndPassNode visit(ASTBitwiseAnd astbitwiseand) {
        AST2ndPassNode left = (AST2ndPassNode) astbitwiseand.left.accept(this);
        AST2ndPassNode right = (AST2ndPassNode) astbitwiseand.right.accept(this);
        return new AST2BitwiseAndNode(left, right);
    }

    @Override
    public AST2ndPassNode visit(ASTBitwiseOr astbitwiseor) {
        AST2ndPassNode left = (AST2ndPassNode) astbitwiseor.left.accept(this);
        AST2ndPassNode right = (AST2ndPassNode) astbitwiseor.right.accept(this);
        return new AST2BitwiseOrNode(left, right);
    }

    @Override
    public AST2ndPassNode visit(ASTBitwiseXor astbitwisexor) {
        AST2ndPassNode left = (AST2ndPassNode) astbitwisexor.left.accept(this);
        AST2ndPassNode right = (AST2ndPassNode) astbitwisexor.right.accept(this);
        return new AST2BitwiseXorNode(left, right);
    }

    @Override
    public AST2ndPassNode visit(ASTLessThan astlessthan) {
        AST2ndPassNode left = (AST2ndPassNode) astlessthan.left.accept(this);
        AST2ndPassNode right = (AST2ndPassNode) astlessthan.right.accept(this);
        return new AST2LessThanNode(left, right);
    }

    @Override
    public AST2ndPassNode visit(ASTLessThanOrEqual astlessthanorequal) {
        AST2ndPassNode left = (AST2ndPassNode) astlessthanorequal.left.accept(this);
        AST2ndPassNode right = (AST2ndPassNode) astlessthanorequal.right.accept(this);
        return new AST2LessThanOrEqualNode(left, right);
    }

    @Override
    public AST2ndPassNode visit(ASTGreaterThan astgreaterthan) {
        AST2ndPassNode left = (AST2ndPassNode) astgreaterthan.left.accept(this);
        AST2ndPassNode right = (AST2ndPassNode) astgreaterthan.right.accept(this);
        return new AST2GreaterThanNode(left, right);
    }

    @Override
    public AST2ndPassNode visit(ASTGreaterThanOrEqual astgreaterthanorequal) {
        AST2ndPassNode left = (AST2ndPassNode) astgreaterthanorequal.left.accept(this);
        AST2ndPassNode right = (AST2ndPassNode) astgreaterthanorequal.right.accept(this);
        return new AST2GreaterThanOrEqualNode(left, right);
    }

    @Override
    public AST2ndPassNode visit(ASTEqual astequal) {
        AST2ndPassNode left = (AST2ndPassNode) astequal.left.accept(this);
        AST2ndPassNode right = (AST2ndPassNode) astequal.right.accept(this);
        return new AST2EqualNode(left, right);
    }

    @Override
    public AST2ndPassNode visit(ASTNotEqual astnotequal) {
        AST2ndPassNode left = (AST2ndPassNode) astnotequal.left.accept(this);
        AST2ndPassNode right = (AST2ndPassNode) astnotequal.right.accept(this);
        return new AST2NotEqualNode(left, right);
    }

    @Override
    public AST2ndPassNode visit(ASTAssignment astassignment) {
        AST2ndPassNode left = (AST2ndPassNode) astassignment.left.accept(this);
        AST2ndPassNode right = (AST2ndPassNode) astassignment.right.accept(this);
        return new AST2AssignmentNode(left, right);
    }

    @Override
    public AST2ndPassNode visit(ASTMultiplicationAssignment astmultiplicationassignment) {
        AST2ndPassNode left = (AST2ndPassNode) astmultiplicationassignment.left.accept(this);
        AST2ndPassNode right = (AST2ndPassNode) astmultiplicationassignment.right.accept(this);
        return new AST2AssignmentNode(left, new AST2MultiplicationNode(left, right));
    }

    @Override
    public AST2ndPassNode visit(ASTDivisionAssignment astdivisionassignment) {
        AST2ndPassNode left = (AST2ndPassNode) astdivisionassignment.left.accept(this);
        AST2ndPassNode right = (AST2ndPassNode) astdivisionassignment.right.accept(this);
        return new AST2AssignmentNode(left, new AST2DivisionNode(left, right));
    }

    @Override
    public AST2ndPassNode visit(ASTModuloAssignment astmoduloassignment) {
        AST2ndPassNode left = (AST2ndPassNode) astmoduloassignment.left.accept(this);
        AST2ndPassNode right = (AST2ndPassNode) astmoduloassignment.right.accept(this);
        return new AST2AssignmentNode(left, new AST2ModuloNode(left, right));
    }

    @Override
    public AST2ndPassNode visit(ASTAdditionAssignment astadditionassignment) {
        AST2ndPassNode left = (AST2ndPassNode) astadditionassignment.left.accept(this);
        AST2ndPassNode right = (AST2ndPassNode) astadditionassignment.right.accept(this);
        return new AST2AssignmentNode(left, new AST2AdditionNode(left, right));
    }

    @Override
    public AST2ndPassNode visit(ASTSubtractionAssignment astsubtractionassignment) {
        AST2ndPassNode left = (AST2ndPassNode) astsubtractionassignment.left.accept(this);
        AST2ndPassNode right = (AST2ndPassNode) astsubtractionassignment.right.accept(this);
        return new AST2AssignmentNode(left, new AST2SubtractionNode(left, right));
    }

    @Override
    public AST2ndPassNode visit(ASTLeftShiftAssignment astleftshiftassignment) {
        AST2ndPassNode left = (AST2ndPassNode) astleftshiftassignment.left.accept(this);
        AST2ndPassNode right = (AST2ndPassNode) astleftshiftassignment.right.accept(this);
        return new AST2AssignmentNode(left, new AST2LeftShiftNode(left, right));
    }

    @Override
    public AST2ndPassNode visit(ASTRightShiftAssignment astrightshiftassignment) {
        AST2ndPassNode left = (AST2ndPassNode) astrightshiftassignment.left.accept(this);
        AST2ndPassNode right = (AST2ndPassNode) astrightshiftassignment.right.accept(this);
        return new AST2AssignmentNode(left, new AST2RightShiftNode(left, right));
    }

    @Override
    public AST2ndPassNode visit(ASTBitwiseAndAssignment astbitwiseandassignment) {
        AST2ndPassNode left = (AST2ndPassNode) astbitwiseandassignment.left.accept(this);
        AST2ndPassNode right = (AST2ndPassNode) astbitwiseandassignment.right.accept(this);
        return new AST2AssignmentNode(left, new AST2BitwiseAndNode(left, right));
    }

    @Override
    public AST2ndPassNode visit(ASTBitwiseOrAssignment astbitwiseorassignment) {
        AST2ndPassNode left = (AST2ndPassNode) astbitwiseorassignment.left.accept(this);
        AST2ndPassNode right = (AST2ndPassNode) astbitwiseorassignment.right.accept(this);
        return new AST2AssignmentNode(left, new AST2BitwiseOrNode(left, right));
    }

    @Override
    public AST2ndPassNode visit(ASTBitwiseXorAssignment astbitwisexorassignment) {
        AST2ndPassNode left = (AST2ndPassNode) astbitwisexorassignment.left.accept(this);
        AST2ndPassNode right = (AST2ndPassNode) astbitwisexorassignment.right.accept(this);
        return new AST2AssignmentNode(left, new AST2BitwiseXorNode(left, right));
    }

    @Override
    public AST2ndPassNode visit(ASTLogicalAnd astlogicaland) {
        AST2ndPassNode left = (AST2ndPassNode) astlogicaland.left.accept(this);
        AST2ndPassNode right = (AST2ndPassNode) astlogicaland.right.accept(this);
        return new AST2LogicalAndNode(left, right);
    }

    @Override
    public AST2ndPassNode visit(ASTLogicalOr astlogicalor) {
        AST2ndPassNode left = (AST2ndPassNode) astlogicalor.left.accept(this);
        AST2ndPassNode right = (AST2ndPassNode) astlogicalor.right.accept(this);
        return new AST2LogicalOrNode(left, right);
    }

    @Override
    public AST2ndPassNode visit(ASTLogicalNot astlogicalnot) {
        AST2ndPassNode left = (AST2ndPassNode) astlogicalnot.left.accept(this);
        return new AST2LogicalNotNode(left);
    }

    @Override
    public AST2ndPassNode visit(ASTBitwiseComplement astbitwisecomplement) {
        AST2ndPassNode left = (AST2ndPassNode) astbitwisecomplement.left.accept(this);
        return new AST2BitwiseComplementNode(left);
    }

    @Override
    public AST2ndPassNode visit(ASTPrefixIncrement astprefixincrement) {
        AST2ndPassNode left = (AST2ndPassNode) astprefixincrement.left.accept(this);
        currentBlock.add(new AST2AssignmentNode(left, new AST2AdditionNode(left, new AST2IntegerConstantNode(1))));
        return left;
    }

    @Override
    public AST2ndPassNode visit(ASTPrefixDecrement astprefixdecrement) {
        AST2ndPassNode left = (AST2ndPassNode) astprefixdecrement.left.accept(this);
        currentBlock.add(new AST2AssignmentNode(left, new AST2SubtractionNode(left, new AST2IntegerConstantNode(1))));
        return left;
    }

    @Override
    public AST2ndPassNode visit(ASTAddressOf astaddressof) {
        AST2ndPassNode left = (AST2ndPassNode) astaddressof.left.accept(this);
        return new AST2AddressOfNode(left);
    }

    @Override
    public AST2ndPassNode visit(ASTDereference astdereference) {
        AST2ndPassNode left = (AST2ndPassNode) astdereference.left.accept(this);
        return new AST2DereferenceNode(left);
    }

    @Override
    public AST2ndPassNode visit(ASTPostfixIncrement astpostfixincrement) {
        AST2ndPassNode left = (AST2ndPassNode) astprefixincrement.left.accept(this);
        currentBlock.add(new AST2VariableDeclarationNode("temp" + tempCount,  CompilerType.AutoResolve));
        currentBlock.add(new AST2AssignmentNode(new AST2IdentifierNode("temp" + tempCount), left));
        currentBlock.add(new AST2AssignmentNode(left, new AST2AdditionNode(left, new AST2IntegerConstantNode(1))));
        return new AST2IdentifierNode("temp" + tempCount++);
    }

    @Override
    public AST2ndPassNode visit(ASTPostfixDecrement astpostfixdecrement) {
        AST2ndPassNode left = (AST2ndPassNode) astprefixincrement.left.accept(this);
        currentBlock.add(new AST2VariableDeclarationNode("temp" + tempCount,  CompilerType.AutoResolve));
        currentBlock.add(new AST2AssignmentNode(new AST2IdentifierNode("temp" + tempCount), left));
        currentBlock.add(new AST2AssignmentNode(left, new AST2SubtractionNode(left, new AST2IntegerConstantNode(1))));
        return new AST2IdentifierNode("temp" + tempCount++);
    }

    @Override
    public AST2ndPassNode visit(ASTUnaryPlus astunaryplus) {
        AST2ndPassNode left = (AST2ndPassNode) astunaryplus.left.accept(this);
        return left; // Unary plus is a no-op
    }

    @Override
    public AST2ndPassNode visit(ASTUnaryMinus astunaryminus) {
        AST2ndPassNode left = (AST2ndPassNode) astunaryminus.left.accept(this);
        return new AST2SubtractionNode(new AST2IntegerConstantNode(0), left);
    }

    @Override
    public AST2ndPassNode visit(ASTLogicalNegation astlogicalnegation) {
        AST2ndPassNode left = (AST2ndPassNode) astlogicalnegation.left.accept(this);
        return new AST2LogicalNotNode(left);
    }

    @Override
    public AST2ndPassNode visit(ASTSizeOf astsizeof) {
        AST2ndPassNode left = (AST2ndPassNode) astsizeof.left.accept(this);
        return new AST2SizeOfNode(left);
    }

    @Override
    public AST2ndPassNode visit(ASTArgumentExpressionList astargumentexpressionlist) {
        ArrayList<AST2ndPassNode> list = new ArrayList<AST2ndPassNode>();
        for (ASTNode node : astargumentexpressionlist.arguments)
        {
            list.add((AST2ndPassNode) node.accept(this));
        }
        return new AST2ArgumentExpressionListNode(list);
    }

    @Override
    public AST2ndPassNode visit(ASTArrayAccess astarrayaccess) {
        //
    }

    int labelCount = 0;
    private String getLabel() {
        return "label" + labelCount;
    }

    private String getEndLabel() {
        return "endlabel" + labelCount;
    }

    @Override
    public AST2ndPassNode visit(ASTWhile astwhile) {
        labelCount++;
        ArrayList<AST2ndPassNode> list = new ArrayList<AST2ndPassNode>();
        currentBlock = list;
        list.add(new AST2VariableDeclarationNode("temp" + tempCount,  CompilerType.AutoResolve));
        AST2ndPassNode temp = new AST2IdentifierNode("temp" + tempCount);
        list.add(new AST2LabelNode(getLabel()));
        list.add(new AST2AssignmentNode(temp, (AST2ndPassNode) astwhile.left.accept(this)));
        list.add(new AST2IfNode(temp, new AST2GotoNode(getEndLabel())));
        list.add((AST2ndPassNode) astwhile.right.accept(this));
        list.add(new AST2GotoNode(getLabel()));
        list.add(new AST2LabelNode(getEndLabel()));
        currentBlock = null;
        tempCount++;
        return new AST2BlockNode(list);
    }

    @Override
    public AST2ndPassNode visit(ASTBlock astblock) {
        ArrayList<AST2ndPassNode> list = new ArrayList<AST2ndPassNode>();
        for (ASTNode node : astblock.statements)
        {
            list.add((AST2ndPassNode) node.accept(this));
        }
        return new AST2BlockNode(list);
    }

    @Override
    public AST2ndPassNode visit(ASTBreak astbreak) {
        return new AST2GotoNode(getEndLabel());
    }

    @Override
    public AST2ndPassNode visit(ASTContinue astcontinue) {
        return new AST2GotoNode(getLabel());
    }

    @Override
    public AST2ndPassNode visit(ASTDoWhile astdowhile) {
        labelCount++;
        ArrayList<AST2ndPassNode> list = new ArrayList<AST2ndPassNode>();
        currentBlock = list;
        list.add(new AST2LabelNode(getLabel()));
        list.add((AST2ndPassNode) astdowhile.right.accept(this));
        list.add(new AST2VariableDeclarationNode("temp" + tempCount,  CompilerType.AutoResolve));
        AST2ndPassNode temp = new AST2IdentifierNode("temp" + tempCount);
        list.add(new AST2AssignmentNode(temp, (AST2ndPassNode) astdowhile.left.accept(this)));
        list.add(new AST2IfNode(temp, new AST2GotoNode(getLabel())));
        list.add(new AST2GotoNode(getLabel()));
        list.add(new AST2LabelNode(getEndLabel()));
        currentBlock = null;
        tempCount++;
        return new AST2BlockNode(list);
    }

    @Override
    public AST2ndPassNode visit(ASTExpressionStatement astexpressionstatement) {
        return (AST2ndPassNode) astexpressionstatement.left.accept(this);
    }

    @Override
    public AST2ndPassNode visit(ASTCharacterConstant astcharacterconstant) {
        return new AST2CharacterConstantNode(astcharacterconstant.value);
    }

    @Override
    public AST2ndPassNode visit(ASTFloatingConstant astfloatingconstant) {
        return new AST2FloatingConstantNode(astfloatingconstant.value);
    }

    @Override
    public AST2ndPassNode visit(ASTIntegerConstant astintegerconstant) {
        return new AST2IntegerConstantNode(astintegerconstant.value);
    }

    @Override
    public AST2ndPassNode visit(ASTStringLiteral aststringliteral) {
        return new AST2StringLiteralNode(aststringliteral.value);
    }

    @Override
    public AST2ndPassNode visit(ASTComma astcomma) {
        ArrayList<AST2ndPassNode> list = new ArrayList<AST2ndPassNode>();
        for (ASTNode node : astcomma.expressions)
        {
            list.add((AST2ndPassNode) node.accept(this));
        }
        return new AST2CommaNode(list);
    }

    @Override
    public AST2ndPassNode visit(ASTDefault astdefault) {
        throw new RuntimeException("ASTDefault should not be visited");
    }

    @Override
    public AST2ndPassNode visit(ASTGoto astgoto) {
        return new AST2GotoNode(astgoto.label);
    }

    @Override
    public AST2ndPassNode visit(ASTLabel astlabel) {
        return new AST2LabelNode(astlabel.label);
    }

    @Override
    public AST2ndPassNode visit(ASTReturn astreturn) {
        return new AST2ReturnNode((AST2ndPassNode) astreturn.left.accept(this));
    }

    @Override
    public AST2ndPassNode visit(ASTVariableDeclaration astvariabledeclaration) {
        return new AST2VariableDeclarationNode(astvariabledeclaration.name, astvariabledeclaration.type, (AST2ndPassNode) astvariabledeclaration.initializer.accept(this));
    }

    @Override
    public AST2ndPassNode visit(ASTUnionDefinition astuniondefinition) {
        return new AST2UnionDefinitionNode(astuniondefinition.name, astuniondefinition.declarations);
    }

    @Override
    public AST2ndPassNode visit(ASTStructDefinition aststructdefinition) {
        return new AST2StructDefinitionNode(aststructdefinition.name, aststructdefinition.declarations);
    }

    @Override
    public AST2ndPassNode visit(ASTEnumDefinition astenumdefinition) {
        return null; // Enum substitution is done in the 1st pass
    }

    int tempCount = 0;
    @Override
    public AST2ndPassNode visit(ASTConditional astconditional) {
        currentBlock.add(new AST2VariableDeclarationNode("temp" + tempCount,  CompilerType.AutoResolve));
        AST2ndPassNode left = (AST2ndPassNode) astconditional.condition.accept(this);
        AST2ndPassNode middle = (AST2ndPassNode) astconditional.thenBranch.accept(this);
        AST2ndPassNode right = (AST2ndPassNode) astconditional.elseBranch.accept(this);
        AST2ndPassNode temp = new AST2IdentifierNode("temp" + tempCount);
        currentBlock.add(new AST2IfNode(left, new AST2AssignmentNode(temp, middle), new AST2AssignmentNode(temp, right)));
        return new AST2IdentifierNode("temp" + tempCount++);
    }

    @Override
    public AST2ndPassNode visit(ASTFunctionCall astfunctioncall) {
        ArrayList<AST2ndPassNode> list = new ArrayList<AST2ndPassNode>();
        for (ASTNode node : astfunctioncall.arguments)
        {
            list.add((AST2ndPassNode) node.accept(this));
        }
        return new AST2FunctionCallNode(astfunctioncall.left, list);
    }

    ArrayList<AST2ndPassNode> currentBlock = null;
    @Override
    public AST2ndPassNode visit(ASTIf astif) {
        labelCount++;
        ArrayList<AST2ndPassNode> list = new ArrayList<AST2ndPassNode>();
        currentBlock = list;
        list.add(new AST2LabelNode(getLabel()));
        list.add(new AST2VariableDeclarationNode("temp" + tempCount,  CompilerType.AutoResolve));
        AST2ndPassNode left = (AST2ndPassNode) astif.condition.accept(this);
        AST2ndPassNode middle = (AST2ndPassNode) astif.thenBranch.accept(this);
        AST2ndPassNode right = (AST2ndPassNode) astif.elseBranch.accept(this);
        AST2ndPassNode temp = new AST2IdentifierNode("temp" + tempCount);
        list.add(new AST2AssignmentNode(temp, left));
        list.add(new AST2IfNode(temp, middle, right));
        list.add(new AST2LabelNode(getEndLabel()));
        currentBlock = null;
        tempCount++;
        return new AST2BlockNode(list);
    }

    @Override
    public AST2ndPassNode visit(ASTFor astfor) {
        return null;
    }

    @Override
    public AST2ndPassNode visit(ASTArrayDeclaration astarraydeclaration) {
        return null;
    }

    @Override
    public AST2ndPassNode visit(ASTTranslationUnit asttranslationunit) {
        return null;
    }

    @Override
    public AST2ndPassNode visit(ASTSwitch astswitch) {
        return null;
    }

    @Override
    public AST2ndPassNode visit(ASTCase astcase) {
        return null;
    }

    @Override
    public AST2ndPassNode visit(ASTDeclaration astdeclaration) {
        return null;
    }

    @Override
    public AST2ndPassNode visit(ASTCast astcast) {
        return null;
    }

    @Override
    public AST2ndPassNode visit(ASTStructPointerAccess aststructpointeraccess) {
        return null;
    }

    @Override
    public AST2ndPassNode visit(ASTStructAccess aststructaccess) {
        return null;
    }

    @Override
    public AST2ndPassNode visit(ASTParameterList astparameterlist) {
        return null;
    }

    @Override
    public AST2ndPassNode visit(ASTInitializerList astinitializerlist) {

    }

    @Override
    public AST2ndPassNode visit(ASTIdentifier astidentifier) {
        return new AST2IdentifierNode(astidentifier.name);
    }

    @Override
    public AST2ndPassNode visit(ASTFunctionPointerDeclaration astfunctionpointerdeclaration) {
        //
    }

    @Override
    public AST2ndPassNode visit(ASTFunctionDefinition astfunctiondefinition) {
        return new AST2FunctionDefinitionNode(astfunctiondefinition.name, astfunctiondefinition.returnType, astfunctiondefinition.parameters, (AST2ndPassNode) astfunctiondefinition.body.accept(this));
    }

    @Override
    public AST2ndPassNode visit(ASTEnumerator astenumerator) {
        return null; // Enum substitution is done in the 1st pass
    }

    @Override
    public AST2ndPassNode visit(ASTEnumeratorList astenumeratorlist) {
        return null; // Enum substitution is done in the 1st pass
    }
}