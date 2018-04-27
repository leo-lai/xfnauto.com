<template>
  <view-box>
    <div class="l-user-card">
      <div v-if="userInfo" class="l-flex-hc l-padding l-margin-t">
        <div class="_avatar l-bg-co" :style="{'background-image':'url('+ userInfo.headPortrait +')'}"></div>
        <div class="l-rest">
          <h4 class="l-txt-wrap1">{{userInfo.userName || userInfo.phoneNumber}}</h4>
          <router-link class="_btn1" to="/me/info">账户资料</router-link>
        </div>
        <div class="_btn2">
          <img src="../assets/images/icon-004.png" alt="">
          <router-link to="/password">修改密码</router-link>
        </div>
      </div>

      <div v-else class="l-padding l-txt-center l-flex-hc" style="width: 70%; margin: 10% auto;">
        <x-button link="/login?to=/me">账号登录</x-button>
        <span class="l-margin-l"></span>
        <x-button link="/register?to=/me">注册账号</x-button>
      </div>
    </div>
    <div class="l-bg-white l-padding-t">
      <div class="l-zoom">
        <group style="margin-top:-1px;" gutter="0">
          <cell title="预约单" link="/order/list1">
            <img class="weui-cell__icon" slot="icon" src="../assets/images/icon-001.png">
          </cell>
          <cell title="订购单" :link="orderLink">
            <img class="weui-cell__icon" slot="icon" src="../assets/images/icon-001.png">
          </cell>
          <cell title="寻车记录" link="/car/seek/list">
            <img class="weui-cell__icon" slot="icon" src="../assets/images/icon-002.png">
          </cell>
          <cell :title="loanTitle" link="/loan/list">
            <img class="weui-cell__icon" slot="icon" src="../assets/images/icon-003.png">
          </cell>
        </group>
      </div>
    </div>

  </view-box>
</template>

<script>

export default {
  name: 'me',
  components: {
  },
  data () {
    return {
      userInfo: null,
      orderLink: '/order/list3',
      loanTitle: ''
    }
  },
  methods: {
  },
  mounted() {
    this.$api.user.getInfo().then(data => {
      console.log(data)
      this.userInfo = data
      if(data.userType == 2) {
        this.orderLink = '/order/list2'
        this.loanTitle = '垫资申请记录'
      }else{
        this.orderLink = '/order/list3'
        this.loanTitle = '贷款申请记录'
      }
    })
  }
}
</script>

<style lang="less">
.l-user-card{
  position: relative; height: 150px; overflow: hidden; color: #fff;
  background: #fff url(../assets/images/20180402011.jpg) no-repeat 50% 50%; background-size: 100%;
  ._avatar{width: 60px; height: 60px; border: 2px solid #ff867b; border-radius: 50%; margin-right: 15px; background-color: #fff;}
  ._btn1{
    position: relative; background-color: #ff8a69; border-radius: 5px; padding: 3px 20px 3px 10px; font-size: 12px; color: #fff;
    &::after{
      content: ' '; height: 5px; width: 5px; border-width: 1px 1px 0 0; border-color: inherit; border-style: solid;
      transform: rotate(45deg) translateY(-50%); position: absolute; top: 50%; right: 10px;
    }
  }
  ._btn2{
    background: #fdc264 no-repeat 50% 50%;
    background-image: linear-gradient(to bottom, #fbc366 0, #fcac57 50%, #fa9d4d 100%);
    border-radius: 50px 0 0 50px; padding: 7px 10px; margin-right: -15px;
    img{width: 20px; height: 20px; vertical-align: -4px; }
    a{text-decoration: underline; font-size: 14px; color: #fff;}
  }
}
</style>

