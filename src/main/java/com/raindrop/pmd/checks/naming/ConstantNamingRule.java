package com.raindrop.pmd.checks.naming;

import com.raindrop.pmd.checks.PrinterVisitor;
import com.raindrop.pmd.constant.IssueConstant;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.VariableTree;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @name: com.raindrop.pmd.checks.naming.UpperCameCaseClassNamingRule.java
 * @description: 常量命名必须全大写，单词之间用下划线隔开
 * @author: Raindrop
 * @create Time: 2020/6/3 17:17
 */
@Rule(key = "ConstantNamingRule")
public class ConstantNamingRule extends BaseTreeVisitor implements JavaFileScanner {

    private JavaFileScannerContext context;
    public static final Set<String> WHITE_LIST = new HashSet<>(Arrays.asList("log", "logger", "serialVersionUID"));

    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
        System.out.println(PrinterVisitor.print(context.getTree()));
    }

    @Override
    public void visitVariable(VariableTree tree) {
        String variableName = tree.simpleName().name();
        // 如果不是常量
        if (!(tree.symbol().isFinal() && tree.symbol().isStatic())) {
            super.visitVariable(tree);
            return;
        }
        // 如果变量是 Log or Logger or serialVersionUID 不检查
        if (WHITE_LIST.contains(variableName)) {
            super.visitVariable(tree);
            return;
        }
        // 常量应该是全大写字符
        if (variableName.equals(variableName.toUpperCase())) {
            // 如果长度大于 12 并且不包含下划线
            if (variableName.length() > 12 && !variableName.contains("_")) {
                context.reportIssue(this, tree, IssueConstant.CONSTANT_NAMING_ISSUE_MSG);
            }
        } else {
            context.reportIssue(this, tree, IssueConstant.CONSTANT_NAMING_ISSUE_MSG);
        }

        super.visitVariable(tree);
    }

}
