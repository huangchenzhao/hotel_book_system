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
export default {
  name: 'orderCharts',
  components: {Header},
  data () {
    return {
      openeds: ['1']
    }
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
      // 绘制图表
      this.mStudyTime.setOption({
        title: {
          text: 'Stacked Line'
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ['Email', 'Union Ads', 'Video Ads', 'Direct', 'Search Engine']
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
          data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            name: 'Email',
            type: 'line',
            stack: 'Total',
            data: [120, 132, 101, 134, 90, 230, 210]
          },
          {
            name: 'Union Ads',
            type: 'line',
            stack: 'Total',
            data: [220, 182, 191, 234, 290, 330, 310]
          },
          {
            name: 'Video Ads',
            type: 'line',
            stack: 'Total',
            data: [150, 232, 201, 154, 190, 330, 410]
          },
          {
            name: 'Direct',
            type: 'line',
            stack: 'Total',
            data: [320, 332, 301, 334, 390, 330, 320]
          },
          {
            name: 'Search Engine',
            type: 'line',
            stack: 'Total',
            data: [820, 932, 901, 934, 1290, 1330, 1320]
          }
        ]
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
