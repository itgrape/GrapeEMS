<template>

    <TopImg />
    <AnnounceList v-for="item in announceList" :key="item" :announce="item" />
    <SideBar />

</template>

<script setup>
import TopImg from '../../components/PlainUser/TopImg.vue'
import AnnounceList from '../../components/PlainUser/AnnounceList.vue'
import SideBar from '../../components/PlainUser/SideBar.vue'
import {onMounted, ref} from "vue";
import instance from "../../api/DataAxios";


let announceList = ref([])
onMounted(() => {
    getAllAnnounce()
})

function getAllAnnounce() {
    instance.get("/plainUser/getAllAnnounceByUserId/" + sessionStorage.getItem('userId')).then(
        response => {
            announceList.value.splice(0, announceList.value.length)
            for (let a of response.data) {
                const announce = {
                    id: a.announceId,
                    title: a.announceTitle,
                    time: new Date(a.announceCreateTime).toLocaleString(),
                    content: a.announceContent,
                }
                announceList.value.push(announce)
            }
            announceList.value.reverse()
        }
    )
}
</script>

<style scoped></style>
