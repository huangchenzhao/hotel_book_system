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
  components: { App, Header },
  template: '<App/>'
})
