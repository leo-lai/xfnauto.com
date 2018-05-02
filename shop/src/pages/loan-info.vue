<template>
  <view-box>
    <template v-if="info && info.loanType == 2">
      <div class="l-bg-white l-zoom">
        <div class="l-flex-hc l-padding vux-1px-b">
          <img class="l-thumb l-margin-r" :src="info.thumb" alt="">
          <div class="l-rest l-fs-m">
            <h4>{{info.carsName}}</h4>
            <p>
              <span class="l-txt-gray">指导价：<i class="l-rmb">{{info.guidancePriceStr}}万</i></span>
              <span v-if="info.carNumber" class="l-txt-gray l-margin-l">数量：{{info.carNumber}}</span>
            </p>
          </div>
        </div>
      </div>
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
      <div class="l-bg-white">
        <flow>
          <flow-state state="1" title="申请金融贷" is-done>
            <div class="l-fs-s" slot="title">申请<br>金融贷</div>
          </flow-state>
          <flow-line is-done></flow-line>
          <flow-state state="2" title="提交个人资料" is-done>
            <div class="l-fs-s" slot="title">提交<br>个人资料</div>
          </flow-state>
          <flow-line is-done></flow-line>
          <flow-state state="3" title="初审结果通知" is-done>
            <div class="l-fs-s" slot="title">初审<br>结果通知</div>
          </flow-state>
          <flow-line></flow-line>
          <flow-state state="4" title="终审免签提车" :is-done="info.loneState == 1">
            <div class="l-fs-s" slot="title">终审<br>免签提车</div>
          </flow-state>
        </flow><br>
      </div>
      <div class="l-bg-white l-zoom l-margin-t">
        <div class="l-flex-hc l-padding vux-1px-b">
          <img class="l-thumb l-margin-r" :src="info.thumb" alt="">
          <div class="l-rest l-fs-m">
            <h4>{{info.carsName}}</h4>
            <p>
              <span class="l-txt-gray">指导价：<i class="l-rmb">{{info.guidancePriceStr}}万</i></span>
              <span v-if="info.carNumber" class="l-txt-gray l-margin-l">数量：{{info.carNumber}}</span>
            </p>
          </div>
        </div>
        <div class="l-margin l-fs-m">
          <!-- <div>
            <span class="l-fr">{{info.institutionName}}</span>
            <span class="l-txt-gray">金融机构：</span>
          </div> -->
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
      <div class="l-flex-hc l-margin-tit">
        <img class="l-img-icon l-margin-r-s" src="../assets/images/icon-024.png" alt="">
        <h4 class="l-rest">提交资料</h4>
      </div>
      <group gutter="0">
        <cell title="申请人姓名" :value="info.loanPeopleName"></cell>
        <cell title="申请人电话" :value="info.loanPeoplePhone"></cell>
        <cell title="上传身份证照片" align-items="flex-start">
          <div slot="title" style="width: 10em;">上传身份证照片</div>
          <div class="l-idcard-upload l-flex-hc" slot="inline-desc">
            <div class="_item" @click="$api.previewImage([info.idCardPicOn, info.idCardPicOff], 0)">
              <img :src="info.idCardPicOn" alt="">
              <p class="l-txt-gray l-fs-s">身份证正面</p>
            </div>
            <div class="l-margin-l"></div>
            <div class="_item" @click="$api.previewImage([info.idCardPicOn, info.idCardPicOff], 1)">
              <img :src="info.idCardPicOff" alt="">
              <p class="l-txt-gray l-fs-s">身份证反面</p>
            </div>
          </div>
        </cell>
      </group>
    </template>
  </view-box>
</template>

<script>
import { Flow, FlowState, FlowLine } from 'vux'
export default {
  name: 'loan-info',
  components: {
    Flow, FlowState, FlowLine
  },
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

