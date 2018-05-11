<template>
  <view-box>
    <group gutter="0" label-width="6em">
      <cell title="托运方式" >
        <div v-if="formData.consignmentTypeLineName">{{formData.consignmentTypeLineName}}</div>
        <div v-else class="l-txt-gray">
          <img class="l-img-icon" src="../assets/images/icon-021.png" alt="">
          <span>请选择</span>
        </div>
      </cell>
      <cell title="起点" align-items="flex-start" :disabled="formData.consignmentType == 2" link="/map/selector?name=wuliu-freight-start">
        <div v-if="formData.startingPointAddress" class="l-fs-m">{{formData.startingPointAddress}}</div>
        <div v-else class="l-txt-gray">
          <img class="l-img-icon" src="../assets/images/icon-021.png" alt="">
          <span>请选择</span>
        </div>
      </cell>
      <cell title="终点" align-items="flex-start" :disabled="formData.consignmentType == 2" link="/map/selector?name=wuliu-freight-end">
        <div v-if="formData.destinationAddress" class="l-fs-m">{{formData.destinationAddress}}</div>
        <div v-else class="l-txt-gray">
          <img class="l-img-icon" src="../assets/images/icon-021.png" alt="">
          <span>请选择</span>
        </div>
      </cell>
    </group>

    <div class="l-margin-t l-bg-white l-zoom">
      <div class="l-flex-hc l-padding-btn">
        <img class="l-img-icon l-margin-r-m" src="../assets/images/icon-013.png" alt="">
        <h4 class="l-rest">托运车辆</h4>
        <router-link class="l-fr l-link-main l-is-link" to="/wuliu/freight/addcar">添加车辆</router-link>
      </div>
      <div class="l-car-item l-flex-hc" v-for="item in addcar" :key="item.guid">
        <!-- <img class="_thumb" src="" alt=""> -->
        <div class="l-rest">
          <h4 class="l-txt-wrap1">{{item.carsName}}</h4>
          <p class="l-txt-gray l-fs-m l-margin-t-s">
            <span class="l-fr" @click="delcar(item.guid)"><icon type="clear"></icon></span>
            <span>数量：{{item.carNumber}}辆</span>
            <span class="l-margin-l">指导价：<i class="l-rmb">{{item.guidancePrice}}万</i></span>
          </p>
        </div>
      </div>
      <div v-if="addcar.length === 0" class="l-car-none">您尚未添加车辆</div>
    </div>
    <div class="l-margin l-fs-s l-txt-gray">
      注意事项：各路线运费，仅供参考，以实际下单的运费为准。可随时咨询
      <a class="l-txt-gray" :href="'tel:' + $config.tel">{{$config.tel}}</a>
    </div>

    <div v-transfer-dom>
      <x-dialog v-model="showFreight" hide-on-blur :dialog-style="{'max-width': '100%', width: '100%', 'background-color': 'transparent'}">
        <div class="l-wuliu-freight">
          <div class="_hd">运费详情</div>
          <div class="_bd l-fs-m">
            <div>
              <span class="l-fr">{{freightInfo.consignmentTypeLineName}}</span>
              <span class="l-txt-gray">运输方式：</span>
            </div>
            <div>
              <span class="l-fr">{{freightInfo.number}}辆</span>
              <span class="l-txt-gray">运输数量：</span>
            </div>
            <!-- <div class="l-margin-t-m">
              <span class="l-fr"><i class="l-rmb">{{freightInfo.amountLine}}</i></span>
              <span class="l-txt-gray">运费：</span>
            </div> -->
            <!-- <div class="l-margin-t-m" v-if="freightInfo.grade">
              <span class="l-fr"><i class="l-rmb">{{freightInfo.grade}}</i></span>
              <span class="l-txt-gray">附加费：</span>
            </div> -->

            <div class="l-margin-t">
              <span class="l-fr"><i class="l-rmb l-txt-theme l-fs-l">{{freightInfo.amount}}</i></span>
              <span class="l-txt-gray">应付总额：</span>
            </div>

            <div class="l-flex-hc _man">
              <img class="_thumb" :src="$config.thumb3" alt="">
              <div class="l-rest">
                <p>联系客户经理下单</p>
                <p>{{$config.tel}}</p>
              </div>
              <a :href="'tel:' + $config.tel" border class="l-txt-icon" title="联系Ta">
                <i class="l-icon">&#xe613;</i>
              </a>
            </div>
          </div>
          <div class="l-margin-t">
            <x-icon type="ios-close-outline" style="fill:#fff;" @click="showFreight = false"></x-icon>
          </div>
        </div>
      </x-dialog>
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
import { Icon, XDialog } from 'vux'
export default {
  name: 'wuliu-freight',
  components: { Icon, XDialog },
  data () {
    return {
      showFreight: false,
      freightInfo: {},
      formData: {
        consignmentType: '',
        consignmentTypeLineId: '',
        consignmentTypeLineName: '',
        startingPointAddress: '',
        startingPointLatitude: '',
        startingPointLongitude: '',
        destinationAddress: '',
        destinationLatitude: '',
        destinationLongitude: '',
        carsIds: '',
        number: ''
      },
      addcar: []
    }
  },
  methods: {
    submit() {
      let formData = Object.assign({}, this.formData)
      if(formData.consignmentType === 1) {
        if(!formData.startingPointAddress) {
          this.$toptip('请选择起点')
          return
        }
        if(!formData.destinationAddress) {
          this.$toptip('请选择终点')
          return
        }
      }

      if(this.addcar.length === 0) {
        this.$toptip('请添加托运车辆')
        return
      }

      let carList = []
      this.addcar.forEach(item => {
        let carItem = {
          carsId: item.carsId,
          carsName: item.carsName
        }
        for (let i = 0; i < item.carNumber; i++) {
          carList.push(carItem)
        }
      })

      formData.carsIds = carList.map(item => item.carsId).join(',')
      formData.number = carList.length
      
      this.$vux.loading.show()
      this.$api.wuliu.getFreight(formData).then(({data}) => {
        this.showFreight = true
        this.freightInfo = Object.assign({}, formData, data)
      }).finally(_ => {
        this.$vux.loading.hide()
      })
    },
    delcar(guid) {
      this.addcar = this.addcar.filter(item => item.guid !== guid)
      this.$storage.session.set('wuliu-freight-addcar', this.addcar)
    }
  },
  mounted() {
    let tempData = this.$storage.session.get('wuliu-freight-formdata')
    if(tempData) {
      this.formData = Object.assign({}, this.formData, tempData)
    }

    let startLoc = this.$storage.session.get('wuliu-freight-start_loc')
    if(this.formData.consignmentType == 1 && startLoc) {
      this.formData.startingPointAddress = startLoc.poiaddress
      this.formData.startingPointLatitude = startLoc.latlng.lat
      this.formData.startingPointLongitude = startLoc.latlng.lng
    }
    let endLoc = this.$storage.session.get('wuliu-freight-end_loc')
    if(this.formData.consignmentType == 1 && endLoc) {
      this.formData.destinationAddress = endLoc.poiaddress
      this.formData.destinationLatitude = endLoc.latlng.lat
      this.formData.destinationLongitude = endLoc.latlng.lng
    }

    this.addcar = this.$storage.session.get('wuliu-freight-addcar') || []
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
.l-wuliu-freight{
  width: 280px; margin:auto;
  ._hd{padding: 15px; line-height: 1; background: url('../assets/images/20180402019.png') no-repeat; background-size: 100%; text-align: left; color: #fff;}
  ._bd{background-color: #fff; padding: 15px; text-align: left;}
  ._man{
    font-size: 12px; width: 100%; margin-top: 30px;
    ._thumb{width: 40px; height: 40px; margin-right: 10px; border-radius: 50%;}
    ._tag{
      display: inline-block; font-size: 12px; border-radius: 15px; padding: 1px 10px 1px 5px; color:#333; border: 1px solid #d9d9d9; margin-top: -5px;
      ._icon{width: 20px; height: 20px; vertical-align: -4px; margin-right: 1px;}
    }
  }
}
</style>
