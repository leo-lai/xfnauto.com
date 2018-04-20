<template>
  <div no-prevent-bounce>
    <div class="l-panel" :class="brand.visible ? '_center' : '_right'">
      <div class='_inner l-flex-v'>
        <div class='l-padding-btn'>选择品牌</div>
        <div class="l-rest l-scroll">
          <group gutter="0">
            <cell v-if="brand.loading">
              <div slot="title" class="l-txt-gray l-txt-center">
                <inline-loading></inline-loading>
                <span class="l-fs-m" style="vertical-align:middle;display:inline-block;">加载中...</span>
              </div>
            </cell>
            <cell is-link v-for="item in brand.list" :key="item.id" :title="item.name" @click.native="sltBrand(item)"></cell>
            <cell v-if="brand.list.length === 0 && !brand.loading">
              <div slot="title" class="l-txt-gray l-txt-center">暂无数据</div>
            </cell>
          </group>
        </div>
      </div>
    </div>

    <div class="l-panel" :class="family.visible ? '_center' : '_right'" @click="closeFamily">
      <div class='_inner l-flex-v' style='left: 15%;' @click.stop>
        <div class='l-padding-btn'>{{ brand.slted.name || '选择车系' }}</div>
        <div class="l-rest l-scroll">
          <group gutter="0">
            <cell v-if="family.loading">
              <div slot="title" class="l-txt-gray l-txt-center">
                <inline-loading></inline-loading>
                <span class="l-fs-m" style="vertical-align:middle;display:inline-block;">加载中...</span>
              </div>
            </cell>
            <cell is-link v-for="item in family.list" :key="item.id" :title="item.name" @click.native="sltFamily(item)"></cell>
            <cell v-if="family.list.length === 0 && !family.loading">
              <div slot="title" class="l-txt-gray l-txt-center">暂无数据</div>
            </cell>
          </group>
        </div>
      </div>
    </div>

    <div class="l-panel" :class="car.visible ? '_center' : '_right'" @click="closeCar">
      <div class='_inner l-flex-v' style='left: 30%;' @click.stop>
        <div class='l-padding-btn'>{{ family.slted.name || '选择型号' }}</div>
        <div class="l-rest l-scroll">
          <group gutter="0">
            <cell v-if="car.loading">
              <div slot="title" class="l-txt-gray l-txt-center">
                <inline-loading></inline-loading>
                <span class="l-fs-m" style="vertical-align:middle;display:inline-block;">加载中...</span>
              </div>
            </cell>
            <cell v-for="item in car.list" :key="item.id" @click.native="sltCar(item)">
              <div slot="title">
                <p class="l-fs-m">{{item.name}}</p>
                <p class="l-fs-s l-txt-gray l-margin-ts-s">指导价：{{item.priceStr}}</p>
              </div>
            </cell>
            <cell v-if="car.list.length === 0 && !car.loading">
              <div slot="title" class="l-txt-gray l-txt-center">暂无数据</div>
            </cell>
          </group>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { InlineLoading } from 'vux'

export default {
  name: 'car-selector',
  components: { InlineLoading },
  data () {
    return {
      brand: {
        visible: true,
        loading: false,
        slted: {},
        list: []
      },
      family: {
        visible: false,
        loading: false,
        slted: {},
        list: []
      },
      car: {
        visible: false,
        loading: false,
        slted: {},
        list: []
      }
    }
  },
  methods: {
    // 品牌列表
    getBrandList: function () {
      this.brand.loading = true
      this.$api.car.getBrandList().then(({ data }) => {
        this.brand.list = data
      }).finally(_ => {
        this.brand.loading = false
      })
    },
    sltBrand: function (item) {
      this.brand.slted = item
      this.$storage.session.set('brand_slted', item)
      this.getFamilyList()
    },
    // 车系列表
    getFamilyList: function () {
      this.family.loading = true
      this.family.visible = true
      this.$api.car.getFamilyList(this.brand.slted.id).then(({ data }) => {
        this.family.list = data
      }).finally(_ => {
        this.family.loading = false
      })
    },
    closeFamily: function() {
      this.family.visible = false
    },
    sltFamily: function (item) {
      this.family.slted = item
      this.$storage.session.set('family_slted', item)
      this.getCarList()
    },
    // 车类列表
    getCarList: function () {
      this.car.loading = true
      this.car.visible = true
      this.$api.car.getCarList(this.family.slted.id).then(({ data }) => {
        this.car.list = data.map(item => {
          item.priceStr = item.price ? (item.price / 10000).toFixed(2) + '万' : '无'
          return item
        })
      }).finally(_ => {
        this.car.loading = false
      })
    },
    sltCar: function (item) {
      this.car.slted = item
      this.$storage.session.set('car_slted', item)
      this.$router.back()
    },
    closeCar: function() {
      this.car.visible = false
    }
  },
  mounted() {
    setTimeout(() => {
      this.getBrandList()  
    }, 500)
  }
}
</script>
<style lang="less">

</style>
