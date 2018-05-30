<template>
  <view-box >
    <div class="l-map"></div>
    <div class="l-tel-mail l-flex-hvc">
      <div class="_item" style="margin-right: 30px;">
        <img src="../assets/images/20180527041.png" alt="">
        <span>联系电话：<br>400-1936-989</span>
      </div>
      <div class="_item">
        <img src="../assets/images/20180527042.png" alt="">
        <span>电子邮箱：<br>customer@xfnauto.com</span>
      </div>
    </div>
    
    <div class="l-margin-tb l-bg-white">
      <div class="l-padding-t l-txt-center"><b>在线留言</b></div>
      <div class="l-contact-form">
        <label class="_ipt">
          <span>您的姓名：</span>
          <input v-model="formData.username" type="text" placeholder="请输入姓名" maxlength="20">
        </label>
        <label class="_ipt">
          <span>您的邮箱：</span>
          <input v-model="formData.email" type="text" placeholder="请输入电子邮箱" maxlength="100">
        </label>
        <div class="_ipt">
          <textarea v-model="formData.content" rows="5" placeholder="请输入您的宝贵留言，我们回尽快跟进回复..." maxlength="1000"></textarea>
        </div>
      </div>
      <div class="l-padding-b l-txt-center">
        <button class="l-btn-1" round @click="submit">提交留言</button>
      </div>
    </div>
    
  </view-box>
</template>

<script>
export default {
  name: 'contact',
  data () {
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
        this.$toptip('请输入您的姓名')
        return
      }
      if(!this.formData.email) {
        this.$toptip('请输入您的电子邮箱')
        return
      }
      if(!this.formData.content) {
        this.$toptip('请输入您的宝贵留言')
        return
      }

      this.$vux.loading.show()
      this.$http.post('/pc_v1/note', this.formData).then(_ => {
        this.$vux.toast.show({
          text: '留言成功',
          onHide: _ => {
            this.$utils.copyObj(this.formData, '')
          }
        })
      }).finally(_ => {
        this.$vux.loading.hide()
      })
    }
  }
}
</script>

<style lang="less" scoped>
.l-img-title{
  text-align: center;
  img{ vertical-align: top; height: 50px;}
}

.l-btn-1{display: inline-block; font-size: 14px; border:none; outline: none; background-color: #ec6e3a; color: #fff;  padding: 10px 30px;}
.l-btn-1[mini]{padding: 5px 30px;}
.l-btn-1[round]{border-radius: 50px;}

.l-map{
  background: url('../assets/images/20180529038.jpg') 50% 50% no-repeat; background-size: 100%;
  height: 140px;
}

.l-tel-mail{
  font-size: 12px; padding: 10px 0; background-color: #fff;
  ._item{background-color: #fff;  white-space: nowrap; display: inline-block;}
  img {margin-right: 5px; width: 30px; height: 30px;}
  span{display: inline-block;}
}

.l-contact-form{
  padding: 15px; background-color: #fff; margin-top: -10px; font-size: 14px;
  ._ipt{display: block; border: 1px solid #eee; border-radius: 5px; padding: 10px; margin: 10px 15px;}
  input, textarea{border: none; background-color: transparent; font-size: 14px; outline: 0;}
  textarea{width: 100%;}
}
</style>

