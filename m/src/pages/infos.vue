<template>
  <view-box ref="viewbox">
    <div class="l-news-bg">
      <div class="_inner l-flex-vc">
        <h3>{{info.title}}</h3>
        <p class="l-fs-s l-margin-t-m l-txt-gray">{{info.publishedTime}}</p>
      </div>
    </div>

    <div class="l-bg-white l-padding" v-html="info.content"></div>

    <div class="l-news-ft l-flex-hc">
      <router-link tag="div" v-if="info.nextId" :to="'/infos?id=' + info.nextId.id">
        <x-icon type="ios-arrow-left" size="20"></x-icon>
        {{ info.nextId.id === info.id ? '没有了' : '上一篇' }}
      </router-link>
      <div class="l-rest"></div>
      <router-link tag="div" v-if="info.preId" :to="'/infos?id=' + info.preId.id">
        {{ info.preId.id === info.id ? '没有了' : '下一篇'}}
        <x-icon type="ios-arrow-right" size="20"></x-icon>
      </router-link>
    </div>
  </view-box>
</template>

<script>
export default {
  name: 'infos',
  data () {
    return {
      info: {}
    }
  },
  watch: {
    '$route.path': {
      immediate: false,
      deep: true,
      handler() {
        this.getInfo()  
      }
    }
  },
  methods: {
    getInfo() {
      this.$vux.loading.show()
      this.$http.ajax('/pc_v1/news/detail?id=' + (this.$route.query.id || '')).then(({data}) => {
        this.info = data
        this.$refs.viewbox.scrollTo()
      }).finally(_ => {
        this.$vux.loading.hide()
      })
    }
  },
  mounted() {
    this.getInfo()
  },
}
</script>
<style lang="less" scoped>
.l-news-bg{
  background: url('../assets/images/20180527042.jpg') 50% 50% no-repeat; background-size: cover;
  height: 120px;
  ._inner{
    height: 100%; margin: auto; color: #fff; padding: 0 15px;
  }
}
.l-news-ft{
  padding: 15px; color: #999;
  a{color: #999;}
}
.vux-x-icon {
  vertical-align: -4px;
  fill: #999;
}
</style>

