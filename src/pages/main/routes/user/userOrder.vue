<template>
<div>
    <Header></Header>
        <el-card class="order-card" style="margin: auto;">
            <div slot="header" class="clearfix">
                <span>订单详情</span>
            </div>
            <el-form :model="orderForm" :rules="rules" label-width="100px" class="orderForm">
                <el-row :gutter="20">
                    <el-col :span="20" :offset="2">
                        <el-form-item label="酒店名称" prop="name">
                            <el-input v-model="orderForm.name" readonly="true" prefix-icon="el-icon-office-building" clearable="false"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :span="20" :offset="2">
                        <el-form-item label="酒店地址" prop="address">
                            <el-input v-model="orderForm.address" readonly="true" prefix-icon="el-icon-map-location"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :span="20" :offset="2">
                        <el-form-item label="房型" prop="room">
                            <el-select v-model="orderForm.room" placeholder="请选择酒店房型" style="width: 100%" prefix-icon="el-icon-house">
                                <el-option
                                        v-for="item in options"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value" clearable="false" disabled="true" prefix-icon="el-icon-house">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :span="10" :offset="2">
                        <el-form-item label="数量" prop="quantity">
                            <el-input-number v-model="orderForm.quantity" controls-position="right" @change="handleChange" :min="1" :max="orderForm.maxRoom" style="width: 100%"></el-input-number>
                        </el-form-item>
                    </el-col>
                    <el-col :span="10" >
                        <el-form-item label="总价" prop="quantity">
<!--                            <el-input-number v-model="orderForm.quantity" controls-position="right" @change="handleChange" :min="1" :max="10" style="width: 100%"></el-input-number>-->
                            <el-input v-model="myTotalPrice" readonly="true" prefix-icon="el-icon-document-checked"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :span="20" :offset="2">
                        <el-form-item label="时间" prop="liveDate">
                            <div class="block">
                                <el-date-picker
                                        v-model="orderForm.liveDate"
                                        type="daterange"
                                        range-separator="至"
                                        start-placeholder="开始日期"
                                        end-placeholder="结束日期" style="width: 100%" :clearable="false" :picker-options="pickerOptions" readonly="true">
                                </el-date-picker>
                            </div>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :span="20" :offset="2">
                        <el-form-item label="姓名" prop="userName">
                            <el-input v-model="orderForm.userName" placeholder="请输入姓名" prefix-icon="el-icon-user"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :span="20" :offset="2">
                        <el-form-item label="联系电话" prop="phone">
                            <el-input v-model="orderForm.phone" placeholder="请输入联系方式" prefix-icon="el-icon-phone-outline"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :span="20" :offset="2">
                        <el-form-item label="身份证号" prop="id">
                            <el-input v-model="orderForm.id" :maxlength="18" placeholder="请输入身份证号" prefix-icon="el-icon-circle-check"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20" type="flex" justify="center">
                    <el-col :span="6" :offset="4">
                        <el-button style="margin: auto" @click="jump">取 消</el-button>
                    </el-col>
                    <el-col :span="6" :offset="1">
                        <el-button type="primary" @click="getOneOrder" style="margin: auto">确 定</el-button>
                    </el-col>
                    <el-dialog
                            title="提示"
                            :visible.sync="centerDialogVisible"
                            width="30%"
                            center>
                        <span>
                            <el-result icon="success" title="成功" subTitle="下单成功，请点击我的订单查看">
      <template slot="extra">
        <el-button type="primary" @click="jumpBack">返回</el-button>
      </template>
    </el-result>
                        </span>
                    </el-dialog>
                </el-row>
            </el-form>
        </el-card>
</div>
</template>

<script>
import Header from '../../../../components/Header'
import {confirmOrder} from '@/api/api'
export default {
  name: 'GetOrder',
  components: {Header},
  data () {
    return {
      orderForm: {
        name: '如家快捷酒店',
        address: '',
        room: '标准间',
        quantity: 1,
        liveDate: '',
        maxRoom: 10,
        userName: '',
        id: '',
        phone: ''
      },
      rules: {
        userName: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' },
          {
            required: true,
            pattern: /^[\u4e00-\u9fa5_a-zA-Z0-9.·-]+$/,
            message: '姓名不支持特殊字符',
            trigger: 'blur'
          }
        ],
        id: [
          { required: true, message: '请输入身份证号', trigger: 'blur' },
          { min: 15, max: 18, message: '请如实填写18位号码，以供酒店方核对', trigger: 'blur' },
          {
            required: true,
            pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
            message: '请输入正确的身份证号码',
            trigger: 'blur'
          }
        ],
        phone: [
          { required: true, message: '请输入联系方式', trigger: 'blur' },
          { min: 11, max: 11, message: '请输入11位手机号码', trigger: 'blur' },
          {
            pattern: /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/,
            message: '请输入正确的手机号码'
          }
        ]
      },
      options: [{
        value: '标准间',
        label: '标准间'
      }, {
        value: '双人房',
        label: '双人房'
      }, {
        value: '大床房',
        label: '大床房'
      }, {
        value: '亲子房',
        label: '亲子房'
      }, {
        value: '总统套房',
        label: '总统套房'
      }],
      centerDialogVisible: false
    }
  },
  created () {
    let beg = this.$route.params.reserveData[0].checkIn
    let end = this.$route.params.reserveData[0].checkOut
    this.orderForm.liveDate = [beg, end]
    this.orderForm.maxRoom = this.$route.params.reserveData[0].detail.remain
    this.orderForm.address = this.$route.params.reserveData[0].address.detail
    this.orderForm.name = this.$route.params.reserveData[0].name
    this.orderForm.room = this.$route.params.reserveData[0].room.roomtype
  },
  computed: {
    myTotalPrice: function () {
      return (this.$route.params.reserveData[0].room.price) * this.orderForm.quantity
    }
  },
  methods: {
    handleChange (value) {
      console.log(value)
    },
    getOneOrder () {
      let orderInfo = {quantity: this.orderForm.quantity, name: this.orderForm.userName, phone: this.orderForm.phone, id: this.orderForm.id}
      confirmOrder(orderInfo).then(res => {
        console.info(res.data)
      })
      this.centerDialogVisible = true
    },
    jump () {
      this.$message({
        showClose: true,
        message: '请重新选择',
        type: 'success',
        center: true
      })
      this.$router.push({path: '/userPage'})
    },
    jumpBack () {
      this.$router.push({path: '/userPage'})
    }
  }
}
</script>

<style scoped>
    .order-card {
        width: 700px;
    }
    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }
    .clearfix:after {
        clear: both
    }
    .clearfix {
        text-align: center;
        font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
        font-size: 20px;
        font-weight: bold;
    }

</style>
