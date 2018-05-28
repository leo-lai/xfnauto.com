<template>
	<div class="l-page-bd">
    <div class="l-map"></div>
    <div class="l-tel-mail l-flex-hvc">
      <div class="_item" style="margin-right: 30px;">
        <img src="~static/images/20180527041.png" alt="">
        <span>联系电话：<br>400-1936-989</span>
      </div>
      <div class="_item">
        <img src="~static/images/20180527042.png" alt="">
        <span>电子邮箱：<br>customer@xfnauto.com</span>
      </div>
    </div>

    <div class="l-contact">
      <div class="_t l-flex-hc">
        <b>在线留言</b>
        <div class="l-rest"></div>
        <label class="_ipt">
          姓名：
          <input type="text" placeholder="请输入您的姓名" maxlength="20" v-model="formData.username">
        </label>
        <label class="_ipt">
          邮箱：
          <input type="text" placeholder="请输入您的邮箱" maxlength="50" v-model="formData.email">
        </label>
      </div>
      <div class="_c">
        <textarea rows="10" placeholder="请输入您的宝贵留言，我们回尽快跟进回复..." v-model="formData.content"></textarea>
      </div>
      <div class="_f">
        <button @click="submit">提交留言</button>
      </div>
    </div>
  </div>
</template>
<script>
export default {
	name: 'contact',
  head: {
    title: '联系我们'
  },
  data() {
    return {
      formData: {
        username: '',
        email: '',
        content: '',
      }
    }
  },
  methods: {
    submit() {
      if(!this.formData.username) {
        this.$message.error('请输入您的姓名')
        return
      }
      if(!this.formData.email) {
        this.$message.error('请输入您的电子邮箱')
        return
      }
      if(!this.formData.content) {
        this.$message.error('请输入您的宝贵留言')
        return
      }

      const loading = this.$loading()
      this.$http.post('/pc_v1/note', this.formData).then(_ => {
        this.$utils.copyObj(this.formData, '')
        this.$message.success('感谢您的留言，我们回尽快跟进回复...')
      }).finally(_ => {
        loading.close()
      })
    }
  }
}
</script>
<style scoped lang="scss">
.l-map{
  background: url('~static/images/20180527040.jpg') 50% 50% no-repeat;
  height: 320px;
}


.l-tel-mail{
  margin-top: -30px;
  ._item{background-color: #fff; border: 1px solid #eee; padding: 20px; border-radius: 20px; width: 250px; white-space: nowrap; display: inline-block;}
  img {margin-right: 10px;}
  span{display: inline-block;}
}

.l-contact{
  width:1000px; margin: 60px auto; border: 1px solid #eee; border-radius: 30px; background-color: #fff;
  b{font-size: 1rem;}
  ._t{background-color: #fbfbfb; padding: 30px;}
  ._c{
    padding: 30px 30px 10px;
    textarea{width: 100%; border:none; outline: 0; }
  }
  ._f{
    text-align: right; padding: 10px 30px 30px; 
    button{border:none; outline: none; background-color: #ec6e3a; color: #fff;  padding: 10px 30px; border-radius: 5px;}
  }
  ._ipt{
    margin-left: 20px;
    input{border: 1px solid #eee; padding: 7px 10px; margin-left: 10px; border-radius: 5px; outline: 0;}
  }
}
</style>