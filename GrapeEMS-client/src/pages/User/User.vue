<template>

    <el-form :inline="true" class="query-form">
        <el-form-item label="姓名">
            <el-input class="name-input" placeholder="支持模糊匹配"></el-input>
        </el-form-item>
        <el-form-item label="性别">
            <el-select class="select-input" placeholder="请选择">
                <el-option>男</el-option>
                <el-option>女</el-option>
                <el-option>非二元性别</el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="年龄">
            <el-input class="age-input"></el-input>
            <span>&nbsp;-&nbsp;</span>
            <el-input class="age-input"></el-input>
        </el-form-item>
        <el-form-item label="部门">
            <el-select class="select-input" placeholder="请选择">
                <el-option>算法部</el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="角色">
            <el-select class="select-input" placeholder="请选择">
                <el-option>总经理</el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="状态">
            <el-select class="select-input" placeholder="请选择">
                <el-option>请假中</el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="家庭住址">
            <el-select class="select-input"></el-select>
            <el-select class="select-input"></el-select>
            <el-select class="select-input"></el-select>
        </el-form-item>
        <el-form-item label="入职时间">
            <el-date-picker type="daterange" unlink-panels range-separator="至"
                            start-placeholder="开始日期"
                            end-placeholder="结束日期"
            />
        </el-form-item>
        <el-form-item>
            <el-button type="primary">查询</el-button>
            <el-button type="danger">重置</el-button>
            <el-button type="primary">添加</el-button>
            <el-button type="danger">删除选中</el-button>
        </el-form-item>
    </el-form>

    <el-table :data="userinfo" border class="user-table">
        <el-table-column type="selection" width="55" />

        <el-table-column prop="name" label="姓名"/>
        <el-table-column prop="sex" label="性别" width="70"/>
        <el-table-column prop="age" label="年龄" width="60" />
        <el-table-column prop="dept" label="部门" />
        <el-table-column prop="role" label="角色" />
        <el-table-column prop="phoneNumber" label="联系方式" width="125" />
        <el-table-column prop="address" label="家庭住址" />
        <el-table-column prop="entryTime" label="入职时间" />
        <el-table-column prop="state" label="状态" />

        <el-table-column label="相关操作" width="120">
            <template #default="scope">
                <el-button type="primary" :icon="Edit" circle />
                <el-button type="danger" :icon="Delete" circle />
            </template>
        </el-table-column>
    </el-table>

</template>

<script setup>
import { Edit, Delete } from '@element-plus/icons-vue'

const userinfo = [
    {
        select: 'select',
        name: 'name',
        sex: '男',
        age: 19,
        dept: '开发部',
        role: '总经理',
        phoneNumber: '18336597584',
        address: '黑龙江省哈尔滨市香坊区',
        entryTime: '2022-09-11',
        state: '工作中',
        option: 'option',
    },{
        select: 'select',
        name: 'name',
        sex: '男',
        age: 19,
        dept: '开发部',
        role: '总经理',
        phoneNumber: '18336597584',
        address: '黑龙江省哈尔滨市香坊区',
        entryTime: '2022-09-11',
        state: '工作中',
        option: 'option',
    },{
        select: 'select',
        name: 'name',
        sex: '男',
        age: 19,
        dept: '开发部',
        role: '总经理',
        phoneNumber: '18336597584',
        address: '黑龙江省哈尔滨市香坊区',
        entryTime: '2022-09-11',
        state: '工作中',
        option: 'option',
    },{
        select: 'select',
        name: 'name',
        sex: '男',
        age: 19,
        dept: '开发部',
        role: '总经理',
        phoneNumber: '18336597584',
        address: '黑龙江省哈尔滨市香坊区',
        entryTime: '2022-09-11',
        state: '工作中',
        option: 'option',
    },
    {
        select: 'select',
        name: 'name',
        sex: '男',
        age: 19,
        dept: '开发部',
        role: '总经理',
        phoneNumber: '18336597584',
        address: '黑龙江省哈尔滨市香坊区',
        entryTime: '2022-09-11',
        state: '工作中',
        option: 'option',
    },{
        select: 'select',
        name: 'name',
        sex: '男',
        age: 19,
        dept: '开发部',
        role: '总经理',
        phoneNumber: '18336597584',
        address: '黑龙江省哈尔滨市香坊区',
        entryTime: '2022-09-11',
        state: '工作中',
        option: 'option',
    },{
        select: 'select',
        name: 'name',
        sex: '男',
        age: 19,
        dept: '开发部',
        role: '总经理',
        phoneNumber: '18336597584',
        address: '黑龙江省哈尔滨市香坊区',
        entryTime: '2022-09-11',
        state: '工作中',
        option: 'option',
    },{
        select: 'select',
        name: 'name',
        sex: '男',
        age: 19,
        dept: '开发部',
        role: '总经理',
        phoneNumber: '18336597584',
        address: '黑龙江省哈尔滨市香坊区',
        entryTime: '2022-09-11',
        state: '工作中',
        option: 'option',
    },
    {
        select: 'select',
        name: 'name',
        sex: '男',
        age: 19,
        dept: '开发部',
        role: '总经理',
        phoneNumber: '18336597584',
        address: '黑龙江省哈尔滨市香坊区',
        entryTime: '2022-09-11',
        state: '工作中',
        option: 'option',
    },{
        select: 'select',
        name: 'name',
        sex: '男',
        age: 19,
        dept: '开发部',
        role: '总经理',
        phoneNumber: '18336597584',
        address: '黑龙江省哈尔滨市香坊区',
        entryTime: '2022-09-11',
        state: '工作中',
        option: 'option',
    },{
        select: 'select',
        name: 'name',
        sex: '男',
        age: 19,
        dept: '开发部',
        role: '总经理',
        phoneNumber: '18336597584',
        address: '黑龙江省哈尔滨市香坊区',
        entryTime: '2022-09-11',
        state: '工作中',
        option: 'option',
    },{
        select: 'select',
        name: 'name',
        sex: '男',
        age: 19,
        dept: '开发部',
        role: '总经理',
        phoneNumber: '18336597584',
        address: '黑龙江省哈尔滨市香坊区',
        entryTime: '2022-09-11',
        state: '工作中',
        option: 'option',
    },
    {
        select: 'select',
        name: 'name',
        sex: '男',
        age: 19,
        dept: '开发部',
        role: '总经理',
        phoneNumber: '18336597584',
        address: '黑龙江省哈尔滨市香坊区',
        entryTime: '2022-09-11',
        state: '工作中',
        option: 'option',
    },{
        select: 'select',
        name: 'name',
        sex: '男',
        age: 19,
        dept: '开发部',
        role: '总经理',
        phoneNumber: '18336597584',
        address: '黑龙江省哈尔滨市香坊区',
        entryTime: '2022-09-11',
        state: '工作中',
        option: 'option',
    },{
        select: 'select',
        name: 'name',
        sex: '男',
        age: 19,
        dept: '开发部',
        role: '总经理',
        phoneNumber: '18336597584',
        address: '黑龙江省哈尔滨市香坊区',
        entryTime: '2022-09-11',
        state: '工作中',
        option: 'option',
    },{
        select: 'select',
        name: 'name',
        sex: '男',
        age: 19,
        dept: '开发部',
        role: '总经理',
        phoneNumber: '18336597584',
        address: '黑龙江省哈尔滨市香坊区',
        entryTime: '2022-09-11',
        state: '工作中',
        option: 'option',
    },
    {
        select: 'select',
        name: 'name',
        sex: '男',
        age: 19,
        dept: '开发部',
        role: '总经理',
        phoneNumber: '18336597584',
        address: '黑龙江省哈尔滨市香坊区',
        entryTime: '2022-09-11',
        state: '工作中',
        option: 'option',
    },{
        select: 'select',
        name: 'name',
        sex: '男',
        age: 19,
        dept: '开发部',
        role: '总经理',
        phoneNumber: '18336597584',
        address: '黑龙江省哈尔滨市香坊区',
        entryTime: '2022-09-11',
        state: '工作中',
        option: 'option',
    },{
        select: 'select',
        name: 'name',
        sex: '男',
        age: 19,
        dept: '开发部',
        role: '总经理',
        phoneNumber: '18336597584',
        address: '黑龙江省哈尔滨市香坊区',
        entryTime: '2022-09-11',
        state: '工作中',
        option: 'option',
    },{
        select: 'select',
        name: 'name',
        sex: '男',
        age: 19,
        dept: '开发部',
        role: '总经理',
        phoneNumber: '18336597584',
        address: '黑龙江省哈尔滨市香坊区',
        entryTime: '2022-09-11',
        state: '工作中',
        option: 'option',
    },
    {
        select: 'select',
        name: 'name',
        sex: '男',
        age: 19,
        dept: '开发部',
        role: '总经理',
        phoneNumber: '18336597584',
        address: '黑龙江省哈尔滨市香坊区',
        entryTime: '2022-09-11',
        state: '工作中',
        option: 'option',
    }
]

</script>

<style scoped>
.query-form {
    height: 105px;
    width: 1270px;
}

.user-table {
    width: 100%;
    height: calc(100vh - 257px);
}

.name-input {
    width: 120px;
}

.select-input {
    width: 120px;
}

.age-input {
    width: 50px;
}

</style>