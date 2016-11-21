# Shepher
Shepher 是一款 Zookeeper 的 web 管理工具。

## 链接
- [Readme in English](README.md)
- [同类产品功能对比](Docs/Similar-zh.md)

## 特性
- ZK 节点的 CRUD 操作
- ZK 节点的快照管理
- ZK 节点修改的 Diff 和 Review 功能
- ZK 节点操作邮件通知
- 集成 CAS 和 LDAP 登录
- 权限管理，参照 [权限管理说明](Docs/Authority-zh.md)

## 环境要求
- JDK 1.8
- Maven 3.2 +
- MySQL 5.6

## 配置和使用

#### 基本配置

- 修改 `db/init.sql` 中的 `INSERT INTO user VALUES (1,'youradmin',now());` ，将 `youradmin` 改为你的管理员用户名

#### 本地编译部署

1. 将 `db/init.sql` 导入到 MySQL
2. 参照 [参数说明](Docs/Parameter-zh.md) 修改 `shepher-web/src/main/resources/application-dev.properties` 的参数配置
3. 运行脚本

    ```sh
    $sh script/dev-build-start.sh
    ```
4. 在浏览器中访问 `http://localhost:8089` 或自定义的服务地址

#### Docker 部署

使用 Docker 部署则自动集成 MySQL 和 Zookeeper，不需要再自行安装，一般用在开发测试环境。

1. 安装 Docker，以 Ubuntu 系统为例，安装 [docker engine](https://docs.docker.com/engine/installation/#installation) 和 [docker-compose](https://docs.docker.com/compose/install/)
2. 参照 [参数说明](Docs/Parameter-zh.md) 修改 `shepher-web/src/main/resources/application-docker.properties` 的参数配置
3. 运行脚本，并等待 Docker 中的各个容器启动完成

    ```sh
    $sh script/docker-build-start.sh
    ```
4. 在浏览器中访问 `http://localhost:8089` 或自定义的服务地址
