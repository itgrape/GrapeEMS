<template>
    <img id="top_img" src="../../assets/top_img/detail.png" alt="detail">

    <div id="back" @click="back">返回</div>

    <hr>

    <div id="article">
        <div id="title">{{announce.title}}</div>

        <div id="time">发表于 {{announce.time}}</div>

        <div id="content" v-html="announce.content"></div>
    </div>
</template>

<script setup>
import {onMounted, reactive} from "vue";
import {useRoute, useRouter} from "vue-router";
import instance from "../../api/DataAxios";

const route = useRoute()
const router = useRouter()
let announce = reactive({
    title: null,
    content: null,
    time: null,
})
onMounted(() => {
    const id = route.query.id
    instance.get("/plainUser/getAnnounceById/" + id).then(
        response => {
            announce.title = response.data.announceTitle
            announce.content = response.data.announceContent
            announce.time = new Date(response.data.announceCreateTime).toLocaleString()
        }
    )
})

const back = () => {
    router.back()
}
</script>

<style scoped>
#top_img {
    height: 230px;
    width: 100%;
    overflow: hidden;
}

#back {
    font-size: 13px;
    margin-left: 20px;
    margin-top: 40px;
    cursor: pointer;
}

hr {
    border: none;
    height: 1px;
    background-color: rgba(0, 0, 0, 0.2);
}

#article {
    width: 800px;
    margin: 10px auto;
}

#title {
    font-size: 35px;
    text-align: center;
}

#time {
    margin: 5px auto 40px auto;
    width: 100%;
    height: 30px;
    line-height: 30px;
    text-align: center;
    background-color: #fffde2;
    color: #9999ac;
    border-radius: 5px;
    font-size: 15px;
}
</style>