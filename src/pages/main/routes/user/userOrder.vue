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
                            <el-input v-model="orderForm.name" readonly="true" prefix-icon="el-icon-office-building"></el-input>
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
                            <el-input-number v-model="orderForm.quantity" controls-position="right" @change="handleChange" :min="1" :max="10" style="width: 100%"></el-input-number>
                        </el-form-item>
                    </el-col>
                    <el-col :span="10" >
                        <el-form-item label="总价" prop="quantity">
<!--                            <el-input-number v-model="orderForm.quantity" controls-position="right" @change="handleChange" :min="1" :max="10" style="width: 100%"></el-input-number>-->
                            <el-input v-model="orderForm.totalPrice" readonly="true" prefix-icon="el-icon-document-checked"></el-input>
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
                    <el-col :span="10" :offset="6">
                        <el-button style="margin: auto">取 消</el-button>
                        <el-button type="primary" @click="handleSave" style="margin: auto">确 定</el-button>
                    </el-col>
                </el-row>
            </el-form>
        </el-card>
</div>
</template>

<script>
import Header from '../../../../components/Header'
export default {
  name: 'GetOrder',
  components: {Header},
  data () {
    return {
      orderForm: {
        name: '',
        address: '',
        room: '',
        quantity: 1,
        totalPrice: '',
        liveDate: ''
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
      }]
    }
  },
  created () {
    let date = new Date()
    let year = date.getFullYear().toString()
    let month = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1).toString() : (date.getMonth() + 1).toString()
    let da = date.getDate() < 10 ? '0' + date.getDate().toString() : date.getDate().toString()
    let day3 = new Date()
    day3.setTime(day3.getTime() + 24 * 60 * 60 * 1000)
    let s3 = day3.getFullYear() + '-' + (day3.getMonth() + 1) + '-' + day3.getDate()
    let tomorrow = s3.toString()
    let beg = year + '-' + month + '-' + da
    let end = tomorrow
    this.orderForm.liveDate = [beg, end]
  },
  methods: {
    handleChange (value) {
      console.log(value)
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
