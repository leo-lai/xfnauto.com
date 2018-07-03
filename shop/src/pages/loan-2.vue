<template>
  <view-box>
    <group gutter="0">
      <cell title="商铺认证" is-link @click.native="storeAuth">
        <img class="weui-cell__icon" slot="icon" src="../assets/images/icon-008.png">
        <span v-if="shopState[userInfo.shopState]" :class="shopState[userInfo.shopState].css">{{shopState[userInfo.shopState].name}}</span>
      </cell>
      <cell title="垫资资格认证" is-link @click.native="loanAuth">
        <img class="weui-cell__icon" slot="icon" src="../assets/images/20180402004.png">
        <span v-if="loanState[userInfo.loanState]" :class="loanState[userInfo.loanState].css">{{loanState[userInfo.loanState].name}}</span>
      </cell>
    </group>

    <template v-if="userInfo.shopState == 1 && userInfo.loanState == 1">
      <div class="l-bg-white l-zoom l-margin-t">
        <div class="l-flex-hc l-padding-btn">
          <img class="l-img-icon l-margin-r-m" src="../assets/images/icon-010.png" alt="">
          <h4 class="l-rest">申请垫资车辆</h4>
          <router-link class="l-fr l-link-main l-is-link" to="/loan/2/addcar">添加车辆</router-link>
        </div>
        <div class="l-car-item" v-for="item in carList" :key="item.guid">
          <div class="l-flex-h">
            <div class="l-rest">
              <h4 class="l-txt-wrap1">{{item.carName}}</h4>
            </div>
            <span @click="delcar(item.guid)"><icon type="clear"></icon></span>
          </div>
          <div class="l-txt-gray l-fs-s l-margin-t-s">
            <p>
              <span style="display: inline-block; width: 50%;">实际购车价：<i class="l-rmb">{{item.price | Int0}}</i></span>
              <span>垫资数量：{{item.number}}辆</span>
            </p>
            <p>
              <span style="display: inline-block; width: 50%;">保证金：<i>{{item.downPayments | Int0}}%</i></span>
              <span>垫资金额：<i class="l-rmb">{{item.amount | Int0}}</i></span>
            </p>
          </div>
        </div>
        <div v-if="carList.length === 0" class="l-car-none">您尚未添加车辆</div>
      </div>

      <group>
        <cell title="当前手续费率">
          <span>{{userInfo.rate}}</span><span class="l-txt-gray l-margin-l-s">%</span>
        </cell>
        <x-input title="垫资期限" keyboard="number" :max="20" placeholder="请输入垫资天数" placeholder-align="right" v-model="formData.period">
          <span class="l-txt-gray l-margin-l-s" slot="right">天</span>
        </x-input>
      </group>

      <div class="l-loan-placeholder"></div>
      <div class="l-loan-fixed">
        <div class="_tip l-fs-s"><i class="l-icon l-fs-s">&#xe619;</i> 确认提交申请，即表示您同意《淘车网垫资协议》</div>
        <div class="l-flex-hc l-bg-white">
          <div class="l-rest l-margin-l-m">
            <p>垫资总额：<span class="l-rmb l-txt-theme">{{formData.amount}}</span></p>
            <p class="l-fs-s l-txt-gray">预计手续费：<span class="l-rmb">{{formData.fee}}</span></p>
          </div>
          <div class="_btn1" @click="submit">确定提交申请</div>
        </div>
      </div>
    </template>
  </view-box>
</template>

<script>
export default {
  name: 'loan-2',
  data () {
    return {
      userInfo: {},
      shopState: {
        '-1': {
          css: 'l-txt-gray',
          name: '去认证'
        },
        '0': {
          css: 'l-txt-warn',
          name: '审核中'
        },
        '1': {
          css: 'l-txt-theme',
          name: '已认证'
        },
        '2': {
          css: 'l-txt-error',
          name: '认证不通过'
        },
      },
      loanState: {
        '-1': {
          css: 'l-txt-gray',
          name: '去认证'
        },
        '0': {
          css: 'l-txt-warn',
          name: '审核中'
        },
        '1': {
          css: 'l-txt-theme',
          name: '已认证'
        },
        '2': {
          css: 'l-txt-error',
          name: '认证不通过'
        },
      },
      carList: [{
        carId: '',
        carName: '',
        colorId: '',
        colorName: '',
        guidancePrice: '',
        price: '',
        downPayments: '',
        number: '',
        amount: '',
      }],
      formData: {
        amount: 0,    // 垫资总额
        rate: '',     // 手续费利率
        fee: 0,       // 手续费
        period: '',   // 垫资期限
      }
    }
  },
  watch: {
    carList: {
      immediate: false,
      deep: true,
      handler() {
        clearTimeout(this.timeid)
        this.timeid = setTimeout(this.getAmount, 200)
      }
    },
    'formData.period': function (value) {
      clearTimeout(this.timeid)
      this.timeid = setTimeout(this.getAmount, 200)
    }
  },
  methods: {
    storeAuth() {
      switch(this.userInfo.shopState) {
        case -1: // 未认证
          this.$router.push('/store/auth')
          break 
        default: // 审核中 已认证 认证不通过
          this.$router.push('/store/info')
          break
      }
    },
    loanAuth() {
      if(this.userInfo.shopState === 1){
        this.$router.push('/loan/auth')
      }else{
        this.$vux.alert.show({
          content: '请先认证商铺',
          onHide: _ => {
            this.storeAuth()
          }
        })
      }
    },
    getUserInfo() {
      this.$api.user.getAllInfo().then(({ data }) => {
        this.userInfo = data
        this.formData.rate = data.rate

        if(this.$route.query.id) {
          this.getOrderInfo()
        }
      })
    },
    getOrderInfo() {
      this.$vux.loading.show()
      this.$api.loan.getInfo2(this.$route.query.id).then(({data}) => {
        if(data && data.id != this.$storage.session.get('loan-2-id')) {
          this.$storage.session.set('loan-2-id', data.id)
          this.formData.period = data.period
          this.carList = data.list
        }
      }).finally(_ => {
        this.$vux.loading.hide()
      })
    },
    delcar(guid) {
      this.carList = this.carList.filter(item => item.guid !== guid)
    },
    getAmount() {
      let amount = 0, fee = 0
      let period = Number(this.formData.period) || '' // 垫资天数
      let rate = Number(this.formData.rate) || ''   // 每天利率
      this.formData.period = period
      
      this.carList.forEach(item => {
        amount += Number(item.amount) || 0
      })

      if(period && rate) {
        fee = Math.ceil(amount * rate * period) / 100
      }

      this.formData.amount = Math.ceil(amount * 100) / 100
      this.formData.fee = fee

      this.$storage.session.set('loan-2-form', this.formData)
      this.$storage.session.set('loan-2-addcar', this.carList)
    },
    submit() {
      if(this.carList.length === 0) {
        this.$toptip('请添加垫资车辆')
        return
      }

      if(!this.formData.period) {
        this.$toptip('请输入垫资期限')
        return
      }

      let formData = Object.assign({}, this.formData)
      formData.carsInfo = JSON.stringify(this.carList)

      this.$vux.loading.show()
      this.$api.loan.apply2(formData).then(({data}) => {
        this.$vux.toast.show({
          text: '提交申请成功',
          onHide: _ => {
            // this.$router.back()
            this.$router.push('/loan/list2')
          }
        })

        this.$storage.session.remove('loan-2-addcar')
        this.$storage.session.remove('loan-2-form')
      }).finally(_ => {
        this.$vux.loading.hide()
      })
    },
  },
  mounted() {
    this.getUserInfo()

    this.carList = this.$storage.session.get('loan-2-addcar') || []
    this.$utils.copyObj(this.formData, this.$storage.session.get('loan-2-form'))
  }
}
</script>

<style lang="less" scoped>
.l-car-none{padding: 15% 0; margin: 5px 15px 15px; text-align:center; background-color:#f6f4f5; color:#999; border-radius: 5px;}
.l-car-item{
  padding: 15px; margin: 5px 15px 15px; background-color:#f6f4f5; border-radius: 5px;
  ._thumb{width: 50px; height: 50px; margin-right: 15px; background-color: #fff;}
}

.l-loan-placeholder{height: 90px;}
.l-loan-fixed{
  position: fixed; left: 0; right: 0; bottom: 0; z-index: 10; overflow: hidden;
  ._tip{background-color: #fdf6ec;color: #e6a23c; text-align: center; padding: 8px;}
  ._btn1{
    font-size: 14px; padding: 15px; background-image: linear-gradient(135deg, #fa8734, #eb6100); color: #fff;
    img{width: 20px; height: 20px; vertical-align: -4px; margin-right: 5px;}
  }
  ._btn2{
    font-size: 14px; padding: 12px 15px; background-color: #eef3f6; color: inherit;
    img{width: 20px; height: 20px; vertical-align: -4px; margin-right: 5px;}
  }
}
</style>

