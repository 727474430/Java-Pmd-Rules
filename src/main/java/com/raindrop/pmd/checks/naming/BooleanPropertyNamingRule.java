package com.raindrop.pmd.checks.naming;

import com.raindrop.pmd.checks.PrinterVisitor;
import com.raindrop.pmd.constant.IssueConstant;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.VariableTree;

import java.util.regex.Pattern;

/**
 * @name: com.raindrop.pmd.checks.naming.UpperCameCaseClassNamingRule.java
 * @description: 布尔类型变量属性命名，不能以 is 开头
 * @author: Raindrop
 * @create Time: 2020/6/3 17:17
 */
@Rule(key = "BooleanPropertyNamingRule")
public class BooleanPropertyNamingRule extends BaseTreeVisitor implements JavaFileScanner {

    private JavaFileScannerContext context;
    public static final Pattern PATTERN = Pattern.compile("^(is)+(.*)");

    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
        System.out.println(PrinterVisitor.print(context.getTree()));
    }


    @Override
    public void visitVariable(VariableTree tree) {
        boolean isBoolean = tree.type().symbolType().isSubtypeOf("boolean");
        if (!isBoolean) {
            super.visitVariable(tree);
            return;
        }

        String propertyName = tree.simpleName().name();
        if (PATTERN.matcher(propertyName).matches()) {
            context.reportIssue(this, tree, IssueConstant.BOOLEAN_PROPERTY_NAMING_ISSUE_MSG);
        }
        super.visitVariable(tree);
    }
}
