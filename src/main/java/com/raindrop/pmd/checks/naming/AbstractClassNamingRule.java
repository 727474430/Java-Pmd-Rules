package com.raindrop.pmd.checks.naming;

import com.raindrop.pmd.checks.PrinterVisitor;
import com.raindrop.pmd.constant.IssueConstant;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;

import java.util.regex.Pattern;

/**
 * @name: com.raindrop.pmd.checks.naming.AbstractClassNamingRule.java
 * @description: 抽象类命名必须使用 Abstract 或 Base 开头
 * @author: Raindrop
 * @create Time: 2020/6/3 17:17
 */
@Rule(key = "AbstractClassNamingRule")
public class AbstractClassNamingRule extends BaseTreeVisitor implements JavaFileScanner {

    private JavaFileScannerContext context;
    private static final Pattern PATTERN = Pattern.compile("^(Abstract|Base).*");

    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
        System.out.println(PrinterVisitor.print(context.getTree()));
    }

    @Override
    public void visitClass(ClassTree tree) {
        String className = tree.simpleName().name();
        if (!tree.symbol().isAbstract()) {
            super.visitClass(tree);
            return;
        }

        if (!PATTERN.matcher(className).matches()) {
            context.reportIssue(this, tree, IssueConstant.ABSTRACT_NAMING_ISSUE_MSG);
        }
        super.visitClass(tree);
    }

}
