<template>
  <div class="l-map-box">
    <iframe id="mapPage" width="100%" height="100%" frameborder=0 src="https://apis.map.qq.com/tools/locpicker?search=1&type=1&policy=1&key=TXTBZ-OE3CO-IMSWK-SRAMI-7PGFZ-6YFA4
&referer=xfnauto-web"></iframe>
    <div class="l-fixed-bottom" v-if="location">
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
      location: null
    }
  },
  methods: {
    locationHandler(event) {
      // 接收位置信息，用户选择确认位置点后选点组件会触发该事件，回传用户的位置信息
      var loc = event.data
      if (loc && loc.module == 'locationPicker') {//防止其他应用也会向该页面post信息，需判断module是否为'locationPicker'
        this.location = loc
      }         
    },
    submit() {
      if(!this.location) {
        this.$toptip('请选择地点')
        return
      }
      this.$storage.session.set((this.$route.query.name || '') + '_loc', this.location)
      this.$router.back()
    }
  },
  mounted() {
    window.addEventListener('message', this.locationHandler, false)
  },
  beforeDestroy() {
    window.removeEventListener('message', this.locationHandler)
  }
}
</script>

<style lang="less">
.l-map-box{position: absolute; left: 0; top: 0; width: 100%; height: 100%;}
</style>

