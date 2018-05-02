<template>
  <div class="l-loan">
    <template v-if="userInfo && userInfo.userType == 2">
      <div class="_bg2"></div>  
      <div class="_bottom">
        <a class="_btn2" @click="loan"></a>
        <a :href="'tel:' + $config.tel" class="_ask"></a>
      </div>
    </template>
    <template v-else>
      <div class="_bg1"></div>
      <div class="_bottom">
        <a class="_btn1" @click="loan"></a>
        <a :href="'tel:' + $config.tel" class="_ask"></a>
      </div>
    </template>
  </div>
</template>
<script>
export default {
  name: 'loan',
  data () {
    return {
      userInfo: null
    }
  },
  methods: {
    loan() {
      if(!this.userInfo) {
        this.$router.push('/login')
      }else {
        this.$router.push(this.userInfo.userType === 2 ? '/loan/2' : '/loan/1')
      }
    }
  },
  created() {
    this.$api.user.getInfo().then(data => {
      this.userInfo = data
      this.$utils.setTitle(data.userType == 2 ? '找垫资' : '金融购')
    })
  }
}
</script>
<style lang="less">
.l-loan{
  position: absolute; top:0; left: 0; width: 100%; height: 100%;
  ._bg1, ._bg2{
    position: absolute; top:0; left: 0; bottom: 50px; right:0; z-index: -1;
    background:#f07d1b no-repeat 50% 50%; background-size: cover;
  }
  ._bg1{background-image: url('../assets/images/20180402016.jpg');}
  ._bg2{background-image: url('../assets/images/20180402015.jpg');}
  ._bottom{
    position: absolute; bottom: 70px; left: 0; right: 0; text-align: center;
    ._btn1, ._btn2{display: inline-block; width: 150px; height: 48px; }
    ._btn1{background: url('../assets/images/20180402017.png') no-repeat 50% 50%; background-size: contain;}
    ._btn2{background: url('../assets/images/20180402021.png') no-repeat 50% 50%; background-size: contain;}
    ._ask{position: absolute; top: 5px; right: 0; width: 80px; height: 40px; background: url('../assets/images/20180402018.png') no-repeat 50% 50%; background-size: contain;}
  }
}
</style>
