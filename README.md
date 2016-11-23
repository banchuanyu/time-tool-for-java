# Shepher

Shepher 是一款 Zookeeper 的 web 管理工具。[Readme in English](README.md)

## 特性
- ZK 节点的 CRUD 操作
- ZK 节点的快照管理
- ZK 节点修改的 Diff 和 Review 功能
- ZK 节点操作邮件通知
- 集成 CAS 和 LDAP 登录
- 权限管理，参照 [权限管理说明](Docs/Authority-zh.md)

## 同类产品功能对比

产品 | 简介 | 节点的CURD 操作 | 快照管理 | 节点修改的 Diff 和 Review 功能 | 节点操作邮件通知 | CAS 和 LDAP 登录 | 权限管理 | 级联删除 | 系统状态监控
---|---|---|---|---|---|---|---|---|---
Shepher | ZK 管理 | √ | √ | √ | √ | √ | √ |   |  
TaoKeeper | ZK 集群监控与报表 |   |   |   |   |   |   |   | √
Zkdash | ZK 管理 | √ | √ |   |   |   |   | √ |  
Disconf | ZK 管理 | √ | √ |   | √ |   | √ | √ | √
XDiamond | 配置中心 | √ |   |   |   | √ | √ |   | √

## 系统截图
- 首页
![Home](home.png)

- 节点编辑
![Node view](node-view.png)

## 安装

### 环境要求
- JDK 1.8
- Maven 3.2 +
- MySQL 5.6

### 基本配置

- 修改 `db/init.sql` 中的 `INSERT INTO user VALUES (1,'youradmin',now());` ，将 `youradmin` 改为你的管理员用户名
- 参照 [参数说明](Docs/Parameter-zh.md)，设置 `shepher-web/src/main/resources` 目录下的参数配置。如果使用 CAS 登录，则需要修改 CAS 相关的配置，并且将 `server.login.type` 设置为 `CAS`；如果使用 LDAP 登录，则需要修改 LDAP 相关的配置，并且将 `server.login.type` 设置为 `LDAP`

### 开发环境部署

开发环境部署包括本地编译部署和 Docker 部署两种方式，用户可以根据使用习惯选择一种部署方式。

#### 本地编译部署

1. 将 `db/init.sql` 导入到 MySQL
2. 参照 [参数说明](Docs/Parameter-zh.md) 修改 `shepher-web/src/main/resources/application-dev.properties` 的参数配置
3. 运行脚本

    ```sh
    $ sh script/dev-build-start.sh
    ```
4. 在浏览器中访问 `http://localhost:8089` 或自定义的 `server.url` （参照 [参数说明](Docs/Parameter-zh.md)）

#### Docker 部署

使用 Docker 部署则自动集成 MySQL 和 Zookeeper，并且自动将 `db/init.sql` 导入MySQL中，不需要自行安装，一般用在开发测试环境。

1. 安装 Docker，以 Ubuntu 系统为例，安装 [docker engine](https://docs.docker.com/engine/installation/#installation) 和 [docker-compose](https://docs.docker.com/compose/install/)
2. 参照 [参数说明](Docs/Parameter-zh.md) 修改 `shepher-web/src/main/resources/application-docker.properties` 的参数配置
3. 运行脚本，并等待 Docker 中的各个容器启动完成

    ```sh
    $ sh script/docker-build-start.sh
    ```
4. 在浏览器中访问 `http://localhost:8089` 或自定义的 `server.url` （参照 [参数说明](Docs/Parameter-zh.md)）

### 生产环境部署

生产环境部署的步骤跟本地编译部署类似，主要注意对数据源、CAS/LDAP 以及域名的设置。另外，由于各个公司内部的邮件服务较为封闭，在线上使用中需要自己实现 `CustomMailSender` 类，并在 `shepher-web/src/main/resources/application.properties` 中设置 `mail.sender=customMailSender`，以便 Shepher 服务可以正常使用邮件服务。

1. 将 `db/init.sql` 导入到 MySQL
2. 创建 `shepher-web/src/main/resources/application-online.properties` 文件，参照 [参数说明](Docs/Parameter-zh.md) 添加和修改配置
3. 在 Shepher 根目录下运行命令

    ```sh
    $ mvn clean package
    ```
4. 将 `shepher-web/target/shepher-1.0.jar` 拷贝到线上，然后运行命令

    ```sh
    $ java -jar shepher-1.0.jar --spring.profiles.active=online
    ```
4. 在浏览器中访问自定义的 `server.url` （参照 [参数说明](Docs/Parameter-zh.md)）
