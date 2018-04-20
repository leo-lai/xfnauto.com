<template>
  <div class="infinite-loading-container">
    <div v-show="isLoading">
      <slot name="spinner"><i :class="spinnerType"></i></slot>
    </div>
    <div v-show="!isLoading && isComplete && isFirstLoad">
      <slot name="no-results">没有相关的数据</slot>
    </div>
    <div v-show="!isLoading && isComplete && !isFirstLoad">
      <slot name="no-more">已经没有更多了</slot>
    </div>
  </div>
</template>
<script>
  const spinnerMapping = {
    bubbles: 'loading-bubbles',
    circles: 'loading-circles',
    default: 'loading-default',
    spiral: 'loading-spiral',
    waveDots: 'loading-wave-dots',
  };

  /**
   * get the first scroll parent of an element
   * @param  {DOM} elm    the element which find scorll parent
   * @return {DOM}        the first scroll parent
   */
  function getScrollParent(elm) {
    if (elm.tagName === 'BODY') {
      return window;
    } else if (['scroll', 'auto'].indexOf(getComputedStyle(elm).overflowY) > -1) {
      return elm;
    }
    return getScrollParent(elm.parentNode);
  }

  /**
   * get current distance from footer
   * @param  {DOM} elm    scroll element
   * @param  {String} dir   calculate direction
   * @return {Number}     distance
   */
  function getCurrentDistance(elm, dir) {
    let distance;
    
    const scrollTop = isNaN(elm.scrollTop) ? elm.pageYOffset : elm.scrollTop;
    if (dir === 'top') {
      distance = scrollTop;
    } else {
      const scrollElmHeight = elm === window ? window.innerHeight : elm.getBoundingClientRect().height;
      distance = this.$el.offsetTop - scrollTop - scrollElmHeight - (elm.offsetTop || 0);
    }
    return distance;
  }

  export default {
    data() {
      return {
        scrollParent: null,
        scrollHandler: null,
        isLoading: false,
        isComplete: false,
        isFirstLoad: true, // save the current loading whether it is the first loading
      };
    },
    computed: {
      spinnerType() {
        return spinnerMapping[this.spinner] || spinnerMapping.waveDots;
      },
    },
    props: {
      distance: {
        type: Number,
        default: 50,
      },
      autoStart: {
        type: Boolean,
        default: true
      },
      onInfinite: Function,
      spinner: String,
      direction: {
        type: String,
        default: 'bottom',
      },
    },
    mounted() {
      this.scrollParent = getScrollParent(this.$el);

      this.scrollHandler = function scrollHandlerOriginal() {
        if (!this.isLoading) {
          this.attemptLoad();
        }
      }.bind(this);

      if(this.autoStart) {
        this.autoStartId = setTimeout(this.scrollHandler, 500);
      }
      this.scrollParent.addEventListener('scroll', this.scrollHandler);

      this.$on('$InfiniteLoading:loaded', () => {
        this.isFirstLoad = false;
        if (this.isLoading) {
          this.$nextTick(this.attemptLoad);
        }
      });
      this.$on('$InfiniteLoading:complete', () => {
        this.isLoading = false;
        this.isComplete = true;
        this.scrollParent.removeEventListener('scroll', this.scrollHandler);
      });
      this.$on('$InfiniteLoading:reset', (firstTime = true) => {
        this.isLoading = false;
        this.isComplete = false;
        this.isFirstLoad = true;
        this.scrollParent.removeEventListener('scroll', this.scrollHandler);
        this.scrollParent.addEventListener('scroll', this.scrollHandler);
        firstTime && setTimeout(this.scrollHandler);
      });
    },
    /**
     * To adapt to keep-alive feature, but only work on Vue 2.2.0 and above, see: http://vuejs.org/v2/api/#keep-alive
     */
    deactivated() {
      this.isLoading = false;
    },
    methods: {
      attemptLoad() {
        const currentDistance = getCurrentDistance.bind(this)(this.scrollParent, this.direction);
        if (!this.isComplete && currentDistance <= this.distance) {
          this.isLoading = true;
          this.onInfinite.call();
        } else {
          this.isLoading = false;
        }
      },
    },
    beforeDestroy() {
      clearTimeout(this.autoStartId)
      if (!this.isComplete) {
        this.scrollParent.removeEventListener('scroll', this.scrollHandler);
      }
    }
  };
</script>
<style lang="less" scoped>
@import './styles/spinner';
.infinite-loading-container{
  clear: both; text-align: center; margin: 15px 0; font-size: 14px; color: #666;
  *[class^=loading-]{
    display: inline-block;
    width: 12px;
    height: 12px;
    line-height: 12px;
    border-radius: 50%;
    vertical-align: -2px;
    margin-right: 5px;
  }
}
</style>
