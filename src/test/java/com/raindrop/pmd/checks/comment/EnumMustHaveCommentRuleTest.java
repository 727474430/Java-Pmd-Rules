package com.raindrop.pmd.checks.comment;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

/**
 * @name: com.raindrop.pmd.checks.comment.EnumMustHaveCommentRuleTest.java
 * @description: 所有的枚举类型字段必须要有注释，说明每个数据项的用途
 * @author: Raindrop
 * @create Time: 2020/6/3 17:17
 */
public class EnumMustHaveCommentRuleTest {

    @Test
    public void test() {
        JavaCheckVerifier.newVerifier()
                .onFile("src/test/files/comment/EnumMustHaveCommentCheck.java")
                .withCheck(new EnumMustHaveCommentRule())
                .verifyIssues();
    }

}