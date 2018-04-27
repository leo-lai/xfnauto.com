<template>
  <view-box>
    <div class="l-bg-white l-zoom" v-if="info">
      <div class="l-flex-hc l-padding vux-1px-b">
        <img class="l-thumb l-margin-r" :src="info.thumb" alt="">
        <div class="l-rest l-fs-m">
          <h4>{{info.carsName}}</h4>
          <p>
            <span class="l-txt-gray">指导价：<i class="l-rmb">{{info.guidancePriceStr}}万</i></span>
            <span class="l-txt-gray l-margin-l">数量：{{info.carNumber}}</span>
          </p>
        </div>
        <!-- <div class="l-txt-theme l-fs-m">{{state[info.loneState]}}</div> -->
      </div>
    </div>

    <template v-if="info && info.loanType == 2">
      <div class="l-bg-white l-zoom l-margin-t">
        <div class="l-flex-hc l-padding-btn">
          <img class="l-img-icon l-margin-r-m" src="../assets/images/icon-022.png" alt="">
          <h4 class="l-rest">审核资料</h4>
        </div>
        <group gutter="0" v-if="storeInfo" label-width="6em">
          <cell title="商家类型" :value="getStoreType(storeInfo.natureType)"></cell>
          <cell title="店铺名称" :value="storeInfo.shortName"></cell>
          <cell title="店铺地址" align-items="flex-start" :value="getAddress()"></cell>
          <cell title="店铺描述" align-items="flex-start" :value="storeInfo.introduce"></cell>
          <cell title="企业法人身份证" align-items="flex-start">
            <div slot="title" style="width: 10em;">企业法人身份证</div>
            <div class="l-idcard-upload l-flex-hc" slot="inline-desc">
              <div class="_item">
                <img :src="storeInfo.idCardPicOn" alt="">
                <p class="l-txt-gray l-fs-s">身份证正面</p>
              </div>
              <div class="l-margin-l"></div>
              <div class="_item">
                <img :src="storeInfo.idCardPicOff" alt="">
                <p class="l-txt-gray l-fs-s">身份证反面</p>
              </div>
            </div>
          </cell>
          <cell title="营业执照" align-items="flex-start">
            <div class="l-margin-t-m" slot="inline-desc">
              <div class="l-preview-imgs">
                <img class="_item" :src="item" v-for="item in businessLicense" :key="item">
              </div>
            </div>
          </cell>
          <cell title="店铺照片" align-items="flex-start">
            <div class="l-margin-t-m" slot="inline-desc">
              <div class="l-preview-imgs">
                <img class="_item" :src="item" v-for="item in storeImages" :key="item">
              </div>
            </div>
          </cell>
        </group>
      </div>
    </template>

    <template v-if="info && info.loanType == 1">
      <div class="l-bg-white l-zoom">
        <div class="l-margin">
          <span class="l-seek-tag1">
            <img class="_icon" src="../assets/images/icon-016.png" alt="">申请人：{{info.loanPeopleName}}
          </span>
          <span class="l-seek-tag1 l-margin-l-s">
            <img class="_icon" src="../assets/images/icon-017.png" alt="">联系电话：{{info.loanPeoplePhone}}
          </span>
        </div>
        <div class="l-margin l-fs-m">
          <div>
            <span class="l-fr">{{info.institutionName}}</span>
            <span class="l-txt-gray">金融机构：</span>
          </div>
          <div>
            <span class="l-fr">{{(info.downPayments * 100)}}%</span>
            <span class="l-txt-gray">首付比例：</span>
          </div>
          <div>
            <span class="l-fr">{{info.loanPeriod}}</span>
            <span class="l-txt-gray">还款期数：</span>
          </div>
          <div>
            <span class="l-fr"><i class="l-rmb l-txt-theme">{{info.loanAmount}}</i></span>
            <span class="l-txt-gray">贷款总额：</span>
          </div>
        </div>
      </div>

      <div class="l-flex-hc l-padding-btn l-bg-white l-margin-t">
        <img class="l-img-icon l-margin-r-m" src="../assets/images/icon-022.png" alt="">
        <h4 class="l-rest">审核资料</h4>
      </div>
      <group gutter="0" v-if="info" label-width="6em">
        <cell title="申请人身份证" align-items="flex-start">
          <div slot="title" style="width: 10em;">申请人身份证</div>
          <div class="l-idcard-upload l-flex-hc" slot="inline-desc">
            <div class="_item">
              <img :src="info.idCardPicOn" alt="">
              <p class="l-txt-gray l-fs-s">身份证正面</p>
            </div>
            <div class="l-margin-l"></div>
            <div class="_item">
              <img :src="info.idCardPicOff" alt="">
              <p class="l-txt-gray l-fs-s">身份证反面</p>
            </div>
          </div>
        </cell>
        <cell title="收入证明" align-items="flex-start">
          <div class="l-margin-t-m" slot="inline-desc">
            <div class="l-preview-imgs">
              <img class="_item" :src="item" v-for="item in annualIncomeImage" :key="item">
            </div>
          </div>
        </cell>
      </group>
    </template>

  </view-box>
</template>

<script>
export default {
  name: 'loan-info',
  data () {
    return {
      state: ['申请中', '已通过', '已拒绝'],
      storeInfo: null,
      businessLicense: [],
      storeImages: [],
      annualIncomeImage: [],
      info: null
    }
  },
  methods: {
    getBankName(key = 1) {
      return (this.$config.bankList.filter(item => item.key === key)[0] || {}).value
    },
    getStoreType(key = 1) {
      return (this.$config.storeType.filter(item => item.key === key)[0] || {}).value
    },
    getAddress() {
      let {provinceName, cityName, areaName, address} = this.storeInfo
      return (provinceName === cityName ? provinceName : provinceName + cityName) + areaName + address
    },
    getInfo() {
      this.$vux.loading.show()
      this.$api.loan.getInfo(this.$route.query.id).then(({data}) => {
        data.thumb = this.$utils.imgThumb(data.image, 120, 120) || this.$config.thumb1
        data.guidancePriceStr = (data.guidancePrice / 10000).toFixed(2)

        this.annualIncomeImage = data.annualIncomeImage ? data.annualIncomeImage.split(',') : []

        this.info = data
        if(data.organizationVo) {
          this.storeInfo = data.organizationVo
          this.businessLicense = data.organizationVo.businessLicense ? data.organizationVo.businessLicense.split(',') : []
          this.storeImages = data.organizationVo.imageUrl ? data.organizationVo.imageUrl.split(',') : []
        }

        this.$utils.setTitle(data.loanType == 2 ? '垫资详情' : '贷款详情')
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

