/* eslint-disable */
import service from '@/utils/request'

const BannerUrl  = '/index/banner.json' // 首页数据接口
const CatalogUrl = '/index/catalog.json' // 首页数据接口
const RecommendUrl = '/index/recommends.json' // 首页数据接口
const AlbumUrl   = '/album.json'
const ArticleUrl = '/article.json'
const CategoryUrl = '/catelog.json'
const ArticleListUrl = '/articlelist.json'
const ArticleContentUrl = '/articlecontent.json'
const AudioListUrl = '/audio/list.json'
const VoiceListUrl = '/voice/list.json'
const VoiceInfoUrl = '/voice/info.json'

export function getBanner () {
  return service({
    url   : BannerUrl,
    method: 'get'
  })
}

export function getCatalog () {
  return service({
    url   : CatalogUrl,
    method: 'get'
  })
}

export function getRecommends () {
  return service({
    url   : RecommendUrl,
    method: 'get',
    params: { type: 1}
  })
}

export function getAlbum (zid) {
  console.log("api:"+zid)
  return service({
    url   : AlbumUrl,
    method: 'get',
    params: { id: zid}
  })
}

export function getArticle (id) {
  console.log("getArticle:"+id)
  return service({
    url   : ArticleUrl,
    method: 'get',
    params: { id: id}
  })
}

export function getCategory () {
  return service({
    url   : CategoryUrl,
    method: 'get'
  })
}

export function getArticleList () {
  return service({
    url   : ArticleListUrl,
    method: 'get'
  })
}

export function getArticleContent (id) {
  return service({
    url   : ArticleContentUrl,
    method: 'get',
    params: { id: id}
  })
}

export function getAudioList (sid,page) {
  return service({
    url   :  AudioListUrl, //uid + "/" + page,
    method: 'get',
    params: {page: page, sid: sid}
  })
}

export function getVoiceList (sid,page) {
  console.log("getVoiceList: "+VoiceListUrl)
  return service({
    url   : VoiceListUrl ,
    method: 'get',
    params: { sid: sid, page: page}
  })
}

export function getVoiceInfo (id) {
  console.log("getVoiceList: "+VoiceListUrl)
  return service({
    url   : VoiceInfoUrl ,
    method: 'get',
    params: { id: id }
  })
}
