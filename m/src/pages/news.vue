<template>
  <view-box>
    <div class="l-tab-top">
      <tab>
        <tab-item selected @on-item-click="tabClick">公司新闻</tab-item>
        <tab-item @on-item-click="tabClick">行业动态</tab-item>
        <tab-item @on-item-click="tabClick">汽车商学院</tab-item>
      </tab>
    </div>
    <div class="_placeholder"></div>

    <panel :list="list.data" type="5"></panel>
    <infinite-loading ref="infinite" :on-infinite="onInfinite" :auto-start="false"></infinite-loading>
  </view-box>
</template>
<script>
import { Tab, TabItem, Panel } from 'vux'
import infiniteLoading from '../components/vue-infinite-loading'
export default {
  name: 'news',
  components: { Tab, TabItem, Panel, infiniteLoading },
  data () {
    return {
      list: {
        filter: {
          type: ''
        },
        rows: 10,
        page: 1,
        data: []
      },
    }
  },
  methods: {
    tabClick(index = 0) {
      this.list.filter.type = (index + 1)
      this.list.page = 1
      this.resetInfinite()
    },
    resetInfinite() {
      this.$refs.infinite.$emit('$InfiniteLoading:reset', false)
      this.onInfinite(1)
    },
    onInfinite(page) {
      this.$http.ajax('/pc_v1/news', {
        page: page || this.list.page, 
        rows: this.list.rows,
        ...this.list.filter
      }).then(({data}) => {
        let returnList = data.list.map(item => {
          return {
            src: this.$utils.imgThumb(item.icon, 100, 100),
            fallbackSrc: 'https://res.xfnauto.com/www/20180527043.png',
            title: item.title,
            desc: item.content,
            url: '/infos?id=' + item.id,
            meta: {
              date: item.publishedTime
            }
          }
        })
        this.list.data = data.page > 1 ? this.list.data.concat(returnList) : returnList

        if(returnList.length > 0){
          this.$refs.infinite.$emit('$InfiniteLoading:loaded')
          
          if(returnList.length >= data.rows){
            this.list.page++
          }else{
            this.$refs.infinite.$emit('$InfiniteLoading:complete')
          }
        }else{
          this.$refs.infinite.$emit('$InfiniteLoading:complete')
        }
      }).catch(err => {
        if(!err.abort) {
          this.$refs.infinite.$emit('$InfiniteLoading:complete')
        }
      })
    }
  },
  mounted() {
    this.tabClick(this.$route.query.type)
  }
}
</script>
<style>
.weui-media-box_appmsg .weui-media-box__thumb{height: 100%;}
</style>
<style lang="less" scoped>
.l-tab-top{position: absolute; top: 0; left: 0; right: 0; z-index: 1;}
.l-tab-top ~ ._placeholder{height: 46px;}
</style>

