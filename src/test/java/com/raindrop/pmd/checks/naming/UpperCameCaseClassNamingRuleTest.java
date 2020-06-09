package com.raindrop.pmd.checks.naming;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

/**
 * @name: com.raindrop.pmd.checks.naming.UpperCameCaseClassNamingRuleTest.java
 * @description: 类名必须遵循驼峰命名规则，领域模型除外
 * @author: Raindrop
 * @create Time: 2020/6/3 17:17
 */
public class UpperCameCaseClassNamingRuleTest {

    @Test
    public void test() {
        JavaCheckVerifier.newVerifier()
                .onFile("src/test/files/naming/UpperCameCaseClassNamingCheck.java")
                .withCheck(new UpperCameCaseClassNamingRule())
                .verifyIssues();
    }

}