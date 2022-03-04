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
                                type="text" @click="orderComplete" :disabled="btnChangeEnable">
                            确定已完成
                        </el-button>
                        <el-dialog
                                title="请填写评价"
                                :visible.sync="centerDialogVisible"
                                width="30%"
                                center>
                            <span>
                                <el-input
                                        type="textarea"
                                        placeholder="请输入评价"
                                        v-model="comment"
                                        maxlength="30"
                                        show-word-limit>
                                </el-input>
                            </span>
                            <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="commentComplete(scope.row)">确 定</el-button>
  </span>
                        </el-dialog>
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
      pageSize: 8,
      centerDialogVisible: false,
      comment: ''
    }
  },
  computed () {
  },
  created () {
  },
  methods: {
    handleCurrentChange1 (currentPage) {
      this.currentPage = currentPage
    },
    handleSizeChange1 (val) {
      this.pageSize = val
    },
    orderComplete () {
      this.centerDialogVisible = true
    },
    commentComplete (comment) {
    }
  }
}
</script>

<style scoped>

</style>
