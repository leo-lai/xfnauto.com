<template>
	<div class="l-page-bd">
		<div class="l-news-bg"></div>
    <div class="l-news-tab">
      <div class="l-news-tabtit l-flex-hc">
        <b>将观点与焦点与您一起分享</b>
        <div class="l-rest"></div>
        <div class="_tabs">
          <a @click="tabClick(1)" :class="{_active: type == 1}">公司新闻</a>
          <a @click="tabClick(2)" :class="{_active: type == 2}">行业动态</a>
          <a @click="tabClick(3)" :class="{_active: type == 3}">汽车商学院</a>
        </div>
      </div>

      <div class="l-news-top3">
        <div class="l-flex-h">
          <router-link tag="div" :to="'/infos?id=' + top3[0].id" class="l-rest _item1" v-if="top3[0]">
            <img class="_thumb" :src="top3[0].icon" alt="">
            <div class="_txt">
              <p class="l-fs-s">{{top3[0].publishedTime}}</p>
              <p>{{top3[0].title}}</p>
              <a>阅读文章</a>
            </div>
          </router-link>
          <div class="l-rest l-margin-l" style="width: 48%;">
            <router-link tag="div" :to="'/infos?id=' + top3[1].id" class="_item2" v-if="top3[1]">
              <img class="_thumb" :src="top3[1].icon" alt="">
              <div class="_txt">
                <p class="l-fs-s">{{top3[1].publishedTime}}</p>
                <p>{{top3[1].title}}</p>
                <a>阅读文章</a>
              </div>
            </router-link>
            <router-link tag="div" :to="'/infos?id=' + top3[2].id" class="_item2 l-margin-t" v-if="top3[2]">
              <img class="_thumb" :src="top3[2].icon" alt="">
              <div class="_txt">
                <p class="l-fs-s">{{top3[2].publishedTime}}</p>
                <p>{{top3[2].title}}</p>
                <a>阅读文章</a>
              </div>
            </router-link>
          </div>
        </div>
      </div>

      <div class="l-news-list">
        <router-link tag="div" :to="'/infos?id=' + item.id" class="_item l-margin-t" v-for="item in list" :key="item.id">
          <img class="_thumb" :src="item.icon" alt="">
          <div class="l-padding-s">
            <p class="l-fs-s">{{item.publishedTime}}</p>
            <p class="l-text-wrap1">{{item.title}}</p>
            <p class="l-text-wrap2 _cont">{{item.content}}</p>
            <p><a>阅读文章</a></p>
          </div>
        </router-link>
      </div>
      <!-- <div class="l-margin-t l-text-center"><br><button class="l-btn-1" mini round>查看更多 >></button></div> -->
    </div>
  </div>
</template>
<script>
export default {
	name: 'news',
  head: {
    title: '新闻资讯'
  },
  data() {
    return {
      type: '',
      top3: [],
      list: []
    }
  },
	methods: {
    tabClick(type = 1) {
      this.type = type
      this.getList()
    },
		getList() {
      const loading = this.$loading()
      this.$http.ajax('/pc_v1/news?page=1&rows=11&type=' + this.type).then(({data}) => {
        if(data && data.list) {
          this.top3 = data.list.slice(0, 3)
          this.list = data.list.slice(3)
        }
      }).finally(_ => {
        loading.close()
      })
    }
  },
  mounted() {
    this.tabClick(this.$route.query.type)
  }
}
</script>
<style scoped lang="scss">
.l-news-bg{
  background: url('~static/images/20180527041.jpg') 50% 50% no-repeat; background-size: cover;
  height: 297px;
}
.l-news-tab{padding: 30px 60px 50px; border-radius: 30px; background-color: #fff; width: 1200px; margin: -100px auto 50px; box-sizing: border-box;}
.l-news-tabtit{
  margin-bottom: 30px;
  b{font-size: 1rem;}
  ._tabs{
    a{margin: 0 0 0 20px; cursor: pointer;}
    a._active{color: #fc6441; font-weight: 700;}
  }
}

.l-news-top3{
  ._item1{
    position: relative; width: 48%; background-color: #f7f7f7;
    ._thumb{
      display: block; width: 100%; height: 100%;  background: #ececec url('~static/images/20180527043.png') 50% 50% no-repeat; background-size: contain;
    }
    ._txt{
      position: absolute; left:0; right: 0; bottom: 0; background-color: rgba(0, 0, 0, 0.6); padding: 10px; color: #fff; overflow: hidden;
      a{
        color: #fff; float: right; text-decoration: underline; margin-top: -1.6em;
      }
    }
  }
  ._item2{
    position: relative; width: 100%; background-color: #f7f7f7;
    ._thumb{
      display: block; width: 100%; height: 200px; background: #ececec url('~static/images/20180527043.png') 50% 50% no-repeat; background-size: contain;
    }
    ._txt{
      position: absolute; left:0; right: 0; bottom: 0; background-color: rgba(0, 0, 0, 0.6); padding: 10px; color: #fff; overflow: hidden;
      a{
        color: #fff; float: right; text-decoration: underline; margin-top: -1.6em;
      }
    }
  }
}

.l-news-list{
  transform: translateX(1px);
  ._item{
    float: left; border: 1px solid #f7f7f7; width: 271px; box-sizing: border-box; margin-left: -1px;
    ._thumb{
      display: block; width: 100%; height: 200px; background: #ececec url('~static/images/20180527043.png') 50% 50% no-repeat; background-size: contain;
    }
    ._cont{color: #999; font-size: 12px; margin: 10px 0;}
    a{text-decoration: underline; font-size: 14px; color: inherit;}
  }
  &::after{
    content: ''; display: block; clear: both; height: 0; visibility: hidden;
  }
}
</style>