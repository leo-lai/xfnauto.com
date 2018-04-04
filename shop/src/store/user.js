import { storage, utils } from 'assets/js/utils'
import router from '../router'
import routes from '../router/routes'
import api from '../api'


let _cache = {
	userMenus: storage.local.get('usermenus')
}
const user = {
	state: {
		fetching: false,
		sessionId: storage.local.get('sessionId'),
		info: storage.local.get('userinfo'),
		menus: [],
		zuzhiList: [],
		roleList: []
	},
	mutations: {
		USER_INFO: (state, info = {}) => {
			storage.local.set('userinfo', info)
			state.info = info
		},
		USER_MENUS: (state, menus = []) => {
			storage.local.set('usermenus', menus)
			state.menus = menus
		},
		USER_SESSIONID: (state, sessionId = '') => {
			storage.local.set('sessionId', sessionId)
			state.sessionId = sessionId
		},
		ZUZHI_LIST: (state, list) => {
			state.zuzhiList = list
		},
		ROLE_LIST: (state, list) => {
			state.roleList = list
		}
	},
	actions: {
		checkLogin() {
			return new Promise((resolve, reject) => {
				if(!!storage.local.get('sessionId')) {
					resolve(storage.local.get('userinfo'))
				}else{
					reject()
				}
			})
		},
		login({ commit }, formData) {
			return api.auth.login(formData).then(({data}) => {
				let { sessionId, menus } = data
				_cache.userMenus = menus
				commit('USER_SESSIONID', sessionId)
				commit('USER_INFO', data)
				return data
			})
		},
		logout({ commit }, toLogin = true) {
			return api.auth.logout(toLogin)
		},
		getUserMenus({ commit }) {
			// 筛选出有权限的菜单
			// routes.forEach()
			// router.addRoutes(_cache.userMenus)
			commit('USER_MENUS', _cache.userMenus)
		},
		getZuzhiList({ commit }) { // 获取登录账号的组织列表
			return api.auth.getZuzhiList().then(({data}) => {
				commit('ZUZHI_LIST', data)
				return data
			})
		},
		getRoleList({ commit }) { // 获取登录账号的角色列表
			return api.auth.getRoleList().then(({data}) => {
				commit('ROLE_LIST', data)
				return data
			})
		}
	}
}

export default user