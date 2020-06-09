package com.raindrop.pmd.checks.naming;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

/**
 * @name: com.raindrop.pmd.checks.naming.ExceptionClassNamingRuleTest.java
 * @description: 异常类命名必须使用 Exception 结尾
 * @author: Raindrop
 * @create Time: 2020/6/3 17:16
 */
public class ExceptionClassNamingRuleTest {

    @Test
    public void testNoEndingException() {
        JavaCheckVerifier.newVerifier()
                .onFile("src/test/files/naming/ExceptionClassNamingCheck.java")
                .withCheck(new ExceptionClassNamingRule())
                .verifyIssues();
    }

}