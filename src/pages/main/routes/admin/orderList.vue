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
                </el-aside>
                <el-main class="el-main">
                    <el-table
                            :data="order.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                            style="width: 100%"
                            >
                        <el-table-column
                                prop="createdTime"
                                label="创建时间"
                                width="100" align="center" header-align="center" >
                        </el-table-column>
                        <el-table-column
                                prop="hotel.name"
                                label="酒店名称"
                                width="100" align="center" header-align="center" >
                        </el-table-column>
                        <el-table-column
                                prop="room.roomtype"
                                label="房型"
                                width="100" align="center" header-align="center" >
                        </el-table-column>
                        <el-table-column
                                prop="quantity"
                                label="数量"
                                width="70" align="center" header-align="center">
                        </el-table-column>
                        <el-table-column
                                prop="totalprice"
                                label="总价格"
                                sortable
                                width="90" align="center" header-align="center" >
                        </el-table-column>
                        <el-table-column
                                prop="state"
                                label="状态"
                                width="70" align="center" header-align="center">
                            <template slot-scope="scope">
                                <el-tag :type="scope.row.state === 1?'success':'danger'">
                                    {{stateTag[scope.row.state]}}
                                </el-tag>
                            </template>
                        </el-table-column>
                        <el-table-column
                                prop="comment"
                                label="评价"
                                align="center" header-align="center" width="150">
                        </el-table-column>
                        <el-table-column
                                prop="truename"
                                label="入住人姓名"
                                align="center" header-align="center" width="100">
                        </el-table-column>
                        <el-table-column
                                prop="phonenumber"
                                label="入住人联系方式"
                                align="center" header-align="center" width="120">
                        </el-table-column>
                        <el-table-column
                                prop="idcard"
                                label="入住人身份证号"
                                align="center" header-align="center">
                        </el-table-column>
                    </el-table>
                    <el-col :span="24" class="toolbar" style="text-align:center">
                        <el-pagination
                                @current-change="handleCurrentChange1" @size-change="handleSizeChange1" :current-page="currentPage"
                                layout="total, prev, pager, next"
                                :page-size="pageSize"
                                :total="order.length"
                        ></el-pagination>
                    </el-col>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
import Header from '../../../../components/Header'
import {adminGetOrderList} from '@/api/api'
export default {
  name: 'orderList',
  components: {Header},
  data () {
    return {
      openeds: ['1'],
      order: [],
      currentPage: 1,
      pageSize: 5,
      stateTag: {
        0: '未完成',
        1: '已完成'
      }
    }
  },
  created () {
    adminGetOrderList().then(res => {
      this.order = res.data
      console.info(res.data)
    })
  },
  methods: {
    handleOpen (key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose (key, keyPath) {
      console.log(key, keyPath)
    },
    handleCurrentChange1 (currentPage) {
      this.currentPage = currentPage
    },
    handleSizeChange1 (val) {
      this.pageSize = val
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
        height:90vh;
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
