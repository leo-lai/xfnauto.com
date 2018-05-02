<template>
  <view-box>
    <div class="l-zoom l-fs-m" v-if="info">
      <group label-width="7em" gutter="0" title="订单基本信息">
        <cell title="客户身份证号" :value="info.customerUserCard"></cell>
        <cell title="订购车辆" align-items="start">
          <div class="l-fs-m">{{info.carsName}}</div>
        </cell>
        <cell title="官方指导价">
          <div>{{info.carsVo.price}}元</div>
        </cell>
        <cell title="车架号" :value="info.frameNumber"></cell>
        <cell title="车身颜色" :value="info.colourName"></cell>
        <cell title="内饰颜色" :value="info.interiorName"></cell>
        <cell title="销售顾问" :value="info.systemUserName"></cell>
      </group>

      <group label-width="7em" title="订单费用">
        <cell title="实际成交裸车价">
          <div>{{info.carUnitPrice}}元</div>
        </cell>
        <cell title="车辆购置税 ">
          <div>{{info.purchaseTaxPriace}}元</div>
        </cell>
        <cell title="上牌费用">
          <div>{{info.licensePlatePriace}}元</div>
        </cell>
        <cell title="商业保险费用">
          <div>{{info.insurancePriace}}元</div>
        </cell>
        <cell title="按揭费用">
          <div>{{info.mortgagePriace}}元</div>
        </cell>
        <cell title="精品选装费用">
          <div>{{info.boutiquePriace}}元</div>
        </cell>
        <cell title="车船税">
          <div>{{info.vehicleAndVesselTax}}元</div>
        </cell>
        <cell>
          <div class="l-txt-theme">总费用金额：￥{{info.amount}}</div>
          <div class="l-txt-warn">定金金额：￥{{info.depositPrice}}</div>
        </cell>
      </group>

      <group label-width="7em" title="购车方案">
        <cell title="购车方案" :value="paymentWay[info.paymentWay]"></cell>
        <template v-if="info.paymentWay == 2">
          <cell title="贷款银行" :value="getBankName(info.loanBank)"></cell>
          <cell title="首付金额">
            <div>{{info.downPayments}}元</div>
          </cell>
          <cell title="贷款金额">
            <div>{{info.loan}}元</div>
          </cell>
          <cell title="分期期数" :value="info.loanPayBackStages"></cell>
          <cell title="是否抵押车辆" :value="info.isMortgage ? '是' : '否'"></cell>
        </template>
      </group>

      <group v-if="info.parts1.length > 0" title="赠送精品">
        <cell>
          <div slot="inline-desc" class="l-padding-tb-m">
            <div v-if="info.parts1.length === 0" class="l-txt-center l-txt-gray">无</div>
            <div v-else class="l-tags">
              <span class="l-tag" v-for="item in info.parts1" :key="item">{{item}}</span>
            </div>
          </div>
        </cell>
      </group>

      <group v-if="info.parts2.length > 0" title="加装精品">
        <cell>
          <div slot="inline-desc" class="l-padding-tb-m">
            <div v-if="info.parts2.length === 0" class="l-txt-center l-txt-gray">无</div>
            <div v-else class="l-tags">
              <span class="l-tag" v-for="item in info.parts2" :key="item">{{item}}</span>
            </div>
          </div>
        </cell>
      </group>

      <group v-if="info.remark" title="订单备注">
        <cell>
          <div slot="inline-desc" class="l-padding-tb-m">{{info.remark}}</div>
        </cell>
      </group>

      <div class="l-margin-t"></div>
    </div>
  </view-box>
</template>
<script>
import { Divider } from 'vux'

export default {
  name: "order-info3",
  components: {
    Divider
  },
  data() {
    return {
      bank: [
        { id: 1, name: '奇瑞金融' },
        { id: 2, name: '瑞福德金融' },
        { id: 3, name: '建设银行' },
        { id: 4, name: '农业银行' },
        { id: 5, name: '工商银行' },
        { id: 6, name: '广州银行' },
        { id: 7, name: '鹤山珠江村镇银行' },
        { id: 8, name: '鹤山农村信用合作社' }
      ],
      paymentWay: ['', '全款购车', '贷款购车'],
      info: null
    }
  },
  methods: {
    getBankName(id = '') {
      return (this.bank.filter(item => item.id == id)[0] || {}).name || ''
    },
    getInfo() {
      this.$vux.loading.show()
      this.$api.order.getInfo2(this.$route.query.id).then(({ data }) => {
        data.parts1 = data.followInformation ? data.followInformation.split(',') : []
        data.parts2 = data.boutiqueInformation ? data.boutiqueInformation.split(',') : []
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
.l-tags{margin: -5px -5px 0 0;}
.l-tag{padding: 2px 10px; background-color: #eee; border-radius: 2px; margin: 5px 5px 0 0; }
</style>


