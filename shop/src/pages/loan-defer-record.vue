<template>
  <view-box>
    <div class="l-loan-payrecord" v-for="item in payRecord" :key="item.id">
      <div class="_hd l-flex-hc">
        <span>还款金额：<i class="l-rmb l-txt-theme">{{item.amount}}</i></span>
        <div class="l-rest"></div>
        <span class="l-fs-s l-txt-gray">{{item.createTime}}</span>
      </div>
      <div class="_cars">
        <div class="l-info-car l-flex-h" v-for="carItem in item.cars" :key="carItem.id">
          <div class="l-thumb1" :style="{'background-image': 'url(' + carItem.thumb + ')'}">
            <span class="_state1">垫资{{item.loanDays}}天</span>
          </div>
          <div class="l-rest">
            <div>{{carItem.carName}}</div>
            <div class="l-txt-gray l-margin-t-s l-fs-s _td">
              <p>
                <span>车辆指导价：<i class="l-rmb">{{carItem.guidancePrice | Int0}}</i></span>
                <span>车身颜色：{{carItem.colorName}}</span>
              </p>
              <p>
                <span>实际购车价：<i class="l-rmb">{{carItem.price | Int0}}</i></span>
                <span>保证金比例：{{carItem.downPayments | Int0}}%</span>
              </p>
              <p class="vux-1px-t l-padding-t-s l-margin-t-s">
                <span>垫资金额：<b class="l-rmb">{{carItem.amount | Int0}}</b></span>
                <span>手续费：<b class="l-rmb">{{carItem.fee | Int0}}</b></span>
              </p>
            </div>
          </div>
        </div>
      </div>
      <div class="_ft">
        <span style="vertical-align: 5px;margin-right: 5px;" class="l-txt-link l-fs-s" @click="$api.previewImage(0, item.voucher)">查看还款凭证</span>
        <img width="40" height="40" style="margin:5px 5px 0 0;" v-for="(img, index) in item.voucher" :key="index" :src="img" @click="$api.previewImage(index, item.voucher)">
      </div>
    </div>
  </view-box>
</template>

<script>
export default {
  name: 'loan-defer-record',
  data () {
    return {
      payRecord: []
    }
  },
  methods: {
    getInfo() {
      this.$vux.loading.show()
      this.$api.loan.getPayRecord(this.$route.query.id).then(({data}) => {
				if(data) {
					this.payRecord = data.map(item => {
						item.cars = item.cars.map(carItem => {
							carItem.thumb = this.$utils.imgThumb(item.carImage, 100, 100) || this.$config.thumb1
							// carItem.fee = Number(carItem.fee)
							return carItem
						})
						item.voucher = item.voucher ? item.voucher.split(',') : [] 
						return item
					})
				}
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
.l-loan-payrecord{
  background-color: #fff; padding: 10px; margin-bottom: 10px;
}

.l-info-car{
  background-color: #f4fafa; padding: 10px; margin: 5px 0; border-radius: 5px;
  &._disabled{opacity: 0.6;}
  .l-thumb1{margin:5px 10px 0 0; position: relative; overflow: hidden;}
  ._state1, ._state2{
    position: absolute; bottom: 0; left: 0; right: 0; font-size: 10px; text-align: center; padding: 1px 0; color: #fff;
  }
  ._state1{background-color: #39b94d; }
  ._state2{background-color: #ff2828; }
  ._icon{vertical-align: -1px; margin-right: 1px;}
  ._td span:first-child{min-width: 50%;}
  ._td span{display: inline-block;}
}

</style>

