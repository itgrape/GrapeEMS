<template>

    <div style="position: relative; width: 100%; height: 100%;">

        <div id="money-table"></div>

        <div id="age-table"></div>

        <div id="sex-table"></div>

    </div>


</template>

<script setup>
import * as echarts from 'echarts'
import { onMounted } from "vue";
import instance from "../../api/DataAxios";

onMounted(() => {
    paintSexTable()
    paintPeopleNumTable()
    paintAgeTable()
})

const paintSexTable = () => {
    let sexChart = echarts.init(document.querySelector('#sex-table'));
    window.onresize = function() {
        sexChart.resize();
    };
    instance.get("/userCenter/getUserSexInfo").then(
        response => {
            const numData = response.data
            sexChart.setOption({
                title: {
                    text: '员工性别分布',
                },
                series: [
                    {
                        type: 'pie',
                        label:{
                            formatter(ar){
                                return ar.name + ':' + ar.value + ' '+ar.percent+'%'
                            }
                        },
                        data: [
                            {
                                value: numData.other,
                                name: '非二元性别'
                            },
                            {
                                value: numData.menNum,
                                name: '男'
                            },
                            {
                                value: numData.womenNum,
                                name: '女'
                            }
                        ],
                        radius: ['40%', '70%']
                    }
                ]
            })
        }
    )
}
const paintPeopleNumTable = () => {
    let moneyChart = echarts.init(document.querySelector('#money-table'));
    window.onresize = function() {
        moneyChart.resize();
    };
    moneyChart.setOption({
        title: {
            text: '公司净利润(万亿)'
        },
        xAxis: {
            type: 'category',
            data: ['2013', '2014', '2015', '2016', '2017', '2018', '2019', '2020', '2021', '2022']
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                data: [22, 80, 443, 1080, 1521, 3306, 6379, 8080, 25565, 27017],
                type: 'line',
                label: {
                    show: true,
                    position: 'top',
                    textStyle: {
                        fontSize: 13
                    }
                }
            }
        ]
    })
}
const paintAgeTable = () => {
    let ageChart = echarts.init(document.querySelector('#age-table'));
    window.onresize = function() {
        ageChart.resize();
    };
    instance.get("/userCenter/getUserAgeInfo").then(
        response => {
            const ageData = response.data
            ageChart.setOption({
                title: {
                    text: '员工年龄分布'
                },
                xAxis: {
                    data: ['10-20', '20-30', '30-40', '40-50', '50-60']
                },
                yAxis: {},
                series: [
                    {
                        type: 'bar',
                        data: [
                            {
                                value: ageData.one,
                                itemStyle: {
                                    color: '#e1f3d8',
                                }
                            },
                            {
                                value: ageData.two,
                                itemStyle: {
                                    color: '#b3e19d',
                                }
                            },
                            {
                                value: ageData.three,
                                itemStyle: {
                                    color: '#91cc75',
                                }
                            },
                            {
                                value: ageData.four,
                                itemStyle: {
                                    color: '#67C23A',
                                }
                            },
                            {
                                value: ageData.five,
                                itemStyle: {
                                    color: '#529b2e',
                                }
                            }
                        ],
                        itemStyle: {
                            borderRadius: 5,
                        }
                    }
                ]
            })
        }
    )
}
</script>

<style scoped>
#money-table {
    width: 100%;
    height: 50%;
    position: absolute;
}

#age-table {
    width: 50%;
    height: 50%;
    position: absolute;
    top: 50%;
    left: 0;
}

#sex-table {
    width: 50%;
    height: 50%;
    position: absolute;
    top: 50%;
    left: 50%;
}
</style>