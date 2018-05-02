<template>
  <view-box>
    <div class="l-bg-white">
      <flow>
        <flow-state state="1" title="申请金融贷" is-done>
          <div class="l-fs-s" slot="title">申请<br>金融贷</div>
        </flow-state>
        <flow-line is-done></flow-line>
        <flow-state state="2" title="提交个人资料" is-done>
          <div class="l-fs-s" slot="title">提交<br>个人资料</div>
        </flow-state>
        <flow-line></flow-line>
        <flow-state state="3" title="初审结果通知">
          <div class="l-fs-s" slot="title">初审<br>结果通知</div>
        </flow-state>
        <flow-line></flow-line>
        <flow-state state="4" title="终审免签提车">
          <div class="l-fs-s" slot="title">终审<br>免签提车</div>
        </flow-state>
      </flow><br>
    </div>

    <div class="l-flex-hc l-padding-btn">
      <img class="l-img-icon l-margin-r-m" src="../assets/images/icon-010.png" alt="">
      <h4 class="l-rest">车辆信息</h4>
    </div>
    <group gutter="0" label-width="6em">
      <cell title="车型" link="/car/selector">
        <div style="padding: 2px 0;" v-if="formData.carsId" class="l-fs-m">{{formData.carsName}}</div>
        <span v-else class="l-txt-gray">请选择车型</span>
      </cell>
      <cell title="指导价">
        <span>{{formData.guidancePrice}} 元</span>
      </cell>
    </group>

    <div class="l-flex-hc l-padding-btn">
      <img class="l-img-icon l-margin-r-m" src="../assets/images/icon-023.png" alt="">
      <h4 class="l-rest">贷款信息</h4>
    </div>
    <group gutter="0" label-width="6em">
      <!-- <popup-radio title="金融机构" :options="bankList" v-model="formData.institutionId" placeholder="请选择">
        <div slot="popup-header" class="vux-1px-b l-padding-btn l-txt-center">请选择金融机构</div>
      </popup-radio> -->
      <popup-radio title="首付比例" :options="downPayments" v-model="formData.downPayments" placeholder="请选择">
        <div slot="popup-header" class="vux-1px-b l-padding-btn l-txt-center">请选择首付比例</div>
      </popup-radio>
      <popup-radio title="还款期数" :options="loanPeriod" v-model="formData.loanPeriod" placeholder="请选择">
        <div slot="popup-header" class="vux-1px-b l-padding-btn l-txt-center">请选择还款期数</div>
      </popup-radio>
      <x-input :show-clear="false" title="贷款总额" placeholder="请输入贷款总额" type="number" :max="10" placeholder-align="right" v-model="formData.loanAmount">
        <span slot="right" class="l-txt-gray l-margin-l-s">元</span>
      </x-input>
    </group>

    <div class="l-flex-hc l-padding-btn">
      <img class="l-img-icon l-margin-r-m" src="../assets/images/icon-024.png" alt="">
      <h4 class="l-rest">提交资料</h4>
    </div>
    <group gutter="0">
      <x-input title="申请人姓名" placeholder="请输入姓名" :show-clear="false" :max="20" placeholder-align="right" v-model="formData.loanPeopleName"></x-input>
      <x-input title="申请人电话" placeholder="请输入电话" type="tel" :max="11" :show-clear="false" placeholder-align="right" v-model="formData.loanPeoplePhone"></x-input>
      <cell title="上传身份证照片" align-items="flex-start">
        <div slot="title" style="width: 10em;">上传身份证照片</div>
        <div class="l-idcard-upload l-flex-hc" slot="inline-desc">
          <div class="_item" @click="chooseImage(1, 1)">
            <img :src="formData.idCardPicOn" alt="">
            <p class="l-txt-gray l-fs-s">身份证正面</p>
          </div>
          <div class="l-margin-l"></div>
          <div class="_item" @click="chooseImage(2, 1)">
            <img :src="formData.idCardPicOff" alt="">
            <p class="l-txt-gray l-fs-s">身份证反面</p>
          </div>
        </div>
      </cell>
      <!-- <cell title="上传收入证明" align-items="flex-start">
        <div slot="title" style="width: 7em;">上传收入证明</div>
        <div slot="inline-desc" class="l-margin-t-m">
          <div class="l-preview-imgs">
            <img class="_item" :src="item" v-for="item in annualIncomeImage" :key="item">
            <i class="_add" src="../assets/images/icon-009.png" @click="chooseImage(3, 9)"></i>
          </div>
        </div>
      </cell> -->
    </group>

    <div class="l-fixed-bottom">
      <div class="_placeholder"></div>
      <div class="_inner">
        <div class="l-btn-w50 l-padding-tb"><x-button @click.native="submit" class="l-btn-radius" type="primary">提交申请</x-button></div>
      </div>
    </div>
  </view-box>
</template>

<script>
import { PopupRadio, Flow, FlowState, FlowLine } from 'vux'
export default {
  name: 'loan-1',
  components: {
    PopupRadio, Flow, FlowState, FlowLine
  },
  data () {
    return {
      userInfo: null,
      bankList: [],
      downPayments: [
        { key: 0.1, value: '10%' },
        { key: 0.2, value: '20%' },
        { key: 0.3, value: '30%' },
        { key: 0.4, value: '40%' },
        { key: 0.5, value: '50%' },
        { key: 0.6, value: '60%' }
      ],
      loanPeriod: [12, 18, 24, 36, 48],
      annualIncomeImage: [],
      formData: {
        carsId: '',
        carsName: '',
        guidancePrice: '',
        // institutionId: '',
        downPayments: '',
        loanPeriod: '',
        loanAmount: '',
        loanPeopleName: '',
        loanPeoplePhone: '',
        idCardPicOn: '',
        idCardPicOff: '',
        annualIncomeImage: '',
      }
    }
  },
  watch: {
    'formData.downPayments': function(val) {
      if(val && this.formData.guidancePrice) {
        this.formData.loanAmount = ((1 - val) * this.formData.guidancePrice).toFixed(2)
      }
    }
  },
  methods: {
    chooseImage(type = 1, number = 1) {
      this.$api.chooseImage(number).then(localIds => {
        this.$api.uploadImage(localIds).then(({ serverIds, images, localIds })=>{
          console.log(serverIds, images, localIds)
          switch (type) {
            case 1: // 身份证正面
              this.formData.idCardPicOn = images[0]
              break
            case 2: // 身份证反面
              this.formData.idCardPicOff = images[0]
              break
            case 3: // 收入证明
              this.annualIncomeImage = this.annualIncomeImage.concat(images)
              this.formData.annualIncomeImage = this.annualIncomeImage.join(',')
              break
          }
        })
      }).catch(errMsg => {
        this.$vux.confirm.show({
          title: '授权提示',
          content: '需要获取您的相册权限，是否允许？',
          cancelText: '不允许',
          confirmText: '允许',
          onConfirm: res => {
            this.$utils.url.reload()
          }
        })
      })
    },
    submit() {
      if(!this.formData.carsId) {
        this.$toptip('请选择车型')
        return
      }
      // if(!this.formData.institutionId) {
      //   this.$toptip('请选择金融机构')
      //   return
      // }
      if(!this.formData.downPayments) {
        this.$toptip('请选择首付比例')
        return
      }
      if(!this.formData.loanPeriod) {
        this.$toptip('请选择还款期数')
        return
      }
      if(!(Number(this.formData.loanAmount) > 0)) {
        this.$toptip('请输入贷款总额')
        return
      }
      if(!this.formData.idCardPicOn) {
        this.$toptip('请上传身份证正面')
        return
      }
      if(!this.formData.idCardPicOff) {
        this.$toptip('请上传身份证反面')
        return
      }

      this.$vux.loading.show()
      this.$api.loan.apply1(this.formData).then(({data}) => {
        // this.$vux.toast.show({
        //   text: '提交申请成功',
        //   onHide: _ => {
        //     this.$router.back()
        //   }
        // })
        this.$router.replace('/msg?type=2')
      }).finally(_ => {
        this.$vux.loading.hide()
      })
    }
  },
  mounted() {
    this.$api.user.getInfo().then(data => {
      this.userInfo = data
    })

    this.bankList = this.$config.bankList

    let sltedCar = this.$storage.session.get('car_slted')
    if(sltedCar) {
      this.formData.carsId = sltedCar.id
      this.formData.carsName = sltedCar.name
      this.formData.guidancePrice = sltedCar.price
    }
  }
}
</script>

<style lang="less">
.l-user-avatar{ width: 50px; height: 50px; background-color: #fff; border-radius: 50%; }
</style>

