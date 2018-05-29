const page = {
	state: {
		loading: false,
		scrollTop: 0,
		direction: 'forward'
	},
	// commit 提交 mutation
	mutations: {
    updateLoading(state, payload) {
      state.loading = payload.loading
    },
    updateDirection (state, payload) {
      state.direction = payload.direction
    },
    updateScrollTop (state, payload) {
      state.scrollTop = payload.top
		}
	},
	// dispatch 分发 action
	actions: {
		// updateScrollTop ({ commit }, top) {
    //   commit('updateScrollTop', { top: top })
    // }
	}
}


export default page