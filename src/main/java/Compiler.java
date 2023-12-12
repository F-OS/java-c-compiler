import AST1stPass.ASTNode;
import antlr4.CParserLexer;
import antlr4.CParserParser;
import org.antlr.v4.runtime.*;
import visitor.ASTPrinter;
import visitor.CVisitor;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;
class ErrorListener extends BaseErrorListener {
    public static final ErrorListener INSTANCE = new ErrorListener();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
            String msg, RecognitionException e) {
        List<String> stack = ((Parser) recognizer).getRuleInvocationStack();
        Collections.reverse(stack);
        System.err.println("rule stack: " + stack);
        System.err.println("line " + line + ":" + charPositionInLine + " at " + offendingSymbol + ": " + msg);
    }
}

public class Compiler {
    public static void main(String[] args) throws IOException {
        var input = Files.readAllLines(Paths.get(args[0])).stream().reduce("", (a, b) -> a + b + "\n");
        var str = new ANTLRInputStream(input);
        var lexer = new CParserLexer(str);
        var tokens = new CommonTokenStream(lexer);
        var parser = new CParserParser(tokens);
        var listener = new ErrorListener();
        parser.addErrorListener(listener);
        var tree = parser.translationUnit();
        var visitor = new CVisitor();
        ASTNode ast = visitor.visit(tree);
        ASTPrinter printer = new ASTPrinter();
        System.out.println(ast.accept(printer));
    }

}
