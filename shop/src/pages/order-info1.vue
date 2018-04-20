<template>
  <view-box>
    <div class="l-zoom" v-if="info && info.carInfo">
      <div class="l-bg-white l-padding vux-1px-b">预约单号：{{info.orderCode}}</div>
      <div class="l-bg-white l-flex-hc l-padding vux-1px-b">
        <img class="l-thumb1 l-margin-r" :src="info.carInfo.thumb" alt="">
        <div class="l-rest l-fs-m">
          <h4 class="l-fs-m">{{info.carInfo.carsName}}</h4>
          <p>
            <span class="l-txt-gray">车身颜色：{{info.carInfo.colourName}}</span>
            <span class="l-txt-gray l-margin-l-s">内饰颜色：{{info.carInfo.interiorName}}</span>
          </p>
        </div>
      </div>

      <div class="l-table-info l-fs-m l-bg-white">
        <table>
          <tr>
            <td class="_tit">指导价：</td>
            <td class="l-txt-right"><span class="l-rmb">{{info.carInfo.guidingPrice}}</span></td>
          </tr>
          <tr v-if="info.discountPriceOnLine > 0">
            <td class="_tit">已加价：</td>
            <td class="l-txt-right l-txt-gray">+ <span class="l-rmb">{{info.carInfo.discountPriceOnLine}}</span></td>
          </tr>
          <tr v-else>
            <td class="_tit">已优惠：</td>
            <td class="l-txt-right l-txt-gray">- <span class="l-rmb">{{0-info.carInfo.discountPriceOnLine}}</span></td>
          </tr>
          <tr>
            <td class="_tit">裸车价：</td>
            <td class="l-txt-right"><span class="l-rmb l-txt-theme">{{info.carInfo.bareCarPriceOnLine}}</span></td>
          </tr>
          <tr>
            <td class="_tit">定金：</td>
            <td class="l-txt-right"><span class="l-rmb">{{info.depositPrice}}</span></td>
          </tr>
        </table>
      </div>

      <div class="l-table-info l-fs-m l-bg-white l-margin-t">
        <table>
          <tr>
            <td class="_tit">购车方式：</td>
            <td class="l-txt-right"><span >{{expectBuyWay[info.expectBuyWay]}}</span></td>
          </tr>
          <tr>
            <td class="_tit">定金支付方式：</td>
            <td class="l-txt-right"><span>{{expectPayWay[info.expectPayWay]}}</span></td>
          </tr>
          <tr>
            <td class="_tit">客户姓名：</td>
            <td class="l-txt-right"><span>{{info.realName}}</span></td>
          </tr>
          <tr>
            <td class="_tit">联系电话：</td>
            <td class="l-txt-right"><span >{{info.phoneNumber}}</span></td>
          </tr>
          <tr>
            <td class="_tit">预约到店日期：</td>
            <td class="l-txt-right"><span >{{info.appointmentDate}}</span></td>
          </tr>
        </table>
        <div class="l-remark l-margin-t">{{info.remarks}}</div>
      </div>
    </div>
  </view-box>
</template>
<script>
export default {
  name: 'order-info1',
  data () {
    return {
      expectBuyWay: ['', '全款', '贷款'],
      expectPayWay: ['', '线上支付', '到店支付'],
      info: null
    }
  },
  methods: {
    getInfo() {
      this.$vux.loading.show()
      this.$api.order.getInfo1(this.$route.query.id).then(({data}) => {
        if(data.orderInfoVos && data.orderInfoVos.length > 0) {
          let carInfo = data.orderInfoVos[0]
          carInfo.thumb = this.$utils.imgThumb(carInfo.image, 100, 100) || this.$config.thumb1
          carInfo.guidingPriceStr = (carInfo.guidingPrice / 10000).toFixed(2)
          data.carInfo = carInfo
        }
        this.info = data
      }).finally(_ => {
        this.$vux.loading.hide()
      })
    }
  },
  mounted() {
    this.getInfo()
  }
}
</script>
<style lang="less" scoped>
.l-thumb{width: 50px; height: 50px; border-radius: 5px; border:1px solid #d9d9d9;}
.l-seek-tag1{
  display: inline-block; font-size: 12px; background-color: #f2f6f9; border-radius: 15px; padding: 3px 15px 3px 10px;
  ._icon{width: 20px; height: 20px; vertical-align: -4px; margin-right: 4px;}
}
.l-seek-tag3{
  display: inline-block; font-size: 12px; border-radius: 15px; padding: 1px 10px 1px 5px; color:#333; border: 1px solid #d9d9d9; margin-top: -5px;
  ._icon{width: 20px; height: 20px; vertical-align: -4px; margin-right: 1px;}
}
.l-seek-tag2{background-color: #d2d2d2; color: #fff; font-size: 11px; padding: 1px 5px; border-radius: 3px;}
</style>


