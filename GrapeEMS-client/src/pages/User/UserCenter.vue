<template>

    <el-form :model="queryUser" :inline="true" class="query-form" id="my-form">
        <el-form-item label="姓名">
            <el-input v-model="queryUser.userName" class="name-input" placeholder="支持模糊匹配"></el-input>
        </el-form-item>
        <el-form-item label="性别">
            <el-select v-model="queryUser.userSex" class="select-input" placeholder="请选择">
                <el-option v-for="sex in sexs" :value="sex">{{ sex }}</el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="年龄">
            <el-input v-model="queryUser.userAgeStart" class="age-input"></el-input>
            <span>&nbsp;-&nbsp;</span>
            <el-input v-model="queryUser.userAgeEnd" class="age-input"></el-input>
        </el-form-item>
        <el-form-item label="部门">
            <el-select v-model="queryUser.deptName" class="select-input" placeholder="请选择">
                <el-option v-for="dept in depts" :value="dept">{{ dept }}</el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="角色">
            <el-select v-model="queryUser.roleName" class="select-input" placeholder="请选择">
                <el-option v-for="role in roles" :value="role">{{ role }}</el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="状态">
            <el-select v-model="queryUser.userState" class="select-input" placeholder="请选择">
                <el-option v-for="state in states" :value="state">{{ state }}</el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="家庭住址">
            <el-input v-model="queryUser.userProvince" class="address-input" placeholder="省"></el-input>
            <el-input v-model="queryUser.userCity" class="address-input" placeholder="市"></el-input>
            <el-input v-model="queryUser.userCommunity" class="address-input" placeholder="县 / 区"></el-input>
        </el-form-item>
        <el-form-item label="入职时间">
            <el-date-picker type="daterange"
                            unlink-panels
                            range-separator="至"
                            start-placeholder="开始日期"
                            end-placeholder="结束日期"
                            v-model="userInter"
            />
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="query()">查询</el-button>
            <el-button type="danger" @click="resetForm()">重置</el-button>
            <el-button type="primary" @click="dialogFormVisible = true">添加员工</el-button>

            <el-dialog v-model="dialogFormVisible" title="添加员工">
                <el-form :model="addUserForm" id="add-user-form">
                    <el-form-item label="姓名" :label-width="formLabelWidth">
                        <el-input v-model="addUserForm.userName" placeholder="请输入员工姓名" autocomplete="off"/>
                    </el-form-item>
                    <el-form-item label="性别" :label-width="formLabelWidth">
                        <el-select v-model="addUserForm.userSex" placeholder="请选择员工性别">
                            <el-option v-for="sex in sexs" :value="sex">{{ sex }}</el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="年龄" :label-width="formLabelWidth">
                        <el-input v-model="addUserForm.userAge" placeholder="请输入员工年龄" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="邮箱" :label-width="formLabelWidth">
                        <el-input v-model="addUserForm.userEmail" placeholder="请输入员工邮箱" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" :label-width="formLabelWidth">
                        <el-input v-model="addUserForm.userPassword" placeholder="请输入员工密码" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="部门" :label-width="formLabelWidth">
                        <el-select v-model="addUserForm.deptName" placeholder="请选择">
                            <el-option v-for="dept in depts" :value="dept">{{ dept }}</el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="角色" :label-width="formLabelWidth">
                        <el-select v-model="addUserForm.roleName" placeholder="请选择">
                            <el-option v-for="role in roles" :value="role">{{ role }}</el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="状态" :label-width="formLabelWidth">
                        <el-select v-model="addUserForm.userState" placeholder="请选择">
                            <el-option v-for="state in states" :value="state">{{ state }}</el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="家庭住址" :label-width="formLabelWidth">
                        <el-input v-model="addUserForm.userProvince" placeholder="省"></el-input>
                        <el-input v-model="addUserForm.userCity" placeholder="市"></el-input>
                        <el-input v-model="addUserForm.userCommunity" placeholder="县 / 区"></el-input>
                    </el-form-item>
                    <el-form-item label="入职时间" :label-width="formLabelWidth">
                        <el-date-picker type="date"
                                        placeholder="请选择入职时间"
                                        v-model="addUserForm.userInterTime"
                        />
                    </el-form-item>
                </el-form>
                <template #footer>
                    <span class="dialog-footer">
                        <el-button @click="dialogFormVisible = false">取消</el-button>
                        <el-button type="primary" @click="dialogFormVisible = false; addUserPost()">添加</el-button>
                    </span>
                </template>
            </el-dialog>

            <el-button type="danger" style="margin-left: 12px;" @click="deleteSelectUser()">删除选中员工</el-button>
        </el-form-item>
    </el-form>

    <el-table :data="userinfo" border class="user-table" @selection-change="handleSelectChange">
        <el-table-column type="selection" width="55"/>

        <el-table-column prop="userName" label="姓名"/>
        <el-table-column prop="userSex" label="性别" width="70"/>
        <el-table-column prop="userAge" label="年龄" width="60"/>
        <el-table-column prop="deptName" label="部门"/>
        <el-table-column prop="roleName" label="角色"/>
        <el-table-column prop="userEmail" label="联系方式" width="200"/>
        <el-table-column prop="userAddress" label="家庭住址" width="170"/>
        <el-table-column prop="userInterTime" label="入职时间" width="160"/>
        <el-table-column prop="userState" label="状态"/>

        <el-table-column label="相关操作" width="120" fixed="right">
            <template #default="scope">
                <el-button @click="editDialogFormVisible = true; editUser(scope.row)" type="primary" :icon="Edit" circle/>
                <el-button @click="deleteUser(scope.row)" type="danger" :icon="Delete" circle/>
            </template>
        </el-table-column>
    </el-table>

    <el-drawer
        ref="drawerRef"
        v-model="editDialogFormVisible"
        title="修改员工信息"
        direction="rtl"
        custom-class="demo-drawer"
    >
        <div class="demo-drawer__content">
            <el-form :model="editUserForm">
                <el-form-item label="姓名" :label-width="formLabelWidth">
                    <el-input v-model="editUserForm.userName" placeholder="请输入员工姓名"/>
                </el-form-item>
                <el-form-item label="性别" :label-width="formLabelWidth">
                    <el-select v-model="editUserForm.userSex" placeholder="请选择员工性别">
                        <el-option v-for="sex in sexs" :value="sex">{{ sex }}</el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="年龄" :label-width="formLabelWidth">
                    <el-input v-model="editUserForm.userAge" placeholder="请输入员工年龄"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" :label-width="formLabelWidth">
                    <el-input v-model="editUserForm.userEmail" placeholder="请输入员工邮箱"></el-input>
                </el-form-item>
                <el-form-item label="部门" :label-width="formLabelWidth">
                    <el-select v-model="editUserForm.deptName" placeholder="请选择">
                        <el-option v-for="dept in depts" :value="dept">{{ dept }}</el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="角色" :label-width="formLabelWidth">
                    <el-select v-model="editUserForm.roleName" placeholder="请选择">
                        <el-option v-for="role in roles" :value="role">{{ role }}</el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="状态" :label-width="formLabelWidth">
                    <el-select v-model="editUserForm.userState" placeholder="请选择">
                        <el-option v-for="state in states" :value="state">{{ state }}</el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="家庭住址" :label-width="formLabelWidth">
                    <el-input v-model="editUserForm.userProvince" placeholder="省"></el-input>
                    <el-input v-model="editUserForm.userCity" placeholder="市"></el-input>
                    <el-input v-model="editUserForm.userCommunity" placeholder="县 / 区"></el-input>
                </el-form-item>
                <el-form-item label="入职时间" :label-width="formLabelWidth">
                    <el-date-picker type="date"
                                    placeholder="请选择入职时间"
                                    v-model="editUserForm.userInterTime"
                    />
                </el-form-item>
            </el-form>
            <div class="demo-drawer__footer">
                <el-button @click="editDialogFormVisible = false">取消</el-button>
                <el-button type="primary" @click="editDialogFormVisible = false; editUserPost()">确认修改</el-button>
            </div>
        </div>
    </el-drawer>
</template>

<script setup>
import {Edit, Delete} from '@element-plus/icons-vue'
import {ElMessage, ElDrawer, ElMessageBox} from 'element-plus'
import axios from 'axios'
import instance from '../../api/DataAxios'
import {onMounted, reactive, ref} from "vue"

let userinfo = reactive([])
let userInter = ref([])
let queryUser = reactive({
    userName: "",
    userSex: "",
    userAgeStart: null,
    userAgeEnd: null,
    deptName: "",
    roleName: "",
    userState: "",
    userProvince: "",
    userCity: "",
    userCommunity: "",
    userInterTimeStart: "",
    userInterTimeEnd: "",
})
let sexs = ref(["男", "女", "非二元性别"])
let depts = ref([])
let roles = ref([])
let states = ref(["正常", "请假中"])

onMounted(() => {
    axios.all([getAllUserCenterUsers(), getAllDeptName(), getAllRoleName()])
})

function query() {
    queryUser.userInterTimeStart = userInter.value[0]
    queryUser.userInterTimeEnd = userInter.value[1]
    instance.post("/userCenter/queryUserCenterUsers", queryUser).then(
        response => {
            userinfo.splice(0, userinfo.length)
            for (let user of response.data) {
                let singleUser = {
                    userId: user.userId,
                    userName: user.userName,
                    userSex: user.userSex,
                    userAge: user.userAge,
                    deptName: user.deptName,
                    roleName: user.roleName,
                    userEmail: user.userEmail,
                    userAddress: user.userProvince + user.userCity + user.userCommunity,
                    userInterTime: new Date(user.userInterTime).toLocaleString(),
                    userState: user.userState
                }
                userinfo.push(singleUser)
            }
        }
    )
}

function resetForm() {
    document.querySelector("#my-form").reset()
    queryUser = {
        userName: "",
        userSex: "",
        userAgeStart: null,
        userAgeEnd: null,
        deptName: "",
        roleName: "",
        userState: "",
        userProvince: "",
        userCity: "",
        userCommunity: "",
        userInterTimeStart: "",
        userInterTimeEnd: "",
    }
}

function getAllUserCenterUsers() {
    return instance.get("/userCenter/getAllUserCenterUsers").then(
        response => {
            for (let user of response.data) {
                let singleUser = {
                    userId: user.userId,
                    userName: user.userName,
                    userSex: user.userSex,
                    userAge: user.userAge,
                    deptName: user.deptName,
                    roleName: user.roleName,
                    userEmail: user.userEmail,
                    userAddress: user.userProvince + user.userCity + user.userCommunity,
                    userInterTime: new Date(user.userInterTime).toLocaleDateString(),
                    userState: user.userState
                }
                userinfo.push(singleUser)
            }
        }
    )
}

function getAllDeptName() {
    return instance.get("/userCenter/getAllDeptName").then(
        response => {
            for (let dept of response.data) {
                depts.value.push(dept)
            }
        }
    )
}

function getAllRoleName() {
    return instance.get("/userCenter/getAllRoleName").then(
        response => {
            for (let role of response.data) {
                roles.value.push(role)
            }
        }
    )
}


const formLabelWidth = '140px'
const dialogFormVisible = ref(false)
const addUserForm = reactive({
    userName: "",
    userSex: "",
    userAge: null,
    userEmail: "",
    userPassword: "",
    deptName: "",
    roleName: "",
    userState: "",
    userProvince: "",
    userCity: "",
    userCommunity: "",
    userInterTime: "",
})
function addUserPost() {
    instance.post("/userCenter/addNewUser", addUserForm).then(
        response => {
            ElMessage.success("添加成功")
            userinfo.splice(0,userinfo.length)
            getAllUserCenterUsers()
        }
    )
}

const editDialogFormVisible = ref(false)
let editUserForm = ref({
    userId: null,
    userName: null,
    userSex: null,
    userAge: null,
    deptName: null,
    roleName: null,
    userEmail: null,
    userProvince: null,
    userCity: null,
    userCommunity: null,
    userInterTime: null,
    userState: null
})
function editUser(row) {
    instance.get("/userCenter/getOneUserById/" + row.userId).then(
        response => {
            const data = response.data
            editUserForm.value = {
                userId: data.userId,
                userName: data.userName,
                userSex: data.userSex,
                userAge: data.userAge,
                deptName: data.deptName,
                roleName: data.roleName,
                userEmail: data.userEmail,
                userProvince: data.userProvince,
                userCity: data.userCity,
                userCommunity: data.userCommunity,
                userInterTime: new Date(data.userInterTime),
                userState: data.userState
            }
        }
    )
}
function editUserPost() {
    instance.post("/userCenter/editOneUser" ,editUserForm.value).then(
        response => {
            ElMessage.success("修改成功")
            userinfo.splice(0,userinfo.length)
            getAllUserCenterUsers()
        }
    )
}

function deleteUser(row) {
    ElMessageBox.confirm(
        '确定删除此员工吗？想想它的优点~~',
        '员工删除提示', {
            confirmButtonText: '确定删除',
            cancelButtonText: '我再想想',
            type: 'warning',
        }
    ).then(() => {
        instance.get("/userCenter/deleteOneUserById/" + row.userId).then(
            respone => {
                ElMessage({
                    type: 'success',
                    message: '员工已删除',
                })
                userinfo.splice(0,userinfo.length)
                getAllUserCenterUsers()
            }
        )
    }).catch(() => {
        ElMessage({
            type: 'info',
            message: '取消删除，真有你的',
        })
    })
}

let deleteUserIdList = ref([])
const handleSelectChange = (users) => {
    deleteUserIdList.value = []
    for (let user of users) {
        deleteUserIdList.value.push(user.userId)
    }
}
function deleteSelectUser() {
    for (let id of deleteUserIdList.value) {
        instance.get("/userCenter/deleteOneUserById/" + id).then(
            respone => {
                ElMessage({
                    type: 'success',
                    message: '员工已删除',
                })
                userinfo.splice(0,userinfo.length)
                getAllUserCenterUsers()
            }
        )
    }
}
</script>

<style scoped>
/*负责修改添加员工表格的属性*/
.el-input {
    width: 300px;
}
.el-select {
    width: 300px;
}

.user-table {
    width: 100%;
    height: calc(100vh - 285px);
}

.name-input {
    width: 120px;
}

.address-input {
    width: 70px;
}

.select-input {
    width: 120px;
}

.age-input {
    width: 50px;
}

</style>