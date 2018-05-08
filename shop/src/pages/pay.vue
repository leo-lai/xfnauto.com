<template>
  <view-box>
    <form-preview header-label="付款金额" :header-value="moneyStr" :body-items="list" :footer-buttons="buttons"></form-preview>
  </view-box>
</template>

<script>
import { FormPreview } from 'vux'

export default {
  name: 'pay',
  components: { FormPreview },
  data () {
    return {
      orderId: '',
      moneyStr: '¥0.00',
      list: [],
      buttons: [
        {
          style: 'default',
          text: '返回',
          onButtonClick: name => {
            this.$router.back()
          }
        }, 
        {
          style: 'primary',
          text: '确定支付',
          onButtonClick: name => {
            this.submit()
          }
        }
      ]
    }
  },
  methods: {
    submit() {
      this.$vux.loading.show()
      this.$api.chooseWXPay2({ orderId: this.orderId }).then(_ => {
        this.$vux.loading.hide()
        this.$vux.toast.show({
          text: '支付成功',
          onHide: _ => {
            this.$router.back()
          }
        })
      }).catch(_ => {
        this.$vux.loading.hide()
        this.$vux.toast.show({
          type: 'warn',
          text: '支付失败'
        })
      })
    }
  },
  mounted() {
    let payInfo = this.$storage.session.get('pay-info')
    if(payInfo) {
      this.orderId = payInfo.id
      this.moneyStr = '￥' + payInfo.money
      this.list.push({ label: '商品名称', value: payInfo.name })
      this.list.push({ label: '定金价格', value: '￥' + payInfo.price })
      this.list.push({ label: '车辆数量', value: payInfo.number })

      this.submit()
    }
  }
}
</script>
