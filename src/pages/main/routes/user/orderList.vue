<template>
    <div>
            <el-table
                    :data="order.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                    style="width: 100%"
                    max-height="510">
                <el-table-column
                        prop="createdTime"
                        label="创建时间"
                        width="200" align="center" header-align="center" >
                </el-table-column>
                <el-table-column
                        prop="hotel.name"
                        label="酒店名称"
                        width="150" align="center" header-align="center" >
                </el-table-column>
                <el-table-column
                        prop="room.roomtype"
                        label="房型"
                        width="150" align="center" header-align="center" >
                </el-table-column>
                <el-table-column
                        prop="quantity"
                        label="数量"
                        width="100" align="center" header-align="center">
                </el-table-column>
                <el-table-column
                        prop="totalprice"
                        label="总价格"
                        sortable
                        width="100" align="center" header-align="center" >
                </el-table-column>
              <el-table-column
                        prop="state"
                        label="状态"
                        width="100" align="center" header-align="center">
                <template slot-scope="scope">
                <el-tag :type="scope.row.state === 1?'success':'danger'">
                  {{stateTag[scope.row.state]}}
              </el-tag>
                </template>
                </el-table-column>
                <el-table-column
                        prop="comment"
                        label="评价"
                        align="center" header-align="center" width="200">
                </el-table-column>
                <el-table-column
                        label="操作"
                        align="center" header-align="center" >
                    <template slot-scope="scope">
                        <el-button
                                 @click="openComment(scope.row)" :disabled="scope.row.state===1">
                            确定已完成
                        </el-button>
                        <el-button
                                type="primary" @click="openDetail(scope.row)">
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
                              <el-button type="primary" @click="commentComplete">确 定</el-button>
                            </span>
                        </el-dialog>
                        <el-dialog
                          title="订单详情"
                          :visible.sync="centerDialogVisible1"
                          width="50%"
                          center>
                          <el-descriptions direction="vertical" :column="3" border>
                            <el-descriptions-item>
                              <template slot="label">
                                <i class="el-icon-user"></i>
                                创建时间
                              </template>
                              {{orderDetail.mycreatedTime}}
                            </el-descriptions-item>
                            <el-descriptions-item label="入住人">
                              {{orderDetail.mytruename}}
                            </el-descriptions-item>
                            <el-descriptions-item label="手机号">
                              {{orderDetail.myphonenumber}}
                            </el-descriptions-item>
                            <el-descriptions-item label="身份证号">
                              {{orderDetail.myidcard}}
                            </el-descriptions-item>
                            <el-descriptions-item label="酒店名称">
                              {{orderDetail.myhotelname}}
                            </el-descriptions-item>
                            <el-descriptions-item label="房型">
                              {{orderDetail.myroomtype}}
                            </el-descriptions-item>
                            <el-descriptions-item label="房间数量">
                              {{orderDetail.myquantity}}
                            </el-descriptions-item>
                            <el-descriptions-item label="总价格">
                              {{orderDetail.mytotalprice}}
                            </el-descriptions-item>
                            <el-descriptions-item label="状态">
                              <el-tag>
                                {{stateTag[orderDetail.mystate]}}
                              </el-tag>
                            </el-descriptions-item>
                            <el-descriptions-item label="评价" :span="2">
                              {{orderDetail.mycomments}}
                            </el-descriptions-item>
                          </el-descriptions>
                          <span slot="footer" class="dialog-footer">
                            <el-button @click="centerDialogVisible1 = false">取 消</el-button>
                            <el-button type="primary" @click="centerDialogVisible1 = false">确 定</el-button>
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
                        :total="order.length"
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
      pageSize: 6,
      centerDialogVisible: false,
      comment: '',
      centerDialogVisible1: false,
      stateTag: {
        0: '未完成',
        1: '已完成'
      },
      orderId: 0,
      orderDetail: {
        mycreatedTime: '',
        mytruename: '',
        myphonenumber: '',
        myidcard: '',
        myhotelname: '',
        myroomtype: '',
        myquantity: 0,
        mytotalprice: 0,
        mystate: 0,
        mycomments: ''
      }
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
    openComment (myRow) {
      this.orderId = myRow.oId
      this.centerDialogVisible = true
    },
    openDetail (myRow2) {
      this.orderDetail.mytruename = myRow2.truename
      this.orderDetail.mycomments = myRow2.comment
      this.orderDetail.mycreatedTime = myRow2.createdTime
      this.orderDetail.myhotelname = myRow2.hotel.name
      this.orderDetail.myidcard = myRow2.idcard
      this.orderDetail.myphonenumber = myRow2.phonenumber
      this.orderDetail.myquantity = myRow2.quantity
      this.orderDetail.myroomtype = myRow2.room.roomtype
      this.orderDetail.mystate = myRow2.state
      this.orderDetail.mytotalprice = myRow2.totalprice
      this.centerDialogVisible1 = true
    },
    commentComplete () {
      alert(this.orderId)
      let myComment = {orderId: this.orderId, comments: this.comment}
      writeComment(myComment).then(res => {
        this.centerDialogVisible = false
        getMyAllOrder().then(res => {
          this.order = res.data
          console.info(res.data)
        })
        this.$message({
          message: '订单已结束，祝您生活愉快~',
          type: 'success',
          center: true
        })
      })
    }
  }
}
</script>

<style scoped>

</style>
