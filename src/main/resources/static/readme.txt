用webpack打包js
(1)安装node
(2)创建文件夹mywebpack,创建文件package.json
(3)初始化package: npm init
(4)
//全局安装npm install -g webpack
//安装到你的项目目录npm install --save-dev webpack
(5)安装 npm install axios
(6)安装qs: npm i qs -D
(7)安装uglifyjs: npm install uglifyjs-webpack-plugin --save-dev
(8)安装es5: npm install --save-div babel-preset-es2015
(9)安装npm install js-cookie --save
(7)创建 webpack.config.js
module.exports = {
	mode: 'production',
　　entry: 'C:\\spatome\\mywebpack\\main.js',
　　output: {
　　　　path: 'C:\\spatome\\mywebpack\\js',
　　　　filename: 'bundle.js'
　　}
};

补充说明:
//package.json
{
  "name": "webpack-demo",
  "version": "1.0.0",
  "description": "",
  "main": "index.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1"
  },
  "author": "",
  "license": "ISC",
  "devDependencies": {
    "axios": "^0.18.0",
    "webpack": "^4.28.2"
  }
}

//main.js
import axios from 'axios'
import Cookies from 'js-cookie'
import {post,fetch,patch,put} from './js/http'

//定义全局变量
Vue.prototype.$post=post;
Vue.prototype.$fetch=fetch;
Vue.prototype.$patch=patch;
Vue.prototype.$put=put;
Vue.prototype.$Cookies=Cookies;


//-----------------http.js------------------
import axios from 'axios';

axios.defaults.timeout = 5000;
axios.defaults.baseURL ='';


//http request 拦截器
axios.interceptors.request.use(
  config => {
    // const token = getCookie('名称');注意使用的时候需要引入cookie方法，推荐js-cookie
    config.data = JSON.stringify(config.data);
    config.headers = {
      'Content-Type':'application/x-www-form-urlencoded'
    }
    // if(token){
    //   config.params = {'token':token}
    // }
    return config;
  },
  error => {
    return Promise.reject(err);
  }
);


//http response 拦截器
axios.interceptors.response.use(
  response => {
    if(response.data.errCode ==2){
      router.push({
        path:"/login",
        querry:{redirect:router.currentRoute.fullPath}//从哪个页面跳转
      })
    }
    return response;
  },
  error => {
    return Promise.reject(error)
  }
)


/**
 * 封装get方法
 * @param url
 * @param data
 * @returns {Promise}
 */

export function fetch(url,params={}){
  return new Promise((resolve,reject) => {
    axios.get(url,{
      params:params
    })
    .then(response => {
      resolve(response.data);
    })
    .catch(err => {
      reject(err)
    })
  })
}


/**
 * 封装post请求
 * @param url
 * @param data
 * @returns {Promise}
 */

 export function post(url,data = {}){
   return new Promise((resolve,reject) => {
     axios.post(url,data)
          .then(response => {
            resolve(response.data);
          },err => {
            reject(err)
          })
   })
 }

 /**
 * 封装patch请求
 * @param url
 * @param data
 * @returns {Promise}
 */

export function patch(url,data = {}){
  return new Promise((resolve,reject) => {
    axios.patch(url,data)
         .then(response => {
           resolve(response.data);
         },err => {
           reject(err)
         })
  })
}

 /**
 * 封装put请求
 * @param url
 * @param data
 * @returns {Promise}
 */

export function put(url,data = {}){
  return new Promise((resolve,reject) => {
    axios.put(url,data)
         .then(response => {
           resolve(response.data);
         },err => {
           reject(err)
         })
  })
}
//-----------------end http.js------------------

