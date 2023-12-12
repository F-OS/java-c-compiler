package visitor;
import AST1stPass.ASTNode;
import AST1stPass.*;
import antlr4.*;

import java.util.ArrayList;
import java.util.Objects;

public class CVisitor extends CParserBaseVisitor<ASTNode>
{

    ArrayList<EnumItem> enumList = new ArrayList<>();
    @Override
    public ASTNode visitUnionSpecifier(CParserParser.UnionSpecifierContext ctx) {
        throw new RuntimeException("Should not be directly visited");
    }

    @Override
    public ASTNode visitCastType(CParserParser.CastTypeContext ctx) {
        throw new RuntimeException("Should not be directly visited");
    }

    @Override
    public ASTNode visitUnaryOperator(CParserParser.UnaryOperatorContext ctx) {
        throw new RuntimeException("Should not be directly visited");
    }

    @Override
    public ASTNode visitCompoundStatement(CParserParser.CompoundStatementContext ctx) {
           ArrayList<ASTNode> statements = new ArrayList<>();
            for(int i = 0; i < ctx.blockItemList().blockItem().size(); i++)
            {
                statements.add(visit(ctx.blockItemList().blockItem(i)));
            }
            return new ASTBlock(statements);
    }

    @Override
    public ASTNode visitAssignmentOperator(CParserParser.AssignmentOperatorContext ctx) {
        throw new RuntimeException("Should not be directly visited");
    }

    private int enumIdx = 0;
    @Override
    public ASTNode visitEnumDefinition(CParserParser.EnumDefinitionContext ctx) {
        enumIdx = 0;
        if(ctx.enumeratorList() != null)
        {
            ArrayList<ASTNode> enumerators = new ArrayList<>();
            for(int i = 0; i < ctx.enumeratorList().enumerator().size(); i++)
            {
                if(ctx.enumeratorList().enumerator(i).IntegerConstant() == null)
                {
                    enumList.add(new EnumItem(ctx.enumeratorList().enumerator(i).Identifier().getText(), enumIdx));
                }
                else {
                    enumList.add(new EnumItem(ctx.enumeratorList().enumerator(i).Identifier().getText(), Integer.parseInt(ctx.enumeratorList().enumerator(i).IntegerConstant().getText())));
                }
                enumerators.add(new ASTEnumerator(ctx.enumeratorList().enumerator(i).Identifier().getText(), enumIdx++));
            }
            CompilerType.registerEnum(ctx.Identifier().getText(), enumList);
            return new ASTEnumDefinition(ctx.Identifier().getText(), enumerators);
        }
        return new ASTEnumDefinition(ctx.Identifier().getText(), null);
    }

    @Override
    public ASTNode visitDirectDeclarator(CParserParser.DirectDeclaratorContext ctx) {
        throw new RuntimeException("Should not be directly visited");
    }

    @Override
    public ASTNode visitCastExpression(CParserParser.CastExpressionContext ctx) {
        if(ctx.unaryExpression() != null)
        {
            return visit(ctx.unaryExpression());
        }
        else if(ctx.castType() != null) {
            CompilerType type = CompilerType.getType(ctx.castType());
            CParserParser.PointerContext pointer = ctx.castType().pointer();
            while (pointer != null) {
                type = type.pointerMe();
                pointer = pointer.pointer();
            }
            return new ASTCast(type, visit(ctx.castExpression()));
        }
        else
        {
            throw new RuntimeException("Unknown cast expression");
        }
    }

    @Override
    public ASTNode visitInitDeclarator(CParserParser.InitDeclaratorContext ctx) {
        throw new RuntimeException("Should not be directly visited");
    }

    @Override
    public ASTNode visitExternalDeclaration(CParserParser.ExternalDeclarationContext ctx) {
        if (ctx.structDefinition() != null) {
            return visit(ctx.structDefinition());
        }
        if (ctx.unionDefinition() != null) {
            return visit(ctx.unionDefinition());
        }
        if (ctx.functionDefinition() != null) {
            return visit(ctx.functionDefinition());
        }
        if (ctx.enumDefinition() != null) {
            return visit(ctx.enumDefinition());
        }
        if (ctx.declaration() != null) {
            return visit(ctx.declaration());
        }
        throw new RuntimeException("Unknown external declaration");
    }

    @Override
    public ASTNode visitLogicalAndExpression(CParserParser.LogicalAndExpressionContext ctx) {
        if(ctx.getChildCount() == 1)
        {
            return visit(ctx.inclusiveOrExpression());
        }
        else if(ctx.AndAnd() != null)
        {
            return new ASTLogicalAnd(visit(ctx.logicalAndExpression()), visit(ctx.inclusiveOrExpression()));
        }
        else
        {
            throw new RuntimeException("Unknown logical and expression");
        }
    }

    @Override
    public ASTNode visitExpressionStatement(CParserParser.ExpressionStatementContext ctx) {
        if(ctx.expression() != null)
        {
            return visit(ctx.expression());
        }
        return null;
    }

    @Override
    public ASTNode visitUnaryExpression(CParserParser.UnaryExpressionContext ctx) {
        if(ctx.postfixExpression() != null)
        {
            return visit(ctx.postfixExpression());
        }
        else if(ctx.PlusPlus() != null)
        {
            return new ASTPrefixIncrement(visit(ctx.unaryExpression()));
        }
        else if(ctx.MinusMinus() != null)
        {
            return new ASTPrefixDecrement(visit(ctx.unaryExpression()));
        }
        else if(ctx.unaryOperator() != null) {
            if (ctx.unaryOperator().And() != null) {
                return new ASTAddressOf(visit(ctx.castExpression()));
            } else if (ctx.unaryOperator().Star() != null) {
                return new ASTDereference(visit(ctx.castExpression()));
            } else if (ctx.unaryOperator().Plus() != null) {
                return new ASTUnaryPlus(visit(ctx.castExpression()));
            } else if (ctx.unaryOperator().Minus() != null) {
                return new ASTUnaryMinus(visit(ctx.castExpression()));
            } else if (ctx.unaryOperator().Tilde() != null) {
                return new ASTBitwiseComplement(visit(ctx.castExpression()));
            } else if (ctx.unaryOperator().Not() != null) {
                return new ASTLogicalNegation(visit(ctx.castExpression()));
            }
            else {
                throw new RuntimeException("Unknown unary expression");
            }
        }
        else if(ctx.Sizeof() != null && ctx.LeftParen() == null)
        {
            return new ASTSizeOf(visit(ctx.unaryExpression()));
        }
        else if(ctx.Sizeof() != null)
        {
            return new ASTIntegerConstant(CompilerType.getType(ctx.declarationSpecifiers()).getSize());
        }
        else
        {
            throw new RuntimeException("Unknown unary expression");
        }
    }

    @Override
    public ASTNode visitPointer(CParserParser.PointerContext ctx) {
        throw new RuntimeException("Should not be directly visited");
    }

    @Override
    public ASTNode visitIterationStatement(CParserParser.IterationStatementContext ctx) {
        if(ctx.Do() != null)
        {
            return new ASTDoWhile(visit(ctx.statement()), visit(ctx.expression()));
        }
        if(ctx.While() != null)
        {
            return new ASTWhile(visit(ctx.expression()), visit(ctx.statement()));
        }
        else if(ctx.For() != null)
        {
            if (ctx.declaration() != null) {
                if (ctx.expression() != null) {
                    return new ASTFor(visit(ctx.declaration()), visit(ctx.expressionStatement(0)), visit(ctx.expression()), visit(ctx.statement()));
                } else {
                    return new ASTFor(visit(ctx.declaration()), visit(ctx.expressionStatement(0)), null, visit(ctx.statement()));
                }
            } else {
                if (ctx.expression() != null) {
                    return new ASTFor(visit(ctx.expressionStatement(0)), visit(ctx.expressionStatement(1)), visit(ctx.expression()), visit(ctx.statement()));
                } else {
                    return new ASTFor(visit(ctx.expressionStatement(0)), visit(ctx.expressionStatement(1)), null, visit(ctx.statement()));
                }
            }
        }
        else
        {
            throw new RuntimeException("Unknown iteration statement");
        }
    }

    @Override
    public ASTNode visitShiftExpression(CParserParser.ShiftExpressionContext ctx) {
        if(ctx.getChildCount() == 1)
        {
            return visit(ctx.additiveExpression());
        }
        else if(ctx.LeftShift() != null)
        {
            return new ASTLeftShift(visit(ctx.shiftExpression()), visit(ctx.additiveExpression()));
        }
        else if(ctx.RightShift() != null)
        {
            return new ASTRightShift(visit(ctx.shiftExpression()), visit(ctx.additiveExpression()));
        }
        else
        {
            throw new RuntimeException("Unknown shift expression");
        }
    }

    @Override
    public ASTNode visitInclusiveOrExpression(CParserParser.InclusiveOrExpressionContext ctx) {
        if(ctx.getChildCount() == 1)
        {
            return visit(ctx.exclusiveOrExpression());
        }
        else if(ctx.Or() != null)
        {
            return new ASTBitwiseOr(visit(ctx.inclusiveOrExpression()), visit(ctx.exclusiveOrExpression()));
        }
        else
        {
            throw new RuntimeException("Unknown inclusive or expression");
        }
    }

    @Override
    public ASTNode visitAssignmentExpression(CParserParser.AssignmentExpressionContext ctx) {
        if(ctx.conditionalExpression() != null)
        {
            return visit(ctx.conditionalExpression());
        }
        else
        {
            if(ctx.assignmentOperator().Assign() != null)
            {
                return new ASTAssignment(visit(ctx.unaryExpression()), visit(ctx.assignmentExpression()));
            }
            else if(ctx.assignmentOperator().StarAssign() != null)
            {
                return new ASTMultiplicationAssignment(visit(ctx.unaryExpression()), visit(ctx.assignmentExpression()));
            }
            else if(ctx.assignmentOperator().DivAssign() != null)
            {
                return new ASTDivisionAssignment(visit(ctx.unaryExpression()), visit(ctx.assignmentExpression()));
            }
            else if(ctx.assignmentOperator().ModAssign() != null)
            {
                return new ASTModuloAssignment(visit(ctx.unaryExpression()), visit(ctx.assignmentExpression()));
            }
            else if(ctx.assignmentOperator().PlusAssign() != null)
            {
                return new ASTAdditionAssignment(visit(ctx.unaryExpression()), visit(ctx.assignmentExpression()));
            }
            else if(ctx.assignmentOperator().MinusAssign() != null)
            {
                return new ASTSubtractionAssignment(visit(ctx.unaryExpression()), visit(ctx.assignmentExpression()));
            }
            else if(ctx.assignmentOperator().LeftShiftAssign() != null)
            {
                return new ASTLeftShiftAssignment(visit(ctx.unaryExpression()), visit(ctx.assignmentExpression()));
            }
            else if(ctx.assignmentOperator().RightShiftAssign() != null)
            {
                return new ASTRightShiftAssignment(visit(ctx.unaryExpression()), visit(ctx.assignmentExpression()));
            }
            else if(ctx.assignmentOperator().AndAssign() != null)
            {
                return new ASTBitwiseAndAssignment(visit(ctx.unaryExpression()), visit(ctx.assignmentExpression()));
            }
            else if(ctx.assignmentOperator().XorAssign() != null)
            {
                return new ASTBitwiseXorAssignment(visit(ctx.unaryExpression()), visit(ctx.assignmentExpression()));
            }
            else if(ctx.assignmentOperator().OrAssign() != null)
            {
                return new ASTBitwiseOrAssignment(visit(ctx.unaryExpression()), visit(ctx.assignmentExpression()));
            }
            else
            {
                throw new RuntimeException("Unknown assignment expression");
            }
        }
    }

    @Override
    public ASTNode visitStructSpecifier(CParserParser.StructSpecifierContext ctx) {
        throw new RuntimeException("Should not be directly visited");
    }

    @Override
    public ASTNode visitDeclarationSpecifiers(CParserParser.DeclarationSpecifiersContext ctx) {
        throw new RuntimeException("Should not be directly visited");
    }

    @Override
    public ASTNode visitTranslationUnit(CParserParser.TranslationUnitContext ctx) {
        ArrayList<ASTNode> children = new ArrayList<>();
        for (int i = 0; i < ctx.getChildCount(); i++) {
            children.add(visit(ctx.getChild(i)));
        }
        return new ASTTranslationUnit(children);
    }

    @Override
    public ASTNode visitBlockItemList(CParserParser.BlockItemListContext ctx) {
        ArrayList<ASTNode> blockItems = new ArrayList<>();
        for(int i = 0; i < ctx.blockItem().size(); i++)
        {
            blockItems.add(visit(ctx.blockItem(i)));
        }
        return new ASTBlock(blockItems);
    }

    @Override
    public ASTNode visitExclusiveOrExpression(CParserParser.ExclusiveOrExpressionContext ctx) {
        if(ctx.getChildCount() == 1)
        {
            return visit(ctx.andExpression());
        }
        else if(ctx.Caret() != null)
        {
            return new ASTBitwiseXor(visit(ctx.exclusiveOrExpression()), visit(ctx.andExpression()));
        }
        else
        {
            throw new RuntimeException("Unknown exclusive or expression");
        }
    }

    @Override
    public ASTNode visitEqualityExpression(CParserParser.EqualityExpressionContext ctx) {
        if(ctx.getChildCount() == 1)
        {
            return visit(ctx.relationalExpression());
        }
        else if(ctx.Equal() != null)
        {
            return new ASTEqual(visit(ctx.equalityExpression()), visit(ctx.relationalExpression()));
        }
        else if(ctx.NotEqual() != null)
        {
            return new ASTNotEqual(visit(ctx.equalityExpression()), visit(ctx.relationalExpression()));
        }
        else
        {
            throw new RuntimeException("Unknown equality expression");
        }
    }

    @Override
    public ASTNode visitParameterList(CParserParser.ParameterListContext ctx) {
        ArrayList<ASTNode> parameters = new ArrayList<>();
        for(int i = 0; i < ctx.parameterDeclaration().size(); i++)
        {
            parameters.add(visit(ctx.parameterDeclaration(i)));
        }
        return new ASTParameterList(parameters);
    }

    @Override
    public ASTNode visitParameterDeclaration(CParserParser.ParameterDeclarationContext ctx) {
        CompilerType type = CompilerType.getType(ctx.declarationSpecifiers());
        CParserParser.DeclaratorContext declarator = ctx.declarator();
        CParserParser.PointerContext pointer = declarator.pointer();
        while(pointer != null)
        {
            type = type.pointerMe();
            pointer = pointer.pointer();
        }
        if(declarator.directDeclarator().Identifier() != null)
        {
            return new ASTVariableDeclaration(type, declarator.directDeclarator().Identifier().getText(), null);
        }
        else
        {
            throw new RuntimeException("Unknown parameter declaration");
        }
    }

    @Override
    public ASTNode visitBlockItem(CParserParser.BlockItemContext ctx) {
        if(ctx.declaration() != null)
        {
            return visit(ctx.declaration());
        }
        else if(ctx.statement() != null)
        {
            return visit(ctx.statement());
        }
        else
        {
            throw new RuntimeException("Unknown block item");
        }
    }

    @Override
    public ASTNode visitInitDeclaratorList(CParserParser.InitDeclaratorListContext ctx) {
        throw new RuntimeException("Should not be directly visited");
    }

    @Override
    public ASTNode visitStructDeclaration(CParserParser.StructDeclarationContext ctx) {
        CompilerType type = CompilerType.getType(ctx.specifierQualifierList());
        CParserParser.DeclaratorContext declarator = ctx.declarator();
        CParserParser.PointerContext pointer = declarator.pointer();
        while(pointer != null)
        {
            type = type.pointerMe();
            pointer = pointer.pointer();
        }
        if(declarator.directDeclarator().Identifier() != null)
        {
            return new ASTVariableDeclaration(type, declarator.directDeclarator().Identifier().getText(), null);
        }
        else
        {
            throw new RuntimeException("Unknown struct declaration");
        }
    }


    @Override
    public ASTNode visitAdditiveExpression(CParserParser.AdditiveExpressionContext ctx) {
        if(ctx.getChildCount() == 1)
        {
            return visit(ctx.multiplicativeExpression());
        }
        else if(ctx.Plus() != null)
        {
            return new ASTAddition(visit(ctx.additiveExpression()), visit(ctx.multiplicativeExpression()));
        }
        else if(ctx.Minus() != null)
        {
            return new ASTSubtraction(visit(ctx.additiveExpression()), visit(ctx.multiplicativeExpression()));
        }
        else
        {
            throw new RuntimeException("Unknown additive expression");
        }
    }

    @Override
    public ASTNode visitRelationalExpression(CParserParser.RelationalExpressionContext ctx) {
        if(ctx.getChildCount() == 1)
        {
            return visit(ctx.shiftExpression());
        }
        else if(ctx.Less() != null)
        {
            return new ASTLessThan(visit(ctx.relationalExpression()), visit(ctx.shiftExpression()));
        }
        else if(ctx.Greater() != null)
        {
            return new ASTGreaterThan(visit(ctx.relationalExpression()), visit(ctx.shiftExpression()));
        }
        else if(ctx.LessEqual() != null)
        {
            return new ASTLessThanOrEqual(visit(ctx.relationalExpression()), visit(ctx.shiftExpression()));
        }
        else if(ctx.GreaterEqual() != null)
        {
            return new ASTGreaterThanOrEqual(visit(ctx.relationalExpression()), visit(ctx.shiftExpression()));
        }
        else
        {
            throw new RuntimeException("Unknown relational expression");
        }
    }

    @Override
    public ASTNode visitEnumeratorList(CParserParser.EnumeratorListContext ctx) {
        throw new RuntimeException("Should not be directly visited");
    }

    @Override
    public ASTNode visitSelectionStatement(CParserParser.SelectionStatementContext ctx) {
        if(ctx.If() != null)
        {
            if(ctx.Else() != null)
            {
                return new ASTIf(visit(ctx.expression()), visit(ctx.statement(0)), visit(ctx.statement(1)));
            }
            else
            {
                return new ASTIf(visit(ctx.expression()), visit(ctx.statement(0)), null);
            }
        }
        else if(ctx.Switch() != null)
        {
            return new ASTSwitch(visit(ctx.expression()), visit(ctx.statement(0)));
        }
        else
        {
            throw new RuntimeException("Unknown selection statement");
        }
    }

    @Override
    public ASTNode visitStorageClassSpecifier(CParserParser.StorageClassSpecifierContext ctx) {
        throw new RuntimeException("Should not be directly visited");
    }

    @Override
    public ASTNode visitStructDefinition(CParserParser.StructDefinitionContext ctx) {
        if(ctx.structDeclaration() != null)
        {
            ArrayList<ASTNode> structMembers = new ArrayList<>();
            for(int i = 0; i < ctx.structDeclaration().size(); i++)
            {
                structMembers.add(visit(ctx.structDeclaration(i)));
            }
            CompilerType.registerStruct(ctx.Identifier().getText(), structMembers);
            return new ASTStructDefinition(ctx.Identifier().getText(), structMembers);
        }
        return new ASTStructDefinition(ctx.Identifier().getText(), null);

    }

    @Override
    public ASTNode visitAndExpression(CParserParser.AndExpressionContext ctx) {
        if(ctx.getChildCount() == 1)
        {
            return visit(ctx.equalityExpression());
        }
        else if(ctx.And() != null)
        {
            return new ASTBitwiseAnd(visit(ctx.andExpression()), visit(ctx.equalityExpression()));
        }
        else
        {
            throw new RuntimeException("Unknown and expression");
        }
    }

    @Override
    public ASTNode visitLabeledStatement(CParserParser.LabeledStatementContext ctx) {
if(ctx.Identifier() != null)
        {
            return new ASTLabel(ctx.Identifier().getText(), visit(ctx.statement()));
        }
        else if(ctx.Case() != null)
        {
            return new ASTCase(visit(ctx.constantExpression()), visit(ctx.statement()));
        }
        else if(ctx.Default() != null)
        {
            return new ASTDefault(visit(ctx.statement()));
        }
        else {
            throw new RuntimeException("Unknown labeled statement");
        }
    }

    @Override
    public ASTNode visitStatement(CParserParser.StatementContext ctx) {
        if(ctx.labeledStatement() != null)
        {
            return visit(ctx.labeledStatement());
        }
        else if(ctx.compoundStatement() != null)
        {
            return visit(ctx.compoundStatement());
        }
        else if(ctx.expressionStatement() != null)
        {
            return visit(ctx.expressionStatement());
        }
        else if(ctx.selectionStatement() != null)
        {
            return visit(ctx.selectionStatement());
        }
        else if(ctx.iterationStatement() != null)
        {
            return visit(ctx.iterationStatement());
        }
        else if(ctx.jumpStatement() != null)
        {
            return visit(ctx.jumpStatement());
        }
        else
        {
            throw new RuntimeException("Unknown statement");
        }
    }

    @Override
    public ASTNode visitArgumentExpressionList(CParserParser.ArgumentExpressionListContext ctx) {
        ArrayList<ASTNode> arguments = new ArrayList<>();
        for(int i = 0; i < ctx.assignmentExpression().size(); i++)
        {
            arguments.add(visit(ctx.assignmentExpression(i)));
        }
        return new ASTArgumentExpressionList(arguments);
    }

    @Override
    public ASTNode visitEnumerator(CParserParser.EnumeratorContext ctx) {
        if(ctx.IntegerConstant() != null)
        {
            return new ASTEnumerator(ctx.Identifier().getText(), Integer.parseInt(ctx.IntegerConstant().getText()));
        }
        return new ASTEnumerator(ctx.Identifier().getText(), enumIdx++);
    }

    @Override
    public ASTNode visitPostfixExpression(CParserParser.PostfixExpressionContext ctx) {
        if(ctx.getChildCount() == 1)
        {
            return visit(ctx.primaryExpression());
        }
        else if(ctx.LeftBracket() != null)
        {
            return new ASTArrayAccess(visit(ctx.postfixExpression()), visit(ctx.expression()));
        }
        else if(ctx.LeftParen() != null)
        {
            ArrayList<ASTNode> arguments = new ArrayList<>();
            if(ctx.argumentExpressionList() != null) {
                for (int i = 0; i < ctx.argumentExpressionList().assignmentExpression().size(); i++) {
                    arguments.add(visit(ctx.argumentExpressionList().assignmentExpression(i)));
                }
            }
            return new ASTFunctionCall(visit(ctx.postfixExpression()), arguments);
        }
        else if(ctx.Dot() != null)
        {
            return new ASTStructAccess(visit(ctx.postfixExpression()), ctx.Identifier().getText());
        }
        else if(ctx.Arrow() != null)
        {
            return new ASTStructPointerAccess(visit(ctx.postfixExpression()), ctx.Identifier().getText());
        }
        else if(ctx.PlusPlus() != null)
        {
            return new ASTPostfixIncrement(visit(ctx.postfixExpression()));
        }
        else if(ctx.MinusMinus() != null)
        {
            return new ASTPostfixDecrement(visit(ctx.postfixExpression()));
        }
        else
        {
            throw new RuntimeException("Unknown postfix expression");
        }
    }

    @Override
    public ASTNode visitTypeQualifierList(CParserParser.TypeQualifierListContext ctx) {
        throw new RuntimeException("Should not be directly visited");
    }

    @Override
    public ASTNode visitPrimaryExpression(CParserParser.PrimaryExpressionContext ctx) {
        if(ctx.Identifier() != null)
        {
            for(EnumItem enumItem : enumList)
            {
                if(Objects.equals(enumItem.name, ctx.Identifier().getText()))
                {
                    return new ASTIntegerConstant(enumItem.value);
                }
            }
            return new ASTIdentifier(ctx.Identifier().getText());
        }
        else if(ctx.IntegerConstant() != null)
        {
            if(ctx.IntegerConstant().getText().endsWith("l") || ctx.IntegerConstant().getText().endsWith("L"))
            {
                return new ASTIntegerConstant(Long.parseLong(ctx.IntegerConstant().getText().substring(0, ctx.IntegerConstant().getText().length() - 1)));
            }
            else
            {
                return new ASTIntegerConstant(Integer.parseInt(ctx.IntegerConstant().getText()));
            }
        }
        else if(ctx.CharacterConstant() != null)
        {
            return new ASTCharacterConstant(ctx.CharacterConstant().getText());
        }
        else if(ctx.FloatingConstant() != null)
        {
            return new ASTFloatingConstant(Double.parseDouble(ctx.FloatingConstant().getText()));
        }
        else if(ctx.StringLiteral() != null)
        {
            return new ASTStringLiteral(ctx.StringLiteral().getText());
        }
        else if(ctx.LeftParen() != null)
        {
            return visit(ctx.expression());
        }
        else
        {
            throw new RuntimeException("Unknown primary expression");
        }
    }

    @Override
    public ASTNode visitExpression(CParserParser.ExpressionContext ctx) {
        if(ctx.assignmentExpression().size() == 1)
        {
            return visit(ctx.assignmentExpression(0));
        }
        else {
            ArrayList<ASTNode> expressions = new ArrayList<>();
            for (int i = 0; i < ctx.assignmentExpression().size(); i++) {
                expressions.add(visit(ctx.assignmentExpression(i)));
            }
            return new ASTComma(expressions);
        }
    }

    @Override
    public ASTNode visitConstantExpression(CParserParser.ConstantExpressionContext ctx) {
        return new ASTIntegerConstant(QuickEvaluator.quickEvaluate(visit(ctx.conditionalExpression()), enumList));
    }

    @Override
    public ASTNode visitFunctionDefinition(CParserParser.FunctionDefinitionContext ctx) {
        CompilerType returnType = CompilerType.getType(ctx.declarationSpecifiers());
        CParserParser.PointerContext pointer = ctx.declarator().pointer();
        while(pointer != null)
        {
            returnType = returnType.pointerMe();
            pointer = pointer.pointer();
        }
        CParserParser.DirectDeclaratorContext directDeclarator = ctx.declarator().directDeclarator();
        if(directDeclarator.Identifier() != null && directDeclarator.parameterList() != null) {
            ArrayList<ASTNode> parameters = new ArrayList<>();
            for (int i = 0; i < directDeclarator.parameterList().parameterDeclaration().size(); i++) {
                parameters.add(visit(directDeclarator.parameterList().parameterDeclaration(i)));
            }
            return new ASTFunctionDefinition(returnType, directDeclarator.Identifier().getText(), parameters, visit(ctx.compoundStatement()));
        }
        else
        {
            throw new RuntimeException("Unknown function definition");
        }
    }

    @Override
    public ASTNode visitConditionalExpression(CParserParser.ConditionalExpressionContext ctx)
    {
        if(ctx.getChildCount() == 1)
        {
            return visit(ctx.logicalOrExpression());
        }
        else
        {
            return new ASTConditional(visit(ctx.logicalOrExpression()), visit(ctx.expression()), visit(ctx.conditionalExpression()));
        }
    }

    @Override
    public ASTNode visitSpecifierQualifierList(CParserParser.SpecifierQualifierListContext ctx) {
        throw new RuntimeException("Should not be directly visited");
    }

    @Override
    public ASTNode visitTypeSpecifier(CParserParser.TypeSpecifierContext ctx) {
        throw new RuntimeException("Should not be directly visited");
    }

    @Override
    public ASTNode visitLogicalOrExpression(CParserParser.LogicalOrExpressionContext ctx) {
        if(ctx.getChildCount() == 1)
        {
            return visit(ctx.logicalAndExpression());
        }
        else if(ctx.OrOr() != null)
        {
            return new ASTLogicalOr(visit(ctx.logicalOrExpression()), visit(ctx.logicalAndExpression()));
        }
        else
        {
            throw new RuntimeException("Unknown logical or expression");
        }
    }

    @Override
    public ASTNode visitMultiplicativeExpression(CParserParser.MultiplicativeExpressionContext ctx) {
        if(ctx.getChildCount() == 1)
        {
            return visit(ctx.castExpression());
        }
        else if(ctx.Star() != null)
        {
            return new ASTMultiplication(visit(ctx.multiplicativeExpression()), visit(ctx.castExpression()));
        }
        else if(ctx.Div() != null)
        {
            return new ASTDivision(visit(ctx.multiplicativeExpression()), visit(ctx.castExpression()));
        }
        else if(ctx.Mod() != null)
        {
            return new ASTModulo(visit(ctx.multiplicativeExpression()), visit(ctx.castExpression()));
        }
        else
        {
            throw new RuntimeException("Unknown multiplicative expression");
        }
    }

    @Override
    public ASTNode visitDeclaration(CParserParser.DeclarationContext ctx) {
        CompilerType type = CompilerType.getType(ctx.declarationSpecifiers());
        CParserParser.InitDeclaratorListContext initDeclaratorList = ctx.initDeclaratorList();
        ArrayList<ASTNode> declarators = new ArrayList<>();
        for(int i = 0; i < initDeclaratorList.initDeclarator().size(); i++) {
            CParserParser.InitDeclaratorContext initDeclarator = initDeclaratorList.initDeclarator(i);
            CParserParser.DeclaratorContext declarator = initDeclarator.declarator();
            CParserParser.PointerContext pointer = declarator.pointer();
            CompilerType declaratorType = type;
            while (pointer != null) {
                type = type.pointerMe();
                pointer = pointer.pointer();
            }
            if (declarator.directDeclarator().IntegerConstant() != null) {
                declarators.add(new ASTArrayDeclaration(declaratorType, Integer.valueOf(declarator.directDeclarator().IntegerConstant().getText()), declarator.directDeclarator().Identifier().getText()));
            } else if (declarator.directDeclarator().LeftBracket() != null) {
                declarators.add(new ASTArrayDeclaration(declaratorType, null, declarator.directDeclarator().Identifier().getText()));
            } else if (declarator.directDeclarator().Identifier() != null) {
                if(initDeclarator.initializer() != null) {
                    declarators.add(new ASTVariableDeclaration(declaratorType, declarator.directDeclarator().Identifier().getText(), visit(initDeclarator.initializer())));
                }
                else
                {
                    declarators.add(new ASTVariableDeclaration(declaratorType, declarator.directDeclarator().Identifier().getText(), null));
                }
            } else {
                throw new RuntimeException("Unknown declaration");
            }
        }
        return new ASTDeclaration(type, declarators);
    }

    @Override
    public ASTNode visitDeclarator(CParserParser.DeclaratorContext ctx) {
        throw new RuntimeException("Should not be directly visited");
    }

    @Override
    public ASTNode visitInitializer(CParserParser.InitializerContext ctx) {
        if(ctx.assignmentExpression() != null)
        {
            return visit(ctx.assignmentExpression());
        }
        else if(ctx.initializerList() != null)
        {
            ArrayList<ASTNode> initializers = new ArrayList<>();
            for(int i = 0; i < ctx.initializerList().initializer().size(); i++)
            {
                initializers.add(visit(ctx.initializerList().initializer(i)));
            }
            return new ASTInitializerList(initializers);
        }
        return null;
    }

    @Override
    public ASTNode visitUnionDefinition(CParserParser.UnionDefinitionContext ctx) {
        if(ctx.structDeclaration() != null)
        {
            ArrayList<ASTNode> unionMembers = new ArrayList<>();
            for(int i = 0; i < ctx.structDeclaration().size(); i++)
            {
                unionMembers.add(visit(ctx.structDeclaration(i)));
            }
            CompilerType.registerUnion(ctx.Identifier().getText(), unionMembers);
            return new ASTUnionDefinition(ctx.Identifier().getText(), unionMembers);
        }
        return new ASTUnionDefinition(ctx.Identifier().getText(), null);
    }

    @Override
    public ASTNode visitEnumSpecifier(CParserParser.EnumSpecifierContext ctx) {
        throw new RuntimeException("Should not be directly visited");
    }

    @Override
    public ASTNode visitJumpStatement(CParserParser.JumpStatementContext ctx) {
        if(ctx.Return() != null)
        {
            if(ctx.expression() != null)
            {
                return new ASTReturn(visit(ctx.expression()));
            }
            else
            {
                return new ASTReturn(null);
            }
        }
        else if(ctx.Break() != null)
        {
            return new ASTBreak();
        }
        else if(ctx.Continue() != null)
        {
            return new ASTContinue();
        }
        else if(ctx.Goto() != null)
        {
            return new ASTGoto(ctx.Identifier().getText());
        }
        else
        {
            throw new RuntimeException("Unknown jump statement");
        }
    }

    @Override
    public ASTNode visitTypeQualifier(CParserParser.TypeQualifierContext ctx) {
        throw new RuntimeException("Should not be directly visited");
    }

    @Override
    public ASTNode visitInitializerList(CParserParser.InitializerListContext ctx) {
        ArrayList<ASTNode> initializers = new ArrayList<>();
        for(int i = 0; i < ctx.initializer().size(); i++)
        {
            initializers.add(visit(ctx.initializer(i)));
        }
        return new ASTInitializerList(initializers);
    }
}