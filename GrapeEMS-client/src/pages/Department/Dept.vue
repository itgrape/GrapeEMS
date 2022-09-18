<template>

    <el-form :inline="true" class="query-form">
        <el-form-item>
            <el-button type="primary">添加部门</el-button>
            <el-button type="danger">删除选中</el-button>
        </el-form-item>
    </el-form>

    <el-table :data="userinfo" border class="user-table">
        <el-table-column type="selection" width="55" />

        <el-table-column prop="deptName" label="部门名称"/>
        <el-table-column prop="deptPersonNumber" label="现有人数"/>
        <el-table-column prop="deptCreateTime" label="成立时间" />

        <el-table-column label="相关操作" width="240">
            <template #default="scope">
                <el-button type="primary">部门重命名</el-button>
                <el-button type="danger">删除部门</el-button>
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
.user-table {
    width: 100%;
}
</style>