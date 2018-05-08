import Vue from 'vue'
import { utils, storage } from '~/assets/js/utils'
import VueScrollTo from 'vue-scrollto'

Vue.mixin({
  created: function () {
    this.$utils = utils
    this.$storage = storage
    this.$scrollTo = (elem_or_selector) => {
    	let elem = utils.isString(elem_or_selector) 
    		? document.querySelector(elem_or_selector) :
    		utils.isDom(elem_or_selector) ? elem_or_selector : null

      if(elem){
        if (window.requestAnimationFrame) {
          VueScrollTo.scrollTo(elem, 300)
        } else {
          window.scrollTo(0,0)
        }  
      }
    }
  }
})
