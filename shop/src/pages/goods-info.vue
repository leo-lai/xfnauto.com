<template>
  <view-box>
    <div class="l-goods-info" v-if="info">
      <swiper auto :list="bannerSwiper.list" dots-position="center" :show-desc-mask="false"></swiper>
      <div class="l-padding-btn l-bg-white">
        <h4>
          <!-- <span class="_tag0">新品</span> -->
          {{info.carsName}}
        </h4>
        <p>
          <span class="_tag1">车身颜色：{{info.colourName}}</span>
          <span class="_tag1">内饰颜色：{{info.interiorName}}</span>
        </p>
        <p class="l-margin-t-s">
          <span class="_tag2 l-fr" v-if="info.overInsurance">带交强险</span>
          <span class="l-txt-theme">{{info.goodsCarsActivityId ? '活动价：' : '裸车价：' }}<i class="l-rmb">{{info.saleingPriceStr}}</i>万</span>
          <span class="l-txt-gray l-fs-s l-margin-l">指导价：<i class="l-rmb">{{info.guidingPriceStr}}</i>万</span>
        </p>
        <p class="l-margin-t l-fs-s">
          <span class="l-txt-icon l-fr" border title="买车计算器" style="margin-top:-4px;" @click="showCounter">
            <i class="l-icon">&#xe618;</i>
          </span>
          <span class="l-txt-gray">仓库：{{info.warehouseName || '--'}}</span>
          <span class="l-txt-gray l-margin-l">{{info.goodsCarsActivityId ? '活动数量：' : '库存：' }}{{info.saleingNumber || '--'}} 辆</span>
        </p>
      </div>

      <div class="l-padding-btn l-bg-white l-flex-h l-margin-tb">
        <div class="l-thumb1 l-margin-r" radius :style="{'background-image': 'url('+ info.usersVo.headPortrait +')'}"></div>
        <div class="l-rest">
          <p>
            {{info.usersVo.realName || '--'}} 
            <span class="l-fs-s l-txt-gray">{{info.usersVo.orgName}}</span></p>
          <p>{{info.usersVo.phoneNumber}}</p>
        </div>
        <div>
          <p>
            <a :href="'tel:'+ info.usersVo.phoneNumber" border class="l-txt-icon l-margin-l-s" title="联系Ta">
              <i class="l-icon">&#xe613;</i>
            </a>
          </p>
          <p v-if="qrcode.img">
            <a @click="qrcode.visible =true" border class="l-txt-icon l-margin-l-s" title="加微信">
              <i class="l-icon">&#xe617;</i>
            </a>
          </p>
        </div>
      </div>

      <div class="l-bg-white l-margin-tb">
        <div class="l-flex-hc l-padding-btn vux-1px-b">
          <img class="l-img-icon l-margin-r-m" src="../assets/images/icon-013.png" alt="">
          <h4 class="l-rest">车辆信息</h4>
        </div>
        <div class="l-table-info l-fs-m">
          <table>
            <tr>
              <td class="_tit">所属区域：</td>
              <td class="l-txt-right">{{info.organization.cityName || '--'}}</td>
            </tr>
            <tr>
              <td class="_tit">生产日期：</td>
              <td class="l-txt-right">{{info.dateOfManufacture || '--'}}</td>
            </tr>
            <tr>
              <td class="_tit">开票价格：</td>
              <td class="l-txt-right">{{info.invoicePrice || '--'}} 元</td>
            </tr>
            <tr>
              <td class="_tit">开票周期：</td>
              <td class="l-txt-right">{{info.invoiceCycle || '--'}} 天</td>
            </tr>
            <tr>
              <td class="_tit">{{info.goodsCarsActivityId ? '活动描述：' : '备注信息：' }}</td>
              <td class="l-txt-right">{{info.remarks || '--'}}</td>
            </tr>
          </table>
        </div>
        
        <img class="l-img-block" :src="item" alt="" v-for="(item,index) in info.carsImageArr" :key="item" @click="$api.previewImage(info.carsImageArr, index)">
      </div>

      <div class="l-goods-placeholder"></div>
      <div class="l-goods-fixed">
        <div class="l-flex-hc l-bg-white">
          <a class="_btn3" :href="'tel:'+ info.usersVo.phoneNumber">
            <i class="l-icon">&#xe606;</i>
          </a>
          <a v-if="qrcode.img" class="_btn3" @click="qrcode.visible =true">
            <i class="l-icon">&#xe62d;</i>
          </a>

          <div class="l-rest"></div>
          <router-link class="_btn2" to="/car/seek">
            <img src="../assets/images/icon-025.png" alt="">我要寻车
          </router-link>
          <div class="_btn1" @click="gotoOrder">
            <img src="../assets/images/icon-026.png" alt="">我要留车
          </div>
        </div>

        <!-- 买车计算器 -->
        <div v-transfer-dom>
          <popup v-model="counter.visible" position="bottom" height="70%">
            <div class="l-buyway-tab l-scroll" style="height: 100%;">
              <div class="l-flex-hc _tit">
                <div class="_item l-rest" :class="{_active: counter.tabIndex === 0}" @click="counterTab(0)">
                  全款购车
                  <inline-loading :class="{_loading: counter.fullLoading}"></inline-loading>
                </div>
                <div class="_item l-rest" :class="{_active: counter.tabIndex === 1}" @click="counterTab(1)">
                  分期贷款
                  <inline-loading :class="{_loading: counter.loanLoading}"></inline-loading>
                </div>
              </div>
              <div class="_cont" v-show="counter.tabIndex === 0">
                <div class="_all l-fs-14 l-text-theme">预计总价：{{counter.fullPayment.fullPrice}}</div>
              </div>
              <div class="_cont" v-if="counter.tabIndex === 1">
                <div class="_all">
                  <span class="l-fs-14 l-text-theme">预计总价：{{counter.loanPayment.fullPrice}}</span>
                  <div class="l-flex-hc l-margin-t-m l-fs-m" style="color: rgb(235, 97, 0);">
                    <span class="l-rest" style="width:33%;text-align:left;">首付:￥{{counter.loanPayment.downPayments}}</span>
                    <span class="l-rest" style="width:33%;text-align:center;">月供:￥{{counter.loanPayment.forTheMonth}}</span>
                    <span class="l-rest" style="width:33%;text-align:right;">利息:￥{{counter.loanPayment.interest}}</span>
                  </div>
                </div>
                <group class="l-margin-b" gutter="0">
                  <cell title="首付比列" primary="content">
                    <div class="l-range-text">
                      <div class="_text">
                        <span>20%</span>
                        <span>30%</span>
                        <span>40%</span>
                        <span>50%</span>
                      </div>
                      <range v-model="counter.percent" :min="20" :max="50" :step="10" @on-change="getLoanPayment"></range>
                    </div>
                  </cell>
                  <cell title="贷款年限" primary="content">
                    <div class="l-range-text">
                      <div class="_text">
                        <span>1年</span>
                        <span>2年</span>
                        <span>3年</span>
                        <span>4年</span>
                      </div>
                      <range v-model="counter.year" :min="1" :max="4" :step="1" @on-change="getLoanPayment"></range>
                    </div>
                  </cell>
                </group>
              </div>

              <group gutter="0">
                <cell title="官方指导价"><span class="l-rmb">{{counter.data.guidancePrice}}</span></cell>
                <cell title="购置税"><span class="l-rmb">{{counter.data.purchaseTax}}</span></cell>
                <cell title="上牌费用"><span class="l-rmb">{{counter.data.premium}}</span></cell>
                <cell title="车船税"><span class="l-rmb">{{counter.data.vehicleAndAesselTax}}</span></cell>
                <cell title="交强险"><span class="l-rmb">{{counter.data.strongInsurance}}</span></cell>
              </group>
              
              <group title="商业保险">
                <popup-picker :popup-style="{zIndex: 502}" :data="insurance.list" v-model="insurance.slted" show-name placeholder="请选择" :display-format="insuranceFormat">
                  <span slot="title">第三者责任险({{insurance.name}})</span>
                </popup-picker>
                <cell title="车辆损失险"><span class="l-rmb">{{counter.data.vehicleLossInsurance}}</span></cell>
                <cell title="全车盗抢险"><span class="l-rmb">{{counter.data.robberyAndTheftInsurance}}</span></cell>
                <cell title="玻璃单独破碎险"><span class="l-rmb">{{counter.data.riskOfGlassBreakage}}</span></cell>
                <cell title="自燃损失险"><span class="l-rmb">{{counter.data.selfIgnitionLossInsurance}}</span></cell>
                <cell title="不计免赔特约险"><span class="l-rmb">{{counter.data.exemptionFromSpecialContract}}</span></cell>
                <cell title="无过责任险"><span class="l-rmb">{{counter.data.noLiabilityInsurance}}</span></cell>
                <cell title="车上人员责任险"><span class="l-rmb">{{counter.data.personnelLiabilityInsurance}}</span></cell>
                <cell title="车身划痕险"><span class="l-rmb">{{counter.data.bodyScratchRisk}}</span></cell>
              </group>

              <div class="l-txt-center l-txt-gray l-padding-btn l-fs-m">政策不同可能导致落地价格稍有偏差</div>
            </div>
          </popup>
        </div>

        <!-- 微信二维码 -->
        <div v-transfer-dom>
          <x-dialog v-model="qrcode.visible" hide-on-blur :dialog-style="{'max-width': '100%', width: '100%', 'background-color': 'transparent'}">
            <div class="l-qrcode">
              <img :src="qrcode.img" alt="">
            </div>
            <div class="l-margin-t">
              <x-icon type="ios-close-outline" style="fill:#fff;" @click="qrcode.visible = false"></x-icon>
            </div>
          </x-dialog>
        </div>
      </div>
    </div>
  </view-box>
</template>

<script>
import { Swiper, Popup, Range, InlineLoading, PopupPicker, XDialog } from 'vux'
export default {
  name: 'goods-info',
  components: { Swiper, Popup, Range, InlineLoading, PopupPicker, XDialog },
  data () {
    return {
      qrcode: {
        img: '',
        visible: false,
      },
      insurance: {
        name: '20万',
        slted: ['200000'],
        list: [[
          {name: '5万', value: '50000', price: 516},
          {name: '10万', value: '100000', price: 746},
          {name: '20万', value: '200000', price: 924},
          {name: '50万', value: '500000', price: 1252},
          {name: '100万', value: '1000000', price: 1630},
        ]]
      },
      counter: {
        visible: false,
        tabIndex: 0,
        percent: 30,
        year: 3,
        fullLoading: false,
        fullPayment: {},
        loanLoading: false,
        loanPayment: {},
        data: {}
      },
      isActive: 0,
      bannerSwiper: {
        list: [
          { 
            url: 'javascript:', 
            img: require('../assets/images/20180402002.jpg'), 
            title: ''
          }
        ]
      },
      info: null
    }
  },
  watch: {
    'insurance.slted': {
      deep: true,
      handler(value) {
        if(value && value[0]){
          let slted = this.insurance.list[0].filter(item => item.value == value[0])[0]
          this.insurance.name = slted.name
          this.counter.tabIndex == 0 ? this.getFullPayment() : this.getLoanPayment()
        }
      }
    }
  },
  methods: {
    insuranceFormat(value) {
      if(value && value[0]){
        return '￥' + this.insurance.list[0].filter(item => item.value == value[0])[0].price
      }
      return ''
    },
    getInfo() {
      this.$vux.loading.show()
      let promise = null

      if(this.isActive) {
        promise = this.$api.goods.getActiveInfo(this.$route.query.id)  
      }else{
        promise = this.$api.goods.getInfo(this.$route.query.id)
      }

      promise.then(({data}) => {
        data.thumb = this.$utils.imgThumb(data.image, 100, 100) || this.$config.thumb1
        data.carsImageArr = data.carsImage ? data.carsImage.split(',') : []
        data.carsImagesArr = data.carsImages ? data.carsImages.split(',') : []
        if(data.carsImagesArr.length > 0) {
          this.bannerSwiper.list = data.carsImagesArr.map(item => {
            return {
              url: 'javascript:', 
              img: this.$utils.imgThumb(item, 640, 400), 
              title: ''
            }
          })
        }
        data.guidingPriceStr = (data.guidingPrice / 10000).toFixed(2)
        
        if(data.goodsCarsActivityId) {
          data.goodsCarsId = data.goodsCarsActivityId
          data.saleingPriceStr = (data.activityPrice / 10000).toFixed(2)
        }else {
          data.saleingPriceStr = (data.bareCarPriceOnLine / 10000).toFixed(2)
        }

        if(!data.discountPriceOnLine) {
          data.discountPriceStr = ''
        }else {
          let discountPriceOnLine = Math.abs(data.discountPriceOnLine)
          data.discountPriceStr = (data.discountPriceOnLine > 0 ? '加价 ' : '降价 ')
          if(discountPriceOnLine >= 10000) {
            data.discountPriceStr += (discountPriceOnLine / 10000).toFixed(2) + '万元'
          }else {
            data.discountPriceStr += discountPriceOnLine+ '元'
          }
        }

        this.qrcode.img = data.usersVo && data.usersVo.weixinQrImage ? data.usersVo.weixinQrImage : ''
        this.info = data

        this.$api.wxShare({
          title: data.carsName,
          desc: '想批发价买新车，就上淘车网——您身边的汽车超市！'
        })
      }).finally(_ => {
        this.$vux.loading.hide()
      })
    },
    gotoOrder() {
      this.$storage.session.set('goods-info', this.info)
      this.$router.push('/goods/order')
    },
    showCounter() {
      this.counter.visible = true
      this.counterTab(this.counter.tabIndex)
    },
    counterTab(index = 0) {
      this.counter.tabIndex = index
      if(index == 0) {
        this.getFullPayment()
      }
    },
    getFullPayment() {
      if(this.counter.fullLoading) return

      this.counter.fullLoading = true
      this.$api.goods.getFullPayment({
        carsId: this.info.carsId,
        thirdPartyLiabilityInsuranceTopBack: this.insurance.slted[0]
      }).then(({data}) => {
        this.counter.fullPayment = data
        this.counter.data = data
      }).finally(_ => {
        this.counter.fullLoading = false
      })
    },
    getLoanPayment() {
      if(this.counter.loanLoading) return
      this.counter.loanLoading = true
      this.$api.goods.getLoanPayment({
        carsId: this.info.carsId,
        paymentRatio: this.counter.percent / 100,
        timeOfPayment: this.counter.year,
        thirdPartyLiabilityInsuranceTopBack: this.insurance.slted[0]
      }).then(({data}) => {
        this.counter.loanPayment = data
        this.counter.data = data
      }).finally(_ => {
        this.counter.loanLoading = false
      })
    }
  },
  created() {
    this.isActive = this.$route.query.isActive == 1 ? 1 : 0
  },
  mounted() {
    this.getInfo()
  }
}
</script>
<style lang="less" scoped>
.l-qrcode{
  background-color: #fff; padding: 15px; width: 200px; height: 200px;
  border-radius: 5px; margin: auto;
  img{width: 100%; height: 100%;}
}
.l-buyway-tab{background-color: #f4fafa;}
.l-buyway-tab ._tit {background-color: #fff; text-align: center; }
.l-buyway-tab ._tit ._item{width: 50%; padding: 10px 0;position: relative;}
.l-buyway-tab ._tit ._item::after{ content: ''; display: block; border: 8px solid transparent; 
border-bottom-color:#ffe8d8; width: 0; height: 0; margin: 0 auto -10px; visibility: hidden;}
.l-buyway-tab ._tit ._item._active{color: rgb(235, 97, 0);}
.l-buyway-tab ._tit ._item._active::after{visibility: visible;}
.l-buyway-tab ._all{background: #ffe8d8; text-align: center; padding:10px; box-shadow: 0 2px 3px 0 #ccc;position: relative;}
.l-buyway-tab .weui-loading{visibility: hidden;}
.l-buyway-tab ._loading{visibility: visible;}
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
  ._btn3{
    margin: 0 0 0 15px; color: #999;
    .l-icon{font-size: 22px !important;}
  }

}
.l-goods-info{
  ._tag0 {
    display: inline-block;
    background-color: #fd8842;
    color: #fff;
    border-radius: 2px;
    padding: 0 5px;
    font-size: 10px;
    vertical-align: middle;
  }
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

