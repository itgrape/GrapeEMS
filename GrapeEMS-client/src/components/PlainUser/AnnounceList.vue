<template>

<div id="total">
    <div id="announce-time">
        发布时间：{{announce.time}}
    </div>
    <div id="announce-title">
        标题：{{announce.title}}
    </div>
    <div id="announce-read">
        <el-button type="success" @click="goDetail">阅读公告全文</el-button>
    </div>
</div>


</template>

<script setup>
import {defineProps} from "vue/dist/vue.esm-browser";
import {useRouter} from "vue-router";

const router = useRouter()
const props = defineProps({
    announce: Object
})

const goDetail = () => {
    router.push({
        path: '/plainUser/detail',
        query: {
            id: props.announce.id
        }
    })
}
</script>

<style scoped>
#total {
    width: 70%;
    height: 100px;
    background-color: #ffffff;
    margin: 15px auto 15px auto;
    border-radius: 7px;
    position: relative;
}

#announce-time {
    position: absolute;
    top: 20px;
    left: 30px;
}

#announce-title {
    position: absolute;
    top: 60px;
    left: 30px;
}

#announce-read {
    position: absolute;
    top: 50px;
    right: 20px;
}
</style>