import Vue from 'vue'
import Vuex from 'vuex'

// import user from './user'
import page from './page'

Vue.use(Vuex)
const store = new Vuex.Store({
  modules: {
		page,
  	// user, 
  },
  getters: {
		loading: state => state.page.loading,
		scrollTop: state => state.page.scrollTop,
		direction: state => state.page.direction
  }
})

export default store