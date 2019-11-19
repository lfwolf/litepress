import Home from '@/components/Home'
import Tabbar from '@/components/Tabbar/index'


export default [
    {
      path: '/',
      name: 'Home',
      components: {
        default: Home,
        tabbar: Tabbar,
      }
    },
    {
      path: '/album/:id',
      name: 'album',
      components: {
        default: () => import('@/components/Album'),
      }
    },
    {
      path: '/article/:id',
      name: 'article',
      component: () => import('@/components/Article'),
    },
    {
      path: '/iframe',
      name: 'iframe',
      components: {
        default:  () => import('@/components/iframe'),
      }
    }

  ]
