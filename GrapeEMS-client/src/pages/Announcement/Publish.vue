<template>

    <div>
        <h3>公告标题</h3>
        <el-input v-model="announcement.title" requried />
    </div>
    <br>
    <div>
        <h3>公告内容</h3>
        <div style="border: 1px solid #ccc">
            <Toolbar
                style="border-bottom: 1px solid #ccc"
                :editor="editorRef"
                :defaultConfig="toolbarConfig"
                :mode="mode"
            />
            <Editor
                style="height: 300px; overflow-y: hidden;"
                v-model="mdValue"
                :defaultConfig="editorConfig"
                :mode="mode"
                @onCreated="handleCreated"
            />
        </div>

    </div>
    <br><br>
    <div>
        <h3>发布</h3>
        <el-form :inline="true">
            <el-form-item label="">
                <el-select v-model="announcement.deptName" placeholder="请选择发布部门">
                    <el-option value="全体部门">全体部门</el-option>
                    <el-option v-for="dept in depts" :value="dept">{{dept}}</el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="">
                <el-button type="success" @click="pubAnnounce()">发布</el-button>
            </el-form-item>
        </el-form>
    </div>

</template>

<script setup>
import '@wangeditor/editor/dist/css/style.css'
import {computed, onBeforeUnmount, reactive, shallowRef, onMounted, ref} from "vue"
import instance from "../../api/DataAxios"
import {ElMessage} from "element-plus"
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'

let depts = ref([])
let announcement = reactive({
    title: null,
    content: null,
    deptName: null
})

onMounted(() => {
    getAllDeptName()
})

function getAllDeptName() {
    return instance.get("/userCenter/getAllDeptName").then(
        response => {
            depts.value = []
            for (let dept of response.data) {
                depts.value.push(dept)
            }
        }
    )
}

// 编辑器实例，必须用 shallowRef
const editorRef = shallowRef()
let mdValue = ref()
const mode = 'default'
const toolbarConfig = {}
const editorConfig = { placeholder: '请输入公告内容...' }

announcement.content = computed(() => editorRef.value.getHtml())

// 组件销毁时，也及时销毁编辑器
onBeforeUnmount(() => {
    const editor = editorRef.value
    if (editor == null) return
    editor.destroy()
})

const handleCreated = (editor) => {
    editorRef.value = editor // 记录 editor 实例，重要！
}

function pubAnnounce() {
    if (announcement.title !== null && announcement.title !== '' && announcement.content !== null && announcement.content !== '' && announcement.deptName !== null && announcement.deptName !== '') {
        instance.post("/announce/addOneAnnounce", announcement).then(
            response => {
                if (response.data) {
                    ElMessage.success("发布成功")
                    mdValue.value = ''
                    announcement.title = null
                    announcement.content = ''
                    announcement.deptName = null
                } else {
                    ElMessage.warning("发布失败")
                }
            }
        )
    } else {
        ElMessage.warning("请检查表单信息是否有误")
    }
}

</script>

<style scoped></style>