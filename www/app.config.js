const apiURL = Object.is(process.env.NODE_ENV, 'production') ? 'https://api.xfnauto.com' : 'http://api.mifengqiche.com'

export default {
	resURL: 'https://res.xfnauto.com/',
	apiURL
}
