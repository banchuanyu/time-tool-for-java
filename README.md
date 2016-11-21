# Shepher
Shepher is a web management tool of Zookeeper. [Readme 中文版](README-zh.md)

## Function comparison of similar products
Product | Introduction | CURD operation of nodes | Snapshot management | Node modified Diff and Review function | Node operated mail notification | CAS and LDAP log | Authority management | Cascade delete | System status monitor
---|---|---|---|---|---|---|---|---|---
Shepher | ZK management | √ | √ | √ | √ | √ | √ |   |  
TaoKeeper | ZK cluster monitor and statement |   |   |   |   |   |   |   | √
Zkdash | ZK management | √ | √ |   |   |   |   | √ |  
Disconf | ZK management | √ | √ |   | √ |   | √ | √ | √
XDiamond | Configuration center | √ |   |   |   | √ | √ |   | √

## Character
- CRUD operation of ZK node
- Snapshot management of ZK node
- Modified Diff and Review function of ZK node
- Mail operation notification of ZK node
- Integrates CAS and LDAP log
- Authority management, reference to [Authority management instruction](Docs/Authority.md)

## Environment requirement
- JDK 1.8
- Maven 3.2 +
- MySQL 5.6

## Configuration and use

### Basic configuration

- Modify `INSERT INTO user VALUES (1,'youradmin',now());` in `db/init.sql`, change `youradmin` into your administrator user name

### Local compiling deployment

1. Import `db/init.sql` in to MySQL
2. Modify parameter configuration of `shepher-web/src/main/resources/application-dev.properties` according to [Parameter instruction](Docs/Parameter.md)
3. Run the script

    ```sh
    $ sh script/dev-build-start.sh
    ```
4. Visit `http://localhost:8089` or self defined service address in a browser

### Docker deployment

MySQL and Zookeeper will be automatic integrated when Docker deployment is used, no self installation is needed, it is generally used for development and testing environment.

1. Install Docker, take Ubuntu system for example, install [docker engine](https://docs.docker.com/engine/installation/#installation) and [docker-compose](https://docs.docker.com/compose/install/)
2. Modify parameter configuration of `shepher-web/src/main/resources/application-docker.properties` according to [Parameter instruction](Docs/Parameter.md)
3. Run the script, waiting start of each container in Docker accomplish

    ```sh
    $ sh script/docker-build-start.sh
    ```
4. Visit `http://localhost:8089` or self defined service address in a browser
