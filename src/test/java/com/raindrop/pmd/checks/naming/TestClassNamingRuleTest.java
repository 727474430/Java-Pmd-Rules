package com.raindrop.pmd.checks.naming;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

/**
 * @name: com.raindrop.pmd.checks.naming.TestClassNamingRuleTest.java
 * @description: 测试类命名必须是以被测试类名开始，以 Test 结尾
 * @author: Raindrop
 * @create Time: 2020/6/3 17:17
 */
public class TestClassNamingRuleTest {

    @Test
    public void test() {
        JavaCheckVerifier.newVerifier()
                .onFile("src/test/files/naming/TestClassNamingCheck.java")
                .withCheck(new TestClassNamingRule())
                .verifyIssues();
    }

}