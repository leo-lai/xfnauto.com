<template>
  <view-box>
    <div class="l-info-top">
      <p class="l-fs-m">
        <span class="l-margin-r">申请日期：{{info.createTime}}</span>
        <span>手续费率：{{info.rate}}%</span>
      </p>
    </div>
    <div class="l-info-main">
      <div>
        <span class="l-fr l-txt-theme">{{info.stateName}}</span>
        <span>垫资单号 <i class="l-fs-m">{{info.orderId}}</i></span>
      </div>
      <div class="l-margin-t-s">垫资本金总额：<span class="l-rmb l-txt-theme">{{info.amount}}</span></div>
      <div class="l-margin-t-s l-txt-gray l-fs-m"><span>垫资期限：{{info.period}}天</span></div>
    </div>

    <div class="l-padding-m" v-if="info.list">
      <div class="l-margin-t l-txt-gray">垫资车辆</div>
      <div class="l-info-car l-flex-h" v-for="item in info.list" :key="item.id">
        <div class="l-thumb1" :style="{'background-image': 'url(' + item.thumb + ')'}"></div>
        <div class="l-rest">
          <div>{{item.carName}}</div>
          <div class="l-txt-gray l-margin-t-s l-fs-s _td">
            <p>
              <span>车辆指导价：<i class="l-rmb">{{item.guidancePrice | Int0}}</i></span>
              <span>车身颜色：{{item.colorName}}</span>
            </p>
            <p>
              <span>实际购车价：<i class="l-rmb">{{item.price | Int0}}</i></span>
              <span>垫资数量：{{item.number}}辆</span>
            </p>
            <p>
              <span>保证金比例：{{item.downPayments | Int0}}%</span>
              <span>垫资金额：<b class="l-rmb">{{item.amount | Int0}}</b></span>
            </p>
          </div>
        </div>
      </div>
    </div>

    <template v-if="info.state == 0">
      <div class="l-info-fixed">
        <x-button @click.native="cancel(info.id)">取消</x-button>
      </div>
      <div class="_fixed-placeholder"></div>
    </template>
  </view-box>
</template>

<script>
export default {
  name: 'loan-info2',
  data () {
    return {
      info: {}
    }
  },
  methods: {
    getInfo() {
      this.$vux.loading.show()
      this.$api.loan.getInfo2(this.$route.query.id).then(({data}) => {
        data.list = data.list.map(item => {
          item.thumb = this.$utils.imgThumb(item.carImage, 100, 100) || this.$config.thumb1
          return item
        })
        this.info = data
      }).finally(_ => {
        this.$vux.loading.hide()
      })
    },
    cancel(id = '') {
      this.$vux.confirm.show({
        title: '系统提示',
        content: '是否确定删除该垫资申请？',
        onConfirm: _ => {
          this.$vux.loading.show()
          this.$api.loan.cancel(id).then(_ => {
            this.$router.back()
          }).finally(_ => {
            this.$vux.loading.hide()
          })
        }
      })
    }
  },
  mounted() {
    this.getInfo()
  }
}
</script>

<style lang="less" scoped>
.l-info-top{
  background: #eb6100 no-repeat 50% 50%;
  background-image: linear-gradient(135deg, #fb936e, #fd926e 50%, #fc865e 55%, #f67d52);
  padding: 15px; height: 100px; color: #fff;
}

.l-info-main{
  background-color: #fff; border-radius: 5px; padding: 10px; box-shadow: 0 0 5px #ccc;
  margin: -80px 10px 0;
}

.l-info-car{
  background-color: #fff; padding: 10px; margin-bottom: 10px; border-radius: 5px;
  .l-thumb1{margin:5px 10px 0 0;}
  ._td span:first-child{min-width: 50%;}
  ._td span{display: inline-block;}
}

.l-info-fixed{
  position: fixed; left: 0; right: 0; bottom: 0; padding: 10px;
}
.l-info-fixed + ._fixed-placeholder{height: 57px;}
</style>

