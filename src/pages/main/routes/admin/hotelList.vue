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
                            <el-menu-item index="/hotel/manage">酒店管理</el-menu-item>
                        </el-submenu>
                        <el-submenu index="2">
                            <template slot="title">
                                <i class="el-icon-sell"></i>
                                <span>订单信息</span>
                            </template>
                            <el-menu-item index="/order/list">订单列表</el-menu-item>
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
                    <el-table
                            :data="hotel.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                            stripe
                            style="width: 100%">
                        <el-table-column
                                prop="name"
                                label="名称"
                                sortable
                                width="150" align="center" header-align="center" >
                        </el-table-column>
                        <el-table-column
                                prop="photo.photoUrl"
                                label="图片"
                                width="250" align="center" header-align="center" >
                            <template slot-scope="scope">
                                <img :src="scope.row.photo.photoUrl" alt="" width="90" height="90">
                            </template>
                        </el-table-column>
                        <el-table-column
                                prop="address.detail"
                                label="详细地址"
                                width="200" align="center" header-align="center">
                        </el-table-column>
                        <el-table-column
                                prop="countt"
                                label="预订次数"
                                :formatter="counttFormat"
                                sortable
                                width="150" align="center" header-align="center" >
                        </el-table-column>
                        <el-table-column
                                prop="star"
                                label="星级"
                                sortable
                                width="200" align="center" header-align="center" >
                            <template slot-scope="scope" >
                                <el-rate v-model="scope.row.star" :allow-half="true" show-score disabled text-color="#ff9900"></el-rate>
                            </template>
                        </el-table-column>
                        <el-table-column
                                label="操作"
                                align="center" header-align="center" >
                            <template slot-scope="scope">
                                <el-popconfirm
                                        title="确定删除这家酒店吗?" @onConfirm="delHotel">
                                    <el-button slot="reference" type="primary" icon="el-icon-delete"></el-button>
                                </el-popconfirm>
                                <el-button
                                        type="primary" icon="el-icon-search" @click="handleClick(scope.row)">
                                    查看详情
                                </el-button>
                            </template>
                            <el-dialog :visible.sync="dialogVisible" append-to-body="true" width="65%">
                                        <el-table :data="returnDetail">
                                            <el-table-column
                                                    prop="photo.photoUrl"
                                                    label="图片"
                                                    width="200" align="center" header-align="center">
                                                <template slot-scope="scope">
                                                    <img :src="scope.row.photo.photoUrl" alt="" width="90" height="90">
                                                </template>
                                            </el-table-column>
                                            <el-table-column
                                                    prop="room.roomtype"
                                                    label="房型"
                                                    width="100" align="center" header-align="center">
                                            </el-table-column>
                                            <el-table-column
                                                    prop="detail.remain"
                                                    label="今日剩余数量"
                                                    width="100" align="center" header-align="center">
                                            </el-table-column>
                                            <el-table-column
                                                    prop="room.amount"
                                                    label="总数量"
                                                    width="100" align="center" header-align="center">
                                            </el-table-column>
                                            <el-table-column
                                                    prop="roomcount"
                                                    label="预订次数"
                                                    :formatter="roomcountFormat"
                                                    width="100" align="center" header-align="center">
                                            </el-table-column>
                                            <el-table-column
                                                    prop="room.price"
                                                    label="价格"
                                                    width="100" align="center" header-align="center">
                                            </el-table-column>
                                            <el-table-column
                                                    label="操作"
                                                    align="center" header-align="center" >
                                                <template slot-scope="scope">
                                                    <el-button
                                                            type="primary" icon="el-icon-edit" @click="editPrice(scope.row)">
                                                        管理
                                                    </el-button>
                                                    <el-dialog title="更改房间信息" :visible.sync="priceDialog" width="30%" :append-to-body="true">
                                                        <el-form :model="roomForm">
                                                            <el-form-item label="价格">
                                                                <el-input v-model="roomForm.price" autocomplete="off"></el-input>
                                                            </el-form-item>
                                                        </el-form>
                                                        <div slot="footer" class="dialog-footer">
                                                            <el-button @click="priceDialog = false">取 消</el-button>
                                                            <el-button type="primary" @click="priceFinish">确 定</el-button>
                                                        </div>
                                                    </el-dialog>
                                                </template>
                                            </el-table-column>
                                        </el-table>
                            </el-dialog>
                        </el-table-column>
                    </el-table>
                    <el-col :span="24" class="toolbar" style="text-align:center">
                        <el-pagination
                                @current-change="handleCurrentChange" @size-change="handleSizeChange" :current-page="currentPage"
                                layout="total, prev, pager, next"
                                :page-size="pageSize"
                                :total="this.hotel.length"
                        ></el-pagination>
                    </el-col>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
import BaiduMap from 'vue-baidu-map/components/map/Map.vue'
import Header from '../../../../components/Header'
import {adminGetHotelList, adminGetDetail} from '@/api/api'

export default {
  name: 'hotelList',
  components: {BaiduMap, Header},
  data () {
    return {
      hotel: [],
      pageSize: 10,
      currentPage: 1,
      returnDetail: [],
      dialogVisible: false,
      priceDialog: false,
      roomForm: {
        price: 100
      }
    }
  },
  created () {
    adminGetHotelList().then(res => {
      this.hotel = res.data
    })
  },
  methods: {
    handleCurrentChange (currentPage) {
      this.currentPage = currentPage
    },
    handleSizeChange (val) {
      this.pageSize = val
    },
    handleOpen (key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose (key, keyPath) {
      console.log(key, keyPath)
    },
    handleClick (myRow) {
      this.dialogVisible = true
      let myDetail = {hid: myRow.hid}
      adminGetDetail(myDetail).then(res => {
        this.returnDetail = res.data
      })
    },
    counttFormat (row) {
      return row.countt == null ? '0' : row.countt
    },
    roomcountFormat (row) {
      return row.roomcount == null ? '0' : row.roomcount
    },
    editPrice (myRow) {
      this.priceDialog = true
    },
    priceFinish () {
      this.priceDialog = false
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
