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
 * @description: 所有的类都必须添加创建者和创建日期
 * @author: Raindrop
 * @create Time: 2020/6/3 17:17
 */
@Rule(key = "ClassMustHaveAuthorRule")
public class ClassMustHaveAuthorRule extends BaseTreeVisitor implements JavaFileScanner {

    private JavaFileScannerContext context;
    public static final Pattern AUTHOR_PATTERN = Pattern.compile(".*(@author).*", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
    public static final Pattern DATE_PATTERN = Pattern.compile(".*(@date).*", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);

    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
        System.out.println(PrinterVisitor.print(context.getTree()));
    }

    @Override
    public void visitClass(ClassTree tree) {
        ModifiersTree modifiers = tree.modifiers();
        for (ModifierTree modifier : modifiers) {
            List<SyntaxTrivia> trivias = modifier.firstToken().trivias();
            for (SyntaxTrivia trivia : trivias) {
                String comment = trivia.comment();
                if (!AUTHOR_PATTERN.matcher(comment).matches() || !DATE_PATTERN.matcher(comment).matches()) {
                    context.reportIssue(this, tree, IssueConstant.CLASS_MUST_HAVE_AUTHOR_ISSUE_MSG);
                }
            }
        }
        super.visitClass(tree);
    }

}
