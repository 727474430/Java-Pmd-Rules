/*
 * SonarQube Java Custom Rules Example
 * Copyright (C) 2016-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package com.raindrop.pmd;

import com.raindrop.pmd.checks.AvoidBrandInMethodNamesRule;
import com.raindrop.pmd.checks.naming.*;
import com.raindrop.pmd.checks.naming.*;
import org.sonar.plugins.java.api.JavaCheck;
import org.sonar.plugins.java.api.JavaFileScanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @name: com.raindrop.pmd.RulesList.java
 * @description: 添加自定义规则列表
 * @author: Raindrop
 * @create Time: 2020/6/3 17:17
 */
public final class RulesList {

    private RulesList() {
    }

    public static List<Class<? extends JavaCheck>> getChecks() {
        List<Class<? extends JavaCheck>> checks = new ArrayList<>();
        checks.addAll(getJavaChecks());
        checks.addAll(getJavaTestChecks());
        return Collections.unmodifiableList(checks);
    }

    public static List<Class<? extends JavaCheck>> getJavaChecks() {
        List<Class<? extends JavaCheck>> checks = new ArrayList<>();
        checks.addAll(defaultChecks());
        checks.addAll(customChecks());
        return Collections.unmodifiableList(checks);
    }

    public static List<Class<? extends JavaCheck>> getJavaTestChecks() {
        return Collections.emptyList();
    }

    private static List<Class<? extends JavaFileScanner>> defaultChecks() {
        return Arrays.asList(
                AvoidBrandInMethodNamesRule.class
        );
    }

    private static List<Class<? extends JavaCheck>> customChecks() {
        return Arrays.asList(
                AbstractClassNamingRule.class,
                ExceptionClassNamingRule.class,
                UpperCameCaseClassNamingRule.class,
                ConstantNamingRule.class,
                TestClassNamingRule.class
        );
    }

}
