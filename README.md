<center><p style="font-size: 24px;"> 基于 Sonar 的 Java-Pmd 自定义规则项目 </p></center>

---

##### 环境

* JDK 1.8
* Maven 3.6


##### 使用方式

* 进入项目路径，使用 maven 打包

```shell
mvn clean package -DskipTests
```

* 将打包出来的 jar 包放入 sonarqube 服务的 extensions/plugins 目录下，重启 sonarqube 服务


##### 目前实现规则

&nbsp;&nbsp;实现规则基于 Alibaba P3C 开发手册为准

* 命名风格
> 1. 抽象类命名必须使用 Abstract 或 Base 开头;
> 2. 布尔类型变量属性命名，不能以 is 开头;
> 3. 常量命名必须全大写，单词之间用下划线隔开;
> 4. 异常类命名必须使用 Exception 结尾;
> 5. 测试类命名必须是以被测试类名开始，以 Test 结尾;
> 6. 类名必须遵循驼峰命名规则，领域模型除外;


##### 引用

> [Java 规则示例文档](https://github.com/SonarSource/sonar-java/blob/master/docs/CUSTOM_RULES_101.md)
>
> [Java 开发示例项目](https://github.com/SonarSource/sonar-custom-rules-examples/tree/master/java-custom-rules)
