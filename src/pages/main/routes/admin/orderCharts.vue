<template>
    <div>
        <el-container class="el-container">
            <el-header class="el-header" height="100px">
                <h2 style="margin-top: 2.5%">微旅——您身边的酒店预订系统</h2>
            </el-header>
            <el-container>
                <el-aside width="200px" class="el-aside">
                    <el-menu class="el-menu" @open="handleOpen"
                             @close="handleClose" :default-openeds="openeds" router :default-active="$route.path">
                        <el-submenu index="1">
                            <template slot="title">
                                <i class="el-icon-office-building"></i>
                                <span>酒店信息</span>
                            </template>
                            <el-menu-item index="/hotel/list">酒店列表</el-menu-item>
                            <el-menu-item index="/hotel/add">增加酒店</el-menu-item>
                            <el-menu-item index="/hotel/manage">酒店管理</el-menu-item>
                        </el-submenu>
                        <el-submenu index="2">
                            <template slot="title">
                                <i class="el-icon-sell"></i>
                                <span>订单信息</span>
                            </template>
                            <el-menu-item index="/order/list">订单列表</el-menu-item>
                            <el-menu-item index="/order/charts">订单分析</el-menu-item>
                        </el-submenu>
                        <el-submenu index="3">
                            <template slot="title">
                                <i class="el-icon-user"></i>
                                <span>用户信息</span>
                            </template>
                            <el-menu-item index="/user/list">用户列表</el-menu-item>
                            <el-menu-item index="3-2">用户管理</el-menu-item>
                        </el-submenu>
                    </el-menu>
                </el-aside>
                <el-main class="el-main">
                    <el-row :gutter="20">
                        <el-col :span="24">
                            <div id="mStudyTime" style="width: 100%; height: 550px;"></div>
                        </el-col>
                    </el-row>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
import Header from '../../../../components/Header'
import {getHotelData} from '@/api/api'
export default {
  name: 'orderCharts',
  components: {Header},
  data () {
    return {
      openeds: ['1'],
      pre7date: [],
      hotel: [],
      hotel2: [],
      item: {
        name: '',
        type: '',
        data: []
      },
      mStudyTime: '',
      seriesData: [],
      seriesData2: []
    }
  },
  created () {
    /* this.hotel2.push('如家1')
    this.item.name = '如家1'
    this.item.type = 'line'
    this.item.data = [1, 2, 3, 4, 5, 6, 7]
    this.seriesData2.push(this.item)
    console.info(this.hotel2)
    console.info(this.seriesData2) */
  },
  mounted () {
    this.mStudyTimeChart()
    window.addEventListener('resize', () => {
      this.mStudyTime.resize()
    })
  },
  methods: {
    handleOpen (key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose (key, keyPath) {
      console.log(key, keyPath)
    },
    mStudyTimeChart () {
      var echarts = require('echarts')
      // 基于准备好的dom，初始化echarts实例
      this.mStudyTime = echarts.init(document.getElementById('mStudyTime'))
      for (let i = 0; i < 7; i++) {
        const time = new Date(new Date().setDate((new Date().getDate() + i) - 7))
        const year = time.getFullYear()
        const month = `0${time.getMonth() + 1}`.slice(-2)
        const strDate = `0${time.getDate()}`.slice(-2)
        this.pre7date.push(`${year}-${month}-${strDate}`)
      }
      // 绘制图表
      let op = {
        title: {
          text: 'Stacked Line'
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: []
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: this.pre7date
        },
        yAxis: {
          type: 'value'
        },
        series: []
      }
      getHotelData().then(res => {
        this.hotel = Object.keys(res.data)
        for (let i = 0; i < this.hotel.length; i++) {
          op.legend.data.push(this.hotel[i])
          op.series.push({name: this.hotel[i], type: 'line', data: res.data[this.hotel[i]]})
        }
        // promise
        this.mStudyTime.setOption(op)
        console.info(this.hotel)
        console.info(this.seriesData)
      })
    }
  }
}
</script>

<style scoped>
    .el-header {
        background: url('../../../../assets/images/header.png');
        text-align: center;
        color: #dcdfe6;
        font-family:Arial,Helvetica,sans-serif;
        font-size: large;
        font-weight: bolder;
    }

    .el-aside {
        background-color: #336699;
        text-align: center;
        line-height: 200px;
        height:135vh;
    }
    .el-submenu {
        background-color: #FFFFCC;
    }
    .el-menu-item {
        background-color: #99CCCC !important;
    }
    .el-menu {
        background-color: #FFFFCC;
    }
    .el-main {
        background-color: #E9EEF3;
        color: #333;
        text-align: center;
        /*line-height: 160px;*/
    }

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }
</style>
