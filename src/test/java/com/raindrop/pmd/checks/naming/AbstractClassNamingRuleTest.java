package com.raindrop.pmd.checks.naming;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

/**
 * @name: com.raindrop.pmd.checks.naming.AbstractClassNamingRuleTest.java
 * @description: 抽象类命名必须使用 Abstract 或 Base 开头
 * @author: Raindrop
 * @create Time: 2020/6/3 17:16
 */
public class AbstractClassNamingRuleTest {

    @Test
    public void testNoBeginAbstract() {
        JavaCheckVerifier.newVerifier()
                .onFile("src/test/files/naming/AbstractClassNamingCheck.java")
                .withCheck(new AbstractClassNamingRule())
                .verifyIssues();
    }

}
