<template>
<div>
    <el-tabs type="border-card">
        <el-tab-pane label="首页">
            <el-form ref="select-form" :model="select-form" label-width="80px">
                <el-form-item label="酒店名称">
                    <el-input v-model="hotelName"></el-input>
                </el-form-item>
                <el-form-item label="入住地点">
                    <el-row>
                        <el-cascader
                                size="small"
                                :options="city.options"
                                :props="props"
                                v-model="cityValue"
                                @active-item-change="handleItemChange"
                                @change="cityChange">
                        </el-cascader>
                    </el-row>
                </el-form-item>
                <el-form-item label="时间">
                    <div class="block">
                        <span class="demonstration">带快捷选项</span>
                        <el-date-picker
                                v-model="indate"
                                type="daterange"
                                align="right"
                                unlink-panels
                                range-separator="至"
                                start-placeholder="开始日期"
                                end-placeholder="结束日期"
                                :picker-options="pickerOptions">
                        </el-date-picker>
                    </div>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSubmit">立即创建</el-button>
                    <el-button>取消</el-button>
                </el-form-item>
            </el-form>
        </el-tab-pane>
        <el-tab-pane label="我的账户">配置管理</el-tab-pane>
        <el-tab-pane label="我的订单">角色管理</el-tab-pane>
        <el-tab-pane label="定时任务补偿">定时任务补偿</el-tab-pane>
    </el-tabs>
</div>
</template>

<script>
export default {
  name: 'userPage',
  props: {
    areaCode: null
  },

  model: {
    prop: 'areaCode',
    event: 'cityChange'
  },

  data () {
    return {
      // 所在省市
      city: {
        obj: {},
        options: []
      },
      props: { // 级联选择器的属性配置
        value: 'value',
        children: 'cities',
        checkStrictly: true
      },
      cityValue: [], // 城市代码
      indate: '',
      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick (picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近一个月',
          onClick (picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近三个月',
          onClick (picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
            picker.$emit('pick', [start, end])
          }
        }]
      }
    }
  },
  computed: {
  },
  created () {
    this._initData()
  },
  mounted () {
  },
  methods: {
    _initData () {
      this.$http({
        method: 'get',
        url: this.API.province + '/156' // 中国
      }).then(res => {
        this.city.options = res.data.body.map(item => { // 所在省市
          return {
            value: item.provinceCode,
            label: item.provinceName,
            cities: []
          }
        })
      })
    },
    getCodeByAreaCode (code) {
      if (code === undefined) return false
      this.$http({
        method: 'get',
        url: this.API.addressCode + '/' + code
      })
        .then(res => {
          if (res.data.code === this.API.SUCCESS) {
            let provinceCode = res.data.body.provinceCode
            let cityCode = res.data.body.cityCode
            let areaCode = res.data.body.areaCode
            this.cityValue = [provinceCode, cityCode, areaCode]
            this.handleItemChange([provinceCode, cityCode])
          }
        })
        .finally(res => {
        })
    },
    handleItemChange (value) {
      let a = (item) => {
        this.$http({
          method: 'get',
          url: this.API.city + '/' + value[0]
        }).then(res => {
          item.cities = res.data.body.map(ite => {
            return {
              value: ite.cityCode,
              label: ite.cityName,
              cities: []
            }
          })
          if (value.length === 2) { // 如果传入的value.length===2 && 先执行的a()，说明是传入了areaCode，需要初始化多选框
            b(item)
          }
        }).finally(_ => {
        })
      }
      let b = (item) => {
        if (value.length === 2) {
          item.cities.find(ite => {
            if (ite.value === value[1]) {
              if (!ite.cities.length) {
                this.$http({
                  method: 'get',
                  url: this.API.area + '/' + value[1]
                }).then(res => {
                  ite.cities = res.data.body.map(ite => {
                    return {
                      value: ite.areaCode,
                      label: ite.areaName
                    }
                  })
                }).finally(_ => {
                })
              }
            }
          })
        }
      }
      this.city.options.find(item => {
        if (item.value === value[0]) {
          if (item.cities.length) {
            b(item)
          } else {
            a(item)
          }
          return true
        }
      })
    },
    getCityCode () {
      return this.cityValue[2]
    },
    reset () {
      this.cityValue = []
    },
    cityChange (value) {
      if (value.length === 3) {
        this.$emit('cityChange', value[2])
      } else {
        this.$emit('cityChange', null)
      }
    }
  },
  watch: {
    areaCode: {
      deep: true,
      immediate: true,
      handler (newVal) {
        if (newVal) {
          this.getCodeByAreaCode(newVal)
        } else {
          this.$nextTick(() => {
            this.reset()
          })
        }
      }
    }
  },
  onSubmit () {
    console.log('submit!')
  }
}
</script>

<style scoped>
</style>
