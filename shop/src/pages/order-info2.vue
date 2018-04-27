<template>
  <view-box>
    <div class="l-zoom l-fs-m" v-if="info">
      <div class="l-padding l-bg-white">
        <div class="l-flex-hc">
          <span class="l-h4 l-margin-r-m">{{info.orgName}}</span>
          <div class="l-rest"></div>
          <div class="l-txt-theme">{{info.orderStateName}}</div>
        </div>
        <div class="l-txt-gray l-margin-t-s">
          <span class="l-dmr">联系人：{{info.orgLinker}}</span>
          <span>联系电话：{{info.orgPhone}}</span>
        </div>

        <div class="vux-1px-t l-margin-t-m l-padding-t-m">
          <div>
            <span class="l-dmr">订单类型：{{orderType[info.orderType - 1]}}</span>
            <span>物流方式：{{wuliu[info.logisticsType - 1]}}</span>
          </div>
          <div>
            <span class="l-dmr">提车日期：{{info.pickCarDate || ''}}</span>
            <span>物流费用：{{info.freight || 0}}元</span>
          </div>
          <div>提车地点：{{info.pickCarAddr || ''}}</div>
          <div>
            <span class="l-dmr">定金总额：{{info.totalDepositPrice}}元</span>
          </div>
          <div>
            <div class="l-dmr">成交总额：<span class="l-txt-theme">{{info.totalFinalPrice}}元</span></div>
          </div>
        </div>
      </div>

      <divider class="l-divider">订购信息</divider>
      <div v-if="info.customers.length > 0">
        <div class="l-flex-h l-list-tab-tit">
          <div class="l-rest l-scroll-x">
            <div class="l-padding-t">
              <div class="_item" :class="{'_slted': item.checked}" v-for="(item,index) in info.customers" :key="item.id" @click="tabCustomer(item.id)">
                <span>客户{{index+1}}</span>
              </div>
            </div>
          </div>
        </div> 
        <div class="l-list-tab-cont l-padding" :class="{'_slted': item.checked}" v-for="item in info.customers" :key="item.id">
          <!-- 客户信息 -->
          <div>
            <span class="l-dmr">客户姓名：{{item.userName || '--'}}</span>
            <span>联系电话：{{item.userPhone || '--'}}</span>
          </div>
          <div>身份证照片：</div>
          <div class="l-flex-hc l-margin-t-s">
            <div class="l-txt-center">
              <div class="l-idcard-img">
                <img :id="item.idCardPicOn" :src="item.idCardPicOn">
              </div>
              <div class="l-txt-gray l-fs-s">正面</div> 
            </div>
            <div class="l-txt-center l-margin-l">
              <div class="l-idcard-img">
                <img :id="item.idCardPicOff" :src="item.idCardPicOff">
              </div>
              <div class="l-txt-gray l-fs-s">反面</div> 
            </div>
          </div>

          <div class="vux-1px-b l-padding-b l-margin-b"></div>

          <!-- 车辆信息 -->
          <div class="l-margin-t" :class="index < item.infos.length - 1? 'vux-1px-b l-padding-b' : ''" v-for="(carItem,index) in item.infos" :key="carItem.id">
            <div>{{carItem.carsName}}</div>
            <div class="l-txt-gray">
              <span class="l-dmr">车身颜色：{{carItem.colorName}}</span>
              <span>指导价：{{carItem.guidePrice}}元</span>
            </div>

            <div class="l-margin-t-s">
              <div>
                <span class="l-dmr">裸车价：{{carItem.nakedPrice}}元</span>
                <span>购买数量：{{carItem.carNum}}</span>
              </div>
              <div>
                <span class="l-dmr">交强险：{{carItem.trafficCompulsoryInsurancePrice || 0}}元</span>
                <span>商业险：{{carItem.commercialInsurancePrice || 0}}元</span>
              </div>
              <div>
                <span class="l-dmr">应付定金：{{carItem.depositPrice}}元</span>
                <span>{{carItem.changePrice > 0 ? "加价金额" : "优惠金额"}}：{{carItem.changePrice2}}元</span>
              </div>
            </div>

            <div class="l-margin-t-s l-txt-gray l-fs-s" v-if="carItem.remark">备注：{{carItem.remark}}</div>

            <!-- 车架号列表 -->
            <div class="l-margin-t-s l-fs-s" v-if="carItem.cars && carItem.cars.length > 0">
              <div v-for="frame in carItem.cars" :key="frame.id">
                <div class="l-flec-hc">
                  <span class="l-dmr">车架号：{{frame.vin}}</span>
                  <span>内饰颜色：{{frame.interiorName}}</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 提车人 -->
      <template v-if="info.pickers.length > 0">
        <divider class="l-divider">提车人信息</divider>
        <div class="l-flex-h l-list-tab-tit">
          <div class="l-rest l-scroll-x">
            <div class="l-padding-t">
              <div class="_item" :class="{_slted: item.checked}" v-for="(item,index) in info.pickers" :key="item.id" @click="tabLinkMan(item.id)">
                <span>提车人{{index+1}}</span>
              </div>
            </div>
          </div>
        </div> 
        <div class="l-list-tab-cont l-padding" :class="{'_slted': item.checked}" v-for="item in info.pickers" :key="item.id">
          <div>
            <span class="l-dmr">提车人姓名：{{item.userName || '--'}}</span>
            <span>联系电话：{{item.userPhone || '--'}}</span>
          </div>
          <div>身份证照片：</div>
          <div class="l-flex-hc l-margin-t-s">
            <div class="l-txt-center">
              <div class="l-idcard-img">
                <img :id="item.idCardPicOn" :src="item.idCardPicOn">
              </div>
              <div class="l-txt-gray l-fs-s">正面</div> 
            </div>
            <div class="l-txt-center l-margin-l">
              <div class="l-idcard-img">
                <img :id="item.idCardPicOff" :src="item.idCardPicOff">
              </div>
              <div class="l-txt-gray l-fs-s">反面</div> 
            </div>
          </div>
        </div>
      </template>
      <!-- 提车人 end-->
      
      <!-- 物流信息 -->
      <template v-if="info.logisticsType == 2">
        <divider class="l-divider">物流信息</divider>
        <div class="l-margin-t l-bg-white l-padding">
          <div>物流单号：{{info.logisticsOrderCode || '--'}}</div>
          <div>
            <span class="l-dmr">物流公司：{{info.logisticsCompany || '--'}}</span>
            <span>运输车牌号：{{info.logisticsPlateNumber || '--'}}</span>
          </div>
          <div>
            <span class='l-dmr'>司机姓名：{{info.logisticsDriver || '--'}}</span>
            <span>联系电话：{{info.logisticsDriverPhone || '--'}}</span>
          </div>
        </div>
      </template>
      <!-- 物流信息 end-->
    </div>
  </view-box>
</template>
<script>
import { Divider } from 'vux'

export default {
  name: "order-info2",
  components: {
    Divider
  },
  data() {
    return {
      wuliu: ["自提", "其他", "送车"],
      orderType: ["常规单", "炒车单"],
      info: null
    }
  },
  methods: {
    getInfo() {
      this.$vux.loading.show()
      this.$api.order.getInfo2(this.$route.query.id).then(({ data }) => {
          // 客户信息
          data.customers.forEach(customer => {
            customer.infos.forEach(cars => {
              cars.changePrice2 = Math.abs(cars.changePrice)
              cars.auditNum = 0 // 待审核车辆
              cars.cars &&
                cars.cars.forEach(frame => {
                  frame.isTicket = !!(
                    frame.ticketPic ||
                    frame.certificationPic ||
                    frame.tciPic ||
                    frame.ciPic ||
                    frame.expressPic ||
                    frame.otherPic
                  )
                  if (frame.auditState == 5) {
                    cars.auditNum += 1
                  }
                })
            })
          })

          // 支付信息
          let pay1Image = [],
            pay2Image = []
          data.orderPaymentVOs.forEach(pay => {
            if (pay.voucher) {
              if (pay.type == 1) {
                pay1Image = pay1Image.concat(pay.voucher.split(','))
              } else if (pay.type == 2) {
                pay2Image = pay2Image.concat(pay.voucher.split(','))
              }
            }
          })
          data.pay1Image = pay1Image
          data.pay2Image = pay2Image

          this.info = data

          if (data.pickers.length > 0) {
            this.tabLinkMan(data.pickers[0].id)
          }
          if (data.customers.length > 0) {
            this.tabCustomer(data.customers[0].id)
          }
        })
        .finally(_ => {
          this.$vux.loading.hide()
        })
    },
    // 提车人tab
    tabLinkMan: function(id) {
      this.info.pickers = this.info.pickers.map(item => {
        item.checked = item.id == id
        return item
      })
    },
    // 客户tab
    tabCustomer: function(id) {
      this.info.customers = this.info.customers.map(item => {
        item.checked = item.id == id
        return item
      })
    }
  },
  mounted() {
    this.getInfo()
  }
}
</script>
<style lang="less" scoped>
.l-dmr{display:inline-block; min-width: 45%; margin-right: 10px;}
.l-divider{margin: 15px 20% -5px; color: #999;}
.l-idcard-img{width: 125px; height: 75px; background-color: #eee;}
.l-idcard-img img{width: 100%; height: 100%;}
/* 提车人 */
.l-list-tab-tit{white-space: nowrap;}
.l-list-tab-tit ._item{display: inline-block; background-color: #fa5539; color: #fff; padding: 3px 10px; margin-right: 2px; opacity: 0.3;}
.l-list-tab-tit ._item._slted{
  border-radius: 7.5px 0 0 0; margin-right: 5px; padding-right: 15px;
  opacity: 1;  transform: scale(1.16); transform-origin: 0 100%; 
  clip-path: polygon(0 0, 90% 0, 100% 100%, 0 100%);
}
.l-list-tab-tit ._item._slted span{ display: block; transform: scale(0.84);}
.l-list-tab-tit ._add{display: inline-block; background-color: #d9d9d9; color: #fff; padding: 2.5px 15px;}
.l-list-tab-cont{background-color: #fff; border-radius: 0 0 5px 5px; display: none;}
.l-list-tab-cont._slted{display: block;}
</style>


