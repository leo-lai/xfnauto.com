<template>
  <div class="l-login-box l-flex-vhc">
    <img class="_logo" src="../assets/images/20180402013.png" alt="欢迎登录淘车网">
    <div class="l-login-form">
      <div class="_ipt l-flex-hc">
        <img class="_icon" src="../assets/images/icon-005.png" alt="">
        <input type="tel" placeholder="请输入手机号码">
      </div>
      <div class="_ipt l-flex-hc">
        <img class="_icon" src="../assets/images/icon-007.png" alt="">
        <input type="password" placeholder="请输入登录密码">
      </div>
      <div class="l-margin-t-m l-txt-right">
        <a class="l-link-gray l-fs-s">忘记密码？</a>
      </div>
      <div class="l-margin-t-l">
        <x-button type="primary">登录</x-button>
      </div>
    </div>
    
    <div class="_footer">
      <p class="l-txt-gray">您还没有账号？</p>
      <p><a class="l-link-main">免费注册</a></p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'login',
  data () {
    return {
      
    }
  }
}
</script>

<style lang="less">
.l-login-box{
  position: absolute; left: 0; top: 0; width: 100%; height: 100%;
  background:#fff url(../assets/images/20180402012.jpg) no-repeat 50% 0;background-size: cover;
  ._logo{width: 150px; height: 150px; margin: -30% 0 30px;}
  ._footer{position: absolute; left: 0; right: 0; bottom: 30px; text-align: center; }
}
.l-login-form{
  width: 280px;
  ._ipt{
    background-color: #fdfdff; border: 1px solid #d5d8dd; border-radius: 5px; padding: 7px; margin-top: 15px;
    ._icon{ width: 25px; height: 25px; margin-right: 10px; }
    input{border:none; background: none; padding: 5px 0; width: 100%; box-sizing: border-box; font-size: 14px;}
  }
}
</style>

