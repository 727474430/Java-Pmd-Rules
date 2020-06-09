package com.raindrop.pmd.constant;

public class IssueConstant {

    public static final String ABSTRACT_NAMING_ISSUE_MSG = "抽象类命名必须使用 Abstract 或 Base 开头";

    public static final String EXCEPTION_NAMING_ISSUE_MSG = "异常类命名必须使用 Exception 结尾";

    public static final String UPPER_CAME_CASE_NAMING_ISSUE_MSG = "类名必须遵循驼峰命名规则，领域模型除外";

    public static final String CONSTANT_NAMING_ISSUE_MSG = "常量命名必须全大写，单词之间用下划线隔开";

    public static final String TEST_CLASS_NAMING_ISSUE_MSG = "测试类命名必须是以被测试类名开始，以 Test 结尾";

    public static final String BOOLEAN_PROPERTY_NAMING_ISSUE_MSG = "布尔类型变量属性命名，不能以 is 开头";

    public static final String METHOD_MUST_HAVE_AUTHOR_ISSUE_MSG = "所有的方法都必须添加创建者";

}
