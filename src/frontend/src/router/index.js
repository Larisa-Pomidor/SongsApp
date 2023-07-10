import { createRouter, createWebHistory } from 'vue-router'
import Songs from '../views/SongsView.vue'
import Admin from '../views/AdminView.vue'
import Login from '../views/LoginView.vue'

const routes = [
  {
    path: '/',
    name: 'songs',
    component: Songs
  },
  {
    path: '/admin',
    name: 'admin',
    component: Admin
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
