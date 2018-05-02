<template>
  <view-box>
    <div class="l-padding">
      <msg :title="title" :description="description" :buttons="buttons" :icon="icon"></msg>
    </div>
  </view-box>
</template>

<script>
import { Msg } from 'vux'
export default {
  name: 'message',
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
        this.title = '提交申请成功'
        this.description = `我们工作人员会在1-3个工作日审核完毕，请耐心等待审核。如有其他疑问，请致电${this.$config.tel}`
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
              this.$router.go(this.$route.query.register ? -2 : -1)
            }
          }
        ]
        break
      case 2: // 个人贷款提交提示
        this.title = '恭喜您通过初审'
        this.description = `您已通过金融贷线上初审，我们的工作人员会在1-3个工作日内与你联系。如有其他疑问，请致电${this.$config.tel}`
        this.buttons = [
          {
            type: 'primary',
            text: '查看详情',
            onClick: _ => {
              this.$router.replace('/loan/list')
            }
          }, {
            type: 'default',
            text: '返回',
            onClick: _ => {
              this.$router.go(-1)
            }
          }
        ]
        break
    }
  }
}
</script>

