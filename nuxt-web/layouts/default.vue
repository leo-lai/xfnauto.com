<template>
	<div class="l-page">
		<l-header></l-header>
		<nuxt/>
		<l-footer></l-footer>
	</div>
</template>
<script>
import lHeader from '~/components/header'
import lFooter from '~/components/footer'

export default {
	name: 'layout-default',
  components: {
    lHeader, lFooter
  }
}
</script>
<style lang="scss">
@import '~assets/css/base.scss';
@import '~assets/css/global.scss';
@import '~assets/css/elementui-custom.scss';
</style>