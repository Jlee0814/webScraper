import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)
export const constantRouterMap = [
  { path: '/',  component: () => import('@/views/event/list') }
]

export default new Router({
  routes: constantRouterMap
})
