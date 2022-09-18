<template>

    <div>
        <h3>公告标题</h3>
        <el-input />
    </div>
    <br>
    <div>
        <h3>公告内容</h3>
        <el-input type="textarea"/>
    </div>
    <br><br>
    <div>
        <h3>发布</h3>
        <el-form :inline="true">
            <el-form-item label="发布对象">
                <el-select></el-select>
            </el-form-item>
            <el-form-item label="确认发布">
                <el-button type="success">发布</el-button>
            </el-form-item>
        </el-form>
    </div>

</template>

<script setup>

</script>

<style scoped></style>