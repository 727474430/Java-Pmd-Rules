package com.raindrop.pmd.checks.comment;

import com.raindrop.pmd.checks.PrinterVisitor;
import com.raindrop.pmd.constant.IssueConstant;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.EnumConstantTree;
import org.sonar.plugins.java.api.tree.SyntaxTrivia;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @name: com.raindrop.pmd.checks.comment.EnumMustHaveCommentRule.java
 * @description: 所有的枚举类型字段必须要有注释，说明每个数据项的用途
 * @author: Raindrop
 * @create Time: 2020/6/3 17:17
 */
@Rule(key = "EnumMustHaveCommentRule")
public class EnumMustHaveCommentRule extends BaseTreeVisitor implements JavaFileScanner {

    private JavaFileScannerContext context;
    public static final Pattern PATTERN = Pattern.compile("^(\\/\\*\\*).*(\\*\\/)$", Pattern.DOTALL);

    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
        System.out.println(PrinterVisitor.print(context.getTree()));
    }

    @Override
    public void visitEnumConstant(EnumConstantTree tree) {
        List<SyntaxTrivia> trivias = tree.initializer().firstToken().trivias();
        if (trivias.isEmpty()) {
            context.reportIssue(this, tree, IssueConstant.ENUM_MUST_HAVE_AUTHOR_ISSUE_MSG);
            super.visitEnumConstant(tree);
            return;
        }

        for (SyntaxTrivia trivia : trivias) {
            String comment = trivia.comment();
            if (!PATTERN.matcher(comment).matches()) {
                context.reportIssue(this, tree, IssueConstant.ENUM_MUST_HAVE_AUTHOR_ISSUE_MSG);
            }
        }
        super.visitEnumConstant(tree);
    }

}
