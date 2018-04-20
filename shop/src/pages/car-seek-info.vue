<template>
  <view-box>
    <div class="l-bg-white l-zoom" v-if="info">
      <div class="l-flex-hc l-padding vux-1px-b">
        <img class="l-thumb l-margin-r" :src="info.thumb" alt="">
        <div class="l-rest">
          <h4>{{info.carsName}}</h4>
          <p class="l-fs-m">
            <span class="l-txt-gray">期望价：</span>
            <span class="l-txt-theme l-rmb l-fs-l">{{info.expectationAmount}}万</span>
            <span class="vux-1px-r l-margin-lr-m"></span>
            <span class="l-txt-gray">指导价：<i class="l-rmb">{{info.guidancePriceStr}}万</i></span>
          </p>
        </div>
      </div>
      <div class="l-margin">
        <span class="l-seek-tag1">
          <img class="_icon" src="../assets/images/icon-016.png" alt="">发布人：{{info.shopUsersVo.realName}}
        </span>
        <span class="l-seek-tag1 l-margin-l-s">
          <img class="_icon" src="../assets/images/icon-015.png" alt="">创建日期：{{info.createDate}}
        </span>
      </div>
      <div v-if="info.remarks" class="l-margin l-remark">{{info.remarks}}</div>
      <div class="l-margin l-fs-m">
        <div>
          <span class="l-fr">{{info.colourName}}</span>
          <span class="l-txt-gray">车身颜色：</span>
        </div>
        <div>
          <span class="l-fr">{{info.interiorName}}</span>
          <span class="l-txt-gray">内饰颜色：</span>
        </div>
        <div>
          <span class="l-fr">{{info.signCity}}</span>
          <span class="l-txt-gray">上牌城市：</span>
        </div>
        <div>
          <span class="l-fr">{{info.linkmanName}}</span>
          <span class="l-txt-gray">寻车人：</span>
        </div>
        <div>
          <span class="l-fr">{{info.linkmanPhone}}</span>
          <span class="l-txt-gray">联系电话：</span>
        </div>
      </div>
    </div>

    <div class="l-bg-white l-margin-t" v-if="info">
      <div class="l-flex-hc l-padding-btn vux-1px-b">
        <img class="l-img-icon l-margin-r-m" src="../assets/images/icon-020.png" alt="">
        <h4 class="l-rest">谁报过价</h4>
      </div>
      <template v-if="info.findCarOffers.length > 0">
        <div class="l-padding vux-1px-b" v-for="item in info.findCarOffers" :key="item.findCarOfferId">
          <div class="l-flex-hc">
            <div class="l-rest l-fs-m">
              <span>{{item.systemUserName}}</span>
              <span class="vux-1px-r l-margin-lr-m"></span>
              <span class="l-txt-gray">{{item.orgName}}</span>
              <span v-if="item.offerState == 1" class="l-seek-tag2">已过期</span>
            </div>
            <div>
              <span class="l-fs-m l-txt-gray">Ta的报价</span>
              <span class="l-rmb l-txt-theme l-fs-l">{{item.offerAmount}}万</span>
            </div>
          </div>
          <div class="l-fs-m l-txt-gray l-margin-t-m">
            <p>车辆所在地：{{item.location}}</p>
            <p>
              <a :href="'tel:' + item.systemUserPhone" class="l-fr l-seek-tag3">
                <img class="_icon" src="../assets/images/icon-017.png" alt="">联系Ta
              </a>
              有效期至：{{item.overdueDate}}
            </p>
          </div>
        </div>
      </template>
      <div v-else class="l-bg-white l-padding l-txt-center" style="padding-top: 10%;padding-bottom: 10%;">
        <img style="width: 50%; margin-right: -1.2rem;" src="../assets/images/20180402014.png" alt="">
        <p class="l-txt-gray l-margin-t l-fs-m">还没有人报价哦~</p>
      </div>
    </div>
  </view-box>
</template>
<script>
export default {
  name: 'car-seek-info',
  data () {
    return {
      info: null
    }
  },
  methods: {
    getInfo() {
      this.$vux.loading.show()
      this.$api.seek.getInfo(this.$route.query.id).then(({data}) => {
        data.thumb = this.$utils.imgThumb(data.image, 120, 120) || this.$config.thumb1
        data.guidancePriceStr = (data.guidancePrice / 10000).toFixed(2)
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


