<template>
  <div class="error">
    <div class="error-img">
      <img src="@/assets/images/403.svg" alt="403" />
    </div>
    <div class="error-txt">
      <h1>
        403
      </h1>
      <div class="error-des">抱歉，你无权限访问此页面</div>
      <div>
        <s-button
          type="primary"
          @click="goBack"
        >回到首页
        </s-button>
      </div>
    </div>
  </div>
</template>

<script>
import Icon from '@/components/Icon'
import {Button} from 'element-ui'
export default {
  name: 'forbidden',
  components: {
    's-icon': Icon,
    's-button': Button
  },
  methods: {
    goBack () {
      this.$router.push('/')
    }
  }
}
</script>
