<template>
    <div>
        <div class="course-nav">
            <div class="nav-menu-left">
                <p style="font-size: 18px;float:left;margin:25px 50px" class="el-icon-s-unfold" @click="drawer = true"></p>
                <el-breadcrumb separator-class="el-icon-arrow-right" style="float:left;font-size: 14px;line-height: 65px;width:700px;font-weight: 400;">
                    <el-breadcrumb-item :to="{ path: '/student' }">课堂</el-breadcrumb-item>
                    <el-breadcrumb-item>{{course.name}}&nbsp;{{course.introduce}}</el-breadcrumb-item>
                </el-breadcrumb>
            </div>
            
            <ul class="nav-menu-right">
                <li class="nav-menu-right-item">
                    <el-dropdown>
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item icon="el-icon-medal-1">开通VIP</el-dropdown-item>
                            <el-dropdown-item icon="el-icon-setting">个人设置</el-dropdown-item>
                            <el-dropdown-item icon="el-icon-switch-button" @click.native="logout">退出账户</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </li>
                <li class="nav-menu-right-item">
                <el-badge :value="1" >
                    <i style="font-size:20px;line-height:52px" class="el-icon-bell"></i>
                </el-badge>
                </li>
                <li class="nav-menu-right-item">
                    <a style="cursor:pointer;font-size:20px;line-height:52px" class="el-icon-document">论文查重</a>
                </li>
            </ul>
        </div>
        
        <div class="topbox" :style="topboxbk">
            <h1 style="margin:10px;text-align:left;color:white">{{course.name}}</h1>
            <h2 style="margin:10px;text-align:left;color:white">{{course.introduce}}</h2>
            <div>
                <div class="codetip">加课码：{{course.code}}</div>
                <div class="codetip">
                    <i class="el-icon-user-solid"></i>
                    <router-link :to="{ path: '/student/course/' + course.id + '/member'}" style="text-decoration: none;color:white">
                            同学
                    </router-link>
                    </div>
                <div class="codetip"><i class="el-icon-document"></i>成绩</div>
            </div>
        </div>
        <div class="coursebannernew">
            <div class="gWidth">
                <el-menu
                    :default-active="activeIndex"
                    class="el-menu-demo"
                    mode="horizontal"
                    background-color="rgba(241,243,244,1)"
                    text-color="color: #818181;"
                    active-text-color="rgba(59,61,69,1)">
                    <el-menu-item index="1">
                        <router-link :to="{ path: '/student/course/' + course.id + '/interaction'}" style="text-decoration: none;font-size:20px">
                            课堂互动
                        </router-link>
                    </el-menu-item>
                    <el-menu-item index="2">
                        <router-link :to="{ path: '/student/course/' + course.id + '/homework'}" style="text-decoration: none;font-size:20px">
                            作业
                        </router-link>
                    </el-menu-item>
                    <el-menu-item index="3">
                        <router-link :to="{ path: '/student/course/' + course.id + '/topic'}" style="text-decoration: none;font-size:20px">
                            话题
                        </router-link>
                    </el-menu-item>
                    <el-menu-item index="4">
                        <router-link :to="{ path: '/student/course/' + course.id + '/resource'}" style="text-decoration: none;font-size:20px">
                            资料
                        </router-link>
                    </el-menu-item>
                    <el-menu-item index="5">
                        <router-link :to="{ path: '/student/course/' + course.id + '/test'}" style="text-decoration: none;font-size:20px">
                            测试
                        </router-link>
                    </el-menu-item>
                    <el-menu-item index="6">
                        <router-link :to="{ path: '/student/course/' + course.id + '/notice'}" style="text-decoration: none;font-size:20px">
                            公告
                        </router-link>
                    </el-menu-item>
                </el-menu>
            </div>
        </div>
        
        <Scourse_content></Scourse_content>

        <el-drawer :visible.sync="drawer" :direction="dir" :modal = false size = "20%">
            <div class="left-nav-top">
                <a href='#/student/'><i class="el-icon-s-order" style="margin-right:30px"></i>课堂</a>
                <a><i class="el-icon-chat-dot-round" style="margin-right:30px"></i>私信</a>
            </div>
            <div class="open-course" data-role="0" style="height: 717px;">
                <div class="open-box" style="top: 0px;">
                    <p style="text-align:left;margin-left:20px">已有课程</p>
                    <div class="list">
                        <router-link v-for="course in courses" :key="course.id" :to="{ path: '/student/course/' + course.id}" style="text-align:left;text-decoration:none;">
                            <p style="margin-left:20px;font-size:20px;">
                                <i class="el-icon-eleme"></i>
                                {{course.name}}&nbsp;{{course.introduce}}
                            </p>
                        </router-link>                 
                    </div>            
                </div>
             </div>
        </el-drawer>
    </div>
</template>

<script>
import Scourse_content from '@/components/Scourse_content'
export default {
    components:{Scourse_content},
    data(){
        return{
            topboxbk:{backgroundImage: "url(" + require("../img/33.png") + ")",},
            drawer: false,
            dir: "ltr",
            activeIndex: '2',
            user:{},
            courses:[],
            course:{},
        }
    },
    computed: {
        id () {
            return this.$route.params.id
        }
    },
    methods:{
        getUserById(id) {
            this.$axios.get('api/UserController/getUserById?id=' + id)
            .then(res => {
                this.user = res.data
                this.getAllCourses(this.user.courses);
            })
            .catch(err => {
                alert("获取用户失败");
                console.log(err);
            })
        },
        getCourseById() {
            this.$axios.get('api/CourseController/getCourseById?id=' + this.id)
            .then(res => {
                this.course = res.data;
            })
            .catch(err => {
                alert("获取课程失败");
                console.log(err);
            })
        },
        getAllCourses(courses){
            this.$axios.get('api/CourseController/getAllCourses?courses=' + courses)
            .then(res => {
                this.courses = res.data;
            })
            .catch(err => {
                alert("获取课程失败");
                console.log(err);
            })
        },
        logout() {
            this.$confirm('将退出登录, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$router.push('/login_page')
            });
        },
    },
    mounted(){
        this.getUserById(1);
        this.getCourseById();
    }
}
</script>

<style >
.course-nav {
    width: 100%;
    position: fixed;
    height: 72px;
    top: 0;
    background: #fff;
    padding-right: 5%;
    z-index: 5;
    box-sizing: border-box;
}
.nav-menu-left {
    margin-left: 0;
    margin-top: 5px;
    float: left;
}
.nav-menu-right {
    margin-right: 0;
    margin-top: 8px;
    float:right;
}
.nav-menu-right-item{
    float:right;
    margin-top: 8px;
    margin-right: 15px;
}
ul {
    padding: 0;
    margin: 0;
    list-style: none;
}
li {
    display: list-item;
    list-style: none;
}
.left-nav-top {
    padding: 9px 0;
}
.left-nav-top a {
    display: block;
    height: 50px;
    line-height: 50px;
    font-size: 20px;
    cursor: pointer;
    text-align: left;
    margin-left:20px;
    text-decoration: none;
}
.left-nav-top a:hover {
    color: #32BAF0;
    background-color: #F1F3F4;
}
.topbox {
    margin: 105px auto 0;
    background: url(/Public/Common/img/ktp2.jpg) 0 center no-repeat;
    width: 1224px;
    background-size: cover;
    height: 200px;
    border-radius: 8px 8px 0 0;
    padding: 48px 0 0 40px;
    box-sizing: border-box;
}
.codetip {
    text-align: center;
    color:white;
    background: rgba(255,255,255,.4);
    border-radius: 2px;
    float: left;
    height: 24px;
    line-height: 24px;
    padding: 0 5px;
    margin-right:10px;
    font-size:14px;
}
.coursebannernew {
    width: 1224px;
    background: rgba(241,243,244,1);
    border-radius: 0 0 8px 8px;
    margin: 0 auto;
}
.gWidth {
    width: 100%;
    text-align: left;
    box-shadow: none;
}

</style>