<template>
<div class="bge">
    <div class="nav-default ">
        <ul class="nav-menu-left">
            <li class="nav-menu-item">
                <el-image class="logo" :src="logo" style="width:96px;height:24px"></el-image>
            </li>
            <li class="nav-menu-item">
                <a class="active">课堂</a>
            </li>
            <li class="nav-menu-item">
                <a>精品专区</a>
            </li>
            <li class="nav-menu-item">
                <a>我的精品</a>
            </li>
        </ul>
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
    <div class="courses">
        <div class="courses_top">
            <p style="float:left;height: 36px;line-height: 36px;">全部课程</p>
            <el-button size="small" type="primary" style="float:right" @click="join_class">+ 加入课程</el-button>
            <div style="float:right">
                <ul>
                    <li style="float:right;margin-right:10px;cursor:pointer" @click="fieldsort_visible = true;fieldsort_part = 'field'">
                        <i class="el-icon-collection"></i> 
                        <a>归档管理</a>
                    </li>
                    <li style="float:right;margin-right:10px;cursor:pointer" @click="fieldsort_visible = true;fieldsort_part = 'sort'"> 
                        <i class="el-icon-sort"></i>
                        <a>课程排序</a>
                    </li>
                </ul>
            </div>
        </div>
        <el-card v-for="course in courses" v-bind:key="course.id" class="course_card" :body-style="{ padding: '0px' }">
            <div class="course_card_top" :style="coursebk">
                <strong class="jump_to_class" >
                    <router-link style="text-decoration: none;color:white" :to="{ path: '/student/course/' + course.id}">{{course.name}}</router-link>
                    <div>{{course.introduce}}</div>
                </strong>
                <p class="code">加课码：{{course.code}}</p>
            </div>
            <ul class="course_card_homework">
                <li style="text-align:left">
                    <span>近期作业</span>
                </li>
                <li v-for="homework in course.homeworks" v-bind:key="homework" style="text-align:left">
                    <a>{{homework}}</a>
                </li>
            </ul>
            <div class="course_card_foot">
                <el-avatar icon="el-icon-user-solid" :size="20" style="float:left;"></el-avatar>
                <span style="float:left;" >{{course.teacher}}</span>
                <el-dropdown style="float:right">
                    <span style="cursor:pointer;color:blue">更多</span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item @click.native="file_class(course.id)">归档</el-dropdown-item>
                        <el-dropdown-item @click.native="exit_class(course.id)">退课</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
                <span style="float:right;font-size:10px;margin-right:10px">置顶</span>
            </div>
        </el-card>
        <el-card class="course_card" :body-style="{ padding: '0px' }">
            <div class="course_card_top" :style="joinbk">
            </div>
            <div>
                <div style="text-align:center;margin:55px;cursor:pointer" >
                    <p @click="join_class">+<br>加入课程</p>
                </div>
            </div>
        </el-card>
    </div>
    <el-dialog :visible.sync="fieldsort_visible" width="30%" center class="field_sort">
        <el-tabs v-model="fieldsort_part">
            <el-tab-pane label="课程排序" name="sort">
                <ul class="course_sort">
                    <li v-for="course in courses" v-bind:key="course.id" class="sort_item">
                        <i class="el-icon-eleme"></i>
                        <span style="font-size: 16px;vertical-align: middle;">{{course.name}}&nbsp;{{course.introduce}}</span>
                    </li>
                </ul>
            </el-tab-pane>
            <el-tab-pane label="归档管理" name="field">
                <p v-if="fieldcourses.length == 0" style="text-align:center">暂无课程被归档</p>
                <el-card v-for="fieldcourse in fieldcourses" v-bind:key="fieldcourse.id" class="field_course_card" :style="fieldcoursebk">
                    <strong class="jump_to_class2" >
                        <div>{{fieldcourse.name}}&nbsp;{{fieldcourse.introduce}}</div>
                    </strong>
                    <p class="code2">
                        角色:学生&nbsp;老师:
                        <span>{{fieldcourse.teacher}}</span>
                        </p>
                    <el-dropdown style="float:right">
                        <i style="font-size:30px;color:white" class="el-icon-more"></i>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item @click.native="unfile_class(fieldcourse.id)">恢复</el-dropdown-item>
                            <el-dropdown-item @click.native="exit_class(fieldcourse.id)">退课</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </el-card>
            </el-tab-pane>
        </el-tabs>
    </el-dialog>

    <el-dialog title="要归档此课程吗" :visible.sync="field_visible" width="20%">
        <span>您可以在“课堂”-“归档管理”中查看此课程</span>
        <span slot="footer" class="dialog-footer">
            <el-button @click="field_visible = false">取消</el-button>
            <el-button type="primary" @click="file_class2">归档</el-button>
        </span>
    </el-dialog>
</div>


</template>

<script>
export default {
    data() {
        return{
            logo:require("../img/logo-mainblue.png"),
            coursebk:{backgroundImage: "url(" + require("../img/04.png") + ")",},
            fieldcoursebk:{backgroundImage: "url(" + require("../img/10.jpg") + ")",},
            joinbk:{backgroundImage: "url(" + require("../img/create-course.png") + ")",},
            fieldsort_visible:false,
            fieldsort_part:"",
            field_visible:false,
            readytofile:"",
            user:{},
            courses:[],
            fieldcourses: [],
        }
    },
    methods: {
        courseInitial(){
            this.getAllCourses(this.user.courses.join(','))
            this.getAllFieldCourses(this.user.fieldcourses.join(','))
        },
        getUserById(id) {
            this.$axios.get('api/UserController/getUserById?id=' + id)
            .then(res => {
                this.user = res.data
                this.user.courses = this.user.courses.split(",");
                this.user.fieldcourses = this.user.fieldcourses.split(",");

                this.courseInitial();
            })
            .catch(err => {
                alert("获取用户失败");
                console.log(err);
            })
        },
        getAllCourses(courses){
            this.$axios.get('api/CourseController/getAllCourses?courses=' + courses)
            .then(res => {
                this.courses = res.data;

                for(let i = 0; i < this.courses.length; i++){
                    this.courses[i].homeworks = this.courses[i].homeworks.split(',')
                }
                this.getHomeworkName();
                this.getCoursesTeachersName();
            })
            .catch(err => {
                alert("获取课程失败");
                console.log(err);
            })
        },
        getAllFieldCourses(courses){
            this.$axios.get('api/CourseController/getAllCourses?courses=' + courses)
            .then(res => {
                this.fieldcourses = res.data;

                this.getFieldCoursesTeachersName();
            })
            .catch(err => {
                alert("获取课程失败");
                console.log(err);
            })
        },
        
        join_class(){
            this.$prompt('请输入加课码', '加入课程', {
                confirmButtonText: '加入',
                cancelButtonText: '取消',
            }).then(({ value }) => {
                this.$axios.get('api/CourseController/joinCourse?id=' + this.user.id + "&code=" + value)
                    .then(res => {
                        alert(res.data);
                        this.getUserById(1);
                    })
                    .catch(err => {
                        alert("加课失败");
                        console.log(err);
                    })
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '取消加课'
                });
            });
        },
        exit_class(cid){
            this.$confirm('你将退选本门课程，确定吗?', '退课', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$axios.post('api/CourseController/exitCourse?id=' + this.user.id + "&cid=" + cid)
                    .then(res => {
                        alert("退课成功");
                        this.fieldsort_visible = false;
                        this.getUserById(1);
                    })
                    .catch(err => {
                        alert("退课失败");
                        console.log(err);
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '取消退课'
                    });
            });
            
        },
        file_class(id){
            this.field_visible = true;
            this.readytofile = id;
        },
        file_class2(){
            this.$axios.post('api/CourseController/fileCourse?id=' + this.user.id + "&cid="+ this.readytofile)
            .then(res => {
                alert("归档成功");
                this.field_visible = false;
                this.getUserById(1);
            })
            .catch(err => {
                alert("归档失败");
                console.log(err);
            })
        },
        unfile_class(cid){
            this.$confirm('此课程会在课堂页上显示。', '要恢复此课程么？', {
                confirmButtonText: '恢复',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$axios.post('api/CourseController/unfileCourse?id=' + this.user.id + "&cid=" + cid)
                    .then(res => {
                        alert("恢复成功");
                        this.fieldsort_visible = false;
                        this.getUserById(1);
                    })
                    .catch(err => {
                        alert("恢复失败");
                        console.log(err);
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '取消恢复'
                    });
            });
        },
        getHomeworkName(){
            for(let i = 0; i < this.courses.length; i++){
                for(let j = 0; j < this.courses[i].homeworks.length; j++){
                    this.$axios.get('api/HomeworkContentController/getHomeworkContentName?id=' + this.courses[i].homeworks[j])
                    .then(res => {
                        this.courses[i].homeworks[j] = res.data;
                    })
                    .catch(err => {
                        alert("获取作业名失败");
                        console.log(err);
                    })
                }
            }
        },
        getCoursesTeachersName(){
            for(let i = 0; i < this.courses.length; i++){
                this.$axios.get('api/UserController/getUserName?id=' + this.courses[i].teacher)
                .then(res => {
                    this.courses[i].teacher = res.data;
                })
                .catch(err => {
                    alert("获取老师姓名失败");
                    console.log(err);
                })
            }
        },
        getFieldCoursesTeachersName(){
            for(let i = 0; i < this.fieldcourses.length; i++){
                this.$axios.get('api/UserController/getUserName?id=' + this.fieldcourses[i].teacher)
                .then(res => {
                    this.fieldcourses[i].teacher = res.data;
                })
                .catch(err => {
                    alert("获取老师姓名失败");
                    console.log(err);
                })
            }
        },
        

        logout() {
            this.$confirm('将退出登录, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$router.push('/login_page')
            });
        }
    },
    mounted(){
        this.getUserById(1);
    },
    computed:{
    }
}
</script>

<style >
.bge {
    height: 100%;
    min-height: 600px;
}
.nav-default {
    z-index: 2;
    padding: 0 4%;
    box-sizing: border-box;
    box-shadow: 0 0 10px #ccc;
    background-color: #fff;
    position: fixed;
    top: 0;
    width: 100%;
}
.nav-menu-left {
    margin-left: 0;
    margin-top: 5px;
    float:left;
}
.nav-menu-item {
    margin: 18px;
    float: left;
    
    position: relative;
    height: 27px;
    line-height: 27px;
    cursor:pointer;
}
.nav-menu-item a {
    padding-bottom: 22px;
    padding-left: 15px;
    padding-right: 15px;
}
.nav-menu-item a.active {
    border-bottom: 4px solid #2C58AB;
    border-left: 2px solid transparent;
    border-right: 2px solid transparent
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

.courses {
    height: 100%;
    overflow: hidden;
    padding: 85px 4% 100px;
}
.courses_top {
    padding-bottom: 12px;
    box-shadow: 0 1px 0 0 whitesmoke;
    margin-bottom: 20px;
    padding-top: 20px;
}
.course_card{
    width: 270px;
    background: #fff;
    position: relative;
    border: 1px solid #E2E6ED;
    border-radius: 8px;
    float:left;
    margin:15px;
}
.course_card_top{
    clear: both;
    height: 95px;
    padding-top: 18px;
    padding-bottom: 14px;
    border-radius: 4px 4px 0 0;
}
.jump_to_class{
    display: block;
    font-size: 20px;
    font-weight: lighter;
    color: #fff;
    height: 62px;
    margin-left: 18px;
    margin-right: 48px;
    overflow: hidden;
    text-align: left;
}
.jump_to_class2{
    font-size: 20px;
    font-weight: lighter;
    color: #fff;
    height: 26px;
    line-height: 26px;
}
.course_card_homework{
    margin-top: 15px;
    padding: 14px;
    height: 105px;
    border-bottom: 1px solid rgba(226,230,237,1);
}
.course_card_foot{
    width: 80%;
    position: absolute;
    bottom: 4px;
    left: 16px;
    right: 16px;
}
.code{
    height: 22px;
    background: rgba(255,255,255,.2);
    border-radius: 2px;
    font-size: 12px;
    font-weight: 500;
    color:white;
    line-height: 22px;
    text-align: center;
    margin-left: 18px;
    padding-left: 2px;
    padding-right: 4px;
    margin-top: 13px;
    float:left;
}
.code2{
    color: #fff;
    line-height: 20px;
    padding-top: 14px;
    font-size: 12px;
}
.filedsort {
    border: 1px solid #dcdcdc;
    position: fixed;
    width: 810px;
    margin-left: -405px;
    overflow: hidden;
}
field_course_card{
    width: 268px;
    clear: both;
    height: 72px;
    padding: 24px 16px 14px;
}
course_sort{
    width: 100%;
    position: absolute;
    left: 0;
    top: 0;
}
sort_item{
    cursor: pointer;
    background: #fff;
    margin-bottom: 1px;
    padding-left: 40px;
    height: 58px;
    line-height: 58px;
    border: 1px solid #ececec;
    margin-top: -1px;
    position: relative;
}
</style>