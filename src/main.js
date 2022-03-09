// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Vuebar from 'vuebar'
import ElementUI from 'element-ui'
import 'normalize.css/normalize.css'
import 'nprogress/nprogress.css'
import '@/assets/styles/index.less'
import '@/assets/styles/element-ui.less'
import App from './App'
import '@/assets/iconfont-default'
import router from './router/'
import store from './store/'
import Header from './components/Header.vue'
import orderList from './pages/main/routes/user/orderList.vue'
import BaiduMap from 'vue-baidu-map'

Vue.use(BaiduMap, {
  // ak 是在百度地图开发者平台申请的密钥 详见 http://lbsyun.baidu.com/apiconsole/key */
  ak: '3VcKkDmuaFz8ur9Q6RfLP7GKdVyQq6Kl'
})
Vue.use(Vuebar)
Vue.use(ElementUI)
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App, Header, orderList },
  template: '<App/>'
})

let echarts = require('echarts/lib/echarts')

// 引入折线图等组件
require('echarts/lib/chart/line') // 折线
require('echarts/lib/chart/bar') // 柱形

// 引入提示框和title组件，图例
require('echarts/lib/component/tooltip')
require('echarts/lib/component/title')
require('echarts/lib/component/legend')
require('echarts/lib/component/legendScroll')
require('echarts/lib/component/toolbox')
require('echarts/lib/component/grid')

Vue.prototype.$echarts = echarts
