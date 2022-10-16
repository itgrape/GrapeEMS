<template>
    <el-carousel :interval="4000" type="card" height="320px">
        <el-carousel-item v-for="item in 4" :key="item">
            <el-image :src="'https://img.pushihao.com/other/grapeems/user_top_img/' + item + '.jpg'" />
        </el-carousel-item>
    </el-carousel>
</template>

<style scoped>
.el-carousel__item h3 {
    color: #475669;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
    text-align: center;
}

.el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
    background-color: #d3dce6;
}
</style>
