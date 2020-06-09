package com.raindrop.pmd.checks.naming;

import com.raindrop.pmd.checks.PrinterVisitor;
import com.raindrop.pmd.constant.IssueConstant;
import org.sonar.check.Rule;
import org.sonar.java.model.PackageUtils;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.*;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @name: com.raindrop.pmd.checks.naming.UpperCameCaseClassNamingRule.java
 * @description: 测试类命名必须是以被测试类名开始，以 Test 结尾
 * @author: Raindrop
 * @create Time: 2020/6/3 17:17
 */
@Rule(key = "TestClassNamingRule")
public class TestClassNamingRule extends BaseTreeVisitor implements JavaFileScanner {

    private JavaFileScannerContext context;
    private static final String TEST_ANNOTATION_NAME = "Test";
    private static final Pattern SUFFIX_TEST_PATTERN = Pattern.compile("^(.*)(Test)$");

    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
        System.out.println(PrinterVisitor.print(context.getTree()));
    }

    @Override
    public void visitClass(ClassTree tree) {
        List<Tree> members = tree.members();
        // if the method contains a @Test annotation
        boolean include = methodContainsTestAnnotation(members);
        if (include) {
            // if the class name ends with Test
            String testClassName = tree.simpleName().name();
            if (!SUFFIX_TEST_PATTERN.matcher(testClassName).matches()) {
                context.reportIssue(this, tree, IssueConstant.TEST_CLASS_NAMING_ISSUE_MSG);
            } else {
                // if test class name start with of the class name under test
                boolean isPresent = classIsPresent(testClassName);
                if (!isPresent) {
                    context.reportIssue(this, tree, IssueConstant.TEST_CLASS_NAMING_ISSUE_MSG);
                }
            }
        }
        super.visitClass(tree);
    }

    /**
     * 是否存在被测试类
     *
     * @param testClassName
     * @return
     */
    private boolean classIsPresent(String testClassName) {
        String packageName = getPackageName();
        String prefixName = testClassName.split("Test")[0];
        String className = packageName + "." + prefixName;
        try {
            Class<?> aClass = Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * 获取包名
     *
     * @return
     */
    private String getPackageName() {
        PackageDeclarationTree packageDeclaration = context.getTree().packageDeclaration();
        String packageName = PackageUtils.packageName(packageDeclaration, File.separator);
        return packageName.replace("/", ".");
    }

    /**
     * 方法是否包含 @Test 注解
     *
     * @param members 方法列表
     * @return
     */
    private boolean methodContainsTestAnnotation(List<Tree> members) {
        for (Tree member : members) {
            if (member.is(Tree.Kind.METHOD)) {
                MethodTree methodTree = (MethodTree) member;
                List<AnnotationTree> annotations = methodTree.modifiers().annotations();
                for (AnnotationTree annotation : annotations) {
                    if (annotation.annotationType().is(Tree.Kind.IDENTIFIER)) {
                        IdentifierTree identifierTree = (IdentifierTree) annotation.annotationType();
                        if (TEST_ANNOTATION_NAME.equals(identifierTree.name())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
