import Vue from 'vue'
import Router from 'vue-router'
import login from '@/components/login'
import register from '@/components/register'
import Sindex from '@/components/Sindex'

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
      children: [
       
      ]
    },
  ]
})
