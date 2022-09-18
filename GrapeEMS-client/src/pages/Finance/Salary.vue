<template>

    <el-form :inline="true" class="query-form">
        <el-form-item label="年月">
            <el-date-picker placeholder="选择一个月份" type="month" />
        </el-form-item>
        <el-form-item label="姓名">
            <el-input class="name-input" placeholder="支持模糊匹配"></el-input>
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
        <el-form-item label="理论工资">
            <el-input class="number-input"></el-input>
            <span>&nbsp;-&nbsp;</span>
            <el-input class="number-input"></el-input>
        </el-form-item>
        <el-form-item label="总工资">
            <el-input class="number-input"></el-input>
            <span>&nbsp;-&nbsp;</span>
            <el-input class="number-input"></el-input>
        </el-form-item>

        <el-form-item>
            <el-button type="primary">查询</el-button>
            <el-button type="danger">重置</el-button>
            <el-button type="primary">添加</el-button>
            <el-button type="danger">删除选中</el-button>
        </el-form-item>
    </el-form>

    <el-table :data="salaryinfo" border class="salary-table">
        <el-table-column type="selection" width="55" />

        <el-table-column prop="yearAndMonth" label="年月" width="100" />
        <el-table-column prop="name" label="姓名"/>
        <el-table-column prop="dept" label="部门" />
        <el-table-column prop="role" label="角色" />
        <el-table-column prop="phoneNumber" label="联系方式" width="125" />
        <el-table-column prop="curSalary" label="理论工资" width="110" />
        <el-table-column prop="moreWorkSalary" label="加班补贴" width="110" />
        <el-table-column prop="subsidySalary" label="贫困补贴" width="110" />
        <el-table-column prop="socialSalary" label="社保/公积金" width="110" />
        <el-table-column prop="leaveSalary" label="请假扣款" width="110" />
        <el-table-column prop="otherSalary" label="其他扣款" width="110" />
        <el-table-column prop="totalSalary" label="总工资（税前）" width="130" />

        <el-table-column label="操作" width="120" fixed="right">
            <template #default="scope">
                <el-button type="primary" :icon="Edit" circle />
                <el-button type="danger" :icon="Delete" circle />
            </template>
        </el-table-column>
    </el-table>

</template>

<script setup>
import { Edit, Delete } from '@element-plus/icons-vue'

const salaryinfo = [
    {
        yearAndMonth: "2022-05",
        name: "张三",
        dept: "开发部",
        role: "总经理",
        phoneNumber: "18336597584",
        curSalary: 15000,
        moreWorkSalary: 100,
        subsidySalary: 200,
        socialSalary: 100,
        leaveSalary: 300,
        otherSalary: 10000,
        totalSalary: 20000,
    }
]

</script>

<style scoped>
.query-form {
    height: 105px;
    width: 1270px;
}

.salary-table {
    width: 100%;
    height: calc(100vh - 257px);
}

.name-input {
    width: 120px;
}

.select-input {
    width: 120px;
}

.number-input {
    width: 50px;
}

</style>