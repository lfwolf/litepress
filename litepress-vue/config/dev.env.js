'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  API_ROOT: '"//localhost:8081/api/front/"',
  API_ROOT_WP: '"//www.akucun.xyz/wp-json/wp/v2"',
  API_ROOT_LIZHI: '"https://m.lizhi.fm/api/user/audios/"'
})
