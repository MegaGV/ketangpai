<template>
    <div>
        <div style="height: 60px;box-shadow: 0px 1px 3px 0px rgba(0, 0, 0, 0.04);">
            <div class="return">
                ←<router-link class="course_button" :to="{ path: '/student/course/' + id + '/homework'}">{{course.name}} </router-link>
            </div>
            
            <el-menu :default-active="activeIndex" class="el-menu-demo"  mode="horizontal" style="position:absolute;margin-left:800px" >
                <el-menu-item index="1" @click="activeIndex = '1'">成员</el-menu-item>
                <el-menu-item index="2" @click="activeIndex = '2'">学生分组</el-menu-item>
                <el-menu-item index="3" @click="activeIndex = '3'">成绩</el-menu-item>
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

        <el-divider></el-divider>

        <div style="width:1224px;margin:0 auto">
            <el-tabs tab-position="left" style="height: auto;width:1222px">
                <el-tab-pane label="教学团队">
                        <div class="title" >
                            <h3 style="margin:0;text-align:left;line-height:55px;float:left">教师团队 老师({{teachers.length}})</h3>
                            <el-input placeholder="姓名，学号" suffix-icon="el-icon-search" style="width:200px;float:right;line-height:55px;"></el-input>
                        </div>

                        <el-table :data="teachers">
                            <el-table-column><el-avatar icon="el-icon-user-solid"></el-avatar></el-table-column>
                            <el-table-column prop="name" label="姓名" />
                            <el-table-column label="身份">
                                <template slot-scope="scope">
                                    <span v-if="scope.$index == 0">(管理员)</span>
                                    <span v-else>(助教)</span>
                                </template>
                            </el-table-column>
                            <el-table-column prop="school" label="学校" />
                            <el-table-column prop="email" label="邮箱" />
                            <el-table-column prop="phone" label="电话" />
                        </el-table>
                </el-tab-pane>
                <el-tab-pane label="全部学生">
                        <div class="title">
                            <h3 style="margin:0;text-align:left;line-height:55px;float:left">全部学生 学生({{students.length}})</h3>
                            <el-input placeholder="姓名，学号" suffix-icon="el-icon-search" style="width:200px;float:right;line-height:55px;"></el-input>
                        </div>

                        <el-table :data="students">
                            <el-table-column><el-avatar icon="el-icon-user-solid"></el-avatar></el-table-column>
                            <el-table-column prop="name" label="姓名" />
                            <el-table-column prop="school" label="学校" />
                            <el-table-column prop="schoolID" label="学号" />
                            <el-table-column prop="email" label="邮箱" />
                            <el-table-column prop="phone" label="电话" />
                        </el-table>
                </el-tab-pane>
            </el-tabs>
        </div>
            
        
    </div>
</template>

<script>
export default {
    data(){
        return{
            activeIndex: "1",
            Ttable_visible:true,
            Stable_visible:false,
            quit: true,
            user:{},
            course:{},
            teachers:[],
            students:[],
        }
    },
    computed: {
        id(){
            return this.$route.params.id;
        },
    },
    methods: {
        getUserById(id) {
            this.$axios.get('api/UserController/getUserById?id=' + id)
            .then(res => {
                this.user = res.data
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
        getTeacherList(){
            this.$axios.get('api/CourseController/getCourseAssistants?id=' + this.id)
            .then(res => {
                this.teachers = res.data;
                this.$axios.get('api/CourseController/getCourseTeacher?id=' + this.id)
                .then(res => {
                    this.teachers.unshift(res.data);
                })
                .catch(err => {
                    alert("获取老师失败");
                    console.log(err);
                })
            })
            .catch(err => {
                alert("获取助教失败");
                console.log(err);
            })
        },
        getStudentList(){
            this.$axios.get('api/CourseController/getCourseStudents?id=' + this.id)
            .then(res => {
                this.students = res.data;
            })
            .catch(err => {
                alert("获取学生失败");
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
        list_change(){
            this.Ttable_visible = this.Stable_visible;
            this.Stable_visible = !this.Stable_visible;
        }
    },
    mounted(){
        this.getUserById(1);
        this.getCourseById();
        this.getTeacherList();
        this.getStudentList();
    }
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
.title {
    padding: 0 12px 0 25px;
    height: 55px;
    background: rgba(241,243,244,1);
    border-radius: 0 8px 0 0;
}


</style>