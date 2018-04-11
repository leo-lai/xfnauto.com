<template>
  <view-box>
    <msg :title="title" :description="description" :buttons="buttons" :icon="icon"></msg>
  </view-box>
</template>

<script>
import { Msg } from 'vux'
export default {
  name: 'Msg',
  components: { Msg },
  data () {
    return {
      title: '操作成功',
      description: '',
      icon: 'success',
      buttons: [
        {
          type: 'default',
          text: '返回',
          onClick: _ => {
            this.$router.back()
          }
        }
      ]
    }
  },
  mounted() {
    switch (Number(this.$route.query.type)) {
      case 1: // 商家审核提交提示
        this.title = '提交成功'
        this.description = '我们工作人员会在1-3个工作日审核完毕，请耐心等待审核！'
        this.buttons = [
          {
            type: 'primary',
            text: '去淘车',
            onClick: _ => {
              this.$router.replace('/')
            }
          }, {
            type: 'default',
            text: '返回',
            onClick: _ => {
              this.$router.go(this.$route.query.register ? -3 : -1)
            }
          }
        ]
        break
      default:
        break
    }
    
  }
}
</script>

