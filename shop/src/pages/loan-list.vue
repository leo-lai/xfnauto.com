<template>
  <view-box>
    
  </view-box>
</template>
<script>
export default {
  name: 'loan-list',
  data () {
    return {
      
    }
  },
  methods: {
    getList() {
      this.$api.loan.getList()  
    }
  },
  mounted() {
    this.getList()
  }
}
</script>
<style lang="less">

</style>
