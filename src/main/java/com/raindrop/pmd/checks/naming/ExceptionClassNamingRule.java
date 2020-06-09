package com.raindrop.pmd.checks.naming;

import com.raindrop.pmd.checks.PrinterVisitor;
import com.raindrop.pmd.constant.IssueConstant;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @name: com.raindrop.pmd.checks.naming.ExceptionClassNamingRule.java
 * @description: 异常类命名必须使用 Exception 结尾
 * @author: Raindrop
 * @create Time: 2020/6/3 17:17
 */
@Rule(key = "ExceptionClassNamingRule")
public class ExceptionClassNamingRule extends BaseTreeVisitor implements JavaFileScanner {

    private JavaFileScannerContext context;
    private static final String EXCEPTION_NAME = "Exception";
    private static final Pattern PATTERN = Pattern.compile("(Exception|RuntimeException)");

    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
        System.out.println(PrinterVisitor.print(context.getTree()));
    }

    @Override
    public void visitClass(ClassTree tree) {
        if (Objects.isNull(tree.superClass())) {
            super.visitClass(tree);
            return;
        }

        String superClassName = tree.superClass().symbolType().name();
        if (!PATTERN.matcher(superClassName).matches()) {
            super.visitClass(tree);
            return;
        }

        String className = tree.simpleName().name();
        if (!className.endsWith(EXCEPTION_NAME)) {
            context.reportIssue(this, tree, IssueConstant.EXCEPTION_NAMING_ISSUE_MSG);
        }
        super.visitClass(tree);
    }

}
