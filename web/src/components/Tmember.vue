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

        <div style="width:1224px;margin:0 auto">
            <div style="margin-top:20px;height:60px">
                <el-button type="primary" style="margin-right:40px;float:left" >导入成员</el-button>
                <i class="el-icon-download" style="margin-right:40px;float:left;line-height:40px">下载成员信息</i>
                <i class="el-icon-picture" style="float:left;line-height:40px" >成员退课记录</i>
                <el-input placeholder="姓名，学号" suffix-icon="el-icon-search" style="width:200px;float:right;"></el-input>
            </div>
            <el-divider></el-divider>

            <el-tabs tab-position="left" style="height: auto;width:1222px">
                <el-tab-pane label="教学团队">
                        <div class="title" >
                            <h3 style="margin:0;text-align:left;line-height:55px;float:left">教师团队 老师({{teachers.length}})</h3>
                            <el-button type="primary" style="margin-top:6px;float:right"><i class="el-icon-s-custom"></i>添加助教/老师</el-button>
                        </div>

                        <el-table :data="teachers">
                            <el-table-column type="selection" />
                            <el-table-column><el-avatar icon="el-icon-user-solid"></el-avatar></el-table-column>
                            <el-table-column prop="name" label="姓名" />
                            <el-table-column label="身份">
                                <template slot-scope="scope">
                                    <span v-if="scope.$index == 0">(管理员)</span>
                                    <span v-else>(助教)</span>
                                </template>
                            </el-table-column>
                            <el-table-column prop="email" label="邮箱" />
                            <el-table-column prop="phone" label="电话" />
                        </el-table>
                </el-tab-pane>
                <el-tab-pane label="全部学生">
                        <div class="title">
                            <h3 style="margin:0;text-align:left;line-height:55px;float:left">全部学生 学生({{students.length}})</h3>
                            <el-switch v-model="quit" active-text="不允许退课" style="float:right;margin:15px" ></el-switch>
                            <el-checkbox style="float:right;line-height:55px;">人数限制</el-checkbox>
                        </div>

                        <el-table :data="students">
                            <el-table-column type="selection" />
                            <el-table-column><el-avatar icon="el-icon-user-solid"></el-avatar></el-table-column>
                            <el-table-column prop="name" label="姓名" />
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
            course:{
                id:"001", 
                name:"JavaEE",
                assistants:["xcy2"],
                students:["stu","stu2"]
            },
            teachers:[
                {id:"xcy", name:"徐传运", email:"",phone:""},
                {id:"xcy2", name:"徐传运2", email:"",phone:""}
            ],
            students:[
                {id:"stu", name:"张三", schoolID:"117030801", email:"",phone:""},
                {id:"stu2", name:"张三2", schoolID:"117030802", email:"",phone:""},
            ],
        }
    },
    computed: {
        id(){
            return this.$route.params.id;
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
        list_change(){
            this.Ttable_visible = this.Stable_visible;
            this.Stable_visible = !this.Stable_visible;
        }
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
.title {
    padding: 0 12px 0 25px;
    height: 55px;
    background: rgba(241,243,244,1);
    border-radius: 0 8px 0 0;
}


</style>