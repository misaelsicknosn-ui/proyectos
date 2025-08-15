import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Welcome from '../views/Welcome.vue'

const routes = [
  { path: '/', name: 'Login', component: Login },
  { path: '/welcome', name: 'Welcome', component: Welcome }
]

export default createRouter({
  history: createWebHistory(),
  routes
})
