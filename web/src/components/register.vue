<template>
    <div :style="background" >
        <div class="reg-logs">
            <div v-if="user.identity == ''" class="identity-box">
                <h3>注册账号</h3>
                <p style=" margin-top: 20px; color: #707070;text-align:left">请选择您在日常教学中的实际身份</p>
                <div class="choose">
                    <el-image :src="Turl" class="img"></el-image>
                    <a class="teacher" @click="identityChange('teacher')">我是老师/助教</a>
                    <el-image :src="Surl" class="img" style="margin-top: 70px;"></el-image>
                    <a class="student" @click="identityChange('student')">我是学生</a>
                </div>
                <router-link to="/" class="btn-login">
                    <span style="color: #AFB1B3;">已有账号？</span>
                    <span style="color: #32BAF0;">去登录</span>
                </router-link>
            </div>
            <el-form ref="user" :model="user" v-if="user.identity == 'teacher' || user.identity == 'student'" class="login-box">
                <div class="title">
                    <el-image :src="Rurl" @click="identityChange('')" class="back" style="float:left"></el-image>
                    <h3 v-if="user.identity == 'teacher'" style="float:left">老师/助教注册</h3>
                    <h3 v-if="user.identity == 'student'" style="float:left">学生注册</h3>
                </div>
                <div class="padding-cont">
                    <el-form-item>
                        <el-input type="text" v-model="user.username" placeholder="帐号"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-input type="password" v-model="user.password" placeholder="密码"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-input type="text" v-model="user.name" placeholder="姓名"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-select style="width:420px" v-model="user.school" filterable placeholder="学校">
                            <el-option
                            v-for="school in schools"
                            :key="school.value"
                            :label="school.label"
                            :value="school.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item v-if="user.identity == 'student'">
                        <el-input type="text" v-model="user.schoolId" placeholder="学号"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-input type="text" v-model="user.phone" placeholder="电话"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-input type="text" v-model="user.email" placeholder="邮箱"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" v-if="user.identity == 'teacher'" @click="register" style="width:420px;">注册为老师/助教</el-button>
                        <el-button type="primary" v-if="user.identity == 'student'" @click="register" style="width:420px;">注册为学生</el-button>
                    </el-form-item>
                    
                    <router-link to="/" class="btn-login">
                        <span style="color: #AFB1B3;">已有账号？</span>
                        <span style="color: #32BAF0;">去登录</span>
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
            backgroundImage: "url(" + require("../img/reg-bg.png") + ")",
            backgroundRepeat:'no-repeat',
            backgroundSize:'cover'
        },
        user:{
            username: "",
            password: "",
            name: "",
            identity: "",
            school: "",
            schoolId: "",
            email: "",
            phone: "",
            courses: "",
            fieldcourses: "",
        },
        schools: [{value: '重庆大学',label: '重庆大学'},
                  {value: '西南大学',label: '西南大学'},
                  {value: '重庆理工大学',label: '重庆理工大学'}, 
                  {value: '重庆邮电大学',label: '重庆邮电大学'}, 
                  {value: '重庆交通大学',label: '重庆交通大学'}, 
                  {value: '重庆工商大学',label: '重庆工商大学'}],
        Turl:require("../img/teacher.png"),
        Surl:require("../img/student.png"),
        Rurl:require("../img/returnReg.png"),
    }
  },
  methods: {
      identityChange(identity){
        this.user.username = "";
        this.user.password = "";
        this.user.name = "";
        this.user.identity = identity;
        this.user.school= "";
        this.user.schoolId= "";
        this.user.email= "";
        this.user.phone= "";
        this.user.courses= "";
        this.user.fieldcourses= ""; 
      },
      register(){
        this.$axios
            .post("api/UserController/register", this.user)
            .then(res => {
                alert("注册成功");
                this.identityChange('')
        })
        .catch(err => {
            alert("注册失败");
            console.log(err);
        });
      }
  },
  
}
</script>

<style >
.reg-logs{
    width: 480px;
    height: 515px;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%,-65%);
}
.identity-box {
    width: 480px;
    height: 504px;
    padding: 47px 41px 0;
    background: #FFF;
    margin-left: auto;
    margin-right: auto;
    box-sizing: border-box;
    border-radius: 4px;
}
.identity-box h3 {
    font-size: 32px;
    font-weight: 400;
    color: #3B3D45;
    text-align: left;
}
.identity-box .choose {
    margin-top: 70px;
    margin-left: 15px;
}
.identity-box .choose a {
    display: block;
    width: 330px;
    font-size: 16px;
    color: #fff;
    height: 64px;
    line-height: 64px;
    text-align: center;
    background: #32BAF0;
    border-radius: 4px;
    margin-left: 70px;
    position: relative;
}
.identity-box .choose a.student {
    margin-top: 70px;
}
.img{
    top: 2px;
    width: 60px;
    height: 60px;
    float: left;
}
.btn-login{
    float: right;
    margin-top: 24px;
    text-decoration: none;
}
.login-box {
    background: #FFF;
    margin-left: auto;
    margin-right: auto;
    border-radius: 4px;
}
.back {
    width: 40px;
    height: 40px;
    display: inline-block;
    margin-right: 22px;
    margin-top:32px;
}
.title h3 {
    line-height: 40px;
    font-size: 32px;
    color: #3B3D45;
}
.padding-cont {
    margin-top: 25px;
}

</style>