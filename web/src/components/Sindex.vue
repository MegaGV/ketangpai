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
                        <el-dropdown-item icon="el-icon-switch-button">退出账户</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </li>
            <li class="nav-menu-right-item">
                <el-badge :value="12" >
                    <i style="font-size:30px" class="el-icon-bell"></i>
                </el-badge>
            </li>
            <li class="nav-menu-right-item">
                <a><el-image  class="paper-repeat" :src=paper_repeat></el-image>论文查重</a>
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
                    <div>{{course.name}}</div>
                    <div>{{course.introduce}}</div>
                </strong>
                <p class="code">加课码：{{course.code}}</p>
            </div>
            <ul class="course_card_homework">
                <li style="text-align:left">
                    <span>近期作业</span>
                    </li>
                <li v-for="homework in course.homeworks" v-bind:key="homework.id" style="text-align:left">
                    <a>{{homework.name}}</a>
                </li>
            </ul>
            <div class="course_card_foot">
                <el-avatar icon="el-icon-user-solid" :size="20" style="float:left;"></el-avatar>
                <span style="float:left;">{{course.teacher}}</span> 
                <el-dropdown style="float:right">
                    <span style="cursor:pointer;color:blue">更多</span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item @click.native="file_class">归档</el-dropdown-item>
                        <el-dropdown-item @click.native="exit_class">退课</el-dropdown-item>
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
                    <p class="code2">角色:学生&nbsp;老师:{{fieldcourse.teacher}}</p>
                    <el-dropdown style="float:right">
                        <i style="font-size:30px;color:white" class="el-icon-more"></i>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item @click.native="unfile_class">恢复</el-dropdown-item>
                            <el-dropdown-item @click.native="exit_class">退课</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </el-card>
            </el-tab-pane>
        </el-tabs>
    </el-dialog>
</div>


</template>

<script>
export default {
    data() {
        return{
            logo:require("../img/logo-mainblue.png"),
            paper_repeat:require("../img/paperrepeaticon.png"),
            coursebk:{backgroundImage: "url(" + require("../img/04.png") + ")",},
            fieldcoursebk:{backgroundImage: "url(" + require("../img/10.jpg") + ")",},
            joinbk:{backgroundImage: "url(" + require("../img/create-course.png") + ")",},
            fieldsort_visible:false,
            fieldsort_part:"",
            user:{
                account: "stu",
                password: "123456",
                name: "张三",
                identity: "student",
                school: "重庆理工大学",
                schoolId: "11703080201",
                email: "",
                phone: "",
                courses: ["001","002"],
                fieldcourses: [],
            },
            courses:[
                    {id: "001", name: "JavaEE", introduce: "117030802", code: "TY94UW", teacher: "徐传运", homeworks:[{id:"001", name:"实验1"}]},
                    {id: "002", name: "UML", introduce: "1738-2", code: "MHZ96P", teacher: "徐传运2", homeworks:[]},
            ],
            fieldcourses: [
                {id: "001", name:"Alogrim", introduce: "117030801、02", code: "NXWR4W", teacher: "徐传运",homeworks:[]},
            ],
        }
    },
    methods: {
        join_class(){
            this.$prompt('请输入加课码', '加入课程', {
                confirmButtonText: '加入',
                cancelButtonText: '取消'
                }).then(({ value }) => {
                    this.$message({
                        type: 'success',
                        message: '加课成功'
                    });
                }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '取消加课'
                });       
            });
        },
        exit_class(){
                this.$confirm('你将退选本门课程，确定吗?', '退课', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                this.$message({
                    type: 'success',
                    message: '退课成功!'
                });
                }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消退课'
                });          
            });
        },
        file_class(){
            this.$confirm('您可以在“课堂”-“归档管理”中查看此课程', '要归档此课程么？', {
                    confirmButtonText: '归档',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                this.$message({
                    type: 'success',
                    message: '归档成功,您可以在“归档管理”进行查看'
                });
                }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消归档'
                });          
            });
        },
        unfile_class(){
            this.$confirm('此课程会在课堂页上显示。', '要恢复此课程么？', {
                    confirmButtonText: '恢复',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                this.$message({
                    type: 'success',
                    message: '恢复成功'
                });
                }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消恢复'
                });          
            });
        }
    },
}
</script>

<style>
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
.paper-repeat{
    height: 32px;
    background: 0 1px/32px no-repeat;
    padding-left: 35px;
    line-height: 32px;
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