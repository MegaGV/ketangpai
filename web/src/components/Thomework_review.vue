<template>
    <div>
        <div style="height: 60px;box-shadow: 0px 1px 3px 0px rgba(0, 0, 0, 0.04);">
            <div class="return">
                ←<router-link class="course_button" :to="{ path: '/teacher/course/' + id + '/homework'}">{{course.name}} </router-link>
            </div>
            
            <el-menu :default-active="activeIndex" class="el-menu-demo"  mode="horizontal" style="position:absolute;margin-left:800px" >
                <el-menu-item index="1" @click="activeIndex = '1'">成员</el-menu-item>
                <el-menu-item index="2" @click="activeIndex = '2'">学生分组</el-menu-item>
                <el-menu-item index="2" @click="activeIndex = '3'">成绩</el-menu-item>
            </el-menu>

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
            </ul>

        </div>

        
            
        
    </div>
</template>

<script>
export default {
    data(){
        return{
            activeIndex: "1",
            course:{
                id:"001", 
                name:"JavaEE",
                students:["001","002"]
            },
            homework:{
                id: "001", 
                name:"实验1", 
                introduce:"提交demo1", 
                starttime:"2019-12-16 16:09:33", 
                endtime:"2019-12-22 16:09:36",
                course: "001",
            },
            homeworks:[
                {id:"001",content:"",file:"",student:"stu",student_name:"张三",score:"0",complete:"1",review:"0", upload_data:"2019-12-24 11:24:59"},
                {id:"002",content:"",file:"",student:"stu2",student_name:"张三2",score:"0",complete:"0",review:"0",upload_data:""},
            ],
        }
    },
    computed: {
        show_detail () {
            return this.activeIndex == '1'
        },
        show_info() {
            return this.activeIndex == '2'
        },
        iscomplete() {
            return this.upload.complete == '1'
        },
        id(){
            return this.$route.params.id
        },
    },
    methods: {
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
}
</script>

<style >
.return{
    position: absolute;
    top: 24px;
    left: 2%;
    height: 24px;
    display: block;
    cursor:pointer;
}
.course_button{
    line-height: 32px;
    vertical-align: middle;
    margin-left: 14px;
    display: inline-block;
    height: 32px;
    border-radius: 16px;
    font-size: 14px;
    color:white;
    background: #2c58ab;
    font-weight: 500;
    padding: 0 10px;
    min-width: 80px;
    text-align: center;
    text-decoration: none;
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


</style>