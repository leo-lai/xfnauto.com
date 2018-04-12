<template>
  <div class="l-map-box">
    <iframe id="mapPage" width="100%" height="100%" frameborder=0 src="http://apis.map.qq.com/tools/locpicker?search=1&type=1&key=TXTBZ-OE3CO-IMSWK-SRAMI-7PGFZ-6YFA4
&referer=xfnauto-web"></iframe>
    <div class="l-fixed-bottom">
      <div class="_inner">
        <div class="l-flex-hc l-padding">
          <x-button style="width: 50%;" class="l-btn-radius" type="primary" @click.native="submit">确定</x-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'map-selector',
  data () {
    return {
      submit() {

      }
    }
  },
  methods: {
    
  }
}
</script>

<style lang="less">
.l-map-box{position: absolute; left: 0; top: 0; width: 100%; height: 100%;}
</style>

