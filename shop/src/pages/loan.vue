<template>
  <div class="l-loan">
    <div v-if="userInfo && userInfo.userType === 2" class="_bg2"></div>
    <div v-else class="_bg1"></div>
    <div class="_bottom">
      <a class="_btn" @click="loan"><img src="../assets/images/20180402017.png" alt="金融购"></a>
      <a href="tel:400-1639-989" class="_ask"><img src="../assets/images/20180402018.png" alt="咨询"></a>
    </div>
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
  mounted() {
    this.$api.user.getInfo().then(data => {
      this.userInfo = data
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
    position: absolute; bottom: 100px; left: 0; right: 0; text-align: center;
    ._btn{display: inline-block; width: 150px;}
    ._ask{position: absolute; top: 4px; right: 0; width: 80px;}
  }
}
</style>
