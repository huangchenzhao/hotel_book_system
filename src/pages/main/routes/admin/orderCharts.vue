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
                        </el-submenu>
                    </el-menu>
                    <div class="loginOut">
                        <div>
                            <el-popconfirm
                                    title="确定退出登录吗" @confirm="logOut">
                                <el-button slot="reference" icon="el-icon-bell">登出</el-button>
                            </el-popconfirm>
                        </div>
                    </div>
                </el-aside>
                <el-main class="el-main">
                    <el-row :gutter="20">
                        <el-col :span="12">
                            <div id="pie" style="width: 100%; height: 400px;"></div>
                        </el-col>
                        <el-col :span="12">
                            <div id="bar" style="width: 100%; height: 400px;"></div>
                        </el-col>
                    </el-row>
                    <el-row :gutter="20">
                        <el-col :span="24">
                            <div id="mStudyTime" style="width: 100%; height: 400px;"></div>
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
      item: {
        name: '',
        type: '',
        data: []
      },
      mStudyTime: '',
      mPie: '',
      myBar: ''
    }
  },
  mounted () {
    this.mStudyTimeChart()
    this.pie()
    this.mBar()
    window.addEventListener('resize', () => {
      this.mStudyTime.resize()
    })
  },
  methods: {
    logOut () {
      this.$router.push({name: 'login'})
    },
    handleOpen (key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose (key, keyPath) {
      console.log(key, keyPath)
    },
    mBar () {
      var echarts = require('echarts')
      // 基于准备好的dom，初始化echarts实例
      this.myBar = echarts.init(document.getElementById('bar'))
      for (let i = 0; i < 7; i++) {
        const time = new Date(new Date().setDate((new Date().getDate() + i) - 7))
        const year = time.getFullYear()
        const month = `0${time.getMonth() + 1}`.slice(-2)
        const strDate = `0${time.getDate()}`.slice(-2)
        this.pre7date.push(`${year}-${month}-${strDate}`)
      }
      // 绘制图表
      let op3 = {
        title: {
          text: '近一周各酒店营业额柱形图',
          left: 'center'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        legend: {
          y: 'bottom'
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '15%',
          containLabel: true
        },
        xAxis: [
          {
            type: 'category',
            data: this.pre7date
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        series: []
      }
      getHotelData().then(res => {
        this.hotel = Object.keys(res.data)
        for (let i = 0; i < this.hotel.length; i++) {
          op3.series.push({name: this.hotel[i], type: 'bar', emphasis: {focus: 'series'}, data: res.data[this.hotel[i]]})
        }
        // promise
        this.myBar.setOption(op3)
        // console.info(this.hotel)
        // console.info(this.seriesData)
      })
    },
    pie () {
      var echarts = require('echarts')
      // 基于准备好的dom，初始化echarts实例
      this.mPie = echarts.init(document.getElementById('pie'))
      let op2 = {
        title: {
          text: '近一周各酒店营业额饼图',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          y: 'bottom'
        },
        series: [
          {
            name: 'Access From',
            type: 'pie',
            radius: '50%',
            data: [],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      }
      getHotelData().then(res => {
        this.hotel = Object.keys(res.data)
        console.info(res.data[this.hotel[0]])
        console.info(res.data[this.hotel[0]][1])
        // console.info(this.hotel)
        let hData = new Array(this.hotel.length).fill(0)
        for (let i = 0; i < this.hotel.length; i++) {
          for (let j = 0; j < 7; j++) {
            hData[i] += res.data[this.hotel[i]][j]
          }
        }
        console.info(hData)
        for (let i = 0; i < this.hotel.length; i++) {
          op2.series[0].data.push({value: hData[i], name: this.hotel[i]})
        }
        // promise
        this.mPie.setOption(op2)
        // console.info(this.seriesData)
      })
    },
    mStudyTimeChart () {
      var echarts = require('echarts')
      // 基于准备好的dom，初始化echarts实例
      this.mStudyTime = echarts.init(document.getElementById('mStudyTime'))
      // 绘制图表
      let op = {
        title: {
          text: '近一周各酒店营业额折线统计图',
          left: 'center'
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          y: 'bottom',
          data: []
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '15%',
          y2: '10%'
          // containLabel: true
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
        // console.info(this.hotel)
        // console.info(this.seriesData)
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
        /*line-height: 200px;*/
        height:140vh;
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
    .loginOut {
        padding-top: 5px;
    }
</style>
