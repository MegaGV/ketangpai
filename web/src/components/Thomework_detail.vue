<template>
    <div>
        <div style="height: 60px;box-shadow: 0px 1px 3px 0px rgba(0, 0, 0, 0.04);">
            <div class="return">
                ←<router-link class="course_button" :to="{ path: '/teacher/course/' + id + '/homework'}">{{course.name}} </router-link>
            </div>
            
            <el-menu :default-active="activeIndex" class="el-menu-demo"  mode="horizontal" style="position:absolute;margin-left:800px" >
                <el-menu-item index="1" @click="activeIndex = '1'">学生作业</el-menu-item>
                <el-menu-item index="2" @click="activeIndex = '2'">作业讨论</el-menu-item>
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

        <div v-if="show_detail" class="homework_detail">
            <div class="homework_detail_top">
                <h3 style="text-align:left">{{homework.name}}</h3>
                <span>
                    <p class="homework_detail_info">截至：{{homework.endtime}}</p>
                    <el-input
                        placeholder="学号，姓名"
                        suffix-icon="el-icon-search"
                        style="width:200px;float:right">
                    </el-input>
                    <el-dropdown style="float:right;margin-right:20px">
                        <el-button type="primary">
                            成绩显示<i class="el-icon-arrow-down el-icon--right"></i>
                        </el-button>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item>对学生隐藏成绩</el-dropdown-item>
                            <el-dropdown-item>对学生显示成绩</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                    <span class="el-icon-setting" style="float:right;line-height:40px;margin-right:20px">查重设置</span>
                </span>
            </div>
            <el-divider></el-divider>
            
            <el-table :data="homeworks" stripe style="width: 1224px;margin:0 auto" >
                <el-table-column type="selection" ></el-table-column>
                <el-table-column prop="student_name" label="姓名"/>
                <el-table-column label="成绩" >
                    <template slot-scope="scope">
                        <div v-if="scope.row.complete == 0"><span style="color:red">未交</span></div>
                        <div v-else>
                            <div v-if="scope.row.review == 0">未批/100</div>
                            <div v-else>{{scope.row.score}}/100</div>
                        </div>
                    </template>
                </el-table-column>
                <el-table-column label="提交状态" >
                    <template slot-scope="scope">
                        <div v-if="scope.row.complete == 0">---</div>
                        <div v-else>{{scope.row.upload_data}}</div>
                    </template>
                </el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <div v-if="scope.row.complete == 0">
                            <el-button style="color:deepskyblue">催交</el-button>
                        </div>
                        <div v-else>
                            <el-button>
                                <router-link style="text-decoration: none;color:deepskyblue"
                                :to="{ path: '/teacher/course/' + course.id + '/homework/detail/' + homework.id + '/review/' + scope.row.id}" >
                                    进入批阅
                                </router-link>
                            </el-button>
                            
                        </div>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        
        <div v-if="show_info" class="homework_info">
            <h2 style="text-align:left">{{homework.name}}</h2>
            <p style="height: 34px;padding-top: 7px;line-height: 34px;font-size: 12px;color: #A0A0A0;margin-bottom: 6px;text-align:left">
                截止日期：{{homework.endtime}}
            </p>
            <h3 style="text-align:left">基本要求：</h3>
            <p style="text-align:left;line-height:auto">
                {{homework.introduce}}
            </p>
            <h3 style="text-align:left">附件：</h3>
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
        hid(){
            return this.$route.params.hid
        }
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
.homework_detail {
    padding-top: 40px;
    padding-bottom: 60px;
}
.homework_detail_top{
    width: 1224px;
    height:100px;
    margin: 0 auto;
}
.homework_detail_info{
    font-size: 18px;
    padding: 0 5px;
    line-height: 20px;
    margin-right: 10px;
    margin-bottom: 40px;
    background-color: #F1F3F4;
    color: #5F6368;
    border-radius: 2px;
    float: left;
}
.homework_info {
    width: 800px;
    margin: 0 auto;
    border: 1px solid #E2E6ED;
    border-radius: 8px;
    background: #FFF;
    padding-left:20px;
    margin-bottom: 20px;
}

</style>