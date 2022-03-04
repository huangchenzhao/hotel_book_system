<template>
    <div>
            <el-table
                    :data="order.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                    style="width: 100%"
                    max-height="510">
                <el-table-column
                        prop="name"
                        label="酒店名称"
                        width="150" align="center" header-align="center" >
                </el-table-column>
                <el-table-column
                        prop="address"
                        label="酒店地址"
                        width="150" align="center" header-align="center" >
                </el-table-column>
                <el-table-column
                        prop="roomType"
                        label="房型"
                        width="100" align="center" header-align="center" >
                </el-table-column>
                <el-table-column
                        prop="quantity"
                        label="数量"
                        width="100" align="center" header-align="center">
                </el-table-column>
                <el-table-column
                        prop="totalPrice"
                        label="总价格"
                        sortable
                        width="100" align="center" header-align="center" >
                </el-table-column>
                <el-table-column
                        prop="checkIn"
                        label="入住时间"
                        width="150" align="center" header-align="center" >
                </el-table-column>
                <el-table-column
                        prop="checkOut"
                        label="退房时间"
                        width="150" align="center" header-align="center" >
                </el-table-column>
                <el-table-column
                        prop="state"
                        label="状态"
                        width="150" align="center" header-align="center"><el-tag type="success">标签二</el-tag>
                </el-table-column>
                <el-table-column
                        prop="comment" label="评价"
                        align="center" header-align="center" width="200">
                </el-table-column>
                <el-table-column
                        label="操作"
                        align="center" header-align="center" >
                    <template slot-scope="scope">
                        <el-button
                                type="text" @click="orderComplete(scope.row)" :disabled="btnChangeEnable">
                            确定已完成
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-col :span="24" class="toolbar" style="text-align:center">
                <el-pagination
                        @current-change="handleCurrentChange1" @size-change="handleSizeChange1" :current-page="currentPage"
                        layout="total, prev, pager, next"
                        :page-size="pageSize"
                        :total="this.order.length"
                ></el-pagination>
            </el-col>
    </div>
</template>

<script>
export default {
  name: 'orderList',
  data () {
    return {
      order: [],
      currentPage: 1,
      pageSize: 8
    }
  },
  methods: {
    handleCurrentChange1 (currentPage) {
      this.currentPage = currentPage
    },
    handleSizeChange1 (val) {
      this.pageSize = val
    },
    orderComplete () {
      this.btnChangeEnable = true
    }
  }
}
</script>

<style scoped>

</style>
