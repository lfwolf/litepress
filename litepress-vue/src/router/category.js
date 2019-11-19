import Category from '@/components/Category'
import Tabbar from '@/components/Tabbar/index'

export default [
  {
    path: '/category',
    name: 'category',
    components: {
      default: Category,
      tabbar: Tabbar,
    }
  }
]