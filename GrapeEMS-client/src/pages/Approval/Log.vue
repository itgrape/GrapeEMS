<template>

    <el-form :inline="true" class="query-form">
        <el-form-item label="姓名">
            <el-input class="name-input" placeholder="支持模糊匹配"></el-input>
        </el-form-item>

        <el-form-item label="部门">
            <el-select class="select-input" placeholder="请选择">
                <el-option>算法部</el-option>
            </el-select>
        </el-form-item>

        <el-form-item label="审批时间">
            <el-date-picker type="daterange" unlink-panels range-separator="至"
                            start-placeholder="开始日期"
                            end-placeholder="结束日期"
            />
        </el-form-item>

        <el-form-item>
            <el-button type="primary">查询</el-button>
            <el-button type="danger">重置</el-button>
            <el-button type="danger">删除选中</el-button>
        </el-form-item>
    </el-form>

    <el-table :data="userinfo" class="user-table">
        <el-table-column type="selection" width="55" />

        <el-table-column prop="approvalTime" label="审批时间"/>
        <el-table-column prop="approvalContent" label="审批内容"/>
        <el-table-column prop="approvalResult" label="审批结果"/>
        <el-table-column prop="name" label="姓名" width="120" />
        <el-table-column prop="dept" label="部门" />
        <el-table-column prop="role" label="角色" />
        <el-table-column prop="phoneNumber" label="联系方式" width="140" />


        <el-table-column label="操作" width="120" fixed="right">
            <template #default="scope">
                <el-button type="danger" :icon="Delete" circle />
            </template>
        </el-table-column>
    </el-table>

</template>

<script setup>
import { Edit, Delete } from '@element-plus/icons-vue'
import LeaveApplication from '../../components/LeaveApplication.vue'
import {onMounted, ref} from "vue";
import instance from "../../api/DataAxios";
import axios from 'axios'
import {ElMessage} from "element-plus";

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
    }
]

onMounted(() => {
    axios.all([])
})

</script>

<style scoped>
.user-table {
    width: 100%;
    height: calc(100vh - 285px);
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