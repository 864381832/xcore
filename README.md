封装java开发过程中常用的工具类。

**注意：**

- 因为代码变动频繁，建议自行下载源码，用 `mvn install` 命令安装，或者
- 从 [dist]() 目录下载临时打包版本，或者
- 添加 gitee 的 maven 托管库依赖

> 因为 "xcore" 是 gitee 的敏感词，因此无法将打包上传到 release 页面。带来不便敬请谅解。

## gitee 的 maven 托管库：

在 `pom.xml` 中加入下面内容可从 gitee 的托管库中下载依赖

```
<repositories>
    <repository>
        <id>xwintop-maven</id>
        <url>https://xwintop.gitee.io/maven/repository</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.xwintop</groupId>
        <artifactId>xcore</artifactId>
        <version>0.0.3-SNAPSHOT</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

git 托管 maven可参考教程(若无法下载请拉取项目自行编译)。[教程地址：点击进入](http://blog.csdn.net/u011747754/article/details/78574026)
