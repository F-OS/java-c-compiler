package visitor;


import AST1stPass.ASTNode;
import AST1stPass.ASTVariableDeclaration;
import antlr4.CParserBaseVisitor;
import antlr4.CParserParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;

public class CompilerType extends CParserBaseVisitor<Void> {
    public final static CompilerType AutoResolve = new CompilerType(); // TODO

    public enum BaseType
    {
        UNDEFINED,
        TVOID,
        TINT,
        TSHORT,
        TCHAR,
        TLONG,
        TLONGLONG,
        TUINT,
        TUSHORT,
        TULONG,
        TULONGLONG,
        TFLOAT,
        TDOUBLE,
        TFUNC,
        TSTRUCT,
        TARRAY,
        TPOINTER,
        TUNION,
        TENUM,
    }
    public BaseType baseType = BaseType.UNDEFINED; // Type of the variable
    int arraySize; // Size of the array.
    int size; // Size of the type
    int Align; // Alignment boundary
    int Sign; // 1 if the type is signed, 0 if it is not
    int Offset; // Offset of the variable
    boolean onHeap; // 1 if the variable is on the heap, 0 if it is on the stack
    boolean isStatic; // 1 if the variable is static, 0 if it is not
    boolean isConst; // 1 if the variable is const, 0 if it is not
    boolean isVolatile; // 1 if the variable is volatile, 0 if it is not
    boolean isExtern; // 1 if the variable is extern, 0 if it is not
    boolean isRegister; // 1 if the variable is register, 0 if it is not
    private boolean setIntegerType;
    String name; // name of the struct or union

    public record StructMember(String name, CompilerType type) {
        public String toString() {
            return type + " " + name;
        }
    }

    CompilerType insideType; // Type of the array or pointer

    ArrayList<StructMember> members; // List of members of the struct or union

    public CompilerType() {
        baseType = BaseType.UNDEFINED;
        arraySize = 0;
        size = 0;
        Align = 0;
        Sign = 0;
        Offset = 0;
        onHeap = false;
        isStatic = false;
        isConst = false;
        isVolatile = false;
        isExtern = false;
        isRegister = false;
        name = "";
        members = new ArrayList<>();
    }

    public static CompilerType getType(ParserRuleContext declarationSpecifiersContext) {
        CompilerType type = new CompilerType();
        type.visit(declarationSpecifiersContext);
        return type;
    }

    public int getSize() {
        return size;
    }

    public int getAlign() {
        return Align;
    }

    public CompilerType pointerMe()
    {
        CompilerType type = new CompilerType();
        type.baseType = BaseType.TPOINTER;
        type.insideType = this;
        type.size = 8;
        type.Align = 8;
        return type;
    }

    @Override
    public Void visitCastType(CParserParser.CastTypeContext ctx) {
        visit(ctx.typeSpecifier());
        if(ctx.pointer() != null)
        {
            visit(ctx.pointer());
        }
        return null;
    }



    @Override
    public Void visitDeclarationSpecifiers(CParserParser.DeclarationSpecifiersContext ctx) {
        if(ctx.storageClassSpecifier() != null)
        {
            visit(ctx.storageClassSpecifier());
        }
        if(ctx.typeSpecifier() != null)
        {
            visit(ctx.typeSpecifier());
        }
        if(ctx.typeQualifier() != null)
        {
            visit(ctx.typeQualifier());
        }
        if(ctx.declarationSpecifiers() != null)
        {
            visit(ctx.declarationSpecifiers());
        }
        return null;
    }

    @Override
    public Void visitStorageClassSpecifier(CParserParser.StorageClassSpecifierContext ctx) {
        if(ctx.Extern() != null)
        {
            if(isExtern)
            {
                System.out.println("Error: Multiple extern declarations");
                System.exit(1);
            }
            isExtern = true;
        }
        if(ctx.Static() != null)
        {
            if(isStatic)
            {
                System.out.println("Error: Multiple static declarations");
                System.exit(1);
            }
            isStatic = true;
        }
        if(ctx.Register() != null)
        {
            if(isRegister)
            {
                System.out.println("Error: Multiple register declarations");
                System.exit(1);
            }
            isRegister = true;
        }
        return null;

    }

    @Override
    public Void visitTypeQualifierList(CParserParser.TypeQualifierListContext ctx) {
        for(var typeQualifier : ctx.typeQualifier())
        {
            visit(typeQualifier);
        }
        return null;
    }

    @Override
    public Void visitSpecifierQualifierList(CParserParser.SpecifierQualifierListContext ctx) {
        if(ctx.typeSpecifier() != null)
        {
            visit(ctx.typeSpecifier());
        }
        if(ctx.typeQualifier() != null)
        {
            visit(ctx.typeQualifier());
        }
        if(ctx.specifierQualifierList() != null)
        {
            visit(ctx.specifierQualifierList());
        }
        return null;
    }

    @Override
    public Void visitTypeSpecifier(CParserParser.TypeSpecifierContext ctx) {
        if(ctx.Void() != null)
        {
            if(baseType != BaseType.UNDEFINED)
            {
                System.out.println("Error: Multiple type specifiers");
                System.exit(1);
            }
            baseType = BaseType.TVOID;
            size = 0;
            Align = 0;
        }
        if(ctx.Char() != null)
        {
            if(baseType != BaseType.UNDEFINED)
            {
                System.out.println("Error: Multiple type specifiers");
                System.exit(1);
            }
            else {
                baseType = BaseType.TCHAR;
                size = 1;
                Align = 1;
                Sign = 0;
                setIntegerType = true;
            }
        }
        if(ctx.Short() != null) {
            if (baseType == BaseType.TSHORT) {
                size = 1;
                Align = 1;
                if (Sign == 0) {
                    baseType = BaseType.TUSHORT;
                }
                else {
                    baseType = BaseType.TSHORT;
                }
                setIntegerType = true;
            } else if (baseType != BaseType.UNDEFINED) {
                System.out.println("Error: Multiple type specifiers");
                System.exit(1);
            }
            else {
                baseType = BaseType.TSHORT;
                size = 2;
                Align = 2;
                Sign = 1;
                setIntegerType = true;
            }
        }
        if(ctx.Int() != null) {
            if (!setIntegerType) {
                size = 4;
                Align = 4;
                Sign = 1;
                setIntegerType = true;
                baseType = BaseType.TINT;
            }
        }
        if(ctx.Long() != null) {
            if (baseType == BaseType.TLONG) {
                size = 8;
                Align = 8;
                if (Sign == 0) {
                    baseType = BaseType.TULONGLONG;
                }
                else {
                    baseType = BaseType.TLONGLONG;
                }
                setIntegerType = true;
            } else if (baseType == BaseType.TINT) {
                size = 8;
                Align = 8;
                if (Sign == 0) {
                    baseType = BaseType.TULONG;
                }
                else {
                    baseType = BaseType.TLONG;
                }
                setIntegerType = true;
            } else if (baseType != BaseType.UNDEFINED) {
                System.out.println("Error: Multiple type specifiers");
                System.exit(1);
            }
            else {
                baseType = BaseType.TLONG;
                size = 8;
                Align = 8;
                Sign = 1;
                setIntegerType = true;
            }
        }
        if(ctx.Float() != null)
        {
            if(baseType != BaseType.UNDEFINED)
            {
                System.out.println("Error: Multiple type specifiers");
                System.exit(1);
            }
            baseType = BaseType.TFLOAT;
            size = 4;
            Align = 4;
        }
        if(ctx.Double() != null)
        {
            if(baseType != BaseType.UNDEFINED)
            {
                System.out.println("Error: Multiple type specifiers");
                System.exit(1);
            }
            baseType = BaseType.TDOUBLE;
            size = 8;
            Align = 8;
        }
        if(ctx.Signed() != null)
        {
            setIntegerType = true;
            switch (baseType)
            {
                case TUSHORT:
                    baseType = BaseType.TSHORT;
                    Sign = 1;
                    break;
                case TUINT:
                    baseType = BaseType.TINT;
                    Sign = 1;
                    break;
                case TULONG:
                    baseType = BaseType.TLONG;
                    Sign = 1;
                    break;
                case TULONGLONG:
                    baseType = BaseType.TLONGLONG;
                    Sign = 1;
                    break;
                default:
                    System.out.println("Error: Invalid signed type");
                    System.exit(1);
        }
        if(ctx.Unsigned() != null)
        {
            setIntegerType = true;
            switch (baseType)
            {
                case TSHORT:
                    baseType = BaseType.TUSHORT;
                    Sign = 0;
                    break;
                case TINT:
                    baseType = BaseType.TUINT;
                    Sign = 0;
                    break;
                case TLONG:
                    baseType = BaseType.TULONG;
                    Sign = 0;
                    break;
                case TLONGLONG:
                    baseType = BaseType.TULONGLONG;
                    Sign = 0;
                    break;
                default:
                    System.out.println("Error: Invalid unsigned type");
                    System.exit(1);
            }
        }
        }
        if(ctx.structSpecifier() != null)
        {
            resolveStruct(ctx.structSpecifier());
        }
        if(ctx.unionSpecifier() != null)
        {
            resolveUnion(ctx.unionSpecifier());
        }
        if(ctx.enumSpecifier() != null)
        {
            resolveEnum(ctx.enumSpecifier());
        }
        return null;
    }

    @Override
    public Void visitTypeQualifier(CParserParser.TypeQualifierContext ctx) {
        if(ctx.Const() != null)
        {
            if(isConst)
            {
                System.out.println("Error: Multiple const specifiers");
                System.exit(1);
            }
            isConst = true;
        }
        if(ctx.Volatile() != null)
        {
            if(isVolatile)
            {
                System.out.println("Error: Multiple volatile specifiers");
                System.exit(1);
            }
            isVolatile = true;
        }
        return null;
    }

    private static ArrayList<CompilerType> structTypes = new ArrayList<>();
    private static ArrayList<CompilerType> unionTypes = new ArrayList<>();
    private static ArrayList<EnumItem> enumItems = new ArrayList<>();

    public static void registerStruct(String name, ArrayList<ASTNode> structMembers)
    {
        for(var structType : structTypes)
        {
            if(structType.name.equals(name))
            {
                System.out.println("Error: Multiple struct declarations");
                System.exit(1);
            }
        }
        CompilerType type = new CompilerType();
        type.baseType = BaseType.TSTRUCT;
        type.name = name;
        int size = 0;
        int align = 0;
        int curPos = 0;
        ArrayList<StructMember> members = new ArrayList<>();
        for(ASTNode structMember : structMembers) {
            if(structMember instanceof ASTVariableDeclaration decl)
            {
                CompilerType memberType = decl.type;
                size += memberType.size;
                if(memberType.Align > align)
                {
                    align = memberType.Align;
                }
                memberType.Offset = curPos;
                curPos += memberType.size;
                if(curPos % memberType.Align != 0)
                {
                    curPos += memberType.Align - curPos % memberType.Align;
                }
                members.add(new StructMember(decl.name, memberType));
            }
            else {
                System.out.println("Error: Invalid struct member");
                System.exit(1);
            }
        }
        type.size = size;
        type.Align = align;
        type.members = members;
        structTypes.add(type);
    }

    public static void registerUnion(String name, ArrayList<ASTNode> unionMembers) {
        for (var unionType : unionTypes) {
            if (unionType.name.equals(name)) {
                System.out.println("Error: Multiple union declarations");
                System.exit(1);
            }
        }
        CompilerType type = new CompilerType();
        type.baseType = BaseType.TUNION;
        type.name = name;
        int size = 0;
        int align = 0;
        int curPos = 0;
        ArrayList<StructMember> members = new ArrayList<>();
        for (ASTNode unionMember : unionMembers) {
            if (unionMember instanceof ASTVariableDeclaration decl) {
                CompilerType memberType = decl.type;
                if (memberType.size > size) {
                    size = memberType.size;
                }
                if (memberType.Align > align) {
                    align = memberType.Align;
                }
                memberType.Offset = 0;
                members.add(new StructMember(decl.name, memberType));
            } else {
                System.out.println("Error: Invalid union member");
                System.exit(1);
            }
        }
        type.size = size;
        type.Align = align;
        type.members = members;
        unionTypes.add(type);
    }

    public static void registerEnum(String name, ArrayList<EnumItem> enumItems) {
        for (var enumItem : enumItems) {
            for (var item : CompilerType.enumItems) {
                if (item.name.equals(enumItem.name)) {
                    System.out.println("Error: Multiple enum declarations");
                    System.exit(1);
                }
            }
        }
        for (var item : CompilerType.enumItems) {
            if (item.name.equals(name)) {
                System.out.println("Error: Multiple enum declarations");
                System.exit(1);
            }
        }
        CompilerType.enumItems.addAll(enumItems);
    }


    private void resolveEnum(CParserParser.EnumSpecifierContext ctx) {
        if(ctx.Identifier() != null)
        {
            for(var item : enumItems)
            {
                if(item.name.equals(ctx.Identifier().getText()))
                {
                    System.out.println("Error: Multiple enum declarations");
                    System.exit(1);
                }
            }
            name = ctx.Identifier().getText();
        }
    }

    public void resolveStruct(CParserParser.StructSpecifierContext ctx) {
        if(ctx.Identifier() != null)
        {
            for(var structType : structTypes)
            {
                if(structType.name.equals(ctx.Identifier().getText()))
                {
                    baseType = BaseType.TSTRUCT;
                    name = ctx.Identifier().getText();
                    size = structType.size;
                    Align = structType.Align;
                    members = structType.members;
                    return;
                }
            }
            System.out.println("Error: Unknown struct type");
            System.exit(1);
        }
        else
        {
            System.out.println("Error: Anonymous struct");
            System.exit(1);
        }
    }

    public void resolveUnion(CParserParser.UnionSpecifierContext ctx) {
        if(ctx.Identifier() != null)
        {
            for(var unionType : unionTypes)
            {
                if(unionType.name.equals(ctx.Identifier().getText()))
                {
                    baseType = BaseType.TUNION;
                    name = ctx.Identifier().getText();
                    size = unionType.size;
                    Align = unionType.Align;
                    members = unionType.members;
                    return;
                }
            }
            System.out.println("Error: Unknown union type");
            System.exit(1);
        }
        else
        {
            System.out.println("Error: Anonymous union");
            System.exit(1);
        }
    }
}
