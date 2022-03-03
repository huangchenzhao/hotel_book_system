<template>
<div>
    <Header></Header>
        <el-card class="order-card" style="margin: auto;">
            <div slot="header" class="clearfix">
                <span>订单详情</span>
            </div>
            <el-form :model="orderForm" :rules="rules" label-width="100px" class="orderForm">
                <el-form-item label="酒店名称" prop="name">
                    <el-input v-model="orderForm.name"></el-input>
                </el-form-item>
                <el-form-item label="酒店地址" prop="address">
                    <el-input v-model="orderForm.address"></el-input>
                </el-form-item>
                <el-form-item label="房型" prop="room">
                    <el-input v-model="orderForm.room"></el-input>
                </el-form-item>
                <el-form-item label="数量" prop="quantity">
                    <el-input v-model="orderForm.quantity"></el-input>
                </el-form-item>
                <el-form-item label="总价格" prop="totalPrice">
                    <el-input v-model="orderForm.totalPrice"></el-input>
                </el-form-item>
                <el-form-item label="" prop="totalPrice">
                    <el-input v-model="orderForm.totalPrice"></el-input>
                </el-form-item>
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
        quantity: '',
        totalPrice: ''
      }
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
