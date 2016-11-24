# Shepher

Shepher is a management tool of Zookeeper. In Xiaomi, we use it as the configuration management center. [Readme 中文版](README-zh.md)

## Character
- CRUD operation of ZK node
- Snapshot management of ZK node
- Modified Diff and Review function of ZK node
- Mail operation notification of ZK node
- Integrates CAS and LDAP log
- Authority management, reference to [Authority management instruction](Docs/Authority.md)

## Function comparison of similar products
Product | Introduction | CURD operation of nodes | Snapshot management | Node modified Diff and Review function | Node operated mail notification | CAS and LDAP log | Authority management | Cascade delete | System status monitor
---|---|---|---|---|---|---|---|---|---
Shepher | ZK management | √ | √ | √ | √ | √ | √ |   |  
TaoKeeper | ZK cluster monitor and statement |   |   |   |   |   |   |   | √
Zkdash | ZK management | √ | √ |   |   |   |   | √ |  
Disconf | ZK management | √ | √ |   | √ |   | √ | √ | √
XDiamond | Configuration center | √ |   |   |   | √ | √ |   | √

## Screenshots
- Home
![Home](../raw/master/Docs/images/home.png)

- Node view
![Node view](../raw/master/Docs/images/node-view.png)

## Installation

### Environment requirements
- JDK 1.8
- Maven 3.2 +
- MySQL 5.6

### Basic configuration

- Modify `INSERT INTO user VALUES (1,'youradmin',now());` in `db/init.sql`, change `youradmin` into your administrator user name
- According to [Parameter instruction](Docs/Parameter.md), modify parameter configuration under the directory `shepher-web/src/main/resources`. If you are using a CAS login, you need to modify the CAS-related configuration and set the `server.login.type` to `CAS`; If you log in with LDAP, you will need to modify the LDAP-related configuration and set `server.login.type` to `LDAP`

### Development environment deployment

Development environment deployment includes local compilation deployment and Docker deployment, you can choose a deployment based on usage.

#### Local compiling deployment

1. Import `db/init.sql` in to MySQL
2. Modify parameter configuration of `shepher-web/src/main/resources/application-dev.properties` according to [Parameter instruction](Docs/Parameter.md)
3. Run the script

    ```sh
    $ sh script/dev-build-start.sh
    ```
4. Visit `http://localhost:8089` or self defined `server.url` (Reference to [Parameter instruction](Docs/Parameter.md))

#### Docker deployment

MySQL and Zookeeper will be automatic integrated when Docker deployment is used, And automatically import `db/init.sql` into MySQL, no self installation is needed.

1. Install Docker, take Ubuntu system for example, install [docker engine](https://docs.docker.com/engine/installation/#installation) and [docker-compose](https://docs.docker.com/compose/install/)
2. Modify parameter configuration of `shepher-web/src/main/resources/application-docker.properties` according to [Parameter instruction](Docs/Parameter.md)
3. Run the script, waiting start of each container in Docker accomplish

    ```sh
    $ sh script/docker-build-start.sh
    ```
4. Visit `http://localhost:8089` or self defined `server.url` (Reference to [Parameter instruction](Docs/Parameter.md))

### Production environment deployment

The steps for production environment deployment is similar to local compiling deployment, but pay attention to the setting of data sources, CAS/LDAP and domain names. In addition, because each company's internal mail service is relatively closed, you need to implement your own `CustomMailSender` class, and set `mail.sender=customMailSender` in` shepher-web/src/main/resources/application.properties`, so that Shepher can use the mail service normally.

1. Import `db/init.sql` into MySQL
2. Create the file `shepher-web/src/main/resources/application-online.properties`, and modify the configuration according to [Parameter instruction](Docs/Parameter.md)
3. Run the command in the Shepher root directory

    ```sh
    $ mvn clean package
    ```
4. Copy `shepher-web/target/shepher-1.0.jar` to the production environment, and run the command

    ```sh
    $ java -jar shepher-1.0.jar --spring.profiles.active=online
    ```
4. Visit self defined `server.url` (Reference to [Parameter instruction](Docs/Parameter.md))
