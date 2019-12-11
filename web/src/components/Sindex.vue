<template>
<div class="nav nav-default ">
    <ul class="nav-menu-left">
        <li class="nav-menu-item ktplogo ">
            <a class="ketangpaitophref" href="/Index/index">
            	<el-image :src="logo" style="width:96px;height:24px"></el-image>
            </a>
        </li>
        <li class="nav-menu-item"><a href="/Main/index.html" class="active">课堂</a></li>
        <li class="nav-menu-item jingpin" data-yy="0">
            <a href="/ExcellentResources/index.html?type=1">精品专区</a>
        </li>
        <li class="nav-menu-item"><a href="/Mooc/Mooc/index.html">我的精品</a></li>
    </ul>
    <ul class="nav-menu-right">
        <li class="paper-repeat nav-menu-item">
            <a href="/PaperCheckrepeat/index.html">
                <i>论文查重</i>
            </a>
        </li>
        
        <li class="nav-menu-item ico notice nav-mian-margin">
	        <a id="notice" 　href="javascript:;">
                <i class="iconfont iconxiaoxi1"></i>
            </a>
            <div class="notice-ct hide notice-new-ct" style="display: none;">
                <p class="zw">暂无新通知</p>       
                <div class="bt-bar">
                    <div class="more operation-more">
                        <a id="ignore-all" class="operation-more-ingoreall" href="javascript:void(0);">全部标记已读</a>
                        <a class="link operation-more-ink" href="/Message/index.html">查看全部</a>
                    </div>
                </div>
            </div>
        </li>
        <li class="nav-menu-item user">
            <a id="user" href="javascript:;">
                <img class="avatar" src="" alt="" title="">
            </a>
            <div class="user-info hide" style="display: none;">
                <ul>
                    <li class="">
                        <a class="vipnew" href="/VipActivity/pay.html">开通VIP</a>
                    </li>
					<li>
					    <a class="link setting" href="/User/setting.html">个人设置</a>
				    </li>
                    <li>
                        <a class="link logout" href="/User/logout.html">退出账户</a>
                    </li>        
                </ul>
            </div>
        </li>
    </ul>

    <div class="leftcon uiupdate" style="left: -350px;">
        <div class="nav-top">
            <a href="" class="leftnavclass">
                <i class="iconfont iconketang"></i>课堂
            </a>
            <a href="" class="leftnavletter" target="_blank">
                <i class="iconfont iconsixin2"></i>
                <b>私信</b>
            </a>
        </div>
        <div class="open-course" data-role="0">
            <div class="open-box">
                <p>已有课程</p>
                <div class="list">
                    <a href="/Course/homework/courseid/MDAwMDAwMDAwMLR2qZWG38lp.html" title="Java EE架构与程序设计(1,2班)">
                        <i>J</i>
                        <span>Java EE架构与程序设计(1,2班)</span>
                    </a>                 
                </div>            
            </div>
        </div>
    </div>
</div>
</template>

<script>
export default {
    data() {
        return{
            logo:require("../img/logo-mainblue.png"),
        }
    },
    method: {

    },
}
</script>

<style>

</style>