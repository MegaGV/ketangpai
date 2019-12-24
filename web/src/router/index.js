import Vue from 'vue'
import Router from 'vue-router'
import login from '@/components/login'
import register from '@/components/register'
import Sindex from '@/components/Sindex'
import Scourse from '@/components/Scourse'
import Shomework from '@/components/Shomework'
import Shomework_detail from '@/components/Shomework_detail'
import Smember from '@/components/Smember'
import Tindex from '@/components/Tindex'
import Tcourse from '@/components/Tcourse'
import Thomework from '@/components/Thomework'
import Thomework_detail from '@/components/Thomework_detail'
import Thomework_review from '@/components/Thomework_review'
import Tmember from '@/components/Tmember'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/login_page',
    },
    {
      path: '/login_page',
      name: 'login',
      component: login,
    },
    {
      path: '/register',
      name: 'register',
      component: register,
    },
    {
      path: '/student',
      name: 'Sindex',
      component: Sindex,
    },
    {
      path: '/student/course/:id',
      name:'Scourse',
      component: Scourse,
      redirect : '/student/course/:id/homework',
      children:[
        {
          path: '/student/course/:id/homework',
          name: 'Shomework',
          component:Shomework,
        },
        {
          path: '/student/course/:id/interaction',
          name: 'Sinteraction',
        },
        {
          path: '/student/course/:id/topic',
          name: 'Stopic',
        },
        {
          path: '/student/course/:id/resource',
          name: 'Sresource',
        },
        {
          path: '/student/course/:id/test',
          name: 'Stest',
        },
        {
          path: '/student/course/:id/notice',
          name: 'Snotice',
        },
      ]
    },
    {
      path:'/student/course/:id/homework/detail/:hid',
      name:'Shomework_detail',
      component:Shomework_detail,
    },
    {
      path:'/student/course/:id/member',
      name:'Smember',
      component:Smember
    },
    {
      path:'/teacher',
      name:'Tindex',
      component:Tindex,
    },
    {
      path: '/teacher/course/:id',
      name:'Tcourse',
      component: Tcourse,
      redirect : '/teacher/course/:id/homework',
      children:[
        {
          path: '/teacher/course/:id/homework',
          name: 'Thomework',
          component: Thomework,
        },
        {
          path: '/teacher/course/:id/interaction',
          name: 'Tinteraction',
        },
        {
          path: '/teacher/course/:id/topic',
          name: 'Ttopic',
        },
        {
          path: '/teacher/course/:id/resource',
          name: 'Tresource',
        },
        {
          path: '/teacher/course/:id/test',
          name: 'Ttest',
        },
        {
          path: '/teacher/course/:id/notice',
          name: 'Tnotice',
        },
      ]
    },
    {
      path:'/teacher/course/:id/homework/detail/:hid',
      name:'Thomework_detail',
      component:Thomework_detail
    },
    {
      path:'/teacher/course/:id/homework/detail/:hid/review/:uid',
      name:'Thomework_detail',
      component:Thomework_review
    },
    {
      path:'/teacher/course/:id/member',
      name:'Tmember',
      component:Tmember
    },
  ]
})
