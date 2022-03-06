<template>
    <div>
        <el-container class="el-container">
            <el-header class="el-header" height="100px">
                <h2 style="margin-top: 2.5%">微旅——您身边的酒店预订系统</h2>
            </el-header>
            <el-container>
                <el-aside width="200px" class="el-aside">
                    <el-menu class="el-menu" @open="handleOpen"
                             @close="handleClose" router :default-active="$route.path">
                        <el-submenu index="1">
                            <template slot="title">
                                <i class="el-icon-office-building"></i>
                                <span>酒店信息</span>
                            </template>
                            <el-menu-item index="/hotel/list">酒店列表</el-menu-item>
                            <el-menu-item index="/hotel/add">增加酒店</el-menu-item>
                            <el-menu-item index="1-3">酒店管理</el-menu-item>
                        </el-submenu>
                        <el-submenu index="2">
                            <template slot="title">
                                <i class="el-icon-sell"></i>
                                <span>订单信息</span>
                            </template>
                            <el-menu-item index="2-1">订单列表</el-menu-item>
                        </el-submenu>
                        <el-submenu index="3">
                            <template slot="title">
                                <i class="el-icon-user"></i>
                                <span>用户信息</span>
                            </template>
                            <el-menu-item index="3-1">用户列表</el-menu-item>
                            <el-menu-item index="3-2">用户管理</el-menu-item>
                        </el-submenu>
                    </el-menu>
                </el-aside>
                <el-main class="el-main">
                    <el-form :model="hotelForm" :rules="rules" ref="ruleForm" class="hotelForm">
                        <el-row :gutter="20">
                            <el-col :span="12" :offset="6">
                                <el-form-item label="酒店名称" prop="name">
                                    <el-input v-model="hotelForm.name"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-form-item label="酒店地点" prop="cityValue">
                            <el-row>
                                <el-cascader
                                        size="large"
                                        :options="hotelForm.options"
                                        v-model="hotelForm.selectedOptions"
                                        @change="handleChange" style="width: 100%">
                                </el-cascader>
                            </el-row>
                        </el-form-item>
                    </el-form>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
import Header from '../../../../components/Header'
import { regionData, CodeToText } from 'element-china-area-data'
export default {
  name: 'addHotel',
  components: {Header},
  data () {
    return {
      hotelForm: {
        name: '',
        cityValue: [],
        selectedOptions: [],
        options: regionData
      }
    }
  },
  methods: {
    handleOpen (key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose (key, keyPath) {
      console.log(key, keyPath)
    },
    handleChange () {
      let loc = ''
      for (let i = 0; i < this.hotelForm.selectedOptions.length; i++) {
        loc += CodeToText[this.hotelForm.selectedOptions[i]]
      }
      console.info(loc)
      console.info(this.hotelForm.selectedOptions[this.hotelForm.selectedOptions.length - 1])
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
        height:100vh;
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
    }

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }

</style>
