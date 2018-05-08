<template>
  <view-box>
    <div class="l-bg-white l-zoom" v-if="goodsInfo">
      <div class="l-flex-hc l-padding vux-1px-b">
        <img class="l-thumb1 l-margin-r" :src="goodsInfo.thumb" alt="">
        <div class="l-rest l-fs-m">
          <h4>{{goodsInfo.carsName}}</h4>
          <p>
            <span class="l-txt-gray">车身颜色：{{goodsInfo.colourName}}</span>
            <span class="l-txt-gray l-margin-l-s">内饰颜色：{{goodsInfo.interiorName}}</span>
          </p>
        </div>
      </div>

      <div class="l-table-info l-fs-m">
        <table>
          <tr>
            <td class="_tit">指导价：</td>
            <td class="l-txt-right"><span class="l-rmb">{{goodsInfo.guidingPrice}}</span></td>
          </tr>
          <tr v-if="goodsInfo.discountPriceOnLine > 0">
            <td class="_tit">已加价：</td>
            <td class="l-txt-right l-txt-gray"><span class="l-rmb">{{goodsInfo.discountPriceOnLine}}</span></td>
          </tr>
          <tr v-else>
            <td class="_tit">已优惠：</td>
            <td class="l-txt-right l-txt-gray"><span class="l-rmb">{{0-goodsInfo.discountPriceOnLine}}</span></td>
          </tr>
          <tr v-if="goodsInfo.goodsCarsActivityId">
            <td class="_tit">活动价：</td>
            <td class="l-txt-right"><span class="l-rmb l-txt-theme">{{goodsInfo.activityPrice}}</span></td>
          </tr>
          <tr v-else>
            <td class="_tit">裸车价：</td>
            <td class="l-txt-right"><span class="l-rmb l-txt-theme">{{goodsInfo.bareCarPriceOnLine}}</span></td>
          </tr>
          <tr>
            <td class="_tit">定金：</td>
            <td class="l-txt-right"><span class="l-rmb">{{goodsInfo.depositPrice}} / 辆</span></td>
          </tr>
        </table>
      </div>
    </div>
    <group>
      <cell title="购买数量">
        <inline-x-number style="display:block;" :min="1" width="50px" button-style="round" v-model="formData.number"></inline-x-number>
      </cell>
      <popup-radio title="购车方式" :options="buyWay" v-model="formData.expectBuyWay" placeholder="请选择">
        <div slot="popup-header" class="vux-1px-b l-padding-btn l-txt-center">请选择购车方式</div>
      </popup-radio>
      <popup-radio title="定金支付方式" :options="payInfo.way" v-model="formData.expectPayWay" placeholder="请选择">
        <div slot="popup-header" class="vux-1px-b l-padding-btn l-txt-center">请选择支付方式</div>
        <template slot-scope="props" slot="each-item">
          {{ props.label }} 
          <span class="l-fs-s" :class="props.index == 0 ? 'l-txt-theme': 'l-txt-gray'">{{payInfo.way[props.index].desc}}</span>
        </template>
      </popup-radio>
    </group>

    <group>      
      <x-input title="客户姓名" placeholder="请填写" :show-clear="false" :max="20" placeholder-align="right" v-model="formData.realName"></x-input>
      <!-- <x-input title="联系电话" placeholder="请填写" type="tel" :max="11" :show-clear="false" placeholder-align="right" v-model="formData.phoneNumber"></x-input> -->
      <cell title="联系电话" :value="formData.phoneNumber"></cell>
      <calendar title="预约到店日期" v-model="formData.appointmentDate" placeholder="请选择" disable-past></calendar>
      <x-textarea class="vux-x-input-placeholder-right" title="预约备注" placeholder="请填写" :rows="3" v-model="formData.remarks"></x-textarea>
    </group>

    <div class="l-goods-placeholder"></div>
    <div class="l-goods-fixed">
      <div class="l-flex-hc l-bg-white">
        <div class="l-rest">
          <span class="l-fs-m l-margin-l">应付定金：</span>
          <span class="l-rmb l-txt-theme">{{payInfo.money}}</span>
        </div>
        <div class="_btn1" @click="submit">
          <img src="../assets/images/icon-026.png" alt="">确定留车
        </div>
      </div>
    </div>


    <div v-transfer-dom>
      <x-dialog v-model="payInfo.visible" hide-on-blur :dialog-style="{'max-width': '100%', width: '100%', 'background-color': 'transparent'}">
        <div class="l-wuliu-freight">
          <div class="_hd">支付定金</div>
          <div class="_bd">
            <div class="l-rmb l-txt-theme l-txt-center" style="font-size:32px;">{{payInfo.money}}</div>
            <div class="l-btn-area">
              <x-button type="primary" @click.native="gotoPay">确定支付</x-button>
            </div>
          </div>
          <div class="l-margin-t">
            <x-icon type="ios-close-outline" style="fill:#fff;" @click="hidePay"></x-icon>
          </div>
        </div>
      </x-dialog>
    </div>
  </view-box>
</template>

<script>
import { InlineXNumber, PopupRadio, Calendar, XTextarea, XDialog } from 'vux'

export default {
  name: 'goods-order',
  components: { InlineXNumber, PopupRadio, Calendar, XTextarea, XDialog },
  data () {
    return {
      userInfo: null,
      buyWay: [
        { key: 1, value: '全款购车' }, 
        { key: 2, value: '分期付款' }
      ],
      payInfo: {
        visible: false,
        way: [
          { key: 1, value: '线上支付', desc: '订金留车，无车双倍订金赔付' }, 
          { key: 2, value: '到店支付', desc: '请尽快到店，下订留车'}
        ],
        money: '0.00'
      },
      goodsInfo: null,
      formData: {
        number: 1,
        expectBuyWay: '',
        expectPayWay: '',
        realName: '',
        phoneNumber: '',
        appointmentDate: '',
        remarks: '',
      }
    }
  },
  watch: {
    'formData.number'(val) {
      if(val) {
        this.payInfo.money = (this.formData.number * this.goodsInfo.depositPrice).toFixed(2)
      }
    }
  },
  methods: {
    hidePay() {
      this.payInfo.visible = false
      this.$router.replace('/order/list1')
    },
    gotoPay() {
      this.$router.replace('/' + this.$router.hostURL + 'pay/')
    },
    submit() {
      if(!this.formData.expectBuyWay) {
        this.$toptip('请选择购车方式')
        return
      }
      if(!this.formData.expectPayWay) {
        this.$toptip('请选择支付方式')
        return
      }
      if(!this.formData.realName) {
        this.$toptip('请填写姓名')
        return
      }
      if(!this.formData.phoneNumber) {
        this.$toptip('请填写联系方式')
        return
      }

      let formData = Object.assign({}, this.formData)
      formData.goodsCarsId = this.goodsInfo.goodsCarsId || ''
      formData.goodsCarsActivityId = this.goodsInfo.goodsCarsActivityId || ''

      this.$vux.loading.show()
      this.$api.order.add(formData).then(({data}) => {
        this.$vux.toast.show({
          text: '预约成功',
          onHide: _ => {
            if(formData.expectPayWay == 1) {
              this.$storage.session.set('pay-info', {
                id: data.advanceOrderId,
                name: this.goodsInfo.carsName,
                price: this.goodsInfo.depositPrice,
                number: formData.number,
                money: this.payInfo.money
              })
              this.payInfo.visible = true
            }else {
              this.$router.replace('/order/list1')
            }
          }
        })
      }).finally(_ => {
        this.$vux.loading.hide()
      })
    }
  },
  mounted() {
    this.goodsInfo = this.$storage.session.get('goods-info')
    if(this.goodsInfo) {
      this.payInfo.money = (this.formData.number * this.goodsInfo.depositPrice).toFixed(2)
    }

    this.$api.user.getInfo().then(data => {
      console.log(data)
      this.userInfo = data
      this.formData.phoneNumber = data.phoneNumber
      this.formData.realName = data.realName
    })
  }
}
</script>

<style lang="less" scoped>
.l-wuliu-freight{
  width: 280px; margin:auto;
  ._hd{padding: 15px; line-height: 1; background: url('../assets/images/20180402019.png') no-repeat; background-size: 100%; text-align: left; color: #fff;}
  ._bd{background-color: #fff; padding: 15px; text-align: left;}
}

.l-goods-placeholder{height: 46px;}
.l-goods-fixed{
  position: fixed; left: 0; right: 0; bottom: 0; z-index: 10; overflow: hidden;
  ._btn1{
    font-size: 14px; padding: 12px 15px; background-image: linear-gradient(135deg, #fa8734, #eb6100); color: #fff;
    img{width: 20px; height: 20px; vertical-align: -4px; margin-right: 5px;}
  }
  ._btn2{
    font-size: 14px; padding: 12px 15px; background-color: #eef3f6; color: inherit;
    img{width: 20px; height: 20px; vertical-align: -4px; margin-right: 5px;}
  }

}
.l-goods-info{
  ._tag1 {
    display: inline-block;
    border-radius: 10px;
    background-color: #e2f3ff;
    padding: 1px 10px;
    margin-right: 5px;
    font-size: 11px;
    color: #999;
  }
  ._tag2 {
    border-radius: 5px;
    border: 1px solid rgb(235, 97, 0);
    color: rgb(235, 97, 0);
    padding: 1px 5px;
    font-size: 10px;
    margin-top: 3px;
  }
}
</style>

