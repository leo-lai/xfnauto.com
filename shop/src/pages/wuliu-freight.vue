<template>
  <view-box>
    <group gutter="0" label-width="6em">
      <cell title="托运方式" value="广州-江门" is-link></cell>
      <cell title="起点" link="/map/selector?name=wuliu-freight-start">
        <div class="l-txt-gray">
          <img class="l-img-icon" src="../assets/images/icon-021.png" alt="">
          <span>请选择</span>
        </div>
      </cell>
      <cell title="终点" link="/map/selector?name=wuliu-freight-end">
        <div class="l-txt-gray">
          <img class="l-img-icon" src="../assets/images/icon-021.png" alt="">
          <span>请选择</span>
        </div>
      </cell>
    </group>

    <div class="l-margin-t l-bg-white l-zoom">
      <div class="l-flex-hc l-padding-btn">
        <img class="l-img-icon l-margin-r-m" src="../assets/images/icon-013.png" alt="">
        <h4 class="l-rest">托运车辆</h4>
        <router-link class="l-fr l-link-main l-is-link" to="/me/store/info">添加车辆</router-link>
      </div>
      <div class="l-car-none">您尚未添加车辆</div>
      <div class="l-car-item l-flex-hc">
        <img class="_thumb" src="" alt="">
        <div class="l-rest">
          <h4 class="l-txt-wrap1">dsfasdfsdasdfdfafafadsfasdfsdasdfdfafafadsfasdfsdasdfdfafafadsfasdfsdasdfdfafafa</h4>
          <p class="l-txt-gray l-fs-m l-margin-t-s">
            <span class="l-fr"><icon type="clear"></icon></span>
            <span>数量：3辆</span>
            <span class="l-margin-l">指导价：<i class="l-rmb">18万</i></span>
          </p>
        </div>
      </div>
    </div>
    <div class="l-margin l-fs-s l-txt-gray">
      注意事项：各路线运费，仅供参考，以实际下单的运费为准。可随时咨询
      <a class="l-txt-gray" href="tel:400-1639-989">400-1639-989</a>
    </div>
    <div class="l-fixed-bottom">
      <div class="_placeholder"></div>
      <div class="_inner">
        <div class="l-flex-hc l-padding">
          <x-button style="width: 50%;" class="l-btn-radius" type="primary" @click.native="submit">查询运费</x-button>
        </div>
      </div>
    </div>
  </view-box>
</template>
<script>
import { Icon } from 'vux'
export default {
  name: 'wuliu-freight',
  components: { Icon },
  data () {
    return {
    }
  },
  methods: {
    submit() {

    }
  },
  mounted() {
    
  }
}
</script>
<style lang="less" scoped>
.l-img-icon{vertical-align: -4px; width: 20px; height: 20px;}
.l-car-none{padding: 5%; margin: 15px; text-align:center; background-color:#f6f4f5; color:#999; border-radius: 5px;}
.l-car-item{
  padding: 15px; margin: 15px; background-color:#f6f4f5; border-radius: 5px;
  ._thumb{width: 50px; height: 50px; margin-right: 15px; background-color: #fff;}
}
</style>
