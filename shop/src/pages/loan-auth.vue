<template>
  <view-box>
    <div class="l-margin l-txt-gray">请按以下步骤完成垫资资格认证</div>
    <flow class="l-loan-step" orientation="vertical">
      <flow-state state="1" is-done>
        <div slot="title">
          <h4 class="l-link">认证商铺</h4>
          <div class="_desc">
            <p>恭喜您，已通过商铺认证。</p>
          </div>
        </div>
      </flow-state>

      <flow-line is-done></flow-line>
      <flow-state state="2" is-done>
        <div slot="title">
          <h4 @click="tpl.visible = true"><span class="l-txt-link">下载认证资料模板</span> <b class="l-icon l-txt-theme l-fs-xl">&#xe659;</b></h4>
          <div class="_desc">
            <p>认证资料模板会以邮件的形式发送给您，收到模板后，完善认证模板资料信息并盖章，然后把资料原件邮寄到喜蜂鸟平台总部。</p>
          </div>
        </div>
      </flow-state>

      <flow-line :line-span="40" :is-done="audited"></flow-line>
      <flow-state state="3" :is-done="audited">
        <div slot="title">
          <h4>等待喜蜂鸟平台总部审核</h4>
          <div class="_desc">
            <p>一般审核时间为总部收到您的认证资料后1-3个工作日。</p>
            <div class="_imgs">
              <img width="50" height="50" v-for="(item,index) in loanInfo.materials" :key="index" :src="item" @click="$api.previewImage(loanInfo.materials, index)">
            </div>
          </div>
        </div>
      </flow-state>

      <flow-line :line-span="40" :is-done="finished"></flow-line>
      <flow-state state="4" :is-done="finished">
        <div slot="title">
          <h4>认证完成</h4>
          <div class="_desc">
            <p>垫资资格认证通过后，便可去申请垫资。</p>
            <div v-if="loanInfo.state == 1" class="l-margin-t">
              <span class="l-txt-ok">认证通过</span>，
              <router-link class="l-txt-link" to="/loan">去申请垫资</router-link>
            </div>
            <div v-if="loanInfo.state == 2" class="l-margin-t">
              <span class="l-txt-error">认证失败</span>
              <p>原因：{{loanInfo.reason}}</p>
            </div>
          </div>
        </div>
      </flow-state>
    </flow>

    <div v-transfer-dom>
      <x-dialog v-model="tpl.visible" hide-on-blur :dialog-style="{'max-width': '100%', width: '100%', 'background-color': 'transparent', 'z-index': 4999}">
        <div class="l-tpl-list">
          <div class="_hd">认证资料模板将发送到以下邮箱：</div>
          <div class="_bd l-fs-m">
            <group gutter="0">
              <cell title="在职证明" inline-desc="用于企业认证内的资料认证">
                <icon class="l-txt-theme l-fs-m" type="success-no-circle"></icon>
              </cell>
              <cell title="授权确认函" inline-desc="用于签章人认证中的资料认证">
                <icon class="l-txt-theme l-fs-m" type="success-no-circle"></icon>
              </cell>
              <cell title="担保函" inline-desc="用于签章人认证中的资料认证">
                <icon class="l-txt-theme l-fs-m" type="success-no-circle"></icon>
              </cell>
              <x-input v-model="tpl.email" is-type="email" type="text" :max="100" placeholder-align="center" placeholder="请输入您的邮箱地址"></x-input>
            </group>
            <div class="_btn" @click="sendTpl">立即发送</div>
          </div>
          <div class="l-margin-t">
            <x-icon type="ios-close-outline" style="fill:#fff;" @click="tpl.visible = false"></x-icon>
          </div>
        </div>
      </x-dialog>
    </div>
  </view-box>
</template>

<script>
import { Flow, FlowState, FlowLine, XDialog } from 'vux'
export default {
  name: 'loan-auth',
  components: { Flow, FlowState, FlowLine, XDialog },
  data() {
    return {
      audited: false,
      finished: false,
      loanInfo: {},
      tpl: {
        visible: false,
        email: ''
      }
    }
  },
  methods: {
    sendTpl() { // 发送模板
      if(!this.tpl.email) {
        this.$toptip('请输入您的邮箱地址')
        return
      }
      
      this.$vux.loading.show()
      this.$api.loan.sendTpl({ email: this.tpl.email }).then(({data}) => {
        this.tpl.visible = false
        this.$vux.alert.show({
          content: '认证资料模板已发送到您的邮箱，注意查收。请尽快完善垫资认证资料并把原件邮寄到喜蜂鸟平台总部。',
          onHide: _ => {
            this.$router.back()
          }
        })
      }).finally(_ => {
        this.$vux.loading.hide()
        // this.tpl.visible = false
      })
    },
    getLoanInfo() {
      this.$api.user.getLoanInfo().then(({ data }) => {
        if(data) {
          this.loanInfo = data
          this.audited = data.state >= 0
          this.finished = data.state >= 1
        }
      })
    }
  },
  mounted() {
    this.getLoanInfo()
  }
}
</script>
<style lang="less">
.l-flow_li-temp{
  width: 100%; height: auto;
  .weui-wepay-flow__state{
    width: 30px; height: 30px; line-height: 30px; border-radius: 20px; 
  }
  .weui-wepay-flow__title-right{top: 3px; transform:none; left: 50px; }
}

.l-loan-step{
  height: 400px; padding: 20px !important;
  .weui-wepay-flow__bd{ align-items: start; }
  .weui-wepay-flow__li{
    .l-flow_li-temp() !important
  }
  .weui-wepay-flow__line{position: relative; left: 13px;}
  ._desc{color: #999; margin-top: 5px; white-space: normal;}
  ._imgs{
    img{margin: 5px 5px 0 0;}
  }
}

.l-tpl-list{
  width: 280px; margin:auto;
  ._hd{padding: 15px; line-height: 1; text-align: left; background-color: #fff;}
  ._bd{background-color: #fff; text-align: left;}
  ._btn{background-color: @theme-color; color: #fff; text-align: center; padding: 10px;}
}
</style>

