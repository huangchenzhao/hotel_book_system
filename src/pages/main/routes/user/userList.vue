<template>
    <div>
        <el-card class="crumbs-card">
            <div class="crumbs">
                <el-breadcrumb separator="/">
                    <el-breadcrumb-item :to="{ path: '/main/' }">首页</el-breadcrumb-item>
                    <el-breadcrumb-item :to="{ path: '/main/first' }">用户列表</el-breadcrumb-item>
                </el-breadcrumb>
            </div>
        </el-card>
        <el-card class="container">
        </el-card>
        <el-table
                :data="hotelData"
                style="width: 100%"
                max-height="250">
            <el-table-column
                    fixed
                    prop="id"
                    label="账号"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="姓名"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="province"
                    label="省份"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="city"
                    label="市区"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="address"
                    label="地址"
                    width="300">
            </el-table-column>
            <el-table-column
                    prop="zip"
                    label="邮编"
                    width="120">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="120">
                <template slot-scope="scope">
                    <el-button
                            @click.native.prevent="deleteRow(scope.$index, hotelData)"
                            type="text"
                            size="small">
                        移除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
import { userList } from '@api/api'
export default {

  name: 'userList',
  data: function () {
    return {
      users: []
    }
  },
  methods: {
    getUsers: function () {
      let page = {pageNo: 1, pageSize: 20}
      userList(page).then(res => {
        this.users = res.data.record
      })
    }
  },
  created () {
    this.getUsers()
  }
}
</script>

<style scoped>

</style>
