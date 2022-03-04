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
<!--                <el-table-column-->
<!--                        prop="address"-->
<!--                        label="酒店地址"-->
<!--                        width="150" align="center" header-align="center" >-->
<!--                </el-table-column>-->
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
<!--                <el-table-column-->
<!--                        prop="checkIn"-->
<!--                        label="入住时间"-->
<!--                        width="150" align="center" header-align="center" >-->
<!--                </el-table-column>-->
<!--                <el-table-column-->
<!--                        prop="checkOut"-->
<!--                        label="退房时间"-->
<!--                        width="150" align="center" header-align="center" >-->
<!--                </el-table-column>-->
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
                                 @click="orderComplete" :disabled="scope.row.state===1">
                            确定已完成
                        </el-button>
                        <el-button
                                type="primary" @click="centerDialogVisible1=true">
                            查看详情
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
                        <el-dialog
                                title="订单详情"
                                :visible.sync="centerDialogVisible1"
                                width="70%"
                                center>
                            <span>
                                <el-descriptions direction="vertical" :column="4" border>
  <el-descriptions-item label="用户名">kooriookami</el-descriptions-item>
  <el-descriptions-item label="手机号">18100000000</el-descriptions-item>
  <el-descriptions-item label="居住地" :span="2">苏州市</el-descriptions-item>
  <el-descriptions-item label="备注">
    <el-tag size="small">学校</el-tag>
  </el-descriptions-item>
  <el-descriptions-item label="联系地址">江苏省苏州市吴中区吴中大道 1188 号</el-descriptions-item>
</el-descriptions>
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
import {writeComment, getMyAllOrder} from '@/api/api'
export default {
  name: 'orderList',
  data () {
    return {
      order: [],
      currentPage: 1,
      pageSize: 8,
      centerDialogVisible: false,
      comment: '',
      centerDialogVisible1: false
    }
  },
  created () {
    getMyAllOrder().then(res => {
      this.order = res.data
    })
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
    commentComplete (row) {
      let myComment = {roomId: row.roomId, comment: this.comment}
      writeComment(myComment).then(res => {
        console.info(res.data)
      })
    }
  }
}
</script>

<style scoped>

</style>
