import Vue from 'vue'
import { utils, storage } from '~/assets/js/utils'
import VueScrollTo from 'vue-scrollto'

Vue.mixin({
  created: function () {
    this.$utils = utils
    this.$storage = storage
    this.$scrollTo = (elemOrselector) => {
    	let elem = utils.isString(elemOrselector) ? document.querySelector(elemOrselector) : utils.isDom(elemOrselector) ? elemOrselector : null
      if (elem) {
        if (window.requestAnimationFrame) {
          VueScrollTo.scrollTo(elem, 300)
        } else {
          window.scrollTo(0, 0)
        }
      }
    }
  }
})
