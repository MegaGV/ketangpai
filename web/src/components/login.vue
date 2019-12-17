<template>
    <div :style="background" >
        <div class="reg-log" >
            <el-form ref="user" :model="user" class="login-box" id="login">
                <div class="items">       
                    <a class="active">账号登录</a>
                </div>
                <div class="padding-cont pt-login">
                    <el-form-item >
                        <el-input v-model="user.account" type="text" placeholder="账号"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-input v-model="user.password" type="password" placeholder="密码"></el-input>
                    </el-form-item>
                    <el-form-item class="opt">
                        <el-checkbox class="auto-login" style="float:left">下次自动登录</el-checkbox>
                        <a class="forget" style="float:right">忘记密码？</a>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="login" style="height: 50px;width:420px;">登录</el-button>
                    </el-form-item>
                    
                    <router-link to="/register" class="btn-register">
                        <span class="str1">还没有账号？</span>
                        <span class="str2">去注册</span>
                    </router-link>
                </div>
            </el-form>
        </div>
    </div>
</template>

<script>
export default {
  data() {
      return {
        background:{
            width: '1920px',
            height: '911px',
            margin:'0',
            backgroundImage: "url(" + require("../img/login-bg2.jpg") + ")",
            backgroundRepeat:'no-repeat',
            backgroundSize:'cover'
        },
        user:{},
        }
  },
  methods: {
    login() {
      this.$axios
        .post("api/login", qs.stringify(this.user), {
          headers: {
            'Content-Type':'application/x-www-form-urlencoded',
            }
        })
        .then(res => {
          alert("登录成功");
          this.$router.push('/index')
      })
      .catch(err => {
        alert(err.response.data.msg);
        console.log(err);
      });
    },
  },
  
}
</script>

<style>
.reg-log{
    width: 480px;
    height: 515px;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%,-65%);
}
.login-box {
    background: #FFF;
    margin-left: auto;
    margin-right: auto;
    border-radius: 4px;
}
.items {
    padding-top: 47px;
    padding-bottom: 58px;
}
.items a.active {
    font-size: 26px;
    font-family: PingFangSC-Medium;
    font-weight: 500;
}

.opt {
    padding: 20px 0;
    height: 24px;
    line-height: 24px;
}
.auto-login:hover {
    background-position: left -34px;
    color: #4d90fe;
}
.forget:hover {
    color: #4d90fe;
}
.btn-register {
    float: right;
    text-decoration: none;
    margin-top: 24px;
}
.btn-register .str1 {
    color: #AFB1B3;
}
.btn-register .str2 {
    color: #32BAF0;
}
.login-box .padding-cont {
    padding: 0 30px 60px;
}

</style>