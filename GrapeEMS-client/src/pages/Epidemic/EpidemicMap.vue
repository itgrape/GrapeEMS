<template>
    <div id="epidemic-map" style="width: calc(100%); height: calc(100%);"></div>
</template>

<script setup>
import { onMounted } from "vue"
import * as echarts from 'echarts'
import instance from "../../api/DataAxios"
import chinaGEO from '../../assets/data/geo/china.json'

onMounted(() => {
    let myChart = echarts.init(document.getElementById('epidemic-map'));
    echarts.registerMap("china", chinaGEO)
    let option = {
        title: {
            text: '中国疫情地图',
            subtext: '数据来源于 https://lab.isaaclin.cn/nCoV/',
            sublink: 'https://lab.isaaclin.cn/nCoV/',
            left: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
        visualMap: {
            type: 'piecewise',
            pieces: [
                { min: 1000, max: 1000000, label: '确诊999人以上', color: '#372a28' },
                { min: 500, max: 999, label: '确诊500-999人', color: '#4e160f' },
                { min: 100, max: 499, label: '确诊100-499人', color: '#974236' },
                { min: 10, max: 99, label: '确诊10-99人', color: '#ee7263' },
                { min: 1, max: 9, label: '确诊1-9人', color: '#f5bba7' },
                { min: 0, max: 0, label: '确诊0人', color: '#95d475' },
            ],
            color: ['#E0022B', '#E09107', '#A3E00B']
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                mark: { show: true },
                dataView: { show: true, readOnly: false },
                restore: { show: true },
                saveAsImage: { show: true }
            }
        },
        roamController: {
            show: true,
            left: 'right',
            mapTypeControl: {
                'china': true
            }
        },
        series: [
            {
                name: '确诊数',
                type: 'map',
                map: 'china',
                roam: false,
                label: {
                    show: true,
                    color: 'rgb(249, 249, 249)'
                },
                data: []
            }
        ]
    };
    myChart.setOption(option);
    myChart.showLoading()
    function getData() {
        instance.get("/epidemicData/epidemicData").then(
            response => {
                let data = response.data.results
                let newArr = [];
                if (data) {
                    for (let d of data) {
                        if (d.countryName === "中国") {
                            let provinceName = d.provinceName.replace('省', '')
                            provinceName = provinceName.replace('市', '')
                            if (provinceName.length > 3) {
                                if (provinceName === '内蒙古自治区') {
                                    provinceName = provinceName.slice(0, 3)
                                } else {
                                    provinceName = provinceName.slice(0, 2)
                                }
                            }
                            let v = {
                                name: provinceName,
                                value: d.currentConfirmedCount
                            }
                            newArr.push(v)
                        }
                    }
                    myChart.hideLoading()
                    myChart.setOption({
                        series: [
                            {
                                name: '确诊数',
                                type: 'map',
                                map: 'china',
                                roam: false,
                                label: {
                                    show: true,
                                    color: 'rgb(249, 249, 249)'
                                },
                                data: newArr
                            }
                        ]
                    });
                }
            }
        )
    }
    getData();
})
</script>

<style scoped>

</style>