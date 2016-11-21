# 权限管理说明

## 设计思路

与常见的管理员统一管理所有权限不同，本项目将权限以小组为单位授权，采用管理员管理小组，小组的组长管理组内成员的方式。这种分级的权限管理模式大大减少了管理员的审核负担，组内自治也增加了灵活性。

权限管理结构图：
其中，zookeeper的权限以节点为单位，小组向管理员申请权限，由超级管理员负责审核和授权。小组成员由用户组成，用户可以选择创建新的小组或者加入已有的小组。

![权限管理结构图](../raw/master/Docs/images/permission-design.png)

## 功能说明

### 超级管理员

超级管理员都属于一个特殊的小组 -- admin，需要负责如下管理：

- 审核小组的权限申请（同意/拒绝）
- 给小组授予某个节点的权限

### 权限

权限是以节点为粒度划分的，是以小组为单位下放的。

- 节点权限：每个节点由集群和路径唯一确定，拥有父节点权限即拥有该节点的子节点权限

### 小组

#### 小组角色

小组成员分为不同的角色，目前有三种角色：

- master：小组的管理者。可以管理组内成员，向管理员申请权限；拥有节点的增、删、改权限
- developer：开发者。拥有节点的增改权限，没有删除权限
- member：暂时同developer

一个小组可以拥有多个节点的权限，同一小组的所有成员共享节点权限。
小组成员均可增改该小组拥有权限的节点，但只有小组的 master 能够删除节点。

#### 小组管理

- 处理用户的入组申请
- 添加/删除小组成员
- 更改小组成员的角色
- 申请新的节点权限


### 用户

用户在操作节点前需要申请权限，主要有两种方式：

- 新建小组：用户在新建小组的时候即会对当前节点发出权限申请，待管理员审核通过即可
- 申请入组：用户申请加入拥有某个节点权限的任意小组，等待小组 master 同意即可

## 使用方法

本部分主要介绍系统功能入口，并附上效果截图。

域名和端口号为 serverName:serverPort，下文只给出相对链接。

### 超级管理员

- 集群管理： `/admin`

- 权限管理： `/permission`

### 小组管理

- 该用户所属小组列表： `/teams`

- 小组成员管理： `/teams/{teamId}/manage`

- 小组权限管理： `/teams/{teamId}/permission`


### 用户申请

- 用户申请权限： `/teams/apply?path=/zookeeper&cluster=local_test`
