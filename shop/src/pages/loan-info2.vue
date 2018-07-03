<template>
  <view-box class="l-bg-white">
    <div class="l-info-top">
      <p class="l-fs-m">
        <span class="l-margin-r">申请日期：{{info.createTime}}</span>
        <span>手续费率：{{info.rate}}%</span>
      </p>
    </div>
    <div class="l-info-main">
      <div class="l-margin-b-s l-flex-hc">
        <div class="l-rest">垫资单号 <i class="l-fs-m">{{info.orderId}}</i></div>
        <span v-if="info.state == 4 || info.state == 5" class="l-txt-error l-fs-m">
          <icon class="l-fs-m _ic1" type="warn"></icon>{{info.stateName}}
        </span>
        <span v-else class="l-fr l-txt-theme l-fs-m">{{info.stateName}}</span>
      </div>
      <div class="l-fs-m">
        <template v-if="info.state < 3">
          <p>垫资本金总额：<b class="l-rmb l-txt-theme">{{info.amount | Int0}}</b></p>
          <p>垫资期限：{{info.period}}天</p>
        </template>
        <template v-else-if="info.state >= 3">
          <p>
            待还总额：<b class="l-rmb l-txt-theme">{{info.unpayAmount + info.unpayFee}}</b>
            <a v-if="info.payRecords.length > 0" class="l-txt-link l-margin-l" @click="$router.push('/loan/defer/record?id=' + info.id)">还款记录</a>
          </p>
          <p>待还本金：<span class="l-rmb">{{info.unpayAmount | Int0}}</span> / <span class="l-txt-gray">{{info.amount | Int0}}</span></p>
          <p>待还手续费：<span class="l-rmb">{{info.unpayFee | Int0}}</span> / <span class="l-txt-gray">{{info.feeTotal | Int0}}</span></p>
          <p>垫资期限：{{info.period}}天 / <span class="l-txt-gray">{{info.deadline}}</span></p>
        </template>

        <div class="l-txt-error">
          <p v-if="info.state == 4 && info.days > 0 && info.days < 7">*还有{{info.days}}天到最终还款日期，请尽快还款</p>
          <p v-if="info.state == 5">*请加缴保证金延期，否则车辆将被处理</p>
          <p v-if="info.state == 6">*已逾期未还款或未延期，车辆已由平台处理</p>
        </div>

        <div v-if="showMore">
          <div class="vux-1px-t l-margin-t-m l-padding-t-m"></div>
          <!-- 拒绝审核 -->
          <div class="l-txt-gray" v-if="info.state === 1">
            <p>审核时间：{{info.updateTime}}</p>
            <p class="l-txt-error">拒绝原因：{{info.reason}}</p>
          </div>
          <!-- 放款凭证 -->
          <div v-else-if="info.state >= 3" class="l-txt-gray">
            <span>放款时间：{{info.updateTime}}</span><br>
            <img width="40" height="40" style="margin:5px 5px 0 0;" v-for="(item, index) in info.voucher" :key="index" :src="item" @click="$api.previewImage(info.voucher, index)">
          </div>
          
          <!-- 延期记录 -->
          <div v-if="deferRecord.length > 0">
            <div class="vux-1px-t l-margin-t-m l-padding-t-m"></div>
            <div v-for="item in deferRecord" :key="item.id">
              <span class="l-txt-gray">延期申请时间：{{item.createTime}}</span>
              <p>
                <span class="l-fr l-txt-error l-margin-l-s" v-if="item.voucher.length == 0">未生效</span>
                <span>加收{{item.downpayment | Int0}}%保证金，手续费率调整为{{item.rate | Int0}}%，延期{{item.period}}天</span>
              </p>
              <p>
                <img width="40" height="40" style="margin:5px 5px 0 0;" v-for="(img, index) in item.voucher" :key="index" :src="img" @click="$api.previewImage(item.voucher, index)">
              </p>
            </div>
          </div>
        </div>

        <div class="l-more-btn">
          <span class="l-fr l-margin-l" @click="showMore = !showMore">
            <i v-if="!showMore" class="l-icon l-txt-blue l-fs-xl">&#xe625;</i>
            <i v-else class="l-icon l-txt-blue l-fs-xl">&#xe624;</i>
          </span>
        </div>
      </div>
    </div>
    
    <!-- 垫资车辆 -->
    <div class="l-padding-m" v-if="info.list">
      <div class="l-margin-tb-m l-txt-gray l-fs-m">垫资车辆</div>
      <div class="l-info-car l-flex-h" :class="item.state == 1 ? '_disabled' : ''"  v-for="item in info.list" :key="item.id">
        <div class="l-thumb1" :style="{'background-image': 'url(' + item.thumb + ')'}">
          <span class="_state1" v-if="item.state == 1"><x-icon class="_icon" type="ios-checkmark" size="12" style="fill: #fff;"></x-icon>{{item.stateName}}</span>
          <span class="_state2" v-else-if="item.state == 2">{{item.stateName}}</span>
        </div>
        <div class="l-rest">
          <div>{{item.carName}}</div>
          <div class="l-txt-gray l-margin-t-s l-fs-s _td">
            <p>
              <span>车辆指导价：<i class="l-rmb">{{item.guidancePrice | Int0}}</i></span>
              <span>车身颜色：{{item.colorName}}</span>
            </p>
            <p>
              <span>实际购车价：<i class="l-rmb">{{item.price | Int0}}</i></span>
              <span>保证金比例：{{item.downPayments | Int0}}%</span>
            </p>
            <p class="vux-1px-t l-padding-t-s l-margin-t-s">
              <span>垫资金额：<b class="l-rmb">{{item.amount | Int0}}</b></span>
              <span v-if="info.state >= 3">手续费：<b class="l-rmb">{{item.fee | Int0}}</b></span>
            </p>
          </div>
        </div>
      </div>
    </div>

    <template v-if="info.state <= 1">
      <div class="l-info-fixed">
        <x-button round plain v-if="info.state == 0" @click.native="cancel(info.id)">取消</x-button>
        <x-button round type="primary" v-if="info.state == 1" @click.native="reapply(info.id)">重新申请</x-button>
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
      showMore: false,
      info: {},
      deferRecord: []
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
				data.voucher = data.voucher ? data.voucher.split(',') : []
				this.$api.loan.deferRecord(data.id).then(({data}) => {
					if(data) {
						this.deferRecord = data.map(item => {
							item.voucher = item.voucher ? item.voucher.split(',') : []
							return item
						})
					}
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
    },
    reapply(id = '') {
      this.$router.push('/loan/2?id=' + id)
    }
  },
  mounted() {
    this.getInfo()
  }
}
</script>

<style lang="less" scoped>
.l-more-btn{margin-top: -2em; overflow: hidden; position: relative;}
.l-info-top{
  background: #eb6100 no-repeat 50% 50%;
  background-image: linear-gradient(135deg, #fb936e, #fd926e 50%, #fc865e 55%, #f67d52);
  padding: 15px; height: 100px; color: #fff;
}

.l-info-main{
  background-color: #fff; border-radius: 5px; padding: 10px; box-shadow: 0 0 5px #ccc;
  margin: -80px 10px 0;
  ._ic1{vertical-align: -2px;}
}

.l-info-car{
  background-color: #f4fafa; padding: 10px; margin-bottom: 10px; border-radius: 5px;
  &._disabled{opacity: 0.6;}
  .l-thumb1{margin: 3px 10px 0 0; position: relative; overflow: hidden; width: 60px; height: 60px;}
  ._state1, ._state2{
    position: absolute; bottom: 0; left: 0; right: 0; font-size: 10px; text-align: center; padding: 1px 0 2px; color: #fff;
  }
  ._state1{background-color: #39b94d; }
  ._state2{background-color: #ff2828; }
  ._icon{vertical-align: -2px; margin-right: 2px;}
  ._td span:first-child{min-width: 50%;}
  ._td span{display: inline-block;}
}

.l-info-fixed{
  position: fixed; left: 30%; right: 30%; bottom: 0; padding: 10px;
}
.l-info-fixed + ._fixed-placeholder{height: 56px;}
</style>

