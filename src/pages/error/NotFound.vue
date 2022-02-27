<template>
    <div class="error">
      <div class="error-img">
        <img src="@/assets/images/404.svg" alt="404" />
      </div>
      <div class="error-txt">
        <h1>
          404
        </h1>
        <div class="error-des">抱歉，你访问的页面不存在</div>
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
  name: 'not-found',
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
