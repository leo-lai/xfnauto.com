<template>
	<div class="l-page-bd">
		<div class="l-news-bg">
      <div class="_inner l-flex-vc">
        <h3>{{info.title}}</h3>
        <p>{{info.publishedTime}}</p>
      </div>
    </div>

    <div class="_inner">
      <div class="l-margin-tb-m l-text-gray">当前位置：新闻资讯 > {{info.type}}</div>
      <div class="l-bg-white" style="padding: 60px;" v-html="info.content"></div>

      <div class="l-news-ft l-flex-hc">
        <a v-if="info.nextId" :href="'/infos?id=' + info.nextId.id">上一篇：{{ info.nextId.id === info.id ? '没有了' : info.nextId.title }}</a>
        <div class="l-rest"></div>
        <a v-if="info.preId" :href="'/infos?id=' + info.preId.id">下一篇：{{ info.preId.id === info.id ? '没有了' : info.preId.title}}</a>
      </div>
    </div>
  </div>
</template>
<script>
export default {
	name: 'infos',
  head: {
    title: '新闻详情'
  },
  data() {
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
      const loading = this.$loading()
      this.$http.ajax('/pc_v1/news/detail?id=' + (this.$route.query.id || '')).then(({data}) => {
        this.info = data
      }).finally(_ => {
        loading.close()
      })
    }
  },
  mounted() {
    this.getInfo()
  },
  // updated() {
  //   this.getInfo()
  // }
}
</script>
<style scoped lang="scss">
.l-page-bd > ._inner{width: 1200px; padding: 0; margin: auto;}
.l-news-bg{
  background: url('~static/images/20180527042.jpg') 50% 50% no-repeat; background-size: cover;
  height: 297px;
  ._inner{
    width: 1200px; height: 100%; margin: auto; color: #fff;
  }
}
.l-news-ft{
  background-color: #fff; padding: 30px 60px;
  a{color: inherit;}
}

</style>