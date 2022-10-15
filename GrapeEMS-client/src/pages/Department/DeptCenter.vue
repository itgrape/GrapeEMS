<template>

    <el-form :inline="true" class="query-form">
        <el-form-item>
            <el-button type="primary" @click="dialogFormVisible = true">添加部门</el-button>
            <el-dialog v-model="dialogFormVisible" title="添加部门">
                <p style="text-align: center;">成立时间默认为当前创建时间，现有人数默认为0</p>
                <el-form :model="addDeptForm">
                    <el-form-item label="部门名称" :label-width="240">
                        <el-input v-model="addDeptForm.deptName" placeholder="请输入部门名称" autocomplete="off"/>
                    </el-form-item>
                </el-form>
                <template #footer>
                    <span class="dialog-footer">
                        <el-button @click="dialogFormVisible = false">取消</el-button>
                        <el-button type="primary" @click="dialogFormVisible = false; addDeptPost()">添加</el-button>
                    </span>
                </template>
            </el-dialog>

            <el-button type="danger" style="margin-left: 20px" @click="deleteSelectDept()">删除选中</el-button>
        </el-form-item>
    </el-form>

    <el-table :data="deptList" border class="user-table" @selection-change="handleSelectChange">
        <el-table-column type="selection" width="55" />

        <el-table-column prop="deptName" label="部门名称"/>
        <el-table-column prop="deptPersonNumber" label="现有人数"/>
        <el-table-column prop="deptCreateTime" label="成立时间" />

        <el-table-column label="相关操作" width="240">
            <template #default="scope">
                <el-button type="primary" @click="editDialogFormVisible = true; editDept(scope.row)">部门重命名</el-button>
                <el-button type="danger" @click="deleteDept(scope.row)">删除部门</el-button>
            </template>
        </el-table-column>
    </el-table>

    <el-drawer
        ref="drawerRef"
        v-model="editDialogFormVisible"
        title="修改部门名称"
        direction="ttb"
        custom-class="demo-drawer"
    >
        <div class="demo-drawer__content">
            <el-form :model="editDeptForm">
                <el-form-item label="部门名称" :label-width="100">
                    <el-input v-model="editDeptForm.deptName" placeholder="请输入部门名称" autocomplete="off"/>
                </el-form-item>
            </el-form>
            <div style="position: absolute; right: 20px; bottom: 15px;">
                <el-button @click="editDialogFormVisible = false">取消</el-button>
                <el-button type="primary" @click="editDialogFormVisible = false; editDeptPost()">确认修改</el-button>
            </div>
        </div>
    </el-drawer>

</template>

<script setup>
import { Edit, Delete } from '@element-plus/icons-vue'
import {ref, onMounted, reactive} from "vue";
import instance from "../../api/DataAxios";
import axios from "axios";
import {ElMessageBox, ElMessage} from "element-plus";

let deptList = ref([])

onMounted(() => {
    axios.all([getAllDept()])
})

function getAllDept() {
    return instance.get("/dept/getAllDept").then(
        response => {
            for (let r of response.data) {
                let dept = {
                    id: r.deptId,
                    deptName: r.deptName,
                    deptPersonNumber: r.deptPersonNumber,
                    deptCreateTime: new Date(r.deptBuildTime).toLocaleDateString()
                }
                deptList.value.push(dept)
            }
        }
    )
}

function refresh_dept_list() {
    deptList.value.splice(0,deptList.value.length)
    getAllDept()
}


let dialogFormVisible = ref(false)
let addDeptForm = reactive({
    id: null,
    deptName: null,
    deptPersonNumber: null,
    deptCreateTime: null
})
function addDeptPost() {
    instance.get("/dept/addNewDept/" + addDeptForm.deptName).then(
        response => {
            ElMessage.success("添加成功")
            addDeptForm.deptName = null
            refresh_dept_list()
        }
    )
}

let editDialogFormVisible = ref(false)
let editDeptForm = reactive({
    id: null,
    deptName: null,
    deptPersonNumber: null,
    deptCreateTime: null
})
function editDept(row) {
    for (let dept of deptList.value) {
        if (row.id === dept.id) {
            editDeptForm.id = dept.id
            editDeptForm.deptName = dept.deptName
        }
    }
}
function editDeptPost() {
    instance.get("/dept/editDept/" + editDeptForm.id + "/" + editDeptForm.deptName).then(
        response => {
            ElMessage.success("修改成功")
            refresh_dept_list()
        }
    )
}

function deleteDept(row) {
    ElMessageBox.confirm(
        '删除部门后，部门的所有员工将会被转移到默认部门',
        '部门删除提示', {
            confirmButtonText: '确定删除',
            cancelButtonText: '我再想想',
            type: 'warning',
        }
    ).then(() => {
        instance.get("/dept/deleteDept/" + row.id).then(
            response => {
                ElMessage({
                    type: 'success',
                    message: '部门已删除',
                })
                refresh_dept_list()
            }
        )
    }).catch(() => {
        ElMessage({
            type: 'info',
            message: '取消删除',
        })
    })
}

let deleteDeptIdList = ref([])
const handleSelectChange = (depts) => {
    deleteDeptIdList.value = []
    for (let dept of depts) {
        deleteDeptIdList.value.push(dept.id)
    }
}
function deleteSelectDept() {
    for (let id of deleteDeptIdList.value) {
        instance.get("/dept/deleteDept/" + id).then(
            respone => {
                ElMessage({
                    type: 'success',
                    message: '部门已删除',
                })
                refresh_dept_list()
            }
        )
    }
}
</script>

<style scoped>
.user-table {
    width: 100%;
}
</style>