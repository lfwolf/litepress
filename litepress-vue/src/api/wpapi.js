/* eslint-disable */
import wpservice from '@/utils/request'

// wordpress api 查询目录及条件参考ref: https://blog.csdn.net/u011537073/article/details/87882337

export function listCategoris () {
  return wpservice({
    url   : "/categories",
    method: 'get'
  })
}

// per_page=5&page=1&cat=1
export function listPosts (condition) {
  return wpservice({
    url   : "/posts",
    method: 'get',
    params: condition
  })
}

export function getPosts (id) {
  return wpservice({
    url   : "/posts/"+id,
    method: 'get'
  })
}