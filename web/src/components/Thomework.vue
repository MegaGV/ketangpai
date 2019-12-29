<template>
    <div class="homework_page">
        <div class="homework_list">
            <div style="position:relative;right:480px">
                <el-button type="primary" @click="new_homework_visible = true">发布个人作业</el-button>
                <el-button type="primary">发布小组作业</el-button>
            </div>
            
            <i class="el-icon-download" style="position:relative;bottom:30px;left:550px;color:deepskyblue">下载所有作业</i>

            <el-dialog :visible.sync="new_homework_visible" width="30%">
                <el-form ref="homework" :model="homework">
                    <el-form-item>
                        <el-input v-model="homework.name" placeholder="作业名称"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-input type="textarea" :rows="3" v-model="homework.introduce" placeholder="作业简介，作业格式等要求"></el-input>
                    </el-form-item>
                    <el-form-item label="截止日期">
                        <el-date-picker
                            v-model="homework.endtime"
                            type="date"
                            placeholder="选择日期">
                        </el-date-picker>
                    </el-form-item>
                </el-form>
                <el-button @click="new_homework_visible = false">取消</el-button>
                <el-button @click="create_homework" type="primary">发布个人作业</el-button>
            </el-dialog>

            <div class="homework_box" v-for="homework in homeworks" :key="homework.id">
                <div class="homework_top">
                    <span style="color: #5F6368;background: #F1F3F4;padding: 3px 5px;border-radius: 2px;">个人作业</span>
                    <span>{{homework.starttime}}</span>
                </div>
                <div>
                    <h3>
                        <router-link :to="{ path: '/teacher/course/' + id + '/homework/detail/' + homework.id}" style="color: #3B3D45;font-size: 20px;font-weight: 400;text-decoration:none">
                            {{homework.name}}
                        </router-link>
                    </h3>
                    <div style="float:right">
                        <span style="font-size:30px;position:relative;bottom:30px">{{homework.reviewed}}</span>
                        <span style="font-size:20px;position:relative;right:35px">已批</span>
                        
                        <span style="font-size:30px;position:relative;bottom:30px">{{parseInt(homework.uploaded)-parseInt(homework.reviewed)}}</span>
                        <span style="color:red;font-size:20px;position:relative;right:35px">未批</span>

                        <span style="font-size:30px;position:relative;bottom:30px">{{course.students.length - parseInt(homework.uploaded)}}</span>
                        <span style="font-size:20px;position:relative;right:35px">未交</span>
                    </div>
                    <p class="word">{{homework.introduce}}</p>
                    <p class="word">截止日期：{{homework.endtime}}</p>
                </div>
            </div>
        </div>
    </div>
</template>

<script >
export default {
    data(){
        return{
            homeworks:[],
            course:{},
            homework:{
                name:"",
                introduce:"",
                endtime:"",
                course:"",
            },
            new_homework_visible:false,
        }
    },
    computed: {
        id () {
            return this.$route.params.id
        },
    },
    methods:{
        getCourseById() {
            this.$axios.get('api/CourseController/getCourseById?id=' + this.id)
            .then(res => {
                this.course = res.data;
                this.course.students = this.course.students.split(',');
                this.getAllHomeworkContent(this.course.homeworks);
            })
            .catch(err => {
                alert("获取课程失败");
                console.log(err);
            })
        },
        getAllHomeworkContent(homework_content){
            this.$axios.get('api/HomeworkContentController/getAllHomeworkContent?homework_content=' + homework_content)
            .then(res => {
                this.homeworks = res.data;
            })
            .catch(err => {
                alert("获取作业失败");
                console.log(err);
            })
        },
        create_homework(){
            this.homework.course = this.id;
            this.$axios
                .post("api/HomeworkContentController/createHomework", this.homework)
                .then(res => {
                    alert("发布作业成功");
                    this.getCourseById(this.id);
                    this.new_homework_visible = false;
            })
            .catch(err => {
                alert("发布作业失败");
                console.log(err);
            });  
        },
        jump(hid){
            this.$router.push('/teacher/course/' + this.id + '/homework/detail/' + hid)
        },
    },
    mounted(){
        this.getCourseById(this.id);
    },
}
</script>

<style>
.homework_page{
    padding-bottom: 60px;
}
.homework_list {
    width: 1224px;
    margin-left: auto;
    margin-right: auto;
    margin-top: 20px;
}
.homework_box {
    padding: 0 20px 0 40px;
    border: 1px solid #E2E6ED;
    border-radius: 8px;
    background: #FFF;
    margin-bottom: 20px;
    text-align: left;
}
.homework_top {
    color: #5F6368;
    font-size: 14px;
    padding: 21px 0 10px;
    line-height: 30px;
}
.word {
    line-height: 30px;
    max-height: 100px;
    overflow: hidden;
    font-size: 14px;
}
</style>