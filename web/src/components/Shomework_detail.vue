<template>
    <div>
        <div style="height: 60px;box-shadow: 0px 1px 3px 0px rgba(0, 0, 0, 0.04);">
            <div class="return">
                ←<router-link class="course_button" :to="{ path: '/student/course/' + id + '/homework'}">{{course.name}} </router-link>
            </div>
            
            <el-menu :default-active="activeIndex" class="el-menu-demo"  mode="horizontal" style="position:absolute;margin-left:800px" >
                <el-menu-item index="1" @click="activeIndex = '1'">提交作业</el-menu-item>
                <el-menu-item index="2" @click="activeIndex = '2'">作业评论</el-menu-item>
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

        <div v-if="show_upload" class="homework_upload">
            <div class="homework_upload_top">
                <h3 style="text-align:left">{{homework.name}}</h3>
                <p class="homework_upload_info">截至日期：{{homework.endtime}}</p>
                <p class="homework_upload_info">个人作业</p>
                <p class="homework_upload_info">需要查重</p>
                <i class="el-icon-picture" style="float:right">看看谁交了</i>
            </div>

            <div class="homework_upload_top2">
                <el-button type="primary" style="position:relative;right:720px" @click="submit">提交</el-button>
                <p style="float:right;margin:0" v-if="iscomplete">已完成</p>
                <p style="float:right;margin:0" v-else>未完成</p>
            </div>

            <el-form class="homework_upload_box">
                <el-upload class="upload-demo" 
                    action="http://127.0.0.1:8080/file/upload"
                    :file-list="file"
                    :limit="1"
                    :on-success="handleAvatarSuccess"
                    >
                    <div class="uploadbox">
                        <i class="el-icon-circle-plus" style="font-size:35px;color:rgba(50,186,240,1);position:relative;right:140px;top:20px"></i>
                        <div class="el-upload__text" style="position:relative;right:70px;bottom:20px">添加作业文件</div>
                        <div class="el-upload__tip" style="position:relative;right:10px;bottom:20px">支持各类文档、图片、代码、压缩包格式</div>
                    </div>  
                </el-upload>
                <div class="message" >
                    <span style="line-height:38px;font-size: 14px;color: #010000;float: left;">作业留言:</span>
                    <el-input type="textarea" v-model="upload.content" 
                    placeholder="添加留言，作业要以附件的形式提交，而不是写在留言里..." 
                    style="width:1000px;line-height: 38px;border: 1px solid #D2D2D2;">
                    </el-input>
                </div>
            </el-form>

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
            user:{},
            course:{},
            homework:{},
            upload:{},
            file: [],

        }
    },
    computed: {
        show_upload () {
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
        getUserById(id) {
            this.$axios.get('api/UserController/getUserById?id=' + id)
            .then(res => {
                this.user = res.data;
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
        getHomeworkContentById() {
            this.$axios.get('api/HomeworkContentController/getHomeworkContentById?id=' + this.hid)
            .then(res => {
                this.homework = res.data;
            })
            .catch(err => {
                alert("获取作业信息失败");
                console.log(err);
            })
        },
        getHomeworkUploadById() {
            this.$axios.get('api/HomeworkUploadController/getHomeworkUploadById?id=1&hid=' +this.hid)
            .then(res => {
                this.upload = res.data;
            })
            .catch(err => {
                alert("获取作业上传情况失败");
                console.log(err);
            })
        },
        handleAvatarSuccess(res, file) {
            this.upload.file = res;
            this.upload.file = this.upload.file[0];
            alert(this.upload.file)
        },
        submit(){
            this.$axios.post('api/HomeworkUploadController/submitHomeworkUpload', this.upload)
            .then(res => {
                alert("上传成功")
            })
            .catch(err => {
                alert("上传失败");
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
        this.getHomeworkContentById();
        this.getHomeworkUploadById();
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
.homework_upload {
    padding-top: 40px;
    padding-bottom: 60px;
}
.homework_upload_info{
    font-size: 12px;
    padding: 0 5px;
    line-height: 20px;
    margin-right: 10px;
    margin-bottom: 40px;
    background-color: #F1F3F4;
    color: #5F6368;
    border-radius: 2px;
    float: left;
}
.homework_upload_top{
    width: 1224px;
    height:100px;
    margin: 0 auto;
}
.homework_upload_top2{
    width: 1224px;
    height: 40px;
    line-height: 40px;
    margin: 0 auto;
}
.homework_upload_box {
    width: 1224px;
    margin: 0 auto;
    margin-top: 20px;
    border: 1px solid #E2E6ED;
    background: #FFF;
    border-radius: 8px;
}
.message {
    background: #F1F3F4;
    border-top: 1px solid #dcdcdc;
    padding: 26px 0 26px 25px;
}
.uploadbox {
    width: 330px;
    height: 80px;
    border: 2px dashed #CCC;
    margin: 20px 0 24px 24px;
    display: block;
    position: relative;
    right: 440px;
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