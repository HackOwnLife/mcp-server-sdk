# 插件API模块

这是独立的插件开发API模块，供第三方插件开发者使用。

## 构建和安装

```bash
# 构建
mvn clean package

# 安装到本地Maven仓库
mvn clean install

# 生成源码和文档
mvn clean install -DskipTests
```

## 使用

插件开发者在 `pom.xml` 中添加依赖：

```xml
<dependency>
    <groupId>cloud.fesh.msgplatform</groupId>
    <artifactId>msgplatform-plugin-api</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <scope>provided</scope>
</dependency>
```

## 依赖说明

插件API模块依赖以下库（均为provided scope）：

- `spring-context` - Spring上下文（用于ApplicationContext）
- `spring-webmvc` - Spring Web MVC（用于HttpServletRequest和ModelAndView）
- `jakarta.servlet-api` - Jakarta Servlet API
- `jackson-databind` - Jackson JSON处理

这些依赖在运行时由主应用提供，插件开发者只需要在编译时可用即可。

## 目录结构

```
plugin-api/
├── pom.xml
├── src/
│   └── main/
│       └── java/
│           ├── module-info.java
│           └── cloud/
│               └── fesh/
│                   └── plugin/
│                       ├── Plugin.java
│                       ├── RequestHandlerPlugin.java
│                       ├── PluginContext.java
│                       ├── PluginMetadata.java
│                       ├── PluginConfig.java
│                       ├── NullValue.java
│                       └── exception/
│                           ├── PluginLoadException.java
│                           ├── PluginNotFoundException.java
│                           ├── PluginActivationException.java
│                           └── PluginDependencyException.java
└── README.md
```

## 注意事项

- 所有依赖都使用 `provided` scope
- 不包含主应用的实现类
- 只包含插件开发必需的接口和类
- 需要Spring Web MVC（不是Spring Web）以支持ModelAndView
