package com.raindrop.pmd.checks.naming;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class BooleanPropertyNamingRuleTest {


    @Test
    public void test() {
        JavaCheckVerifier.newVerifier()
                .onFile("src/test/files/naming/BooleanPropertyNamingCheck.java")
                .withCheck(new BooleanPropertyNamingRule())
                .verifyIssues();
    }

}