<template>

    <el-form :inline="true" class="query-form">
        <el-form-item label="部门">
            <el-select class="select-input" placeholder="请选择">
                <el-option>算法部</el-option>
            </el-select>
        </el-form-item>

        <el-form-item label="发布时间">
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

    <el-table :data="userinfo" border class="user-table">
        <el-table-column type="selection" width="55" />

        <el-table-column prop="announceTitle" label="公告标题"/>
        <el-table-column prop="announceContent" label="公告内容"/>
        <el-table-column prop="announceTime" label="发布时间"/>

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
    {}, {}, {}, {}, {}, {}, {}, {}, {},
]

</script>

<style scoped>
.user-table {
    width: 100%;
}

.select-input {
    width: 120px;
}

</style>