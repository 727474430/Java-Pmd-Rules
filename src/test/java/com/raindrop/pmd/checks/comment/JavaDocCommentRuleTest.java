package com.raindrop.pmd.checks.comment;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

/**
 * @name: com.raindrop.pmd.checks.comment.JavaDocCommentRuleTest.java
 * @description: 所有的方法都必须添加创建者
 * @author: Raindrop
 * @create Time: 2020/6/3 17:17
 */
public class JavaDocCommentRuleTest {

    @Test
    public void test() {
        JavaCheckVerifier.newVerifier()
                .onFile("src/test/files/comment/JavaDocCommentCheck.java")
                .withCheck(new JavaDocCommentRule())
                .verifyIssues();
    }

}