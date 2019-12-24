<template>
    <div class="homework_page">
        <div class="homework_list">
            <div class="homework_box" v-for="homework in homeworks" :key="homework.id">
                <div class="homework_top">
                    <span style="color: #5F6368;background: #F1F3F4;padding: 3px 5px;border-radius: 2px;">个人作业</span>
                    <span>{{homework.starttime}}</span>
                </div>
                <div>
                    <h3>
                        <router-link :to="{ path: '/student/course/' + id + '/homework/detail/' + homework.id}" style="color: #3B3D45;font-size: 20px;font-weight: 400;text-decoration:none">
                            {{homework.name}}
                        </router-link>
                    </h3>
                    <el-button @click="jump(homework.id)" type="primary" size="small" style="float:right">上传作业</el-button>
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
            homeworks:[
                {id: "001", name:"实验1", introduce:"提交demo1", starttime:"2019-12-16 16:09:33", endtime:"2019-12-22 16:09:36"},
            ],
        }
    },
    computed: {
        id () {
            return this.$route.params.id
        }
    },
    methods:{
        jump(hid){
            this.$router.push('/student/course/' + this.id + '/homework/detail/' + hid)
        }
    }
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