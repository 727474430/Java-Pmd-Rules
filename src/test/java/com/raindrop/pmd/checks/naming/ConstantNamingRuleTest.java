package com.raindrop.pmd.checks.naming;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class ConstantNamingRuleTest {

    @Test
    public void test() {
        JavaCheckVerifier.newVerifier()
                .onFile("src/test/files/naming/ConstantNamingCheck.java")
                .withCheck(new ConstantNamingRule())
                .verifyIssues();
    }

}