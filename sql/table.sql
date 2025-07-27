create database
    if not exists `build_your_agent`;
use `build_your_agent`;
# 智能体表
create table if not exists agent
(
    id          bigint comment '智能体id' primary key,
    user_id       bigint comment '用户id'           not null,
    name        varchar(255) comment '智能体名称' not null,
    description   text comment '智能体描述',
    system_prompt text comment '系统提示语' not null,
    createTime   datetime     default CURRENT_TIMESTAMP comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间'
);