create database
    if not exists `build_your_agent`;
use `build_your_agent`;

create table if not exists user
(
    id            bigint comment '用户id' primary key,
    user_account  varchar(256)                         not null comment '账号',
    user_password varchar(512)                         not null comment '密码',
    user_name     varchar(256)                         null comment '用户昵称',
    user_role     varchar(8) default 'user'            not null comment '用户角色：user/admin/ban',
    create_time   datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time   datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
);


# 智能体表
create table if not exists agent
(
    id            bigint comment '智能体id' primary key,
    user_id       bigint comment '用户id'           not null,
    name          varchar(255) comment '智能体名称' not null,
    description   text comment '智能体描述',
    system_prompt text comment '系统提示语'         not null,
    create_time    datetime default CURRENT_TIMESTAMP comment '创建时间',
    update_time    datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间'
);

# 新增智能体关联工具和知识库列表
alter table agent
    add column tool_ids varchar(255) comment '关联的工具id列表',
    add column knowledge_ids varchar(255) comment '关联的知识库id列表';