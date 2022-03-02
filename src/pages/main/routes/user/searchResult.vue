<template>
<div>
    <Header></Header>
    <el-tabs type="border-card">
        <el-tab-pane label="搜索结果">
            <el-table
                    :data="hotel"
                    style="width: 100%"
                    max-height="510">
<!--                <el-table-column
                        prop="id"
                        label="序号"
                        width="150">
                </el-table-column>-->
                <el-table-column
                        prop="name"
                        label="名称"
                        sortable
                        width="250">
                </el-table-column>
                <el-table-column
                        prop="photo.photoUrl"
                        label="图片"
                        width="200">
                </el-table-column>
              <el-table-column label="地址">
                <el-table-column
                        prop="address.province"
                        label="省"
                        width="90">
                </el-table-column><el-table-column
                        prop="address.city"
                        label="市"
                        width="90">
                </el-table-column><el-table-column
                        prop="address.district"
                        label="区"
                        width="90">
                </el-table-column>
              </el-table-column>
                <el-table-column
                        prop="room.price"
                        label="最低价格"
                        sortable
                        width="200">
                </el-table-column>
              <el-table-column
                  prop="star"
                  label="星级"
                  sortable
                  width="200">
              </el-table-column>
                <el-table-column
                        fixed="right"
                        label="操作"
                        width="160">
                    <el-button
                            @click.native.prevent="deleteRow(scope.$index, hotelData)"
                            type="text"
                            size="small" @click="dialogTableVisible = true">
                        查看详情
                    </el-button>
                  <el-button
                      @click.native.prevent="deleteRow(scope.$index, hotelData)"
                      type="text"
                      size="small" @click="dialogTableVisible = true">
                    查看地图
                  </el-button>
                    <el-dialog title="收货地址" :visible.sync="dialogTableVisible">
                        <el-table :data="room">
                            <el-table-column property="date" label="日期" width="150"></el-table-column>
                            <el-table-column property="name" label="姓名" width="200"></el-table-column>
                            <el-table-column property="address" label="地址"></el-table-column>
                        </el-table>
                    </el-dialog>
                </el-table-column>
            </el-table>
        </el-tab-pane>
        <el-tab-pane label="我的账户">
            <el-card class="user-card" style="margin: auto">
                <el-descriptions class="des-user" :column="2" border :size="size" title="账户信息">
                    <template slot="extra">
                        <el-button type="primary" @click="centerDialogVisible = true">修改头像</el-button>
                        <el-dialog
                                title="上传头像"
                                :visible.sync="centerDialogVisible"
                                width="30%"
                                center>
                            <span>
                                <el-upload
                                        class="avatar-uploader"
                                        action="/api/file/upload"
                                        :show-file-list="false"
                                        :http-request="uploadImg"
                                        :on-success="handleAvatarSuccess"
                                        :before-upload="beforeAvatarUpload">
  <img v-if="imageUrl" :src="imageUrl" class="avatar">
  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
</el-upload>
                            </span>
                            <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="centerDialogVisible = false">确 定</el-button>
  </span>
                        </el-dialog>
                    </template>
                    <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-user"></i>
                            用户名
                        </template>
                        kooriookami
                    </el-descriptions-item>
                    <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-mobile-phone"></i>
                            密码
                        </template>
                        xiaoming
                    </el-descriptions-item>
                    <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-location-outline"></i>
                            邮箱
                        </template>
                        543892034@qq.com
                    </el-descriptions-item>
                    <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-tickets"></i>
                            备注
                        </template>
                        <el-tag size="small">用户</el-tag>
                    </el-descriptions-item>
                    <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-office-building"></i>
                            头像
                        </template>
                        <img src="../../../../assets/images/user2.jpeg" alt="" id="imgid">
                    </el-descriptions-item>
                </el-descriptions>
            </el-card>
        </el-tab-pane>
        <el-tab-pane label="我的订单">
            <el-table
                    :data="orderData"
                    stripe
                    style="width: 100%">
                <el-table-column
                        type="index"
                        width="50">
                </el-table-column>
                <el-table-column
                        prop=""
                        label="日期"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="姓名"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="address"
                        label="地址">
                </el-table-column>
            </el-table>
        </el-tab-pane>
    </el-tabs>
</div>
</template>

<script>
import Header from '../../../../components/Header'

export default {
  name: 'searchResult',
  components: {Header},
  data () {
    return {
      hotel: [],
      room: [],
      dialogTableVisible: false,
      centerDialogVisible: false,
      imageUrl: '',
      imgReturn: '',
      orderData: []
    }
  },
  methods: {
    deleteRow (index, rows) {
      rows.splice(index, 1)
    }
  },
  created () {
    // this.hotel = this.$route.params.hotelName
    /* console.info(this.$route.params.hotelName) */
    console.info(this.$route.params.returnData)
    this.hotel = this.$route.params.returnData
  }
}
</script>

<style scoped>
    .user-card {
        width: 700px;
    }
    .avatar-uploader {
        border: 1px dashed #6b9bce;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }
    .avatar-uploader:hover {
        border-color: #409EFF;
    }
    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        line-height: 178px;
        text-align: center;
    }
    .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }
    .avatar-uploader{
        display: flex;
        justify-content: center;
        align-items: center;
    }
</style>
