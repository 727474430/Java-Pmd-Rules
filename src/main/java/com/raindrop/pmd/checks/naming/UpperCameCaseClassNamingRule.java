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
 * @name: com.raindrop.pmd.checks.naming.UpperCameCaseClassNamingRule.java
 * @description: 类名必须遵循驼峰命名规则，领域模型除外
 * @author: Raindrop
 * @create Time: 2020/6/3 17:17
 */
@Rule(key = "UpperCameCaseClassNamingRule")
public class UpperCameCaseClassNamingRule extends BaseTreeVisitor implements JavaFileScanner {

    private JavaFileScannerContext context;
    private static final Pattern PATTERN = Pattern.compile("^[A-Z]?([A-Z][a-z0-9]+)+((A-Z)|(DO|VO|BO|DTO|POJO))?$");

    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
        System.out.println(PrinterVisitor.print(context.getTree()));
    }

    @Override
    public void visitClass(ClassTree tree) {
        String className = tree.simpleName().name();

        if (!PATTERN.matcher(className).matches()) {
            context.reportIssue(this, tree, IssueConstant.UPPER_CAME_CASE_NAMING_ISSUE_MSG);
        }
        super.visitClass(tree);
    }

}
