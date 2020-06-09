package com.raindrop.pmd.checks.comment;


import com.raindrop.pmd.checks.PrinterVisitor;
import com.raindrop.pmd.constant.IssueConstant;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.*;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @name: com.raindrop.pmd.checks.comment.ClassMustHaveAuthorRule.java
 * @description: 类、类属性、类方法的注释必须使用 Javadoc 规范，使用\/**内容*\/格式，不得使用 xxx 方式
 * @author: Raindrop
 * @create Time: 2020/6/3 17:17
 */
@Rule(key = "JavaDocCommentRule")
public class JavaDocCommentRule extends BaseTreeVisitor implements JavaFileScanner {

    private JavaFileScannerContext context;
    public static final Pattern PATTERN = Pattern.compile("^(\\/\\*\\*).*(\\*\\/)$", Pattern.DOTALL);

    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
        System.out.println(PrinterVisitor.print(context.getTree()));
    }

    @Override
    public void visitClass(ClassTree tree) {
        ModifiersTree modifiers = tree.modifiers();
        List<SyntaxTrivia> trivias = modifiers.firstToken().trivias();
        for (SyntaxTrivia trivia : trivias) {
            String comment = trivia.comment();
            if (!PATTERN.matcher(comment).matches()) {
                context.reportIssue(this, tree, IssueConstant.CLASS_MUST_HAVE_AUTHOR_ISSUE_MSG);
            }
        }
        super.visitClass(tree);
    }

    @Override
    public void visitMethod(MethodTree tree) {
        ModifiersTree modifiers = tree.modifiers();
        List<SyntaxTrivia> trivias = modifiers.firstToken().trivias();
        for (SyntaxTrivia trivia : trivias) {
            String comment = trivia.comment();
            if (!PATTERN.matcher(comment).matches()) {
                context.reportIssue(this, tree, IssueConstant.CLASS_MUST_HAVE_AUTHOR_ISSUE_MSG);
            }
        }
        super.visitMethod(tree);
    }

    @Override
    public void visitVariable(VariableTree tree) {
        ModifiersTree modifiers = tree.modifiers();
        List<SyntaxTrivia> trivias = modifiers.firstToken().trivias();
        for (SyntaxTrivia trivia : trivias) {
            String comment = trivia.comment();
            if (!PATTERN.matcher(comment).matches()) {
                context.reportIssue(this, tree, IssueConstant.CLASS_MUST_HAVE_AUTHOR_ISSUE_MSG);
            }
        }
        super.visitVariable(tree);
    }

}
